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
@TableName("SYS_SEC_PERMISSION")
public class Permission extends DataEntity {
    @TableId
    private String permissionId;

    private String permissionName;

    private String permissionSign;

    private String permissionType;

    private static final long serialVersionUID = 1L;
}