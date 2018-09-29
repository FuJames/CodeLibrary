package code.library.job.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author fuqianzhong
 * @date 18/9/29
 */
public class SpringQuartzJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("I am a job, my name is " + context.getJobDetail().getJobDataMap().get("name"));
    }
}
