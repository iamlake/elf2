package com.elf.techcomp.scheduler.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

/**
 * @program: elf
 * @description: JobAndTrigger
 * @author: Liyiming
 * @create: 2018-05-08 20:51
 **/
@Setter
@Getter
@ToString
public class JobAndTrigger {
    private String jobName;
    private String jobGroup;
    private String jobClassName;
    private String triggerName;
    private String triggerGroup;
    private BigInteger REPEAT_INTERVAL;
    private BigInteger TIMES_TRIGGERED;
    private String cronExpression;
    private String timeZoneId;
}
