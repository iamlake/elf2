package com.elf.sys.ria.mapper;

import com.elf.sys.ria.entity.SysMenuPermission;
import com.elf.sys.ria.entity.SysMenuPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMenuPermissionMapper {
    long countByExample(SysMenuPermissionExample example);

    int deleteByExample(SysMenuPermissionExample example);

    int insert(SysMenuPermission record);

    int insertSelective(SysMenuPermission record);

    List<SysMenuPermission> selectByExample(SysMenuPermissionExample example);

    int updateByExampleSelective(@Param("record") SysMenuPermission record, @Param("example") SysMenuPermissionExample example);

    int updateByExample(@Param("record") SysMenuPermission record, @Param("example") SysMenuPermissionExample example);
}