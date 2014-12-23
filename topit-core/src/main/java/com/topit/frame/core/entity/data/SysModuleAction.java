 package com.topit.frame.core.entity.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
 /** 
* @ClassName: SysModuleAction 
* @Description: 系统模块操作 明细表 
* @author qiugui 
* @date 2014年12月3日 上午11:47:52 
*  
*/ 
@Entity
@Table(name="sys_module_action")
public class SysModuleAction implements Serializable{

	/** 
	* @Fields serialVersionUID : SysModuleAction序列化 
	*/ 
	
	private static final long serialVersionUID = 1L;

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
	* @Fields line : 排序行号 
	*/ 
	@Column(name="Line",length=11,nullable=false)
	private int line;
	
	/** 
	* @Fields name : 操作的名称，如：新增、修改、删除等 
	*/ 
	@Column(name="Name",length=128,nullable=false)
	private String name;
	
	/** 
	* @Fields description : 操作的介绍说明 
	*/ 
	@Column(name="Description",length=500,nullable=true)
	private String description;
	
	/** 
	* @Fields controllerClassName : 对应的控制器类全名称 
	*/ 
	@Column(name="ControllerClassName",length=500,nullable=true)
	private String controllerClassName;
	
	/** 
	* @Fields actionFunctionName : 对应的Action方法名称 
	*/ 
	@Column(name="ActionFunctionName",length=128,nullable=true)
	private String actionFunctionName;

	public SysModuleAction(){
		
	}
	
	
	
	public SysModuleAction(int moduleId, int actionId, int line, String name,
			String description, String controllerClassName,
			String actionFunctionName) {

		this.moduleId = moduleId;
		this.actionId = actionId;
		this.line = line;
		this.name = name;
		this.description = description;
		this.controllerClassName = controllerClassName;
		this.actionFunctionName = actionFunctionName;
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

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
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

	public String getControllerClassName() {
		return controllerClassName;
	}

	public void setControllerClassName(String controllerClassName) {
		this.controllerClassName = controllerClassName;
	}

	public String getActionFunctionName() {
		return actionFunctionName;
	}

	public void setActionFunctionName(String actionFunctionName) {
		this.actionFunctionName = actionFunctionName;
	}
}

 