package com.elf.sys.ria.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.elf.core.common.utils.StringUtils;
import com.elf.core.context.context.ContextHolder;
import com.elf.core.persistence.constants.Global;
import com.elf.core.service.impl.BaseServiceImpl;
import com.elf.sys.org.entity.User;
import com.elf.sys.ria.entity.Menu;
import com.elf.sys.ria.mapper.MenuMapper;
import com.elf.sys.ria.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
    public List<Menu> getMenuList(Menu menu) {
        EntityWrapper<Menu> entityWrapper = new EntityWrapper<>(menu);
        return menuMapper.selectList(entityWrapper);
    }

    @Override
    public List<Menu> getAuthorityMenuList(List<String> authorityMenuIdList, Menu menu) {
        EntityWrapper<Menu> entityWrapper = new EntityWrapper<>(menu);
        entityWrapper.in("MENU_ID", authorityMenuIdList).orderBy("MENU_ORDER", true);
        return menuMapper.selectList(entityWrapper);
    }

    @Override
    public List<Menu> getMenuListByAppId(String appId) {
        EntityWrapper<Menu> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("APP_ID", appId);
        return menuMapper.selectList(entityWrapper);
    }

    @Override
    public Menu saveMenu(Menu menu) {
        User currentUser = ContextHolder.getContext().getCurrentUser();
        String account = currentUser.getAccount();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        menu.setMenuId(StringUtils.getUUID());
        menu.setCreatedBy(account);
        menu.setCreationTime(currentTime);
        menu.setModifiedBy(account);
        menu.setModificationTime(currentTime);
        menu.setActiveFlag(Global.ACTIVE_FLAG_ENABLED);
        int iRet = menuMapper.insert(menu);
        if (iRet > 0) {
            return menu;
        }
        return null;
    }

    @Override
    public Menu updateMenu(Menu menu) {
        User currentUser = ContextHolder.getContext().getCurrentUser();
        String account = currentUser.getAccount();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        menu.setModifiedBy(account);
        menu.setModificationTime(currentTime);
        int iRet = menuMapper.updateById(menu);
        if (iRet > 0) {
            return menu;
        }
        return null;
    }
}
