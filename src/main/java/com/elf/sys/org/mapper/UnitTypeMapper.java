package com.elf.sys.org.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elf.sys.org.entity.SysOrgUnitType;
import com.elf.sys.org.entity.SysOrgUnitTypeExample;


public interface UnitTypeMapper {
	
	long countByExample(SysOrgUnitTypeExample example);

    int deleteByExample(SysOrgUnitTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysOrgUnitType record);

    int insertSelective(SysOrgUnitType record);

    List<SysOrgUnitType> selectByExample(SysOrgUnitTypeExample example);

    SysOrgUnitType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysOrgUnitType record, @Param("example") SysOrgUnitTypeExample example);

    int updateByExample(@Param("record") SysOrgUnitType record, @Param("example") SysOrgUnitTypeExample example);

    int updateByPrimaryKeySelective(SysOrgUnitType record);

    int updateByPrimaryKey(SysOrgUnitType record);
	    
}
