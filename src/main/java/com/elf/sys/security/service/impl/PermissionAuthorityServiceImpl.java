package com.elf.sys.security.service.impl;

import com.elf.core.context.context.ContextHolder;
import com.elf.sys.org.entity.User;
import com.elf.sys.security.entity.ResourceAuthority;
import com.elf.sys.security.mapper.ResourceAuthorityMapper;
import com.elf.sys.security.service.ResourceAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: elf
 * @description: PermissionAuthorityServiceImpl
 * @author: Liyiming
 * @create: 2018-05-26 21:29
 **/
@Service("permissionAuthorityService")
public class PermissionAuthorityServiceImpl extends ResourceAuthorityServiceImpl implements ResourceAuthorityService {

    @Autowired
    private ResourceAuthorityMapper resourceAuthorityMapper;

    /**
     * @Description: 查询当前用户可使用的permission授权信息
     * @Param: []
     * @return: java.util.List<java.lang.String>
     * @Author: Liyiming
     * @Date: 2018/5/26
     */
    @Override
    public List<String> getResourceAuthority() {
        User currentUser = ContextHolder.getContext().getCurrentUser();
        return resourceAuthorityMapper.selectResourceAuthorityByUserId(currentUser.getUserId(), ResourceTypeEnum.permission.toString(), AuthorityTypeEnum.available.toString());
    }

    /**
     * @Description: 查询当前用户被禁用的permission授权信息
     * @Param: []
     * @return: java.util.List<java.lang.String>
     * @Author: Liyiming
     * @Date: 2018/5/26
     */
    @Override
    public List<String> getResourceAuthorityForbidden() {
        User currentUser = ContextHolder.getContext().getCurrentUser();
        return resourceAuthorityMapper.selectResourceAuthorityByUserId(currentUser.getUserId(), ResourceTypeEnum.permission.toString(), AuthorityTypeEnum.forbidden.toString());
    }

    /**
     * @Description: 根据指定角色查询可使用的permission授权信息
     * @Param: [roleId]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/26
     */
    @Override
    public List<ResourceAuthority> getResourceAuthorityByRoleId(String roleId) {
        ArrayList<String> roleIdList = new ArrayList<>();
        roleIdList.add(roleId);
        return this.getResourceAuthority(roleIdList, ResourceTypeEnum.permission.toString(), AuthorityTypeEnum.available.toString());
    }

    /**
     * @Description: 根据指定角色查询被禁用的permission授权信息
     * @Param: [roleId]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/26
     */
    @Override
    public List<ResourceAuthority> getResourceAuthorityForbiddenByRoleId(String roleId) {
        ArrayList<String> roleIdList = new ArrayList<>();
        roleIdList.add(roleId);
        return this.getResourceAuthority(roleIdList, ResourceTypeEnum.permission.toString(), AuthorityTypeEnum.forbidden.toString());
    }

    /**
     * @Description: 保存指定角色可使用的permission授权信息
     * @Param: [roleId, permissionIdList]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/26
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ResourceAuthority> saveResourceAuthority(String roleId, List<String> permissionIdList) {
        String authorityType = AuthorityTypeEnum.available.toString();
        String resourceType = ResourceTypeEnum.permission.toString();
        this.deleteResourceAuthority(roleId, resourceType, authorityType);
        return this.saveResourceAuthority(roleId, permissionIdList, resourceType, authorityType);
    }

    /**
     * @Description: 保存指定角色被禁用的permission授权信息
     * @Param: [roleId, permissionIdList]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/26
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ResourceAuthority> saveResourceAuthorityForbidden(String roleId, List<String> permissionIdList) {
        String authorityType = AuthorityTypeEnum.forbidden.toString();
        String resourceType = ResourceTypeEnum.permission.toString();
        this.deleteResourceAuthority(roleId, resourceType, authorityType);
        return this.saveResourceAuthority(roleId, permissionIdList, resourceType, authorityType);
    }
}
