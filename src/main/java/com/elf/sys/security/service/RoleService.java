package com.elf.sys.security.service;

import com.elf.core.service.BaseService;
import com.elf.sys.security.entity.Role;

import java.util.List;

/**
 * @program: elf
 * @description: RoleService
 * @author: Liyiming
 * @create: 2018-05-17 09:14
 **/
public interface RoleService extends BaseService<Role> {
    List<Role> getRoleList(Role role);

    Role saveRole(Role role);

    Role updateRole(Role role);

    void saveUserRoles(List<String> userIdsList, String roleId) throws Exception;

    void deleteUserRoles(List<String> userIdsList, String roleId) throws Exception;

    List<Role> getRoleListByUserId(String userId);

    List<Role> getRoleListByCurrentUser();
}
