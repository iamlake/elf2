package com.elf.sys.security.mapper;

import com.elf.sys.security.entity.SysSecPermission;
import com.elf.sys.security.entity.SysSecPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysSecPermissionMapper {
    long countByExample(SysSecPermissionExample example);

    int deleteByExample(SysSecPermissionExample example);

    int deleteByPrimaryKey(String permissionId);

    int insert(SysSecPermission record);

    int insertSelective(SysSecPermission record);

    List<SysSecPermission> selectByExample(SysSecPermissionExample example);

    SysSecPermission selectByPrimaryKey(String permissionId);

    int updateByExampleSelective(@Param("record") SysSecPermission record, @Param("example") SysSecPermissionExample example);

    int updateByExample(@Param("record") SysSecPermission record, @Param("example") SysSecPermissionExample example);

    int updateByPrimaryKeySelective(SysSecPermission record);

    int updateByPrimaryKey(SysSecPermission record);
}