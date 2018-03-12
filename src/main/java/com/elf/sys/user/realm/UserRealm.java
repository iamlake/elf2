package com.elf.sys.user.realm;

import com.elf.sys.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @Title UserRealm
 * @Description
 * @Author icelake
 * @Date 2018/3/11 18:31
 */
@Component
public class UserRealm extends AbstractUserRealm {

    @Override
    public UserRolesAndPermissions doGetGroupAuthorizationInfo(User userInfo) {
        Set<String> userRoles = new HashSet<>();
        Set<String> userPermissions = new HashSet<>();
        //TODO 获取当前用户下拥有的所有角色列表,及权限
        return new UserRolesAndPermissions(userRoles, userPermissions);
    }

    @Override
    public UserRolesAndPermissions doGetRoleAuthorizationInfo(User userInfo) {
        Set<String> userRoles = new HashSet<>();
        Set<String> userPermissions = new HashSet<>();
        //TODO 获取当前用户下拥有的所有角色列表,及权限
        return new UserRolesAndPermissions(userRoles, userPermissions);
    }
}
