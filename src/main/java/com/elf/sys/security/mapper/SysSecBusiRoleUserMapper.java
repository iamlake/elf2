package com.elf.sys.security.mapper;

import com.elf.sys.security.entity.SysSecBusiRoleUser;
import com.elf.sys.security.entity.SysSecBusiRoleUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysSecBusiRoleUserMapper {
    long countByExample(SysSecBusiRoleUserExample example);

    int deleteByExample(SysSecBusiRoleUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysSecBusiRoleUser record);

    int insertSelective(SysSecBusiRoleUser record);

    List<SysSecBusiRoleUser> selectByExample(SysSecBusiRoleUserExample example);

    SysSecBusiRoleUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysSecBusiRoleUser record, @Param("example") SysSecBusiRoleUserExample example);

    int updateByExample(@Param("record") SysSecBusiRoleUser record, @Param("example") SysSecBusiRoleUserExample example);

    int updateByPrimaryKeySelective(SysSecBusiRoleUser record);

    int updateByPrimaryKey(SysSecBusiRoleUser record);
}