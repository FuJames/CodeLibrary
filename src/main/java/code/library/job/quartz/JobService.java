package code.library.job.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

/**
 * @author fuqianzhong
 * @date 18/9/28
 * Quartz调度器
 * 1. Scheduler,JobDetail,Trigger 完成一次调度
 * 2. name/group: JobDetail和Trigger都有;group.name唯一,如果重复定义时,会抛出exception
 * 3. 一个JobDetail对象,同时只能被一个Scheduler对象调用,否则会抛出exception
 */
@Component
public class JobService {
    @Autowired
    private Scheduler quartzScheduler;

    public static void main(String[] args) throws SchedulerException, ParseException, InterruptedException {
//        addJob("QuartzJob");
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();//调度器
        scheduler.start();

        //定义JobDetail,任务执行的具体逻辑
        JobDetail jobCron = JobBuilder.newJob(QuartzJob.class)
                .withIdentity("job_name_cron","group_name_cron")
                .usingJobData("name","cron_job")
                .build();

        CronTrigger cronTrigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger_name_cron", "trigger_group_cron")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/3 * * * * ?"))
                .build();
        scheduler.scheduleJob(jobCron, cronTrigger);

        JobDetail jobSimple = JobBuilder.newJob(QuartzJob.class)
                .withIdentity("job_name_simple","job_group_simple")
                .usingJobData("name","simple_job")
                .build();
        Date startTime = DateBuilder.nextGivenSecondDate(new Date( ),3);
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger_name_simple", "trigger_group_simple")
                .startAt(startTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(1)
                .withRepeatCount(0))
                .build();

        scheduler.scheduleJob(jobSimple, simpleTrigger);


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
