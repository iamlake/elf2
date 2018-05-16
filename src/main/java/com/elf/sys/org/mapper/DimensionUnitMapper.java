package com.elf.sys.org.mapper;

import com.elf.core.persistence.BaseMapper;
import com.elf.sys.org.entity.DimensionUnit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DimensionUnitMapper extends BaseMapper<DimensionUnit> {
    List<DimensionUnit> selectChildDimensionUnitList(@Param("parentDimensionUnitId") String parentDimensionUnitId, @Param("dimensionId") String dimensionId);

    List<DimensionUnit> selectDimensionUnitsByUnitPath(@Param("unitPath") String unitPath, @Param("dimensionId") String dimensionId);
}
