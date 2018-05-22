package com.elf.sys.security.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.elf.core.common.utils.StringUtils;
import com.elf.core.context.context.ContextHolder;
import com.elf.core.persistence.constants.Global;
import com.elf.core.service.impl.BaseServiceImpl;
import com.elf.sys.org.entity.User;
import com.elf.sys.security.entity.ResourceAuthority;
import com.elf.sys.security.entity.Role;
import com.elf.sys.security.mapper.ResourceAuthorityMapper;
import com.elf.sys.security.mapper.RoleMapper;
import com.elf.sys.security.service.ResourceAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: elf
 * @description: ResourceAuthorityServiceImpl
 * @author: Liyiming
 * @create: 2018-05-20 09:55
 **/
@Service
public class ResourceAuthorityServiceImpl extends BaseServiceImpl<ResourceAuthorityMapper, ResourceAuthority> implements ResourceAuthorityService {
    enum RoleTypeEnum {
        adminRole, bizRole, unitRole, stationRole
    }

    enum ResourceTypeEnum {
        app, menu, permision
    }

    enum AuthorityTypeEnum {
        available, forbidden
    }

    @Autowired
    private ResourceAuthorityMapper resourceAuthorityMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ResourceAuthority> savePermissionAuthority(String roleId, List<String> permissionList) {
        String authorityType = AuthorityTypeEnum.available.toString();
        return this.savePermissionAuthority(roleId, permissionList, authorityType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ResourceAuthority> savePermissionAuthorityForbidden(String roleId, List<String> permissionList) {
        String authorityType = AuthorityTypeEnum.forbidden.toString();
        return this.savePermissionAuthority(roleId, permissionList, authorityType);
    }

    private List<ResourceAuthority> savePermissionAuthority(String roleId, List<String> permissionList, String authorityType) {
        String resourceType = ResourceTypeEnum.permision.toString();
        return this.saveResourceAuthority(roleId, permissionList, resourceType, authorityType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ResourceAuthority> saveMenuAuthority(String roleId, List<String> menuIdList) {
        String authorityType = AuthorityTypeEnum.available.toString();
        return this.saveMenuAuthority(roleId, menuIdList, authorityType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ResourceAuthority> saveMenuAuthorityForbidden(String roleId, List<String> menuIdList) {
        String authorityType = ResourceAuthorizeServiceImpl.AuthorityTypeEnum.forbidden.toString();
        return this.saveMenuAuthority(roleId, menuIdList, authorityType);
    }

    private List<ResourceAuthority> saveMenuAuthority(String roleId, List<String> menuIdList, String authorityType) {
        String resourceType = ResourceTypeEnum.menu.toString();
        this.deleteResourceAuthority(roleId, resourceType, authorityType);
        return this.saveResourceAuthority(roleId, menuIdList, resourceType, authorityType);
    }

    @Override
    public List<ResourceAuthority> getMenuAuthorityByRoleId(String roleId) {
        String authorityType = AuthorityTypeEnum.available.toString();
        ArrayList<String> roleIdList = new ArrayList<>();
        roleIdList.add(roleId);
        String resourceType = ResourceTypeEnum.menu.toString();
        return this.getResourceAuthority(roleIdList, resourceType, authorityType);
    }

    @Override
    public List<ResourceAuthority> getMenuAuthorityForbiddenByRoleId(String roleId) {
        String authorityType = AuthorityTypeEnum.forbidden.toString();
        ArrayList<String> roleIdList = new ArrayList<>();
        roleIdList.add(roleId);
        String resourceType = ResourceTypeEnum.menu.toString();
        return this.getResourceAuthority(roleIdList, resourceType, authorityType);
    }

    @Override
    public List<ResourceAuthority> getMenuAuthority() {
        String authorityType = AuthorityTypeEnum.available.toString();
        return this.getMenuAuthority(authorityType);
    }

    @Override
    public List<ResourceAuthority> getMenuAuthorityForbidden() {
        String authorityType = AuthorityTypeEnum.forbidden.toString();
        return this.getMenuAuthority(authorityType);
    }

    private List<ResourceAuthority> getMenuAuthority(String authorityType) {
        User currentUser = ContextHolder.getContext().getCurrentUser();
        List<Role> roleList = roleMapper.selectRoleListByUserId(currentUser.getUserId());
        ArrayList<String> roleIdList = new ArrayList<>();
        for (Role role : roleList) {
            roleIdList.add(role.getRoleId());
        }
        String resourceType = ResourceTypeEnum.menu.toString();
        return this.getResourceAuthority(roleIdList, resourceType, authorityType);
    }

    private List<ResourceAuthority> saveResourceAuthority(String roleId, List<String> resourceList, String resourceType, String authorityType) {
        User currentUser = ContextHolder.getContext().getCurrentUser();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        List<ResourceAuthority> returnList = new ArrayList<>();
        if (resourceList != null) {
            ResourceAuthority resourceAuthority;
            for (String resource : resourceList) {
                resourceAuthority = new ResourceAuthority();
                resourceAuthority.setAuthorityId(StringUtils.getUUID());
                resourceAuthority.setRoleId(roleId);
                resourceAuthority.setResourceId(resource);
                resourceAuthority.setResourceType(resourceType);
                resourceAuthority.setAuthorityType(authorityType);
                resourceAuthority.setCreatedBy(currentUser.getAccount());
                resourceAuthority.setCreationTime(currentTime);
                resourceAuthority.setModifiedBy(currentUser.getAccount());
                resourceAuthority.setModificationTime(currentTime);
                resourceAuthority.setActiveFlag(Global.ACTIVE_FLAG_ENABLED);
                returnList.add(resourceAuthority);
                resourceAuthorityMapper.insert(resourceAuthority);
            }
        }
        return returnList;
    }

    private List<ResourceAuthority> getResourceAuthority(List<String> roleIdList, String resourceType, String authorityType) {
        EntityWrapper<ResourceAuthority> entityWrapper = new EntityWrapper<>();
        entityWrapper.in("ROLE_ID", roleIdList);
        entityWrapper.eq("RESOURCE_TYPE", resourceType).eq("AUTHORITY_TYPE", authorityType);
        List<ResourceAuthority> resourceAuthorityList = resourceAuthorityMapper.selectList(entityWrapper);
        return resourceAuthorityList;
    }

    private void deleteResourceAuthority(String roleId, String resourceType, String authorityType) {
        EntityWrapper<ResourceAuthority> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("ROLE_ID", roleId).eq("RESOURCE_TYPE", resourceType).eq("AUTHORITY_TYPE", authorityType);
        resourceAuthorityMapper.delete(entityWrapper);
    }
}
