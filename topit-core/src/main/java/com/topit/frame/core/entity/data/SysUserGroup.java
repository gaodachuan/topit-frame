 package com.topit.frame.core.entity.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

 /** 
* @ClassName: SysUserGroup 
* @Description: 系统用户组 表 
* @author qiugui 
* @date 2014年11月24日 下午1:52:33 
*  
*/ 
@Entity
 @Table(name="sys_user_group")
 public class SysUserGroup implements Serializable{

	/** 
	* @Fields serialVersionUID : SysUserGroup的序列化 
	*/ 
	
	private static final long serialVersionUID = 1L;

	/** 
	* @Fields id : (主键)用户角色ID 
	*/ 
	@Id
	@Column(name="Id",length=11,nullable=false)
	private int id;
	 
	/** 
	* @Fields name :用户角色名称 
	*/ 
	@Column(name="Name",length=50,nullable=false) 
	private String name;
	 
	/** 
	* @Fields description : 描述信息 
	*/ 
	@Column(name="Description",length=200,nullable=false)
	 private String description;
	 
	/** 
	* @Fields inactive : 是否禁用 
	*/ 
	@Column(name="Inactive",length=11,nullable=false,columnDefinition="INT default 1")
	 private int inactive;
	 
	/** 
	* @Fields lastRightEditTime : 最后一次权限修改时间戳 
	*/ 
	@Column(name="LastRightEditTime",nullable=true,columnDefinition="datetime default sysdate()")
	 private Date lastRightEditTime;
	 
	/** 
	* @Fields isSystemDefine : 是否系统预定义用户角色 
	*/ 
	@Column(name="IsSystemDefine",length=11,nullable=false,columnDefinition="INT default 1")
	 private int isSystemDefine;
	 
	/** 
	* @Fields creator : 创建人 
	*/ 
	@Column(name="Creator",length=11,nullable=false,columnDefinition="INT default 1")
	 private int creator;
	 
	/** 
	* @Fields createTime : 创建时间 
	*/ 
	@Column(name="CreateTime",nullable=true,columnDefinition="datetime default sysdate()")
	 private Date createTime;
	 
	/** 
	* @Fields lastEditor : 最后修改人 
	*/ 
	@Column(name="LastEditor",length=11,nullable=false,columnDefinition="INT default 1")
	 private int lastEditor;
	 
	/** 
	* @Fields lastEditTime : 最后修改时间 
	*/ 
	@Column(name="LastEditTime",nullable=true,columnDefinition="datetime default sysdate()")
	 private Date lastEditTime;
	 
	/** 
	* @Fields version : 数据记录版本号 
	*/ 
	@Version
	@Column(name="Version",length=11,nullable=false)
	 private int version;
	/** 
	* @Fields userGroup : 一个系统用户对应多个用户组 
	*/ 
    @Transient
	private SysUserUserGroup[]userGroup;
    /**  
	 * @Title:  getUserGroup <BR>  
	 * @Description: please write your description <BR>  
	 * @return: SysUserUserGroup[] <BR>  
	 */
	
	public SysUserUserGroup[] getUserGroup() {
		return userGroup;
	}



	/**  
	 * @Title:  setUserGroup <BR>  
	 * @Description: please write your description <BR>  
	 * @return: SysUserUserGroup[] <BR>  
	 */
	
	public void setUserGroup(SysUserUserGroup[] userGroup) {
		this.userGroup = userGroup;
	}


	public SysUserGroup (){
		
	}
	
	
	
	public SysUserGroup(int id, String name, String description, int inactive,
			Date lastRightEditTime, int isSystemDefine, int creator,
			Date createTime, int lastEditor, Date lastEditTime, int version) {

		this.id = id;
		this.name = name;
		this.description = description;
		this.inactive = inactive;
		this.lastRightEditTime = lastRightEditTime;
		this.isSystemDefine = isSystemDefine;
		this.creator = creator;
		this.createTime = createTime;
		this.lastEditor = lastEditor;
		this.lastEditTime = lastEditTime;
		this.version = version;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getInactive() {
		return inactive;
	}

	public void setInactive(int inactive) {
		this.inactive = inactive;
	}

	public Date getLastRightEditTime() {
		return lastRightEditTime;
	}

	public void setLastRightEditTime(Date lastRightEditTime) {
		this.lastRightEditTime = lastRightEditTime;
	}

	public int getIsSystemDefine() {
		return isSystemDefine;
	}

	public void setIsSystemDefine(int isSystemDefine) {
		this.isSystemDefine = isSystemDefine;
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

	public int getLastEditor() {
		return lastEditor;
	}

	public void setLastEditor(int lastEditor) {
		this.lastEditor = lastEditor;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}

 