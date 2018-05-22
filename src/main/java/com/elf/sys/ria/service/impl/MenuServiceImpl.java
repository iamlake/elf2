package com.elf.sys.ria.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.elf.core.service.impl.BaseServiceImpl;
import com.elf.sys.ria.entity.Menu;
import com.elf.sys.ria.mapper.MenuMapper;
import com.elf.sys.ria.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: elf
 * @description: MenuServiceImpl
 * @author: Liyiming
 * @create: 2018-03-15 17:05
 **/
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuListByAppId(String appId) {
        EntityWrapper<Menu> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("APP_ID", appId);
        List<Menu> menuList = menuMapper.selectList(entityWrapper);
        return menuList;
    }
}
