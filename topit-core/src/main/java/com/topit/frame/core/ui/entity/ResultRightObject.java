package com.topit.frame.core.ui.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.common.view.servlet.ResultPageObject;
import com.topit.frame.core.entity.data.SysModuleAction;

/** 
* @ClassName: ResultRightObject 
* @Description: 页面返回对象 
* @author ivan.zhang 
* @date 2014年12月11日 下午3:31:14 
*  
*/ 
public class ResultRightObject implements Serializable {
	private static final long serialVersionUID = 117602715514671762L;
	
	/** 
	* @Fields resultObject : 页面返回结果对象 
	*/ 
	private ResultObject  resultObject;
	
	/** 
	* @Fields listAction : 模块权限 
	*/ 
	private List<SysModuleAction> listAction=new ArrayList<SysModuleAction>();
	/** 
	* @Fields resultPageObject : 页面返回数据
	*/ 
	private ResultPageObject resultPageObject;

	public List<SysModuleAction> getListAction() {
		return listAction;
	}

	public void setListAction(List<SysModuleAction> listAction) {
		this.listAction = listAction;
	}

	public ResultPageObject getResultPageObject() {
		return resultPageObject;
	}

	public void setResultPageObject(ResultPageObject resultPageObject) {
		this.resultPageObject = resultPageObject;
	}
	
	public ResultObject getResultObject() {
		return resultObject;
	}

	public void setResultObject(ResultObject resultObject) {
		this.resultObject = resultObject;
	}


}
