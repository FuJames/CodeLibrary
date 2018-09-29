package code.library.job.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * @author fuqianzhong
 * @date 18/9/28
 * Quartz调度器
 */
@Component
public class JobService {
    @Autowired
    private Scheduler quartzScheduler;
    public static void main(String[] args) throws SchedulerException, ParseException {
//        addJob("QuartzJob");
//        SchedulerFactory sf = new StdSchedulerFactory();
//        Scheduler scheduler = sf.getScheduler();
//        JobDetail job = JobBuilder
//                .newJob(QuartzJob.class)
//                .withIdentity("testJob","group1")
//                .usingJobData("name","fqz")
//                .build();
//
//        CronTrigger trigger = TriggerBuilder
//                .newTrigger()
//                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
//                .build();
//
//        scheduler.start();
//
//        scheduler.scheduleJob(job, trigger);

    }

    public void addJob(String className) throws SchedulerException {
        JobDetail job = JobBuilder
                .newJob(SpringQuartzJob.class)
                .withIdentity(className,"group1")
                .usingJobData("name","fqz")
                .build();

        CronTrigger trigger = TriggerBuilder
                .newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
                .build();

        quartzScheduler.scheduleJob(job, trigger);
    }

    public void pauseJob(String className) throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();
        scheduler.pauseJob(JobKey.jobKey(className, "group1"));
    }

    public void deleteJob(String className) throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();
        scheduler.pauseTrigger(TriggerKey.triggerKey(className, "group1"));
        scheduler.unscheduleJob(TriggerKey.triggerKey(className, "group1"));
        scheduler.deleteJob(JobKey.jobKey(className, "group1"));

    }

}
