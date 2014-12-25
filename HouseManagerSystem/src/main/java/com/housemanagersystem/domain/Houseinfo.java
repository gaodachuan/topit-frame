package com.housemanagersystem.domain;import java.io.Serializable;import java.util.Date;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.Id;import javax.persistence.Table;import org.springframework.format.annotation.DateTimeFormat;/******************************************************************************* * javaBeans houseinfo --> Houseinfo * <table explanation> *  * @author 2014-12-14 15:59:27 *  */@Entity@Table(name = "houseinfo")public class Houseinfo implements Serializable {	/**	 * @Fields serialVersionUID	 */	private static final long serialVersionUID = 1L;	// field	/** 主键 **/	private int id;	/** 房源编号 **/	private String number;	/** 房间名称 **/	private String name;	/** 房间类型 （单间，一室户，套房） **/	private String housetype;	/** 房间面积 **/	private Double area;	/** 装修情况（精装，简装，毛坯房） **/	private String decorate;	/** 房间设施 **/	private String equipment;	/** 房间用途（住宿，写字楼） **/	private String usetype;	/** 报警天数：租房到期时间 **/	private int warningtime;	/** 日租价格（只针对租房） **/	private Double dayrentpri;	/** 月租/月供价格（租房：月租金，售房：分期月供） **/	private Double monthrentpri;	/** 业务类型（出租，出售） **/	private String dealType;	/** 房间状态（空闲 0，在1/在售 2） **/	private String status = "出租";	/** 合同开始时间（租房合同开始时间） **/	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  	private Date rentstart;	/** 合同结束时间（租房结束时间） **/	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")	private Date rentend;	/** 月份数（租房按月时间） **/	/** 电表数据 **/		private int ElectricStart = 0;	/** 水表数据 **/		private int WaterStart = 0;	@Column(name = "ElectricStart")	public int getElectricStart() {		return ElectricStart;	}	public void setElectricStart(int electricStart) {		ElectricStart = electricStart;	}	@Column(name = "WaterStart")	public int getWaterStart() {		return WaterStart;	}	public void setWaterStart(int waterStart) {		WaterStart = waterStart;	}	private int months;	/** 租期天数 **/	private int days;	/** 备注 **/	private String description;	public Houseinfo() {	}	@Column(name = "description")	public String getDescription() {		return description;	}	public void setDescription(String description) {		this.description = description;	}	@Id	@Column(name = "id")	public int getId() {		return id;	}	public void setId(int id) {		this.id = id;	}	@Column(name = "number")	public String getNumber() {		return number;	}	public void setNumber(String number) {		this.number = number;	}	@Column(name = "name")	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	@Column(name = "housetype")	public String getHousetype() {		return housetype;	}	public void setHousetype(String housetype) {		this.housetype = housetype;	}	@Column(name = "area")	public Double getArea() {		return area;	}	public void setArea(Double area) {		this.area = area;	}	@Column(name = "decorate")	public String getDecorate() {		return decorate;	}	public void setDecorate(String decorate) {		this.decorate = decorate;	}	@Column(name = "Equipment")	public String getEquipment() {		return equipment;	}	public void setEquipment(String equipment) {		this.equipment = equipment;	}	@Column(name = "usetype")	public String getUsetype() {		return usetype;	}	public void setUsetype(String usetype) {		this.usetype = usetype;	}	@Column(name = "warningtime")	public int getWarningtime() {		return warningtime;	}	public void setWarningtime(int warningtime) {		this.warningtime = warningtime;	}	@Column(name = "dayrentpri")	public Double getDayrentpri() {		return dayrentpri;	}	public void setDayrentpri(Double dayrentpri) {		this.dayrentpri = dayrentpri;	}	@Column(name = "monthrentpri")	public Double getMonthrentpri() {		return monthrentpri;	}	public void setMonthrentpri(Double monthrentpri) {		this.monthrentpri = monthrentpri;	}	@Column(name = "dealType")	public String getDealType() {		return dealType;	}	public void setDealType(String dealType) {		this.dealType = dealType;	}	@Column(name = "status")	public String getStatus() {		return status;	}	public void setStatus(String status) {		this.status = status;	}	@Column(name = "rentstart")	public Date getRentstart() {		return rentstart;	}	public void setRentstart(Date rentstart) {		this.rentstart = rentstart;	}	@Column(name = "rentend")	public Date getRentend() {		return rentend;	}	public void setRentend(Date rentend) {		this.rentend = rentend;	}	@Column(name = "months")	public int getMonths() {		return months;	}	public void setMonths(int months) {		this.months = months;	}	@Column(name = "days")	public int getDays() {		return days;	}	public void setDays(int days) {		this.days = days;	}	// override toString Method	public String toString() {		StringBuffer sb = new StringBuffer();		sb.append("{");		sb.append("'id':'" + this.getId() + "',");		sb.append("'number':'" + this.getNumber() + "',");		sb.append("'name':'" + this.getName() + "',");		sb.append("'housetypee':'" + this.getHousetype() + "',");		sb.append("'area':'" + this.getArea() + "',");		sb.append("'decorate':'" + this.getDecorate() + "',");		sb.append("'equipment':'" + this.getEquipment() + "',");		sb.append("'usetype':'" + this.getUsetype() + "',");		sb.append("'warningtime':'" + this.getWarningtime() + "',");		sb.append("'dayrentpri':'" + this.getDayrentpri() + "',");		sb.append("'monthrentpri':'" + this.getMonthrentpri() + "',");		sb.append("'dealtype':'" + this.getDealType() + "',");		sb.append("'status':'" + this.getStatus() + "',");		sb.append("'rentstart':'" + this.getRentstart() + "',");		sb.append("'rentend':'" + this.getRentend() + "',");		sb.append("'months':'" + this.getMonths() + "',");		sb.append("'days':'" + this.getDays() + "'");		sb.append("}");		return sb.toString();	}}