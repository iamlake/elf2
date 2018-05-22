package com.elf.techcomp.scheduler.web;

import com.baomidou.mybatisplus.plugins.Page;
import com.elf.core.persistence.constants.ResultStatusEnum;
import com.elf.core.persistence.result.JSONResult;
import com.elf.core.persistence.result.QueryResult;
import com.elf.core.persistence.result.Result;
import com.elf.core.web.BaseController;
import com.elf.techcomp.scheduler.entity.JobAndTrigger;
import com.elf.techcomp.scheduler.job.BaseJob;
import com.elf.techcomp.scheduler.service.JobAndTriggerService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @program: elf
 * @description: JobController
 * @author: Liyiming
 * @create: 2018-05-08 21:38
 **/
@RestController
@RequestMapping("/job")
public class JobController extends BaseController {

    @Autowired
    private JobAndTriggerService jobAndTriggerService;

    /**
     * @Description: 加入Qulifier注解，通过名称注入bean
     * @Author: Liyiming
     * @Date: 2018/5/8
     */
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    @PostMapping("/scheduler")
    public Result createJob(JobAndTrigger jobAndTrigger) {
        JSONResult result = new JSONResult();
        try {
            doJobAdd(jobAndTrigger.getJobClassName(), jobAndTrigger.getJobGroup(), jobAndTrigger.getCronExpression(), jobAndTrigger.getDescription());
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("创建定时任务成功！");
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.getErrors().put("exception", ex);
            result.setMsg("创建定时任务失败！");
        }
        return result;
    }

    public void doJobAdd(String jobClassName,
                         String jobGroupName,
                         String cronExpression,
                         String jobDescription) throws Exception {
        // 启动调度器
        scheduler.start();
        // 构建job信息
        JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).withDescription(jobDescription).build();
        // 表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        // 按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName).withSchedule(scheduleBuilder).build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            logger.error("创建定时任务失败" + e);
            throw new Exception("创建定时任务失败");
        }
    }


    @PostMapping("/pause")
    public Result pauseJob(@RequestParam(value = "jobClassName") String jobClassName,
                           @RequestParam(value = "jobGroupName") String jobGroupName) {
        JSONResult result = new JSONResult();
        try {
            doJobPause(jobClassName, jobGroupName);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("停止定时任务成功！");
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.getErrors().put("exception", ex);
            result.setMsg("停止定时任务失败！");
        }
        return result;
    }

    public void doJobPause(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    @PostMapping("/resume")
    public Result resumeJob(@RequestParam(value = "jobClassName") String jobClassName,
                            @RequestParam(value = "jobGroupName") String jobGroupName) {
        JSONResult result = new JSONResult();
        try {
            doJobResume(jobClassName, jobGroupName);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("重启定时任务成功！");
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.getErrors().put("exception", ex);
            result.setMsg("重启定时任务失败！");
        }
        return result;
    }

    public void doJobResume(String jobClassName, String jobGroupName) throws Exception {
        scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    @PostMapping("/reschedule")
    public Result rescheduleJob(JobAndTrigger jobAndTrigger) {
        JSONResult result = new JSONResult();
        try {
            doJobReschedule(jobAndTrigger.getJobClassName(), jobAndTrigger.getJobGroup(), jobAndTrigger.getCronExpression());
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("更新定时任务成功！");
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.getErrors().put("exception", ex);
            result.setMsg("更新定时任务失败！");
        }
        return result;
    }

    public void doJobReschedule(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            logger.error("更新定时任务失败" + e);
            throw new Exception("更新定时任务失败");
        }
    }

    @PostMapping("/delete")
    public Result deleteJob(@RequestParam(value = "jobClassName") String jobClassName,
                            @RequestParam(value = "jobGroupName") String jobGroupName) {
        JSONResult result = new JSONResult();
        try {
            doJobDelete(jobClassName, jobGroupName);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("删除定时任务成功！");
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.getErrors().put("exception", ex);
            result.setMsg("删除定时任务失败！");
        }
        return result;
    }

    public void doJobDelete(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    @GetMapping("/query")
    public Result queryJob(JobAndTrigger jobAndTrigger, @RequestParam(value = "page") Integer pageNum, @RequestParam(value = "limit") Integer pageSize) {
        Page<JobAndTrigger> page = new Page<>(pageNum, pageSize);
        Page<JobAndTrigger> jobAndTriggerData = jobAndTriggerService.getJobAndTriggerDetails(jobAndTrigger, page);
        QueryResult<JobAndTrigger> result = new QueryResult<>();
        result.setCode(ResultStatusEnum.SUCCESS.getValue());
        result.setData(jobAndTriggerData.getRecords());
        result.setCount(jobAndTriggerData.getTotal());
        return result;
    }

    public static BaseJob getClass(String classname) throws Exception {
        Class<?> clz = Class.forName(classname);
        return (BaseJob) clz.newInstance();
    }
}
