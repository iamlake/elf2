package com.elf.sys.security.mapper;

import com.elf.sys.security.entity.SysSecBusiRole;
import com.elf.sys.security.entity.SysSecBusiRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysSecBusiRoleMapper {
    long countByExample(SysSecBusiRoleExample example);

    int deleteByExample(SysSecBusiRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysSecBusiRole record);

    int insertSelective(SysSecBusiRole record);

    List<SysSecBusiRole> selectByExample(SysSecBusiRoleExample example);

    SysSecBusiRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysSecBusiRole record, @Param("example") SysSecBusiRoleExample example);

    int updateByExample(@Param("record") SysSecBusiRole record, @Param("example") SysSecBusiRoleExample example);

    int updateByPrimaryKeySelective(SysSecBusiRole record);

    int updateByPrimaryKey(SysSecBusiRole record);
    
    List<SysSecBusiRole> getBusiRolesByUserId(@Param("userId") String userId);
}