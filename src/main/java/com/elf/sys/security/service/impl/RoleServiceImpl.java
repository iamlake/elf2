package com.elf.sys.security.service.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.elf.core.common.utils.StringUtils;
import com.elf.core.context.context.ContextHolder;
import com.elf.core.exception.BusinessException;
import com.elf.core.service.impl.BaseServiceImpl;
import com.elf.sys.org.entity.User;
import com.elf.sys.security.entity.Role;
import com.elf.sys.security.entity.UserRole;
import com.elf.sys.security.mapper.RoleMapper;
import com.elf.sys.security.mapper.UserRoleMapper;
import com.elf.sys.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @program: elf
 * @description: RoleServiceImpl
 * @author: Liyiming
 * @create: 2018-05-17 09:15
 **/
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> getRoleList(Role role) {
        EntityWrapper<Role> entityWrapper = new EntityWrapper<>();
        if (StringUtils.isNotBlank(role.getRoleName())) {
            entityWrapper.eq("ROLE_NAME", role.getRoleName());
        }
        if (StringUtils.isNotBlank(role.getRemark())) {
            entityWrapper.like("REMARK", role.getRemark(), SqlLike.DEFAULT);
        }
        return roleMapper.selectList(entityWrapper);
    }

    @Override
    public Role saveRole(Role role) {
        EntityWrapper<Role> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("ROLE_NAME", role.getRoleName());
        if (roleMapper.selectCount(entityWrapper) > 0) {
            throw new BusinessException("SYSSEC0001");
        }
        User currentUser = ContextHolder.getContext().getCurrentUser();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        role.setCreatedBy(currentUser.getAccount());
        role.setCreationTime(currentTime);
        role.setModifiedBy(currentUser.getAccount());
        role.setModificationTime(currentTime);
        role.setRoleId(StringUtils.getUUID());
        roleMapper.insert(role);
        return role;
    }

    @Override
    public Role updateRole(Role role) {
        EntityWrapper<Role> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("ROLE_NAME", role.getRoleName()).notIn("ROLE_ID",role.getRoleId());
        if (roleMapper.selectCount(entityWrapper) > 0) {
            throw new BusinessException("SYSSEC0001");
        }
        User currentUser = ContextHolder.getContext().getCurrentUser();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        role.setModifiedBy(currentUser.getAccount());
        role.setModificationTime(currentTime);
        roleMapper.updateById(role);
        return role;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUserRoles(List<String> userIdsList, String roleId) {
        User currentUser = ContextHolder.getContext().getCurrentUser();
        String currentAccount = currentUser.getAccount();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        UserRole userRole;
        for (String userId : userIdsList) {
            userRole = new UserRole();
            userRole.setUserRoleId(StringUtils.getUUID());
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRole.setCreatedBy(currentAccount);
            userRole.setCreationTime(currentTime);
            userRole.setModifiedBy(currentAccount);
            userRole.setModificationTime(currentTime);
            userRoleMapper.insert(userRole);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUserRoles(List<String> userIdsList, String roleId) {
        EntityWrapper<UserRole> entityWrapper;
        for (String userId : userIdsList) {
            entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("user_id", userId).eq("role_id", roleId);
            userRoleMapper.delete(entityWrapper);
        }
    }

    @Override
    public List<Role> getRoleListByUserId(String userId) {
        List<Role> roleList = roleMapper.selectRoleListByUserId(userId);
        return roleList;
    }

    @Override
    public List<Role> getRoleListByCurrentUser() {
        User currentUser = ContextHolder.getContext().getCurrentUser();
        List<Role> roleList= this.getRoleListByUserId(currentUser.getUserId());
        return roleList;
    }
}
