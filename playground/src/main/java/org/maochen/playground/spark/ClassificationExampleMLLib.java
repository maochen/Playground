package org.maochen.playground.spark;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.classification.SVMModel;
import org.apache.spark.mllib.classification.SVMWithSGD;
import org.apache.spark.mllib.evaluation.MulticlassMetrics;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import scala.Tuple2;

import java.util.Arrays;

/**
 * Created by mguan on 5/10/16.
 */
public class ClassificationExampleMLLib {

    // <label> <index1>:<value1> <index2>:<value2> ... <indexN>:<valueN>
    public static final String path = "/Users/mguan/Desktop/logistic_regress_demo/sample_libsvm_data.txt";
    public static final String pathPlain = "/Users/mguan/Desktop/logistic_regress_demo/data.txt";

    public static void run() {

        // Prepare configuration and data
        SparkConf conf = new SparkConf().setAppName("Logistic Regression").setMaster("local[4]");
        JavaSparkContext jsc = new JavaSparkContext(conf);

        JavaRDD<LabeledPoint> data = jsc.textFile(pathPlain).map(line -> {
            String[] fields = line.split(",");
            double[] realVal = Arrays.stream(fields).mapToDouble(Double::parseDouble).toArray();
            double label = realVal[2];
            Vector v = Vectors.dense(new double[]{realVal[0], realVal[1]});
            LabeledPoint lp = new LabeledPoint(label, v);
            return lp;
        });

//        data = MLUtils.loadLibSVMFile(jsc.sc(), path).toJavaRDD().cache();

        JavaRDD<LabeledPoint>[] splits = data.randomSplit(new double[]{0.7, 0.3}, 11L);

        JavaRDD<LabeledPoint> trainingData = splits[0];
        JavaRDD<LabeledPoint> testData = splits[1];

        SVMWithSGD svmWithSGD = (SVMWithSGD) new SVMWithSGD().setIntercept(true);
        SVMModel model = svmWithSGD.run(trainingData.rdd());

        System.out.println("Learned w: " + model.intercept() + ", " + model.weights());

        // Get evaluation metrics.
        JavaRDD<Tuple2<Object, Object>> scoreAndLabels = testData.map(
                p -> {
                    Double score = model.predict(p.features());
                    return new Tuple2<>(score, p.label());
                }
        );

        // Get evaluation metrics.
        MulticlassMetrics metrics = new MulticlassMetrics(JavaRDD.toRDD(scoreAndLabels));
        double precision = metrics.precision();

        System.out.println("Precision = " + precision);

    }

    public static void main(String[] args) {
        Logger.getLogger("org").setLevel(Level.ERROR);
        Logger.getLogger("akka").setLevel(Level.ERROR);
        run();
    }
}

