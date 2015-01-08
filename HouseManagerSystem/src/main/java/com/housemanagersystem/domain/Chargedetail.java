
package com.housemanagersystem.domain;import java.util.Date;

import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.Id;import javax.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;
/******************************************************************************* * javaBeans * chargedetail --> Chargedetail  * <table explanation> * @author 2014-12-29 15:38:22 *  */	@Entity@Table(name= "chargedetail")
@DynamicUpdatepublic class Chargedetail implements java.io.Serializable {		private static final long serialVersionUID = 1L;	/** 缴费完成情况 **/
	@Column(name="isComplete")	private String isComplete="未完成";
	/** 收费生成时间 **/
	@Column(name="createTime")
	private Date createTime=new Date(System.currentTimeMillis());
	/** 收费完成时间 **/
	@Column(name="completeTime")
	private Date completeTime;	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCompleteTime() {		
		return completeTime;
	}
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	//field	/** 手工单号 **/	private String dealid;	/** 操作员 **/	private String servername;	public String getIsComplete() {		return isComplete;	}	public void setIsComplete(String isComplete) {		this.isComplete = isComplete;	}	/** 金额 **/	private Double countcharge;	/**数量  **/	private int number=1;	/** 单价 **/	private Double price;	/** 房客姓名 **/	private String customername;	/** 资费名称 **/	private String chargename;	/** 房间名称 **/	private String housename;	/** 房间编号 **/	private String houseid;	/** 主键 **/	private int id;		public Chargedetail() {			}	public Chargedetail(Contact contact,Chargeitem chargeitem){		this.chargename=chargeitem.getName();		this.houseid=contact.getHouseid();		this.housename=contact.getHousename();		this.customername=contact.getCustomername();		this.servername=contact.getServername();		this.price=chargeitem.getPrice();		this.countcharge=price*number;
		this.createTime=new Date(System.currentTimeMillis());
		this.completeTime=isComplete.equals("未完成")?null:getCompleteTime();	}	//method	@Id	@Column(name= "dealId"  )	public String getDealid() {		return dealid;	}	public void setDealid(String dealid) {		this.dealid = dealid;	}	@Column(name= "serverName" , nullable= false , length = 11 )	public String getServername() {		return servername;	}	public void setServername(String servername) {		this.servername = servername;	}	@Column(name= "countCharge" , nullable= false , length = 11 )	public Double getCountcharge() {		return countcharge;	}	public void setCountcharge(Double countcharge) {		this.countcharge = countcharge;	}	@Column(name= "number" , nullable= false , length = 11 )	public int getNumber() {		return number;	}	public void setNumber(int number) {		this.number = number;	}	@Column(name= "price" , nullable= false , length = 11 )	public Double getPrice() {		return price;	}	public void setPrice(Double price) {		this.price = price;	}	@Column(name= "customerName" , nullable= false , length = 20 )	public String getCustomername() {		return customername;	}	public void setCustomername(String customername) {		this.customername = customername;	}	@Column(name= "chargeName" , nullable= false , length = 11 )	public String getChargename() {		return chargename;	}	public void setChargename(String chargename) {		this.chargename = chargename;	}	@Column(name= "houseName" , nullable= false , length = 20 )	public String getHousename() {		return housename;	}	public void setHousename(String housename) {		this.housename = housename;	}	@Column(name= "houseId" , nullable= false , length = 11 )	public String getHouseid() {		return houseid;	}	public void setHouseid(String houseid) {		this.houseid = houseid;	}	@Column(name= "id" , nullable= false , length = 11 )	public int getId() {		return id;	}	public void setId(int id) {		this.id = id;	}	//override toString Method 	public String toString() {		StringBuffer sb=new StringBuffer();		sb.append("{");		sb.append("'dealid':'"+this.getDealid()+"',");		sb.append("'servername':'"+this.getServername()+"',");		sb.append("'countcharge':'"+this.getCountcharge()+"',");		sb.append("'number':'"+this.getNumber()+"',");		sb.append("'price':'"+this.getPrice()+"',");		sb.append("'customername':'"+this.getCustomername()+"',");		sb.append("'chargename':'"+this.getChargename()+"',");		sb.append("'housename':'"+this.getHousename()+"',");		sb.append("'houseid':'"+this.getHouseid()+"',");		sb.append("'id':'"+this.getId()+"'");		sb.append("}");		return sb.toString();	}}
