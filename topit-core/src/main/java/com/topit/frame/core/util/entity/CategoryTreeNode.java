package com.topit.frame.core.util.entity;

import java.sql.Timestamp;


public class CategoryTreeNode extends Node {

	private int tabId;

	private int sortXtypeId;

	private int itemId;

	private String categoryCode;

	private String categoryName;

	private String categoryPinyin;

	private int parentLid;

	private int line;

	private int levelNumber;

	private String webForeColor;

	private String webBackColor;

	private int otherOption;

	private String remark;

	private int inactive;

	private int isSystemDefine;

	private int creator;

	private Timestamp createTime;

	private int lastEditor;

	private Timestamp lastEditTime;

	private int version;

	public int getTabId() {
		return tabId;
	}

	public void setTabId(int tabId) {
		this.tabId = tabId;
	}

	public int getSortXtypeId() {
		return sortXtypeId;
	}

	public void setSortXtypeId(int sortXtypeId) {
		this.sortXtypeId = sortXtypeId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryPinyin() {
		return categoryPinyin;
	}

	public void setCategoryPinyin(String categoryPinyin) {
		this.categoryPinyin = categoryPinyin;
	}

	public int getParentLid() {
		return parentLid;
	}

	public void setParentLid(int parentLid) {
		this.parentLid = parentLid;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}

	public String getWebForeColor() {
		return webForeColor;
	}

	public void setWebForeColor(String webForeColor) {
		this.webForeColor = webForeColor;
	}

	public String getWebBackColor() {
		return webBackColor;
	}

	public void setWebBackColor(String webBackColor) {
		this.webBackColor = webBackColor;
	}

	public int getOtherOption() {
		return otherOption;
	}

	public void setOtherOption(int otherOption) {
		this.otherOption = otherOption;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getInactive() {
		return inactive;
	}

	public void setInactive(int inactive) {
		this.inactive = inactive;
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getLastEditor() {
		return lastEditor;
	}

	public void setLastEditor(int lastEditor) {
		this.lastEditor = lastEditor;
	}

	public Timestamp getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Timestamp lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
	
}
