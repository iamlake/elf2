package com.elf.sys.security.mapper;

import com.elf.core.persistence.BaseMapper;
import com.elf.sys.security.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: elf
 * @description: RoleMapper
 * @author: Liyiming
 * @create: 2018-05-17 09:08
 **/
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * @Description: 根据用户ID获取权限信息
     * @Param: [userId]
     * @return: java.util.List<com.elf.sys.security.entity.Role>
     * @Author: Liyiming
     * @Date: 2018/5/20
     */
    @Select("select r.* from SYS_SEC_ROLE r, SYS_SEC_USER_ROLE ur where r.ROLE_ID = ur.ROLE_ID and ur.USER_ID = #{userId}")
    List<Role> selectRoleListByUserId(@Param("userId") String userId);
}
