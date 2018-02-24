package com.elf.core.service.impl;

import com.elf.core.persistence.BaseMapper;
import com.elf.core.persistence.DataEntity;
import com.elf.core.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public class BaseServiceImpl<M extends BaseMapper<T>, T extends DataEntity<T>>implements BaseService<T> {
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private M mapper;

    @Override
    public List<T> list(T entity) {
        return mapper.select(entity);
    }

    @Override
    public T get(T entity) {
        return mapper.selectOne(entity);
    }

    @Override
    public int update(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public int save(T entity) {
        return mapper.insertSelective(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(T entity) {
        return mapper.deleteByPrimaryKey(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public int saveOrUpdate(T entity) {
        //TODO 自动判断插入或修改
        if (entity.getIsNew()) {
            entity.preInsert();
            return mapper.insertSelective(entity);
        } else {
            entity.preUpdate();
            return mapper.updateByPrimaryKeySelective(entity);
        }
    }
    //    public Page<T> findPage(Page<T> page, T entity) {
    //        //TODO 分页查询
    //        //        entity.setPage(page);
    //        //        page.setList(dao.findList(entity));
    //        return page;
    //    }
}
