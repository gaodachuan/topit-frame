package com.topit.frame.common.view.servlet;

import java.io.Serializable;
import java.util.List;

/** 
* @ClassName: ResultPageObject 
* @Description: 分页返回对象 
* @author ivan.zhang 
* @date 2014年11月25日 下午2:36:00 
*  
*/ 
public class ResultPageObject implements Serializable {

	private static final long serialVersionUID = 117602715514671762L;

	/** 
	* @Fields total : 页面中记录数
	*/ 
	private String total;
	/** 
	* @Fields rows : 返回页面查询对象
	*/ 
	private List<?> rows;

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
