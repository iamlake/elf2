package com.elf.techcomp.scheduler.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.elf.core.persistence.BaseMapper;
import com.elf.techcomp.scheduler.entity.JobAndTrigger;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: elf
 * @description: JobAndTriggerMapper
 * @author: Liyiming
 * @create: 2018-05-08 20:59
 **/
public interface JobAndTriggerMapper extends BaseMapper<JobAndTrigger> {
    List<JobAndTrigger> getJobAndTriggerDetails(@Param("jobClassName") String jobClassName, @Param("description") String description, Page<JobAndTrigger> page);
}
