package com.topit.frame.core.entity.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @ClassName: SysOption
 * @Description: 系统选项表
 * @author qiugui
 * @date 2014年11月20日 上午11:51:16
 * 
 */

@Entity
@Table(name = "sys_option")
public class SysOption implements Serializable {

	/**
	 * @Fields serialVersionUID : SysOption的序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @Fields categoryId : （主键）选项分类的id
	 */
	@Id
	@Column(name = "CategoryId", length = 11)
	private int categoryId;

	/**
	 * @Fields optionKey : 选项的key值
	 */
	@Id
	@Column(name = "OptionKey", length = 50)
	private String optionKey;

	/**
	 * @Fields line : 选项所在行号
	 */
	@Column(name = "Line", length = 11)
	private int line;

	/**
	 * @Fields optionName : 选项名称
	 */
	@Column(name = "OptionName", length = 50)
	private String optionName;

	/**
	 * @Fields optionValue : 选项值
	 */
	@Column(name = "OptionValue", length = 128)
	private String optionValue;

	/**
	 * @Fields dataType : 选项值的数据类型
	 */
	@Column(name = "DataType", length = 20)
	private String dataType;

	/**
	 * @Fields sortCategoryId : 是否有可供选择的选项值集合
	 */
	@Column(name = "SortTypeId", length = 11)
	private int sortTypeId;

	/**
	 * @Fields description : 选项的说明
	 */
	@Column(name = "Description", length = 128)
	private String description;

	/**
	 * @Fields creator : 创建人
	 */
	@Column(name = "Creator", length = 11)
	private int creator;

	/**
	 * @Fields createTime : 创建时间
	 */
	@Column(name = "CreateTime")
	private Date createTime;

	/**
	 * @Fields lastEditor : 最后一次修改人
	 */
	@Column(name = "LastEditor", length = 11)
	private int lastEditor;

	/**
	 * @Fields lastEditTime : 最后一次修改时间
	 */
	@Column(name = "LastEditTime")
	private Date lastEditTime;

	/**
	 * @Fields version : 数据记录版本号
	 */
	@Version
	@Column(name = "Version", length = 11)
	private int version;

	public SysOption(int categoryId, String optionKey, int line,
			String optionName, String optionValue, String dataType,
			int sortTypeId, String description, int creator, Date createTime,
			int lastEditor, Date lastEditTime, int version) {
		super();
		this.categoryId = categoryId;
		this.optionKey = optionKey;
		this.line = line;
		this.optionName = optionName;
		this.optionValue = optionValue;
		this.dataType = dataType;
		this.sortTypeId = sortTypeId;
		this.description = description;
		this.creator = creator;
		this.createTime = createTime;
		this.lastEditor = lastEditor;
		this.lastEditTime = lastEditTime;
		this.version = version;
	}

	public SysOption(int categoryId, String optionKey, String optionValue) {
		super();
		this.categoryId = categoryId;
		this.optionKey = optionKey;
		this.optionValue = optionValue;
	}

	public SysOption() {
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getOptionKey() {
		return optionKey;
	}

	public void setOptionKey(String optionKey) {
		this.optionKey = optionKey;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getSortTypeId() {
		return sortTypeId;
	}

	public void setSortTypeId(int sortTypeId) {
		this.sortTypeId = sortTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getLastEditor() {
		return lastEditor;
	}

	public void setLastEditor(int lastEditor) {
		this.lastEditor = lastEditor;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
