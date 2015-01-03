package com.topit.frame.core.util.entity;

import java.util.List;

public class Node {

	protected int id;
	protected String nodeCode;
	protected String text;
	protected int nodeParent;
	protected Object obj;
	protected List<Node> children;
	protected String iconCls;

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public int getNodeParent() {
		return nodeParent;
	}

	public void setNodeParent(int nodeParent) {
		this.nodeParent = nodeParent;
	}

}
