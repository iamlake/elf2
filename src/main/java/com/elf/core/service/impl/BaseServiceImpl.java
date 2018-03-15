package com.elf.core.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.elf.core.persistence.BaseMapper;
import com.elf.core.persistence.DataEntity;
import com.elf.core.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: BaseServiceImpl
 * @Description: Service支持类
 * @Author:李一鸣(liyiming.neu@neusoft.com)
 * @Date:2017年11月12日
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends DataEntity<T>> extends ServiceImpl<M, T> implements BaseService<T> {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

}
