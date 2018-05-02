package com.elf.sys.org.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elf.core.persistence.DataEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("sys_org_dimension_unit")
public class DimensionUnit extends DataEntity{

    @TableId
    private String dimensionUnitId;

    private String dimensionId;

    private String unitId;

    private String parentDimensionUnitId;

    private String aliasName;

    private String unitPath;

    private BigDecimal depth;

    private BigDecimal sort;

    private Date timeBegin;

    private Date timeEnd;

    private String description;

    private String isEnabled;

    @TableField(exist = false)
    private Unit unit;

    @TableField(exist = false)
    private Dimension dimension;


    @Override
    public void preInsert() {

    }

    @Override
    public void preUpdate() {

    }
}