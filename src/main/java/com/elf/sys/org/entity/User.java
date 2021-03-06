package com.elf.sys.org.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elf.core.persistence.DataEntity;
import lombok.*;

import java.util.Date;
import java.util.List;

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
public class User extends DataEntity {

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

    @TableField(exist = false)
    private List<Unit> unitList;

    public User() {
    }

    public User(String userAccount, String userPwd) {
        this.account = userAccount;
        this.password = userPwd;
    }

    /**
     * @Description: serialVersionUID
     * @Author:李一鸣(liyiming.neu@neusoft.com)
     * @Date:2017年11月15日
     */
    private static final long serialVersionUID = 1L;
}