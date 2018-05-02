package com.elf.sys.org.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elf.core.persistence.DataEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_org_unit")
public class Unit extends DataEntity {

    @TableId
    private String id;

    private String name;

    private String description;

    private String code;

    private String typeId;

    private Date timeBegin;

    private Date timeEnd;

    private String isEnabled;

    @TableField(exist = false)
    private UnitType unitType;

    @Override
    public void preInsert() {

    }

    @Override
    public void preUpdate() {

    }
}