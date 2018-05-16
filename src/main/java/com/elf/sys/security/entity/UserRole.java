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
@TableName("SYS_SEC_ROLE_USER")
public class UserRole extends DataEntity {
    @TableId
    private String roleUserId;

    private String userId;

    private String roleId;

    private static final long serialVersionUID = 1L;
}