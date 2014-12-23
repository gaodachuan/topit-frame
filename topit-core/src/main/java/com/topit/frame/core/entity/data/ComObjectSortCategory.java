package com.topit.frame.core.entity.data;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @ClassName: ComObjectSortCategory
 * @Description: 分类类型下的分类
 * @author ivan.zhang
 * @date 2014年11月24日 下午3:45:27
 * 
 */
@Entity
@Table(name = "com_object_sort_category")
public class ComObjectSortCategory implements Serializable {
	private static final long serialVersionUID = -4281512051758059960L;

	/**
	 * @Fields id :编号主键
	 */
	@Id
	@Column(name = "Id")
	private int id;
	/**
	 * @Fields sortXtypeId : 分类类型编号
	 */
	@Column(name = "sortXtypeId")
	private int sortXtypeId;
	/**
	 * @Fields itemId : 分类编号
	 */
	@Column(name = "ItemId")
	private int itemId;
	/**
	 * @Fields categoryCode : 分类代码
	 */
	@Column(name = "CategoryCode")
	private String categoryCode;
	/**
	 * @Fields categoryName : 分类名称
	 */
	@Column(name = "CategoryName")
	private String categoryName;
	/**
	 * @Fields categoryPinyin : 分类拼音
	 */
	@Column(name = "CategoryPinyin")
	private String categoryPinyin;
	/**
	 * @Fields parentLid : 父类编号
	 */
	@Column(name = "ParentLid")
	private int parentLid;
	/**
	 * @Fields line : 排序行号
	 */
	@Column(name = "Line")
	private int line;
	/**
	 * @Fields levelNumber : 层次编号，顶层为1，以此类推
	 */
	@Column(name = "LevelNumber")
	private int levelNumber;
	/**
	 * @Fields webForeColor 前景色
	 */
	@Column(name = "WebForeColor")
	private String webForeColor;
	/**
	 * @Fields webBackColor :背景色
	 */
	@Column(name = "WebBackColor")
	private String webBackColor;
	/**
	 * @Fields otherOption : 二进制位置属性，扩展使用
	 */
	@Column(name = "OtherOption")
	private int otherOption;
	/**
	 * @Fields remark :备注说明
	 */
	@Column(name = "Remark")
	private String remark;
	/**
	 * @Fields inactive : 是否禁用
	 */
	@Column(name = "Inactive")
	private int inactive;
	/**
	 * @Fields isSystemDefine : 是否系统预定义分类
	 */
	@Column(name = "IsSystemDefine")
	private int isSystemDefine;
	/**
	 * @Fields creator : 创建人
	 */
	@Column(name = "Creator")
	private int creator;
	/**
	 * @Fields createTime : 创建时间
	 */
	@Column(name = "CreateTime")
	private Timestamp createTime;
	/**
	 * @Fields lastEditor : 最后修改人
	 */
	@Column(name = "LastEditor")
	private int lastEditor;
	/**
	 * @Fields lastEditTime : 修改时间
	 */
	@Column(name = "LastEditTime")
	private Timestamp lastEditTime;
	/**
	 * @Fields version : 版本控制
	 */
	@Version
	@Column(name = "Version")
	private int version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
