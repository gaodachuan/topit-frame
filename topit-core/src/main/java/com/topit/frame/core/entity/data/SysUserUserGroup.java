 package com.topit.frame.core.entity.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
 /** 
* @ClassName: SysUserUserGroup 
* @Description: 系统用户  与 系统用户组 关联表 
* @author qiugui 
* @date 2014年12月3日 上午11:45:10 
*  
*/ 
@Entity
@Table(name="sys_user_user_group")
public class SysUserUserGroup implements Serializable{

	/** 
	* @Fields serialVersionUID : SysUserUserGroup序列化 
	*/ 
	
	private static final long serialVersionUID = 1L;

	/** 
	* @Fields userId : 系统用户Id 
	*/ 
	@Id
	@Column(name="UserId",length=11,nullable=false)
	private int userId;
	
	/** 
	* @Fields groupId : 系统用户组Id 
	*/ 
	@Id
	@Column(name="GroupId",length=11,nullable=false)
	private int groupId;
	
	/** 
	* @Fields creator : 创建人 
	*/ 
	@Column(name="Creator",length=11,nullable=true)
	private Integer creator;
	
	/** 
	* @Fields createTime : 创建时间 
	*/ 
	@Column(name="CreateTime",nullable=false)
	private Date createTime;

	public SysUserUserGroup(){
		
	}
	
	
	
	public SysUserUserGroup(int userId, int groupId, int creator,
			Date createTime) {

		this.userId = userId;
		this.groupId = groupId;
		this.creator = creator;
		this.createTime = createTime;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

 