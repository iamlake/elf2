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
@TableName("SYS_SEC_RESOURCE_AUTHORITY")
public class ResourceAuthority extends DataEntity {
    @TableId
    private String authorityId;

    private String roleId;

    private String resourceId;

    private String resourceType;

    private String authorityType;

    private String constraints;

    private String description;

    private static final long serialVersionUID = 1L;
}