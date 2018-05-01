package com.elf.sys.org.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elf.sys.org.entity.SysOrgUnit;
import com.elf.sys.org.entity.SysOrgUnitExample;


public interface UnitMapper {
	
	long countByExample(SysOrgUnitExample example);

    int deleteByExample(SysOrgUnitExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysOrgUnit record);

    int insertSelective(SysOrgUnit record);

    List<SysOrgUnit> selectByExample(SysOrgUnitExample example);

    SysOrgUnit selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysOrgUnit record, @Param("example") SysOrgUnitExample example);

    int updateByExample(@Param("record") SysOrgUnit record, @Param("example") SysOrgUnitExample example);

    int updateByPrimaryKeySelective(SysOrgUnit record);

    int updateByPrimaryKey(SysOrgUnit record);
	
	public SysOrgUnit getUnitById(@Param("id") String id);

	//public void insertSelective(SysOrgUnit unit);
	
	//public void updateByPrimaryKeySelective(SysOrgUnit unit);
	    
}
