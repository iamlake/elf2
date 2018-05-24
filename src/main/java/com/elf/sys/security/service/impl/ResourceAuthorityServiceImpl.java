package com.elf.sys.security.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.elf.core.common.utils.StringUtils;
import com.elf.core.context.context.ContextHolder;
import com.elf.core.persistence.constants.Global;
import com.elf.core.service.impl.BaseServiceImpl;
import com.elf.sys.org.entity.User;
import com.elf.sys.security.entity.ResourceAuthority;
import com.elf.sys.security.mapper.ResourceAuthorityMapper;
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
    enum RoleTypeEnum {adminRole, bizRole, unitRole, stationRole}

    enum ResourceTypeEnum {app, menu, permission}

    enum AuthorityTypeEnum {available, forbidden}

    @Autowired
    private ResourceAuthorityMapper resourceAuthorityMapper;

    /**
     * @Description: 保存指定角色可使用的Permission授权信息
     * @Param: [roleId, permissionList]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ResourceAuthority> savePermissionAuthority(String roleId, List<String> permissionList) {
        String authorityType = AuthorityTypeEnum.available.toString();
        return this.savePermissionAuthority(roleId, permissionList, authorityType);
    }

    /**
     * @Description: 保存指定角色被禁用的Permission授权信息
     * @Param: [roleId, permissionList]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ResourceAuthority> savePermissionAuthorityForbidden(String roleId, List<String> permissionList) {
        String authorityType = AuthorityTypeEnum.forbidden.toString();
        return this.savePermissionAuthority(roleId, permissionList, authorityType);
    }

    /**
     * @Description: 保存指定角色的Permission授权信息，参数标识可使用/被禁用
     * @Param: [roleId, permissionList, authorityType]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    private List<ResourceAuthority> savePermissionAuthority(String roleId, List<String> permissionList, String authorityType) {
        String resourceType = ResourceTypeEnum.permission.toString();
        return this.saveResourceAuthority(roleId, permissionList, resourceType, authorityType);
    }

    /**
     * @Description: 保存指定角色可使用的应用授权信息
     * @Param: [roleId, appIdList]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ResourceAuthority> saveAppAuthority(String roleId, List<String> appIdList) {
        String authorityType = AuthorityTypeEnum.available.toString();
        return this.saveAppAuthority(roleId, appIdList, authorityType);
    }

    /**
     * @Description: 保存指定角色被禁用的应用授权信息
     * @Param: [roleId, appIdList]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ResourceAuthority> saveAppAuthorityForbidden(String roleId, List<String> appIdList) {
        String authorityType = AuthorityTypeEnum.forbidden.toString();
        return this.saveAppAuthority(roleId, appIdList, authorityType);
    }

    /**
     * @Description: 保存指定角色的应用授权信息，参数标识可使用/被禁用
     * @Param: [roleId, appIdList, authorityType]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    private List<ResourceAuthority> saveAppAuthority(String roleId, List<String> appIdList, String authorityType) {
        String resourceType = ResourceTypeEnum.app.toString();
        this.deleteResourceAuthority(roleId, resourceType, authorityType);
        return this.saveResourceAuthority(roleId, appIdList, resourceType, authorityType);
    }

    /**
     * @Description: 根据指定角色查询可使用的应用授权信息
     * @Param: [roleId]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    @Override
    public List<ResourceAuthority> getAppAuthorityByRoleId(String roleId) {
        String authorityType = AuthorityTypeEnum.available.toString();
        ArrayList<String> roleIdList = new ArrayList<>();
        roleIdList.add(roleId);
        String resourceType = ResourceTypeEnum.app.toString();
        return this.getResourceAuthority(roleIdList, resourceType, authorityType);
    }

    /**
     * @Description: 根据指定角色查询被禁用的应用授权信息
     * @Param: [roleId]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    @Override
    public List<ResourceAuthority> getAppAuthorityForbiddenByRoleId(String roleId) {
        String authorityType = AuthorityTypeEnum.forbidden.toString();
        ArrayList<String> roleIdList = new ArrayList<>();
        roleIdList.add(roleId);
        String resourceType = ResourceTypeEnum.app.toString();
        return this.getResourceAuthority(roleIdList, resourceType, authorityType);
    }

    /**
     * @Description: 查询当前用户可使用的应用授权信息
     * @Param: []
     * @return: java.util.List<java.lang.String>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    @Override
    public List<String> getAppAuthority() {
        String authorityType = AuthorityTypeEnum.available.toString();
        return this.getAppAuthority(authorityType);
    }

    /**
     * @Description: 查询当前用户被禁用的应用授权信息
     * @Param: []
     * @return: java.util.List<java.lang.String>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    @Override
    public List<String> getAppAuthorityForbidden() {
        String authorityType = AuthorityTypeEnum.forbidden.toString();
        return this.getAppAuthority(authorityType);
    }

    /**
     * @Description: 查询当前用户的应用授权信息，参数标识可使用/被禁用
     * @Param: [authorityType]
     * @return: java.util.List<java.lang.String>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    private List<String> getAppAuthority(String authorityType) {
        User currentUser = ContextHolder.getContext().getCurrentUser();
        String resourceType = ResourceTypeEnum.app.toString();
        return resourceAuthorityMapper.selectResourceAuthorityByUserId(currentUser.getUserId(), resourceType, authorityType);
    }

    /**
     * @Description: 保存指定角色可使用的菜单授权信息
     * @Param: [roleId, menuIdList]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ResourceAuthority> saveMenuAuthority(String roleId, List<String> menuIdList) {
        String authorityType = AuthorityTypeEnum.available.toString();
        return this.saveMenuAuthority(roleId, menuIdList, authorityType);
    }

    /**
     * @Description: 保存指定角色被禁用的菜单授权信息
     * @Param: [roleId, menuIdList]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ResourceAuthority> saveMenuAuthorityForbidden(String roleId, List<String> menuIdList) {
        String authorityType = AuthorityTypeEnum.forbidden.toString();
        return this.saveMenuAuthority(roleId, menuIdList, authorityType);
    }

    /**
     * @Description: 保存指定角色的菜单授权信息，参数标识可使用/被禁用
     * @Param: [roleId, menuIdList, authorityType]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    private List<ResourceAuthority> saveMenuAuthority(String roleId, List<String> menuIdList, String authorityType) {
        String resourceType = ResourceTypeEnum.menu.toString();
        this.deleteResourceAuthority(roleId, resourceType, authorityType);
        return this.saveResourceAuthority(roleId, menuIdList, resourceType, authorityType);
    }

    /**
     * @Description: 根据指定角色查询可使用的菜单授权信息
     * @Param: [roleId]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    @Override
    public List<ResourceAuthority> getMenuAuthorityByRoleId(String roleId) {
        String authorityType = AuthorityTypeEnum.available.toString();
        ArrayList<String> roleIdList = new ArrayList<>();
        roleIdList.add(roleId);
        String resourceType = ResourceTypeEnum.menu.toString();
        return this.getResourceAuthority(roleIdList, resourceType, authorityType);
    }

    /**
     * @Description: 根据指定角色查询被禁用的菜单授权信息
     * @Param: [roleId]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    @Override
    public List<ResourceAuthority> getMenuAuthorityForbiddenByRoleId(String roleId) {
        String authorityType = AuthorityTypeEnum.forbidden.toString();
        ArrayList<String> roleIdList = new ArrayList<>();
        roleIdList.add(roleId);
        String resourceType = ResourceTypeEnum.menu.toString();
        return this.getResourceAuthority(roleIdList, resourceType, authorityType);
    }

    /**
     * @Description: 查询当前用户可使用的菜单授权信息
     * @Param: []
     * @return: java.util.List<java.lang.String>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    @Override
    public List<String> getMenuAuthority() {
        String authorityType = AuthorityTypeEnum.available.toString();
        return this.getMenuAuthority(authorityType);
    }

    /**
     * @Description: 查询当前用户被禁用的菜单授权信息
     * @Param: []
     * @return: java.util.List<java.lang.String>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    @Override
    public List<String> getMenuAuthorityForbidden() {
        String authorityType = AuthorityTypeEnum.forbidden.toString();
        return this.getMenuAuthority(authorityType);
    }

    /**
     * @Description: 查询当前用户的菜单授权信息，参数标识可使用/被禁用
     * @Param: [authorityType]
     * @return: java.util.List<java.lang.String>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    private List<String> getMenuAuthority(String authorityType) {
        User currentUser = ContextHolder.getContext().getCurrentUser();
        String resourceType = ResourceTypeEnum.menu.toString();
        return resourceAuthorityMapper.selectResourceAuthorityByUserId(currentUser.getUserId(), resourceType, authorityType);
    }

    /**
     * @Description: 保存资源授权信息
     * @Param: [roleId, resourceList, resourceType, authorityType]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
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

    /**
     * @Description: 查询资源授权信息
     * @Param: [roleIdList, resourceType, authorityType]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    private List<ResourceAuthority> getResourceAuthority(List<String> roleIdList, String resourceType, String authorityType) {
        EntityWrapper<ResourceAuthority> entityWrapper = new EntityWrapper<>();
        entityWrapper.in("ROLE_ID", roleIdList);
        entityWrapper.eq("RESOURCE_TYPE", resourceType).eq("AUTHORITY_TYPE", authorityType);
        return resourceAuthorityMapper.selectList(entityWrapper);
    }

    /**
     * @Description: 删除资源授权信息
     * @Param: [roleId, resourceType, authorityType]
     * @return: int
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    private int deleteResourceAuthority(String roleId, String resourceType, String authorityType) {
        EntityWrapper<ResourceAuthority> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("ROLE_ID", roleId).eq("RESOURCE_TYPE", resourceType).eq("AUTHORITY_TYPE", authorityType);
        return resourceAuthorityMapper.delete(entityWrapper);
    }
}