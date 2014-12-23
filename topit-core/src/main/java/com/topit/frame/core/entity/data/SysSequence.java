package com.topit.frame.core.entity.data;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javassist.expr.NewArray;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName: SysSequence
 * @Description:主键生成策略表
 * @author gaodachuan
 * @date 2014年11月18日 下午4:06:10
 *
 */
@Entity()
@Table(name = "sys_sequence")
public class SysSequence {
	/**
	 * @Field:主键
	 */
	@Id
	private int id;
	/**
	 * @Field:需求表名
	 */
	@Column(name = "SequenceName")
	private String sequenceName;
	/**
	 * @Field:系统预留字段
	 */
	@Column(name = "SystemName")
	private String systemName;
	/**
	 * @Field:当前的id值
	 */
	@Column(name = "CurrentId")
	private BigInteger currentId;
	/**
	 * @Field:步长
	 */
	@Column(name = "IncrementSize")
	private int incrementSize = 1;
	/**
	 * @Field:最小值
	 */
	@Column(name = "MinId")
	private BigInteger minId = new BigInteger("1");
	/**
	 * @Field:最大值
	 */
	@Column(name = "MaxId")
	private BigInteger maxId;
	/**
	 * @Field:生成时间
	 */
	@Column(name = "LastApplyTime")
	private Date LastApplyTime;

	public SysSequence() {
	}

	public SysSequence(String sequenceName) {
		super();
		this.sequenceName = sequenceName;
		this.currentId = new BigInteger("1");
		this.systemName = "default";
		this.incrementSize = 1;
		this.LastApplyTime = new Date(System.currentTimeMillis());

	}

	public SysSequence(String sequenceName, BigInteger currentId,
			int incrementSize, BigInteger minId) {
		super();
		this.sequenceName = sequenceName;
		this.currentId = currentId;
		this.systemName = "default";
		this.incrementSize = incrementSize;
		this.minId = minId;
		this.LastApplyTime = new Date(System.currentTimeMillis());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	public String getSystemName() {
		return systemName != null ? systemName : "default";
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public BigInteger getCurrentId() {
		return currentId;
	}

	public void setCurrentId(BigInteger currentId) {
		this.currentId = currentId;
	}

	public int getIncrementSize() {
		return incrementSize;
	}

	public void setIncrementSize(int incrementSize) {
		this.incrementSize = incrementSize;
	}

	public BigInteger getMinId() {
		return minId;
	}

	public void setMinId(BigInteger minId) {
		this.minId = minId;
	}

	public BigInteger getMaxId() {
		return maxId;
	}

	public void setMaxId(BigInteger maxId) {
		this.maxId = maxId;
	}

	public Date getLastApplyTime() {
		return LastApplyTime;
	}

	public void setLastApplyTime(Date lastApplyTime) {
		LastApplyTime = lastApplyTime;
	}

}
