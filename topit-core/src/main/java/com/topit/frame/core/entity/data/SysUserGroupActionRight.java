 package com.topit.frame.core.entity.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
 /** 
* @ClassName: SysUserGroupModuleActionRight 
* @Description: 系统用户组模块操作 明细表 
* @author qiugui 
* @date 2014年12月3日 上午11:46:32 
*  
*/ 
@Entity
@Table(name="sys_user_group_action_right")
public class SysUserGroupActionRight implements Serializable {

	/** 
	* @Fields serialVersionUID : SysUserGroupModuleActionRight序列化 
	*/ 
	
	private static final long serialVersionUID = 1L;

	/** 
	* @Fields groupId : 系统用户组Id 
	*/ 
	@Id
	@Column(name="GroupId",length=11,nullable=false)
	private int groupId;

	/** 
	* @Fields moduleId : 模块Id 
	*/ 
	@Id
	@Column(name="ModuleId",length=11,nullable=false)
	private int moduleId;
	
	/** 
	* @Fields actionId : 模块对应下的操作权限编号，如：操作编号”1“表示新增操作 
	*/ 
	@Id
	@Column(name="ActionId",length=11,nullable=false)	
	private int actionId;
	
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

	public SysUserGroupActionRight(){
		
	}
	
	public SysUserGroupActionRight(int groupId, int moduleId,
			int actionId, int creator, Date createTime) {

		this.groupId = groupId;
		this.moduleId = moduleId;
		this.actionId = actionId;
		this.creator = creator;
		this.createTime = createTime;
	}



	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
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

 