package code.library.job.quartz;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author fuqianzhong
 * @date 18/9/28
 */
@Controller
@RequestMapping(value = "/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @RequestMapping(value="/add")
    public void addjob(@RequestParam(value="jobClassName")String jobClassName) throws SchedulerException {
        jobService.addJob(jobClassName);
    }

    @RequestMapping(value="/pause")
    public void pausejob(@RequestParam(value="jobClassName")String jobClassName) throws SchedulerException {
        jobService.pauseJob(jobClassName);
    }

    @RequestMapping(value="/delete")
    public void deletejob(@RequestParam(value="jobClassName")String jobClassName) throws SchedulerException {
        jobService.deleteJob(jobClassName);
    }


}
