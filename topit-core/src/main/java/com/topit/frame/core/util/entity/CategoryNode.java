package com.topit.frame.core.util.entity;

/**
 * @ClassName: CategoryNode
 * @Description: 类型树对象
 * @author ivan.zhang
 * @date 2014年12月5日 下午2:16:21
 * 
 */
public class CategoryNode extends Node {

	private int tabId;
	private int line;
	private String tablename;


	public int getTabId() {
		return tabId;
	}

	public void setTabId(int tabId) {
		this.tabId = tabId;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

}
