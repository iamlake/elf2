package com.elf.core.service;

import java.util.List;

/**
 * <br>Title: BaseService
 * <br>Description: Service支持类
 * <br>Author:李一鸣(liyiming.neu@neusoft.com)
 * <br>Date:2017年11月12日
 */
public interface BaseService<T> {

    List<T> list(T entity);

    T get(T entity);

    int update(T entity);

    int save(T entity);

    int delete(T entity);

    int saveOrUpdate(T entity);
}
