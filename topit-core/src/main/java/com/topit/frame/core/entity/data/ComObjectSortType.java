package com.topit.frame.core.entity.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
* @ClassName: ComObjectSortType 
* @Description: 分类体系分类类型表 
* @author ivan.zhang 
* @date 2014年12月4日 下午2:41:59 
*  
*/ 
@Entity
@Table(name = "com_object_sort_type")
public class ComObjectSortType implements Serializable {

	private static final long serialVersionUID = -1881981384272813393L;

	/**
	 * @Fields id :分类类型ID
	 */
	@Id
	@Column(name = "Id")
	private int id;
	/**
	 * @Fields name : 分类类型名称
	 */
	@Column(name = "name")
	private String name;
	/**
	 * @Fields line : 排序行号
	 */
	@Column(name = "line")
	private int line;
	/**
	 * @Fields categoryId : 分类体系的分类编码
	 */
	@Column(name = "categoryId")
	private int categoryId;
	/**
	 * @Fields description :描述信息
	 */
	@Column(name = "description")
	private String description;
	/**
	 * @Fields assemblePath : 删除数据的时候，调用的程序名称（或者完整路径）
	 */
	@Column(name = "assemblePath")
	private String assemblePath;
	/**
	 * @Fields className : 删除是校验的接口实现名称。
	 */
	@Column(name = "className")
	private String className;
	/**
	 * @Fields maxLevelNumber : 允许最大的层次数，如果唯恐表示无穷大
	 */
	@Column(name = "maxLevelNumber")
	private int maxLevelNumber;
	/**
	 * @Fields selLevelNumber : 最少选定的层次编号 ，如果为空表示无限制
	 */
	@Column(name = "selLevelNumber")
	private int selLevelNumber;
	/**
	 * @Fields isTreeStruct : 分类体系是否为树形结构
	 */
	@Column(name = "isTreeStruct")
	private int isTreeStruct;
	/**
	 * @Fields currentId :以使用的主键
	 */
	@Column(name = "currentId")
	private int currentId;
	/**
	 * @Fields inCrementSize : 增长数
	 */
	@Column(name = "inCrementSize")
	private int inCrementSize;
	/**
	 * @Fields idType : 1.整型连续主键，2.二进制主键
	 */
	@Column(name = "idType")
	private int idType;
	/**
	 * @Fields startId : 用户自定义分类时的起始编号， 以配合一些类型由系统预先定义并编号。
	 */
	@Column(name = "startId")
	private int startId;
	/**
	 * @Fields visible : 用户是否可见
	 */
	@Column(name = "visible")
	private int visible;
	/**
	 * @Fields editable : 是否允许用户编辑
	 */
	@Column(name = "editable")
	private int editable;

	/**
	 * @Fields codeLengthRule :存放编码规则长度的字符串
	 */
	@Column(name = "codeLengthRule")
	private String codeLengthRule;
	/**
	 * @Fields codeLevelChar : 体现代码层次的分隔符
	 */
	@Column(name = "codeLevelChar")
	private String codeLevelChar;
	/**
	 * @Fields isAutoCode : 是否自动生成代码
	 */
	@Column(name = "isAutoCode")
	private int isAutoCode;
	/**
	 * @Fields isSystemDefine :是否系统预定义分类
	 */
	@Column(name = "isSystemDefine")
	private int isSystemDefine;
	/**
	 * @Fields cacheTime : 时间戳，用于配合缓存分类数据的机制使用。
	 */
	@Column(name = "cacheTime")
	private Date cacheTime;
	/**
	 * @Fields creator : 创建人ID
	 */
	@Column(name = "creator")
	private int creator;
	/**
	 * @Fields createTime : 创建时间
	 */
	@Column(name = "createTime")
	private Date createTime;
	/**
	 * @Fields lastEditor : 最后修改人ID
	 */
	@Column(name = "lastEditor")
	private int lastEditor;
	/**
	 * @Fields lastEditTime :最后修改时间
	 */
	@Column(name = "lastEditTime")
	private Date lastEditTime;
	/**
	 * @Fields version :数据库记录版本号
	 */
	@Column(name = "version")
	private int version;

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

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssemblePath() {
		return assemblePath;
	}

	public void setAssemblePath(String assemblePath) {
		this.assemblePath = assemblePath;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getMaxLevelNumber() {
		return maxLevelNumber;
	}

	public void setMaxLevelNumber(int maxLevelNumber) {
		this.maxLevelNumber = maxLevelNumber;
	}

	public int getSelLevelNumber() {
		return selLevelNumber;
	}

	public void setSelLevelNumber(int selLevelNumber) {
		this.selLevelNumber = selLevelNumber;
	}

	public int getIsTreeStruct() {
		return isTreeStruct;
	}

	public void setIsTreeStruct(int isTreeStruct) {
		this.isTreeStruct = isTreeStruct;
	}

	public int getCurrentId() {
		return currentId;
	}

	public void setCurrentId(int currentId) {
		this.currentId = currentId;
	}

	public int getInCrementSize() {
		return inCrementSize;
	}

	public void setInCrementSize(int inCrementSize) {
		this.inCrementSize = inCrementSize;
	}

	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public int getStartId() {
		return startId;
	}

	public void setStartId(int startId) {
		this.startId = startId;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public String getCodeLengthRule() {
		return codeLengthRule;
	}

	public void setCodeLengthRule(String codeLengthRule) {
		this.codeLengthRule = codeLengthRule;
	}

	public String getCodeLevelChar() {
		return codeLevelChar;
	}

	public void setCodeLevelChar(String codeLevelChar) {
		this.codeLevelChar = codeLevelChar;
	}

	public int getIsAutoCode() {
		return isAutoCode;
	}

	public void setIsAutoCode(int isAutoCode) {
		this.isAutoCode = isAutoCode;
	}

	public int getIsSystemDefine() {
		return isSystemDefine;
	}

	public void setIsSystemDefine(int isSystemDefine) {
		this.isSystemDefine = isSystemDefine;
	}

	public Date getCacheTime() {
		return cacheTime;
	}

	public void setCacheTime(Date cacheTime) {
		this.cacheTime = cacheTime;
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

	public int getEditable() {
		return editable;
	}

	public void setEditable(int editable) {
		this.editable = editable;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
