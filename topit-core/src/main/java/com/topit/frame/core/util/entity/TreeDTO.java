package com.topit.frame.core.util.entity;

import java.util.Map;
import java.util.Set;

/**
 * 
 * @ClassName: TreeDTO
 * @Description: 页面的树形列表的传输结构
 * @author gaodachuan
 * @date 2014年11月25日 下午4:05:18
 */
public class TreeDTO {
	private int id;// 节点的 id，它对于加载远程数据很重要。
	private String text;// 显示的节点文字。
	private String state;// 节点状态， 'open' 或 'closed'，
	private int checked;// 指示节点是否被选中。
	private Map<String, Object> attributes;// 给一个节点追加的自定义属性。

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public Set<TreeDTO> getChildren() {
		return children;
	}

	public void setChildren(Set<TreeDTO> children) {
		this.children = children;
	}

	private Set<TreeDTO> children;// 定义了一些子节点的节点数组。
}
