package org.maochen.playground.spark;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.classification.LogisticRegressionModel;
import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator;
import org.apache.spark.ml.tuning.CrossValidator;
import org.apache.spark.ml.tuning.CrossValidatorModel;
import org.apache.spark.ml.tuning.ParamGridBuilder;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by mguan on 5/9/16.
 */
public class ClassificationWithCV {
    // <label> <index1>:<value1> <index2>:<value2> ... <indexN>:<valueN>
    public static final String path = "/Users/mguan/Desktop/logistic_regress_demo/sample_libsvm_data.txt";
    public static final String pathPlain = ClassificationWithCV.class.getResource("/spark/student_exam_data.txt").getPath();

    public static void run() {

        // Prepare configuration and data
        SparkConf conf = new SparkConf().setAppName("Logistic Regression").setMaster("local[4]");
        JavaSparkContext jsc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(jsc);

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

        DataFrame trainingData = sqlContext.createDataFrame(splits[0], LabeledPoint.class);
        DataFrame testData = sqlContext.createDataFrame(splits[1], LabeledPoint.class);


        LogisticRegression lr = new LogisticRegression().setFitIntercept(true).setMaxIter(400);

        // DIFFERENT START HERE
        Pipeline pipeline = new Pipeline().setStages(new PipelineStage[]{lr});
        CrossValidator cv = new CrossValidator().setEstimator(pipeline)
                .setEvaluator(new BinaryClassificationEvaluator())
                .setEstimatorParamMaps(new ParamGridBuilder().build())
                .setNumFolds(6);

        CrossValidatorModel cvModel = cv.fit(trainingData);
        System.out.println("6-fold CV Avg Precision: " + cvModel.avgMetrics()[0]);

        LogisticRegressionModel lrModel = ((LogisticRegressionModel) ((PipelineModel) cvModel.bestModel()).stages()[0]);
        System.out.println("Learned w: " + lrModel.intercept() + ", " + lrModel.coefficients());
        // END ---------

//        // Get evaluation metrics.
//        LogisticRegressionSummary trainingSummary = model.summary();
//        BinaryLogisticRegressionSummary summary = (BinaryLogisticRegressionSummary) trainingSummary;
//
//        DataFrame fMeasure = summary.fMeasureByThreshold();
//        double maxFMeasure = fMeasure.select(functions.max("F-Measure")).head().getDouble(0);
//        double bestThreshold = fMeasure.where(fMeasure.col("F-Measure").equalTo(maxFMeasure))
//                .select("threshold").head().getDouble(0);
//
//        System.out.println("Precision: " + summary.precisionByThreshold().first().toString());
//        System.out.println("F-score: " + maxFMeasure);
//        System.out.println("Threshold: " + bestThreshold);


        DataFrame pred = cvModel.transform(testData);
        for (Row r : pred.select("probability", "prediction", "label", "features").collect()) {

            if (!r.get(1).equals(r.get(2))) {
                double[] probs = ((DenseVector) r.get(0)).toArray();

                System.out.print("Expected: " + r.get(2) + "\t");
                System.out.print("Actual: " + r.get(1) + "\t");
                System.out.print("Prob: " + Collections.max(Arrays.stream(probs).boxed().collect(Collectors.toList())) + "\t");
                System.out.print("Feats: " + r.get(3) + "\t");
                System.out.println("");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Logger.getLogger("org").setLevel(Level.ERROR);
        Logger.getLogger("akka").setLevel(Level.ERROR);
        run();
    }
}
