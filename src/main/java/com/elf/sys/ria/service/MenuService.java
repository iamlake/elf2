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
public interface MenuService extends BaseService<Menu>{

    List<Menu> getMenuListByAppId(String appId);
}
