package com.elf.sys.user.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elf.core.common.utils.StringUtils;
import com.elf.core.persistence.DataEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Title: User
 * @Description: 用户
 * @Author:李一鸣(liyiming.neu@neusoft.com)
 * @Date:2017年12月6日
 */
@Setter
@Getter
@ToString
@TableName("SYS_ORG_USER")
public class User extends DataEntity<User> {

    @TableId
    private String userId;

    private String account;

    private String password;

    private String fullname;

    private String userType;

    private Date pwdChangedTime;

    private String isLocked;

    private String lockedReason;

    private String userHead;

    private String sex;

    private String birthdate;

    private String mobileTelephone;

    private String email;

    private String credentialsType;

    private String credentialsNumber;

    private String homeAddress;

    public User() {
    }

    public User(String userAccount, String userPwd) {
        this.account = userAccount;
        this.password = userPwd;
    }

    @Override
    public void preInsert() {
        this.setUserId(StringUtils.getUUID());
        this.setCreationTime(new Date());
    }

    @Override
    public void preUpdate() {
        this.setModificationTime(new Date());
    }

    /**
     * @Description: serialVersionUID
     * @Author:李一鸣(liyiming.neu@neusoft.com)
     * @Date:2017年11月15日
     */
    private static final long serialVersionUID = 1L;
}