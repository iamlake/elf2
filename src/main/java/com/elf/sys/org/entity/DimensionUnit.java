package com.elf.sys.org.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elf.core.persistence.DataEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@ToString
@TableName("sys_org_dimension_unit")
public class DimensionUnit extends DataEntity{

    @TableId
    private String dimensionUnitId;

    private String dimensionId;

    private String unitId;

    private String parentDimensionUnitId;

    private String aliasName;

    private String unitPath;

    private BigDecimal dimensionUnitLevel;

    private BigDecimal dimensionUnitOrder;

    private Date timeBegin;

    private Date timeEnd;

    private String isEnabled;

    @TableField(exist = false)
    private Unit unit;

    @TableField(exist = false)
    private Dimension dimension;

    /**
     * @Description: serialVersionUID
     * @Author: Liyiming
     * @Date: 2018/3/30
     */
    private static final long serialVersionUID = 1L;
}