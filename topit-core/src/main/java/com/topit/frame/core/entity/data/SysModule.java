package com.topit.frame.core.entity.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: SysModules
 * @Description: 模块基础信息表
 * @author ivan.zhang
 * @date 2014年11月13日 下午2:21:44
 * 
 */
@Entity
@Table(name = "SYS_MODULE")
public class SysModule implements Serializable {
	private static final long serialVersionUID = 8145151933526596601L;

	/**
	 * @Fields id : 模块编号
	 */
	@Id
	@Column(name = "Id")
	private int id;
	/**
	 * @Fields name : 模块名称
	 */
	@Column(name = "Name")
	private String name;
	/**
	 * @Fields categoryId : 模块分类编号
	 */
	@Column(name = "CategoryId")
	private int categoryId;
	/**
	 * @Fields systemId : 模块所属子系统编号
	 */
	@Column(name = "SystemId")
	private int systemId;
	/**
	 * @Fields description :模块功能说明
	 */
	@Column(name = "Description")
	private String description;
	/**
	 * @Fields modulePath : 模块所在的程序路径
	 */
	@Column(name = "ModulePath")
	private String modulePath;
	/**
	 * @Fields className : 模块对应的类全名
	 */
	@Column(name = "ClassName")
	private String className;
	/**
	 * @Fields functionName : 模块的入口函数
	 */
	@Column(name = "FunctionName")
	private String functionName;
	/**
	 * @Fields interfaceName : 模块实现的接口全名
	 */
	@Column(name = "InterfaceName")
	private String interfaceName;
	/**
	 * @Fields createTime : 模块创建时间
	 */
	@Column(name = "CreateTime")
	private Date createTime;
	/**
	 * @Fields developer : 模块实现的第一作者
	 */
	@Column(name = "Developer")
	private String developer;

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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModulePath() {
		return modulePath;
	}

	public void setModulePath(String modulePath) {
		this.modulePath = modulePath;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

}
