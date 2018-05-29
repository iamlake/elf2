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

    List<String> getResourceAuthority();

    List<String> getResourceAuthorityForbidden();

    List<ResourceAuthority> getResourceAuthorityByRoleId(String roleId);

    List<ResourceAuthority> getResourceAuthorityForbiddenByRoleId(String roleId);

    List<ResourceAuthority> saveResourceAuthority(String roleId, List<String> resourceIdList) throws Exception;

    List<ResourceAuthority> saveResourceAuthorityForbidden(String roleId, List<String> resourceIdList) throws Exception;

}
