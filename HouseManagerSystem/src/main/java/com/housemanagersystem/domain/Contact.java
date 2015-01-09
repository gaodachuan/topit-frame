
package com.housemanagersystem.domain;import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.Id;import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
/******************************************************************************* * javaBeans * contact --> Contact  * 合同 * <table explanation> * @author 2014-12-29 17:16:35 *  */	@Entity@Table(name= "contact")
@DynamicUpdatepublic class Contact implements Serializable {		private static final long serialVersionUID = 1L;	//field	/** 合同结束时间 **/	private Date contactend;	/** 合同开始时间 **/	private Date contactstart;	/** 租房还是买房 **/	private String servicetype;	/** 房客人数（只针对租房） **/	private int customerno;	/** 房间类型 **/	private String housetype;	/** 合同编号 **/		private int id;
	/** 是否有效 **/
	private String isVariable="有效";
	@Column(name= "isVariable" )	public String getIsVariable() {
		return isVariable;
	}
	public void setIsVariable(String isVariable) {
		this.isVariable = isVariable;
	}
	/** 房间用途 **/	private String houseUseType;	@Column(name= "houseUseType" )	public String getHouseUseType() {		return houseUseType;	}	public void setHouseUseType(String houseUseType) {		this.houseUseType = houseUseType;	}	/** 装修情况 **/	private String houseDecorate;	@Column(name= "houseDecorate" )	public String getHouseDecorate() {		return houseDecorate;	}	public void setHouseDecorate(String houseDecorate) {		this.houseDecorate = houseDecorate;	}		private Double houseDaypri;		private Double houseMonthpri;	@Column(name= "houseDaypri" )	public Double getHouseDaypri() {		return houseDaypri;	}	public void setHouseDaypri(Double houseDaypri) {		this.houseDaypri = houseDaypri;	}	@Column(name= "houseMonthpri" )	public Double getHouseMonthpri() {		return houseMonthpri;	}	public void setHouseMonthpri(Double houseMonthpri) {		this.houseMonthpri = houseMonthpri;	}	/** 房间面积 **/		private Double houseArea;	@Column(name= "houseArea" )	public Double getHouseArea() {		return houseArea;	}	public void setHouseArea(Double houseArea) {		this.houseArea = houseArea;	}	/** 房屋名称 **/	private String housename;	/** 房源编号 **/	private String houseid;	/** 客户姓名 **/	private String customername;	/** 资费项目 **/	private String chargeitem;	/** 房间设施 **/	private String houseequipment;	/** 办理人员 **/	private String servername;	/** 初始电表度数 **/	private int waterstart;	/** 初始电表度数 **/	private int electricstart;	//method		@Column(name= "contactEnd" )	public Date getContactend() {		return contactend;	}	public void setContactend(Date contactend) {		this.contactend = contactend;	}	@Column(name= "contactStart" )	public Date getContactstart() {		return contactstart;	}	public void setContactstart(Date contactstart) {		this.contactstart = contactstart;	}	@Column(name= "serviceType" )	public String getServicetype() {		return servicetype;	}	public void setServicetype(String servicetype) {		this.servicetype = servicetype;	}	@Column(name= "customerNo" )	public int getCustomerno() {		return customerno;	}	public void setCustomerno(int customerno) {		this.customerno = customerno;	}	@Column(name= "houseType" )	public String getHousetype() {		return housetype;	}	public void setHousetype(String housetype) {		this.housetype = housetype;	}	@Id	@Column(name= "id" )	public int getId() {		return id;	}	public void setId(int id) {		this.id = id;	}	@Column(name= "houseName" )	public String getHousename() {		return housename;	}	public void setHousename(String housename) {		this.housename = housename;	}	@Column(name= "houseId" )	public String getHouseid() {		return houseid;	}	public void setHouseid(String houseid) {		this.houseid = houseid;	}	@Column(name= "customerName")	public String getCustomername() {		return customername;	}	public void setCustomername(String customername) {		this.customername = customername;	}	@Column(name= "chargeItem" )	public String getChargeitem() {		return chargeitem;	}	public void setChargeitem(String chargeitem) {		this.chargeitem = chargeitem;	}	@Column(name= "houseEquipment")	public String getHouseequipment() {		return houseequipment;	}	public void setHouseequipment(String houseequipment) {		this.houseequipment = houseequipment;	}	@Column(name= "serverName" )	public String getServername() {		return servername;	}	public void setServername(String servername) {		this.servername = servername;	}	@Column(name= "WaterStart" )	public int getWaterstart() {		return waterstart;	}	public void setWaterstart(int waterstart) {		this.waterstart = waterstart;	}	@Column(name= "ElectricStart" )	public int getElectricstart() {		return electricstart;	}	public void setElectricstart(int electricstart) {		this.electricstart = electricstart;	}	//override toString Method 	public String toString() {		StringBuffer sb=new StringBuffer();		sb.append("{");		sb.append("'contactend':'"+this.getContactend()+"',");		sb.append("'contactstart':'"+this.getContactstart()+"',");		sb.append("'servicetype':'"+this.getServicetype()+"',");		sb.append("'customerno':'"+this.getCustomerno()+"',");		sb.append("'housetype':'"+this.getHousetype()+"',");		sb.append("'id':'"+this.getId()+"',");		sb.append("'housename':'"+this.getHousename()+"',");		sb.append("'houseid':'"+this.getHouseid()+"',");		sb.append("'customername':'"+this.getCustomername()+"',");		sb.append("'chargeitem':'"+this.getChargeitem()+"',");		sb.append("'houseequipment':'"+this.getHouseequipment()+"',");		sb.append("'servername':'"+this.getServername()+"',");		sb.append("'waterstart':'"+this.getWaterstart()+"',");		sb.append("'electricstart':'"+this.getElectricstart()+"'");		sb.append("}");		return sb.toString();	}}
