package com.elf.sys.org.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elf.sys.org.entity.SysOrgDimensionUnit;
import com.elf.sys.org.entity.SysOrgDimensionUnitExample;


public interface DimensionUnitMapper {
	
	long countByExample(SysOrgDimensionUnitExample example);

    int deleteByExample(SysOrgDimensionUnitExample example);

    int deleteByPrimaryKey(String dimensionUnitId);

    int insert(SysOrgDimensionUnit record);

    int insertSelective(SysOrgDimensionUnit record);

    List<SysOrgDimensionUnit> selectByExample(SysOrgDimensionUnitExample example);

    SysOrgDimensionUnit selectByPrimaryKey(String dimensionUnitId);

    int updateByExampleSelective(@Param("record") SysOrgDimensionUnit record, @Param("example") SysOrgDimensionUnitExample example);

    int updateByExample(@Param("record") SysOrgDimensionUnit record, @Param("example") SysOrgDimensionUnitExample example);

    int updateByPrimaryKeySelective(SysOrgDimensionUnit record);

    int updateByPrimaryKey(SysOrgDimensionUnit record);

	//public SysOrgDimensionUnit selectByPrimaryKey(@Param("dimensionUnitId") String id);
	
	public List<SysOrgDimensionUnit> getChildDimensionUnitList(@Param("parentDimensionUnitId") String parentDimensionUnitId, @Param("dimensionId") String dimensionId);
	
	public List<SysOrgDimensionUnit> getDimensionUnitsByUnitPath(@Param("unitPath") String unitPath, @Param("dimensionId") String dimensionId);
	
	//public SysOrgDimensionUnit getRootDimensionUnit(@Param("dimensionId") String dimensionId);

	//public void insertSelective(SysOrgDimensionUnit dimensionUnit);
	
	//public SysOrgDimensionUnit getDimensionUnitByUnitId(@Param("unitId") String unitId);
	
	//public void updateByPrimaryKeySelective(SysOrgDimensionUnit dimensionUnit);
	
}
