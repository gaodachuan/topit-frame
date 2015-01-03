package com.topit.frame.core.util.entity;

import java.io.Serializable;

/** 
* @ClassName: DataDic 
* @Description: 页面下拉列表数据bean
* @author ivan.zhang 
* @date 2014年11月24日 下午4:33:13 
*  
*/ 
public class DataDic implements Serializable {

	private static final long serialVersionUID = 1305220165705511751L;
	
	/** 
	* @Fields id : 类型代码
	*/ 
	private int id;
	/** 
	* @Fields name :类型名称 
	*/ 
	private String name;

	public DataDic(int id, String name) {
		this.id = id;
		this.name = name;

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

}
