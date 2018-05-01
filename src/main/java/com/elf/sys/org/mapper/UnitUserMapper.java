package com.elf.sys.org.mapper;

import com.elf.sys.org.entity.SysOrgUnitUser;
import com.elf.sys.org.entity.SysOrgUnitUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;


public interface UnitUserMapper {
	
    long countByExample(SysOrgUnitUserExample example);

    int deleteByExample(SysOrgUnitUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysOrgUnitUser record);

    int insertSelective(SysOrgUnitUser record);

    List<SysOrgUnitUser> selectByExample(SysOrgUnitUserExample example);

    SysOrgUnitUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysOrgUnitUser record, @Param("example") SysOrgUnitUserExample example);

    int updateByExample(@Param("record") SysOrgUnitUser record, @Param("example") SysOrgUnitUserExample example);

    int updateByPrimaryKeySelective(SysOrgUnitUser record);

    int updateByPrimaryKey(SysOrgUnitUser record);
}