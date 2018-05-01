package com.elf.sys.security.mapper;

import com.elf.sys.security.entity.SysSecAdminRoleUser;
import com.elf.sys.security.entity.SysSecAdminRoleUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysSecAdminRoleUserMapper {
    long countByExample(SysSecAdminRoleUserExample example);

    int deleteByExample(SysSecAdminRoleUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysSecAdminRoleUser record);

    int insertSelective(SysSecAdminRoleUser record);

    List<SysSecAdminRoleUser> selectByExample(SysSecAdminRoleUserExample example);

    SysSecAdminRoleUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysSecAdminRoleUser record, @Param("example") SysSecAdminRoleUserExample example);

    int updateByExample(@Param("record") SysSecAdminRoleUser record, @Param("example") SysSecAdminRoleUserExample example);

    int updateByPrimaryKeySelective(SysSecAdminRoleUser record);

    int updateByPrimaryKey(SysSecAdminRoleUser record);
}