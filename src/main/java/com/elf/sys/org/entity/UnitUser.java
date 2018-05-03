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
@TableName("sys_org_unit_user")
public class UnitUser extends DataEntity {

    @TableId
    private String id;

    private String userId;

    private String unitId;

    private String isLeader;

    private String description;

    /**
     * @Description: serialVersionUID
     * @Author: Liyiming
     * @Date: 2018/3/30
     */
    private static final long serialVersionUID = 1L;
}