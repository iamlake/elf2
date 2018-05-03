package com.elf.sys.org.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elf.core.persistence.DataEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@TableName("sys_org_unit_type")
public class UnitType extends DataEntity {
    @TableId
    private String id;

    private String name;

    private String code;

    private String description;

    private String parentId;

    private String isLeaf;

    /**
     * @Description: serialVersionUID
     * @Author: Liyiming
     * @Date: 2018/3/30
     */
    private static final long serialVersionUID = 1L;
}