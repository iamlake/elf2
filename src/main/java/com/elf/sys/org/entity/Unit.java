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
@TableName("sys_org_unit")
public class Unit extends DataEntity {

    @TableId
    private String unitId;

    private String unitName;

    private String description;

    private String unitCode;

    private String unitType;

    private String timeBegin;

    private String timeEnd;

    private String isEnabled;

    /**
     * @Description: serialVersionUID
     * @Author: Liyiming
     * @Date: 2018/3/30
     */
    private static final long serialVersionUID = 1L;
}