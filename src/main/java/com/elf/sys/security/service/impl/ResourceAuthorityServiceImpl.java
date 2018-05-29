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
public abstract class ResourceAuthorityServiceImpl extends BaseServiceImpl<ResourceAuthorityMapper, ResourceAuthority> implements ResourceAuthorityService {
    enum RoleTypeEnum {adminRole, bizRole, unitRole, stationRole}

    enum ResourceTypeEnum {app, menu, permission}

    enum AuthorityTypeEnum {available, forbidden}

    @Autowired
    private ResourceAuthorityMapper resourceAuthorityMapper;

    /**
     * @Description: 保存资源授权信息
     * @Param: [roleId, resourceList, resourceType, authorityType]
     * @return: java.util.List<com.elf.sys.security.entity.ResourceAuthority>
     * @Author: Liyiming
     * @Date: 2018/5/22
     */
    List<ResourceAuthority> saveResourceAuthority(String roleId, List<String> resourceList, String resourceType, String authorityType) {
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
    List<ResourceAuthority> getResourceAuthority(List<String> roleIdList, String resourceType, String authorityType) {
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
    int deleteResourceAuthority(String roleId, String resourceType, String authorityType) {
        EntityWrapper<ResourceAuthority> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("ROLE_ID", roleId).eq("RESOURCE_TYPE", resourceType).eq("AUTHORITY_TYPE", authorityType);
        return resourceAuthorityMapper.delete(entityWrapper);
    }
}