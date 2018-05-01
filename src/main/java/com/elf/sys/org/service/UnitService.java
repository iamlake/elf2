package com.elf.sys.org.service;

import java.util.List;

import com.elf.sys.org.entity.SysOrgDimension;
import com.elf.sys.org.entity.SysOrgDimensionUnit;
import com.elf.sys.org.entity.SysOrgUnit;

public interface UnitService {

	List<SysOrgDimension> getDimensionList();

	List<SysOrgDimensionUnit> getChildDimensionUnitList(String parentDimensionUnitId, String dimensionId);

	List<SysOrgDimensionUnit> getAllChildDimensionUnitList(String parentDimensionUnitId, String dimensionId);

	SysOrgUnit getUnitById(String id);

	SysOrgDimensionUnit getRootDimensionUnit(String dimensionId);

	SysOrgDimensionUnit saveUnitAndDimensionUnit(SysOrgUnit paramUnit, String parentDimensionUnitId, String dimensionId);

	SysOrgDimensionUnit updateUnitAndDimensionUnit(SysOrgUnit paramUnit);

	void deleteUnitAndDimensionUnit(String dimensionUnitId);

	void saveUnitUser(String userId, String unitId);

	void deleteUnitUser(String userId, String unitId);

	SysOrgDimension saveDimension(SysOrgDimension dimension);

	void deleteDimension(String dimensionId);

}
