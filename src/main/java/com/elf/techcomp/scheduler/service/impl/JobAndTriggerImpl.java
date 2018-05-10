package com.elf.techcomp.scheduler.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.elf.core.service.impl.BaseServiceImpl;
import com.elf.techcomp.scheduler.entity.JobAndTrigger;
import com.elf.techcomp.scheduler.mapper.JobAndTriggerMapper;
import com.elf.techcomp.scheduler.service.JobAndTriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: elf
 * @description: JobAndTriggerImpl
 * @author: Liyiming
 * @create: 2018-05-08 21:11
 **/
@Service
public class JobAndTriggerImpl extends BaseServiceImpl<JobAndTriggerMapper, JobAndTrigger> implements JobAndTriggerService {

    @Autowired
    private JobAndTriggerMapper jobAndTriggerMapper;

    @Override
    public Page<JobAndTrigger> getJobAndTriggerDetails(JobAndTrigger jobAndTrigger, Page<JobAndTrigger> page) {
        List<JobAndTrigger> jobAndTriggerList = jobAndTriggerMapper.getJobAndTriggerDetails(jobAndTrigger.getJobClassName(), jobAndTrigger.getDescription() + "%", page);
        page = page.setRecords(jobAndTriggerList);
        return page;
    }
}
