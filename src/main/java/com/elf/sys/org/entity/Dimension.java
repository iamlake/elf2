package com.elf.sys.org.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elf.core.persistence.DataEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("sys_org_dimension")
public class Dimension extends DataEntity{

    @TableId
    private String id;

    private String name;

    private String type;

    private String description;

    private String isDefault;

    private BigDecimal sort;

    private Date timeBegin;

    private Date timeEnd;

    private String isEnabled;

    @Override
    public void preInsert() {

    }

    @Override
    public void preUpdate() {

    }
}