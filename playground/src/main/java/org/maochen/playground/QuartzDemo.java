package org.maochen.playground;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.Date;
import java.util.List;

/**
 * For CRON Expression, check http://castorgmc.wordpress.com/2013/09/23/quartz-cron-expression/
 * <p>
 * javac QuartzDemo.java -classpath ~/.m2/repository/org/quartz-scheduler/quartz/2.2.0/quartz-2.2.0.jar
 *
 * @author Maochen
 */

public class QuartzDemo implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        String arg1 = arg0.getJobDetail().getJobDataMap().getString("arg1");
        System.out.println(arg1);

    }

    public static String listAllJobs(Scheduler scheduler) throws SchedulerException {
        StringBuffer buf = new StringBuffer();

        for (String groupName : scheduler.getJobGroupNames()) {

            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

                String jobName = jobKey.getName();
                String jobGroup = jobKey.getGroup();

                @SuppressWarnings("unchecked")
                List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                Date nextFireTime = triggers.get(0).getNextFireTime();

                buf.append("[jobName] : " + jobName + " [groupName] : " + jobGroup + " - [Next Fire Time] : " + nextFireTime);

            }

        }

        return buf.toString();
    }

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        JobDetail job = JobBuilder.newJob(QuartzDemo.class).withIdentity("DemoJob", "group1").build();
        job.getJobDataMap().put("arg1", "input1");

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("DemoTrigger", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?")).build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(job, trigger);

        scheduler.start();

        System.out.println(QuartzDemo.listAllJobs(scheduler));

        Thread.sleep(2000L);
        System.out.println("Pause All");
        scheduler.pauseAll();
        Thread.sleep(3000L);
        System.out.println("Resume All");
        scheduler.resumeAll();
        Thread.sleep(3000L);
        System.out.println("Shutdown");
        scheduler.shutdown();

    }
}
