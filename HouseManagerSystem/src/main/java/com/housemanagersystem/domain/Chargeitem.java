package com.housemanagersystem.domain;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.Id;import javax.persistence.Table;/******************************************************************************* * javaBeans chargeitem --> Chargeitem * <table explanation> *  * @author 2014-12-14 15:59:27 *  */@Entity@Table(name = "chargeitem")public class Chargeitem implements java.io.Serializable {	/**	 * @Fields serialVersionUID	 */	private static final long serialVersionUID = 1L;	// field	/** 编号 **/	private int id;	/** 收费名称 **/	private String name;	/** 单价 **/	private Double price;	/**	 * 备注	 */	private String description;	public Chargeitem() {	}	// method	@Id	@Column(name = "id", nullable = false, length = 11)	public int getId() {		return id;	}	public void setId(int id) {		this.id = id;	}	@Column(name = "name", nullable = false, length = 20)	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	@Column(name = "price", nullable = false, length = 12)	public Double getPrice() {		return price;	}	public void setPrice(Double price) {		this.price = price;	}	@Column(name = "description")	public String getDescription() {		return description;	}	public void setDescription(String description) {		this.description = description;	}	// override toString Method	public String toString() {		StringBuffer sb = new StringBuffer();		sb.append("{");		sb.append("'id':'" + this.getId() + "',");		sb.append("'name':'" + this.getName() + "',");		sb.append("'price':'" + this.getPrice() + "'");		sb.append("}");		return sb.toString();	}}