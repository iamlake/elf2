package com.elf.sys.org.mapper;

import com.elf.core.persistence.BaseMapper;
import com.elf.sys.org.entity.Unit;
import org.apache.ibatis.annotations.Param;

public interface UnitMapper extends BaseMapper<Unit> {
    Unit getUnitById(@Param("id") String id);
}
