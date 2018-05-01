package com.elf.sys.security.mapper;

import com.elf.sys.security.entity.SysSecAdminRoleBusiRole;
import com.elf.sys.security.entity.SysSecAdminRoleBusiRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysSecAdminRoleBusiRoleMapper {
    long countByExample(SysSecAdminRoleBusiRoleExample example);

    int deleteByExample(SysSecAdminRoleBusiRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysSecAdminRoleBusiRole record);

    int insertSelective(SysSecAdminRoleBusiRole record);

    List<SysSecAdminRoleBusiRole> selectByExample(SysSecAdminRoleBusiRoleExample example);

    SysSecAdminRoleBusiRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysSecAdminRoleBusiRole record, @Param("example") SysSecAdminRoleBusiRoleExample example);

    int updateByExample(@Param("record") SysSecAdminRoleBusiRole record, @Param("example") SysSecAdminRoleBusiRoleExample example);

    int updateByPrimaryKeySelective(SysSecAdminRoleBusiRole record);

    int updateByPrimaryKey(SysSecAdminRoleBusiRole record);
}