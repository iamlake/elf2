package com.elf.sys.security.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elf.core.persistence.DataEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@TableName("SYS_SEC_ROLE")
public class Role extends DataEntity {
    @TableId
    private String roleId;

    private String roleName;

    private String roleType;

    private String parentRoleId;

    private String rolePath;

    private String roleLevel;

    private String timeBegin;

    private String timeEnd;

    private static final long serialVersionUID = 1L;
}