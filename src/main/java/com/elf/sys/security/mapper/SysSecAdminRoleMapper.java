package com.elf.sys.security.mapper;

import com.elf.sys.security.entity.SysSecAdminRole;
import com.elf.sys.security.entity.SysSecAdminRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysSecAdminRoleMapper {
    long countByExample(SysSecAdminRoleExample example);

    int deleteByExample(SysSecAdminRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysSecAdminRole record);

    int insertSelective(SysSecAdminRole record);

    List<SysSecAdminRole> selectByExample(SysSecAdminRoleExample example);

    SysSecAdminRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysSecAdminRole record, @Param("example") SysSecAdminRoleExample example);

    int updateByExample(@Param("record") SysSecAdminRole record, @Param("example") SysSecAdminRoleExample example);

    int updateByPrimaryKeySelective(SysSecAdminRole record);

    int updateByPrimaryKey(SysSecAdminRole record);
}