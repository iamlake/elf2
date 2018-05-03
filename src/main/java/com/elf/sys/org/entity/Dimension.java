package com.elf.sys.org.entity;

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
@TableName("sys_org_dimension")
public class Dimension extends DataEntity {

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

    /**
     * @Description: serialVersionUID
     * @Author: Liyiming
     * @Date: 2018/3/30
     */
    private static final long serialVersionUID = 1L;
}