/**   
 * @Title: SysUser.java 
 * @Package com.topit.frame.core.entity.data 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author ivan.zhang
 * @date 2014年11月20日 下午12:14:22 
 * @version V1.0   
 */

package com.topit.frame.core.entity.data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.stereotype.Component;

/**
 * @ClassName: SysUser
 * @Description:系统用户表
 * @author doc.zhou
 * @date 2014年11月20日 下午12:14:22
 * 
 */
@Entity
@Table(name="sys_user")
public class SysUser implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * @Fields id : 主键id
	 */
	@Id
	private BigInteger id;
	/**
	 * @Fields loginName :用户代码
	 */
	@Column(name="LoginName")
	private String loginName;
	/**
	 * @Fields passWord :用户登录密码
	 */
	@Column(name="PassWord")
	private String passWord;
	/**
	 * @Fields realName :用户称呼
	 */
	@Column(name="RealName")
	private String realName;
	/**
	 * @Fields passwordAnswer : 密码安全的问题
	 */
	@Column(name="PasswordAnswer")
	private String passwordAnswer;
	/**
	 * @Fields email : TODO 用户Email地址
	 */
	@Column(name="Email")
	private String email;
	/**
	 * @Fields Remark : 用户描述信息
	 */
	@Column(name="Remark")
	private String remark;
	/**
	 * @Fields NeedChangePassword :下次登录的时候，必须修改密码。0：不需要修改；1：需要修改
	 */
	@Column(name="NeedChangePassword")
	private Integer needChangePassword;
	/**
	 * @Fields LastPasswordChangedTime : 最后一次设置密码的时间
	 */
	@Column(name="LastPasswordChangedTime")
	private Date lastPasswordChangedTime;
	/**
	 * @Fields loginTimes :用户登录次数
	 */
	@Column(name="LoginTimes")
	private Integer loginTimes;
	/**
	 * @Fields ActivitySessionGUID :当前活跃的会话GUID
	 */
	@Column(name="ActivitySessionGUID")
	private String activitySessionGUID;
	/**
	 * @Fields IsOnline : 是否在线
	 */
	@Column(name="IsOnline")
	private Integer isOnline;
	/**
	 * @Fields LastActivityTime : 上次活跃时间
	 */
	@Column(name="LastActivityTime")
	private Date lastActivityTime;
	/**
	 * @Fields lastLoginTime :最后一次登录时间
	 */
	@Column(name="LastLoginTime")
	private Date lastLoginTime;
	/**
	 * @Fields LastLogoutTime :最后一次正常退出系统时间。
	 */
	@Column(name="LastLogoutTime")
	private Date lastLogoutTime;

	/**
	 * @Fields lastRightEditTime : 最后一次权限修改时间戳
	 */
	@Column(name="LastRightEditTime")
	private Date lastRightEditTime;
	/** 
	* @Fields allowLoginWeekDay : TODO(用一句话描述这个变量表示什么) 
	*/ 
	@Column(name="AllowLoginWeekDay")
	private String allowLoginWeekDay;
	/**
	 * @Fields allowLoginTime1:每天允许登录的开始时间
	 */
	@Column(name="AllowLoginTime1")
	private Date allowLoginTime1;
	/**
	 * @Fields AllowLoginTime2:每天允许登录的截至时间
	 */
	@Column(name="AllowLoginTime2")
	private Date allowLoginTime2;
	/**
	 * @Fields failedPasswordAttemptCount:错误的用户密码登录尝试次数
	 */
	@Column(name="FailedLoginAttemptCount")
	private Integer failedLoginAttemptCount;
	/**
	 * @Fields failedPasswordAttemptTime:上一次由于密码错误而登录失败时间
	 */
	@Column(name="FailedLoginAttemptTime")
	private Date failedLoginAttemptTime;
	/**
	 * @Fields failedPasswordAnswerAttemptCount:尝试找回密码时错误答案次数
	 */
	@Column(name="FailedLoginAnswerAttemptCount")
	private Integer failedLoginAnswerAttemptCount;
	/**
	 * @Fields failedPasswordAnswerAttemptTime:上一次尝试找回密码的错误答案时间
	 */
	@Column(name="FailedLoginAnswerAttemptTime")
	private Date failedLoginAnswerAttemptTime;
	/**
	 * @Fields isSystemDefine:是否系统预定义用户
	 */
	@Column(name="IsSystemDefine")
	private Integer isSystemDefine;
	/**
	 * @Fields inactive:是否禁用
	 */
	@Column(name="Inactive")
	private Integer inactive;
	/**
	 * @Fields isDeleted:是否标志为已删除 0：否，1：是
	 */
	@Column(name="IsDeleted")
	private Integer isDeleted;
	/**
	 * @Fields creator:创建人
	 */
	@Column(name="Creator")
	private Integer creator;
	/**
	 * @Fields createTime:创建时间
	 */
	@Column(name="CreateTime")
	private Date createTime;
	/**
	 * @Fields lastEditor:最后修改人
	 */
	
	@Column(name="LastEditor")
	private Integer lastEditor;
	/**
	 * @Fields lastEditTime:最后修改时间
	 */
	@Column(name="LastEditTime")
	private Date lastEditTime;
	/**
	 * @Fields version:数据记录版本号
	 */
	@Column(name="Version")
	@Version
	private Integer version;
    /** 
    * @Fields GroupName :用户所属分组 
    */ 
    @Transient
    private String GroupName;
    @Transient
    private String GroupIds;

	/**  
	 * @Title:  getGroupIds <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public String getGroupIds() {
		return GroupIds;
	}

	/**  
	 * @Title:  setGroupIds <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public void setGroupIds(String groupIds) {
		GroupIds = groupIds;
	}

	/**  
	 * @Title:  getGroupName <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public String getGroupName() {
		return GroupName;
	}

	/**  
	 * @Title:  setGroupName <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public void setGroupName(String groupName) {
		GroupName = groupName;
	}

	/**  
	 * @Title:  getId <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	
	public BigInteger getId() {
		return id;
	}

	/**  
	 * @Title:  setId <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	
	public void setId(BigInteger id) {
		this.id = id;
	}

	/**  
	 * @Title:  getLoginName <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public String getLoginName() {
		return loginName;
	}

	/**  
	 * @Title:  setLoginName <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**  
	 * @Title:  getPassWord <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public String getPassWord() {
		return passWord;
	}

	/**  
	 * @Title:  setPassWord <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**  
	 * @Title:  getRealName <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public String getRealName() {
		return realName;
	}

	/**  
	 * @Title:  setRealName <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**  
	 * @Title:  getPasswordAnswer <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public String getPasswordAnswer() {
		return passwordAnswer;
	}

	/**  
	 * @Title:  setPasswordAnswer <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public void setPasswordAnswer(String passwordAnswer) {
		this.passwordAnswer = passwordAnswer;
	}

	/**  
	 * @Title:  getEmail <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public String getEmail() {
		return email;
	}

	/**  
	 * @Title:  setEmail <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public void setEmail(String email) {
		this.email = email;
	}

	/**  
	 * @Title:  getRemark <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public String getRemark() {
		return remark;
	}

	/**  
	 * @Title:  setRemark <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**  
	 * @Title:  getNeedChangePassword <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	
	public Integer getNeedChangePassword() {
		return needChangePassword;
	}

	/**  
	 * @Title:  setNeedChangePassword <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	
	public void setNeedChangePassword(Integer needChangePassword) {
		this.needChangePassword = needChangePassword;
	}

	/**  
	 * @Title:  getLastPasswordChangedTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	
	public Date getLastPasswordChangedTime() {
		return lastPasswordChangedTime;
	}

	/**  
	 * @Title:  setLastPasswordChangedTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	
	public void setLastPasswordChangedTime(Date lastPasswordChangedTime) {
		this.lastPasswordChangedTime = lastPasswordChangedTime;
	}

	/**  
	 * @Title:  getLoginTimes <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	
	public Integer getLoginTimes() {
		return loginTimes;
	}

	/**  
	 * @Title:  setLoginTimes <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	
	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	/**  
	 * @Title:  getActivitySessionGUID <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public String getActivitySessionGUID() {
		return activitySessionGUID;
	}

	/**  
	 * @Title:  setActivitySessionGUID <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public void setActivitySessionGUID(String activitySessionGUID) {
		this.activitySessionGUID = activitySessionGUID;
	}

	/**  
	 * @Title:  getIsOnline <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	
	public Integer getIsOnline() {
		return isOnline;
	}

	/**  
	 * @Title:  setIsOnline <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	
	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	/**  
	 * @Title:  getLastActivityTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	
	public Date getLastActivityTime() {
		return lastActivityTime;
	}

	/**  
	 * @Title:  setLastActivityTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	
	public void setLastActivityTime(Date lastActivityTime) {
		this.lastActivityTime = lastActivityTime;
	}

	/**  
	 * @Title:  getLastLoginTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**  
	 * @Title:  setLastLoginTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**  
	 * @Title:  getLastLogoutTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	
	public Date getLastLogoutTime() {
		return lastLogoutTime;
	}

	/**  
	 * @Title:  setLastLogoutTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	
	public void setLastLogoutTime(Date lastLogoutTime) {
		this.lastLogoutTime = lastLogoutTime;
	}

	/**  
	 * @Title:  getLastRightEditTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	
	public Date getLastRightEditTime() {
		return lastRightEditTime;
	}

	/**  
	 * @Title:  setLastRightEditTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	
	public void setLastRightEditTime(Date lastRightEditTime) {
		this.lastRightEditTime = lastRightEditTime;
	}

	/**  
	 * @Title:  getAllowLoginWeekDay <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public String getAllowLoginWeekDay() {
		return allowLoginWeekDay;
	}

	/**  
	 * @Title:  setAllowLoginWeekDay <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	
	public void setAllowLoginWeekDay(String allowLoginWeekDay) {
		this.allowLoginWeekDay = allowLoginWeekDay;
	}

	/**  
	 * @Title:  getAllowLoginTime1 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	
	public Date getAllowLoginTime1() {
		return allowLoginTime1;
	}

	/**  
	 * @Title:  setAllowLoginTime1 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	
	public void setAllowLoginTime1(Date allowLoginTime1) {
		this.allowLoginTime1 = allowLoginTime1;
	}

	/**  
	 * @Title:  getAllowLoginTime2 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	
	public Date getAllowLoginTime2() {
		return allowLoginTime2;
	}

	/**  
	 * @Title:  setAllowLoginTime2 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	
	public void setAllowLoginTime2(Date allowLoginTime2) {
		this.allowLoginTime2 = allowLoginTime2;
	}

	/**   
	 * @Title: getFailedLoginAttemptCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @return        
	 */
	public Integer getFailedLoginAttemptCount() {
		return failedLoginAttemptCount;
	}

	/**   
	 * @Title: setFailedLoginAttemptCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param failedLoginAttemptCount        
	 */
	public void setFailedLoginAttemptCount(Integer failedLoginAttemptCount) {
		this.failedLoginAttemptCount = failedLoginAttemptCount;
	}

	/**   
	 * @Title: getFailedLoginAttemptTime   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @return        
	 */
	public Date getFailedLoginAttemptTime() {
		return failedLoginAttemptTime;
	}

	/**   
	 * @Title: setFailedLoginAttemptTime   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param failedLoginAttemptTime        
	 */
	public void setFailedLoginAttemptTime(Date failedLoginAttemptTime) {
		this.failedLoginAttemptTime = failedLoginAttemptTime;
	}

	/**   
	 * @Title: getFailedLoginAnswerAttemptCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @return        
	 */
	public Integer getFailedLoginAnswerAttemptCount() {
		return failedLoginAnswerAttemptCount;
	}

	/**   
	 * @Title: setFailedLoginAnswerAttemptCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param failedLoginAnswerAttemptCount        
	 */
	public void setFailedLoginAnswerAttemptCount(
			Integer failedLoginAnswerAttemptCount) {
		this.failedLoginAnswerAttemptCount = failedLoginAnswerAttemptCount;
	}

	/**   
	 * @Title: getFailedLoginAnswerAttemptTime   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @return        
	 */
	public Date getFailedLoginAnswerAttemptTime() {
		return failedLoginAnswerAttemptTime;
	}

	/**   
	 * @Title: setFailedLoginAnswerAttemptTime   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param failedLoginAnswerAttemptTime        
	 */
	public void setFailedLoginAnswerAttemptTime(Date failedLoginAnswerAttemptTime) {
		this.failedLoginAnswerAttemptTime = failedLoginAnswerAttemptTime;
	}

	/**  
	 * @Title:  getIsSystemDefine <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	
	public Integer getIsSystemDefine() {
		return isSystemDefine;
	}

	/**  
	 * @Title:  setIsSystemDefine <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	
	public void setIsSystemDefine(Integer isSystemDefine) {
		this.isSystemDefine = isSystemDefine;
	}

	/**  
	 * @Title:  getInactive <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	
	public Integer getInactive() {
		return inactive;
	}

	/**  
	 * @Title:  setInactive <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	
	public void setInactive(Integer inactive) {
		this.inactive = inactive;
	}

	/**  
	 * @Title:  getIsDeleted <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	
	public Integer getIsDeleted() {
		return isDeleted;
	}

	/**  
	 * @Title:  setIsDeleted <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**  
	 * @Title:  getCreator <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	
	public Integer getCreator() {
		return creator;
	}

	/**  
	 * @Title:  setCreator <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	
	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	/**  
	 * @Title:  getCreateTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	
	public Date getCreateTime() {
		return createTime;
	}

	/**  
	 * @Title:  setCreateTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**  
	 * @Title:  getLastEditor <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	
	public Integer getLastEditor() {
		return lastEditor;
	}

	/**  
	 * @Title:  setLastEditor <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	
	public void setLastEditor(Integer lastEditor) {
		this.lastEditor = lastEditor;
	}

	/**  
	 * @Title:  getLastEditTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	
	public Date getLastEditTime() {
		return lastEditTime;
	}

	/**  
	 * @Title:  setLastEditTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	/**  
	 * @Title:  getVersion <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	
	public Integer getVersion() {
		return version;
	}

	/**  
	 * @Title:  setVersion <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**  
	 * @Title:  getSerialversionuid <BR>  
	 * @Description: please write your description <BR>  
	 * @return: long <BR>  
	 */
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (!(obj instanceof SysUser)) {
			return false;
		} else {
			SysUser sysUser = (SysUser) obj;
			if (null == this.getId() || null == sysUser.getId()) {
				return false;
			} else {
				return (this.getId() == (sysUser.getId()));
			}
		}
	}
	
	/**
	 * @Title: SysUser
	 * @Description:
	 */

	public SysUser(BigInteger id, int creator, String realName, String passWorld) {
		this.id = id;
		this.creator = creator;
		this.realName = realName;
		this.passWord = passWorld;
	}

	public SysUser() {

	}

	/**
	 * @Title: SysUser
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @param loginName
	 * @param passWord
	 * @param realName
	 * @param passwordAnswer
	 * @param email
	 * @param remark
	 * @param needChangePassword
	 * @param lastPasswordChangedTime
	 * @param loginTimes
	 * @param activitySessionGUID
	 * @param isOnline
	 * @param lastActivityTime
	 * @param lastLoginTime
	 * @param lastLogoutTime
	 * @param lastRightEditTime
	 * @param allowLoginWeekDay
	 * @param allowLoginTime1
	 * @param allowLoginTime2
	 * @param failedPasswordAttemptCount
	 * @param failedPasswordAttemptTime
	 * @param failedPasswordAnswerAttemptCount
	 * @param failedPasswordAnswerAttemptTime
	 * @param isSystemDefine
	 * @param inactive
	 * @param isDeleted
	 * @param creator
	 * @param createTime
	 * @param lastEditor
	 * @param lastEditTime
	 * @param version
	 */

	public SysUser(BigInteger id, String loginName, String passWord,
			String realName, String passwordAnswer, String email,
			String remark, int needChangePassword,
			Date lastPasswordChangedTime, int loginTimes,
			String activitySessionGUID, int isOnline, Date lastActivityTime,
			Date lastLoginTime, Date lastLogoutTime, Date lastRightEditTime,
			String allowLoginWeekDay, Date allowLoginTime1,
			Date allowLoginTime2, int failedPasswordAttemptCount,
			Date failedPasswordAttemptTime,
			int failedPasswordAnswerAttemptCount,
			Date failedPasswordAnswerAttemptTime, int isSystemDefine,
			int inactive, int isDeleted, int creator, Date createTime,
			int lastEditor, Date lastEditTime, int version) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.passWord = passWord;
		this.realName = realName;
		this.passwordAnswer = passwordAnswer;
		this.email = email;
		this.remark = remark;
		this.needChangePassword = needChangePassword;
		this.lastPasswordChangedTime = lastPasswordChangedTime;
		this.loginTimes = loginTimes;
		this.activitySessionGUID = activitySessionGUID;
		this.isOnline = isOnline;
		this.lastActivityTime = lastActivityTime;
		this.lastLoginTime = lastLoginTime;
		this.lastLogoutTime = lastLogoutTime;
		this.lastRightEditTime = lastRightEditTime;
		this.allowLoginWeekDay = allowLoginWeekDay;
		this.allowLoginTime1 = allowLoginTime1;
		this.allowLoginTime2 = allowLoginTime2;
		this.failedLoginAttemptCount = failedPasswordAttemptCount;
		this.failedLoginAttemptTime = failedPasswordAttemptTime;
		this.failedLoginAnswerAttemptCount = failedPasswordAnswerAttemptCount;
		this.failedLoginAnswerAttemptTime = failedPasswordAnswerAttemptTime;
		this.isSystemDefine = isSystemDefine;
		this.inactive = inactive;
		this.isDeleted = isDeleted;
		this.creator = creator;
		this.createTime = createTime;
		this.lastEditor = lastEditor;
		this.lastEditTime = lastEditTime;
		this.version = version;
	}

}
