package com.elf.techcomp.scheduler.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @program: elf
 * @description: JobAndTrigger
 * @author: Liyiming
 * @create: 2018-05-08 21:51
 **/
public interface BaseJob extends Job {
    @Override
    void execute(JobExecutionContext context) throws JobExecutionException;
}
