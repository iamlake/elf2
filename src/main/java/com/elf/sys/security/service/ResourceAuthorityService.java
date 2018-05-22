package com.elf.sys.security.service;

import com.elf.core.service.BaseService;
import com.elf.sys.security.entity.ResourceAuthority;

import java.util.List;

/**
 * @program: elf
 * @description: ResourceAuthorityService
 * @author: Liyiming
 * @create: 2018-05-20 09:52
 **/
public interface ResourceAuthorityService extends BaseService<ResourceAuthority> {
    List<ResourceAuthority> savePermissionAuthority(String roleId, List<String> permissionList) throws Exception;

    List<ResourceAuthority> savePermissionAuthorityForbidden(String roleId, List<String> permissionList) throws Exception;

    List<ResourceAuthority> saveMenuAuthority(String roleId, List<String> menuIdList) throws Exception;

    List<ResourceAuthority> saveMenuAuthorityForbidden(String roleId, List<String> menuIdList) throws Exception;

    List<ResourceAuthority> getMenuAuthorityByRoleId(String roleId);

    List<ResourceAuthority> getMenuAuthorityForbiddenByRoleId(String roleId);

    List<ResourceAuthority> getMenuAuthority();

    List<ResourceAuthority> getMenuAuthorityForbidden();
}
