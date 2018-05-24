package com.elf.sys.ria.service;

import com.elf.core.service.BaseService;
import com.elf.sys.ria.entity.App;

import java.util.List;

public interface AppService extends BaseService<App> {

    List<App> getAuthorityAppList(List<String> authorityAppIdList, App app);
}
