package com.elf.sys.security.mapper;

import com.elf.core.persistence.BaseMapper;
import com.elf.sys.security.entity.ResourceAuthority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: elf
 * @description: ResourceAuthorityMapper
 * @author: Liyiming
 * @create: 2018-05-20 09:56
 **/
public interface ResourceAuthorityMapper extends BaseMapper<ResourceAuthority> {
    /**
     * @Description: 通过用户ID查询授权资源
     * @Param: [userId, resourceType, authorityType]
     * @return: java.util.List<java.lang.String>
     * @Author: Liyiming
     * @Date: 2018/5/23
     */
    List<String> selectResourceAuthorityByUserId(@Param("userId") String userId,
                                                 @Param("resourceType") String resourceType,
                                                 @Param("authorityType") String authorityType);
}
