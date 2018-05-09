package com.elf.techcomp.scheduler.job.impl;

import com.elf.techcomp.scheduler.job.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @program: elf
 * @description: HelloJob
 * @author: Liyiming
 * @create: 2018-05-08 22:04
 **/
public class HelloJob implements BaseJob {

    private static Logger _log = LoggerFactory.getLogger(HelloJob.class);

    public HelloJob() {

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        _log.error("Hello Job执行时间: " + new Date());

    }
}