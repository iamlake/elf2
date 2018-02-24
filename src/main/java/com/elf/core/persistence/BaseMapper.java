package com.elf.core.persistence;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * <br>Title: BaseMapper
 * <br>Description: Mapper支持类
 * <br>Author:李一鸣(liyiming.neu@neusoft.com)
 * <br>Date:2017年11月12日
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
