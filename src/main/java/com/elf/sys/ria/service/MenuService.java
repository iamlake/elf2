package com.elf.sys.ria.service;

import com.elf.core.service.BaseService;
import com.elf.sys.ria.entity.Menu;

import java.util.List;

/**
 * @Title MenuService
 * @Description
 * @Author icelake
 * @Date 2018/3/13 17:16
 */
public interface MenuService extends BaseService<Menu> {

    List<Menu> getMenuList(Menu menu);

    List<Menu> getAuthorityMenuList(List<String> authorityMenuIdList, Menu menu);

    List<Menu> getMenuListByAppId(String appId);

    Menu saveMenu(Menu menu) throws Exception;

    Menu updateMenu(Menu menu) throws Exception;
}
