package com.elf.sys.org.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elf.core.persistence.DataEntity;
import lombok.Data;

@Data
@TableName("sys_org_unit_user")
public class UnitUser extends DataEntity {

    @TableId
    private String id;

    private String userId;

    private String unitId;

    private String isLeader;

    private String description;

    @Override
    public void preInsert() {

    }

    @Override
    public void preUpdate() {

    }
}