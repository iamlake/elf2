package com.elf.sys.org.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elf.sys.org.entity.SysOrgDimension;
import com.elf.sys.org.entity.SysOrgDimensionExample;


public interface DimensionMapper {
	
	long countByExample(SysOrgDimensionExample example);

    int deleteByExample(SysOrgDimensionExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysOrgDimension record);

    int insertSelective(SysOrgDimension record);

    List<SysOrgDimension> selectByExample(SysOrgDimensionExample example);

    SysOrgDimension selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysOrgDimension record, @Param("example") SysOrgDimensionExample example);

    int updateByExample(@Param("record") SysOrgDimension record, @Param("example") SysOrgDimensionExample example);

    int updateByPrimaryKeySelective(SysOrgDimension record);

    int updateByPrimaryKey(SysOrgDimension record);

	//public List<SysOrgDimension> getDimensionList();
	    
}
