package com.elf.sys.user.entity;

import com.elf.core.common.utils.StringUtils;
import com.elf.core.persistence.DataEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <br>Title: User
 * <br>Description: 用户
 * <br>Author:李一鸣(liyiming.neu@neusoft.com)
 * <br>Date:2017年12月6日
 */
@Setter
@Getter
@ToString
@Table(name = "SYS_ORG_USER")
public class User extends DataEntity<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * <br>Description: serialVersionUID
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年11月15日
     */
    private static final long serialVersionUID = 1L;
}