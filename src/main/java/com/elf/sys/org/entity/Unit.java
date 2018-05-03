package com.elf.sys.org.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elf.core.persistence.DataEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
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

    /**
     * @Description: serialVersionUID
     * @Author: Liyiming
     * @Date: 2018/3/30
     */
    private static final long serialVersionUID = 1L;
}