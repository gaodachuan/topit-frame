 package com.topit.frame.core.entity.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

 /** 
* @ClassName: SysUserErrorLoginLog 
* @Description: 系统用户登陆错误日志表 
* @author qiugui 
* @date 2014年11月24日 上午10:54:41 
*  
*/ 
 @Entity
 @Table(name="sys_user_error_login_log")
 public class SysUserErrorLoginLog implements Serializable{

	 /** 
	* @Fields serialVersionUID : SysUserErrorLoginLog的序列化 
	*/ 
	
	private static final long serialVersionUID = 1L;

	/** 
	* @Fields id : （主键）使用错误的用户名或密码登录的日志ID 
	*/ 
	@Id
	@Column(name="Id",length=11)
	private int id;
	 
	/** 
	* @Fields loginName : 用户登陆使用的用户名 
	*/ 
	@Column(name="LoginName",length=30) 
	private String loginName;
	 
	/** 
	* @Fields password : 登录密码
	*/ 
	@Column(name="Password",length=128)
	 private String password;
	 
	/** 
	* @Fields loginType : 登陆类型，1：Windows登录；2：Web登录 
	*/ 
	@Column(name="LoginType",length=11)
	 private int loginType;
	 
	/** 
	* @Fields loginIp : 登录时的客户端IP地址
	*/ 
	@Column(name="LoginIp",length=15)
	 private String loginIp;
	 
	/** 
	* @Fields hostName : 登录时的客户端主机名 
	*/ 
	@Column(name="HostName",length=50)
	 private String hostName;
	 
	/** 
	* @Fields loginTime : 登录时间 
	*/ 
	@Column(name="LoginTime")
	 private Date loginTime;

	public SysUserErrorLoginLog(){
		
	}
	
	/**   
	 * @Title:  SysUserErrorLoginLog   
	 * @Description:    带参数的构造函数   
	 * @param id
	 * @param loginName
	 * @param password
	 * @param loginType
	 * @param loginIp
	 * @param hostName
	 * @param loginTime   
	 */  
	 
	public SysUserErrorLoginLog(int id, String loginName, String password,
			int loginType, String loginIp, String hostName, Date loginTime) {
		this.id = id;
		this.loginName = loginName;
		this.password = password;
		this.loginType = loginType;
		this.loginIp = loginIp;
		this.hostName = hostName;
		this.loginTime = loginTime;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLoginType() {
		return loginType;
	}

	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}

 