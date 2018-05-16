package com.elf.sys.org.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elf.core.persistence.DataEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@TableName("sys_org_dimension")
public class Dimension extends DataEntity {
    @TableId
    private String dimensionId;

    private String dimensionName;

    private String dimensionType;

    private String description;

    private String isDefault;

    private BigDecimal dimensionOrder;

    /**
     * @Description: serialVersionUID
     * @Author: Liyiming
     * @Date: 2018/3/30
     */
    private static final long serialVersionUID = 1L;
}