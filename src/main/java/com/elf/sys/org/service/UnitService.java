package com.elf.sys.org.service;

import com.elf.core.service.BaseService;
import com.elf.sys.org.entity.Dimension;
import com.elf.sys.org.entity.DimensionUnit;
import com.elf.sys.org.entity.Unit;
import com.elf.sys.org.entity.UnitUser;

import java.util.List;

public interface UnitService extends BaseService<Unit> {

    List<Dimension> getDimensionList();

    List<DimensionUnit> getChildDimensionUnitList(String parentDimensionUnitId, String dimensionId);

    List<DimensionUnit> getAllChildDimensionUnitList(String parentDimensionUnitId, String dimensionId);

    Unit getUnitById(String id);

    DimensionUnit getRootDimensionUnit(String dimensionId);

    DimensionUnit saveUnitAndDimensionUnit(Unit paramUnit, String parentDimensionUnitId, String dimensionId);

    DimensionUnit updateUnitAndDimensionUnit(Unit paramUnit);

    void deleteUnitAndDimensionUnit(String dimensionUnitId);

    UnitUser saveUnitUser(String userId, String unitId);

    void saveUnitUsers(List<String> userIdsList, String unitId) throws Exception;

    void deleteUnitUsers(List<String> userIdsList, String unitId) throws Exception;

    int deleteUnitUser(String userId, String unitId);

    Dimension saveDimension(Dimension dimension);

    int deleteDimension(String dimensionId);

}
