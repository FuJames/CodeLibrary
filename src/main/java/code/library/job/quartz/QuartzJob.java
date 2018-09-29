package code.library.job.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @author fuqianzhong
 * @date 18/9/28
 * Quartz任务需要被Quartz调度器来调度执行
 */
@Component
public class QuartzJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("hello world, my name is " + context.getJobDetail().getJobDataMap().get("name"));
    }
}
