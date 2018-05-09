package com.elf.techcomp.scheduler.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.elf.core.persistence.BaseMapper;
import com.elf.techcomp.scheduler.entity.JobAndTrigger;

import java.util.List;

/**
 * @program: elf
 * @description: JobAndTriggerMapper
 * @author: Liyiming
 * @create: 2018-05-08 20:59
 **/
public interface JobAndTriggerMapper extends BaseMapper<JobAndTrigger> {
    List<JobAndTrigger> getJobAndTriggerDetails(Pagination page);
}
