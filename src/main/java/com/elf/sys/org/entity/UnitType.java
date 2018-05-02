package com.elf.sys.org.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elf.core.persistence.DataEntity;
import lombok.Data;

@Data
@TableName("sys_org_unit_type")
public class UnitType extends DataEntity {
    @TableId
    private String id;

    private String name;

    private String code;

    private String description;

    private String parentId;

    private String isLeaf;

    @Override
    public void preInsert() {

    }

    @Override
    public void preUpdate() {

    }
}