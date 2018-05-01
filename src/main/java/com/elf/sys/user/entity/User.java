package com.elf.sys.user.entity;//package com.elf.sys.user.entity;
//
//import com.baomidou.mybatisplus.annotations.TableId;
//import com.baomidou.mybatisplus.annotations.TableName;
//import com.elf.core.common.utils.StringUtils;
//import com.elf.core.persistence.DataEntity;
//import lombok.Data;
//
//import java.util.Date;
//
///**
// * @Title: User
// * @Description: 用户
// * @Author:李一鸣(liyiming.neu@neusoft.com)
// * @Date:2017年12月6日
// */
//@Data
//@TableName("SYS_ORG_USER")
//public class User extends DataEntity {
//
//    @TableId
//    private String userId;
//
//    private String account;
//
//    private String password;
//
//    private String fullname;
//
//    private String userType;
//
//    private Date pwdChangedTime;
//
//    private String isLocked;
//
//    private String lockedReason;
//
//    private String userHead;
//
//    private String sex;
//
//    private String birthdate;
//
//    private String mobileTelephone;
//
//    private String email;
//
//    private String credentialsType;
//
//    private String credentialsNumber;
//
//    private String homeAddress;
//
//    public User() {
//    }
//
//    public User(String userAccount, String userPwd) {
//        this.account = userAccount;
//        this.password = userPwd;
//    }
//
//    @Override
//    public void preInsert() {
//        this.setUserId(StringUtils.getUUID());
//        this.setCreationTime(new Date());
//    }
//
//    @Override
//    public void preUpdate() {
//        this.setModificationTime(new Date());
//    }
//
//    /**
//     * @Description: serialVersionUID
//     * @Author:李一鸣(liyiming.neu@neusoft.com)
//     * @Date:2017年11月15日
//     */
//    private static final long serialVersionUID = 1L;
//
//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//
//	public String getAccount() {
//		return account;
//	}
//
//	public void setAccount(String account) {
//		this.account = account;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getFullname() {
//		return fullname;
//	}
//
//	public void setFullname(String fullname) {
//		this.fullname = fullname;
//	}
//
//	public String getUserType() {
//		return userType;
//	}
//
//	public void setUserType(String userType) {
//		this.userType = userType;
//	}
//
//	public Date getPwdChangedTime() {
//		return pwdChangedTime;
//	}
//
//	public void setPwdChangedTime(Date pwdChangedTime) {
//		this.pwdChangedTime = pwdChangedTime;
//	}
//
//	public String getIsLocked() {
//		return isLocked;
//	}
//
//	public void setIsLocked(String isLocked) {
//		this.isLocked = isLocked;
//	}
//
//	public String getLockedReason() {
//		return lockedReason;
//	}
//
//	public void setLockedReason(String lockedReason) {
//		this.lockedReason = lockedReason;
//	}
//
//	public String getUserHead() {
//		return userHead;
//	}
//
//	public void setUserHead(String userHead) {
//		this.userHead = userHead;
//	}
//
//	public String getSex() {
//		return sex;
//	}
//
//	public void setSex(String sex) {
//		this.sex = sex;
//	}
//
//	public String getBirthdate() {
//		return birthdate;
//	}
//
//	public void setBirthdate(String birthdate) {
//		this.birthdate = birthdate;
//	}
//
//	public String getMobileTelephone() {
//		return mobileTelephone;
//	}
//
//	public void setMobileTelephone(String mobileTelephone) {
//		this.mobileTelephone = mobileTelephone;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getCredentialsType() {
//		return credentialsType;
//	}
//
//	public void setCredentialsType(String credentialsType) {
//		this.credentialsType = credentialsType;
//	}
//
//	public String getCredentialsNumber() {
//		return credentialsNumber;
//	}
//
//	public void setCredentialsNumber(String credentialsNumber) {
//		this.credentialsNumber = credentialsNumber;
//	}
//
//	public String getHomeAddress() {
//		return homeAddress;
//	}
//
//	public void setHomeAddress(String homeAddress) {
//		this.homeAddress = homeAddress;
//	}
//}