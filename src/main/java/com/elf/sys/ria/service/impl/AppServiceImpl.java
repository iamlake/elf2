package com.elf.sys.ria.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.elf.core.service.impl.BaseServiceImpl;
import com.elf.sys.ria.entity.App;
import com.elf.sys.ria.mapper.AppMapper;
import com.elf.sys.ria.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: elf
 * @description: AppServiceImpl
 * @author: Liyiming
 * @create: 2018-03-17 17:21
 **/
@Service
public class AppServiceImpl extends BaseServiceImpl<AppMapper, App> implements AppService {

    @Autowired
    private AppMapper appMapper;

    @Override
    public List<App> getAuthorityAppList(List<String> authorityAppIdList, App app) {
        EntityWrapper<App> entityWrapper = new EntityWrapper<>(app);
        entityWrapper.in("APP_ID", authorityAppIdList).orderBy("APP_ORDER", true);
        return appMapper.selectList(entityWrapper);
    }
}
