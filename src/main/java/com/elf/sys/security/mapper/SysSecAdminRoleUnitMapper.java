package com.elf.sys.security.mapper;

import com.elf.sys.security.entity.SysSecAdminRoleUnit;
import com.elf.sys.security.entity.SysSecAdminRoleUnitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysSecAdminRoleUnitMapper {
    long countByExample(SysSecAdminRoleUnitExample example);

    int deleteByExample(SysSecAdminRoleUnitExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysSecAdminRoleUnit record);

    int insertSelective(SysSecAdminRoleUnit record);

    List<SysSecAdminRoleUnit> selectByExample(SysSecAdminRoleUnitExample example);

    SysSecAdminRoleUnit selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysSecAdminRoleUnit record, @Param("example") SysSecAdminRoleUnitExample example);

    int updateByExample(@Param("record") SysSecAdminRoleUnit record, @Param("example") SysSecAdminRoleUnitExample example);

    int updateByPrimaryKeySelective(SysSecAdminRoleUnit record);

    int updateByPrimaryKey(SysSecAdminRoleUnit record);
}