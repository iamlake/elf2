package com.elf.techcomp.scheduler.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.elf.core.service.BaseService;
import com.elf.techcomp.scheduler.entity.JobAndTrigger;

/**
 * @program: elf
 * @description: JobAndTriggerService
 * @author: Liyiming
 * @create: 2018-05-08 21:10
 **/
public interface JobAndTriggerService extends BaseService<JobAndTrigger> {
    Page<JobAndTrigger> getJobAndTriggerDetails(JobAndTrigger jobAndTrigger, Page<JobAndTrigger> page);
}
