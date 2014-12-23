package com.topit.frame.common.util;
/** 
* @ClassName: IdGeneratorConstant 
* @Description: Id生成工具的常量值 
* @author ivan.zhang 
* @date 2014年11月20日 下午6:26:50 
*  
*/ 
public class IdGeneratorConstant {
	
	/************************** Generator表字段 *************************************/
	/** 
	* @Fields GENERATOR_FILED_TABLENAME : Id生成策略表 
	*/ 
	public static final String GENERATOR_FILED_TABLENAME = "sys_sequence";
	/** 
	* @Fields GENERATOR_FILED_CURRENTID : 当前Id 
	*/ 
	public static final String GENERATOR_FILED_CURRENTID = "CurrentId";
	/** 
	* @Fields GENERATOR_FILED_SEQUENCENAME : 指定的表名 
	*/ 
	public static final String GENERATOR_FILED_SEQUENCENAME = "SequenceName";
	/** 
	* @Fields GENERATOR_FILED_SYSTEMNAME :系统表名（预留字段） 
	*/ 
	public static final String GENERATOR_FILED_SYSTEMNAME = "SystemName";

	/************************** Generator操作sql *************************************/
	/** 
	* @Fields GENERATOR_SQL_UPDATECURRENTID : 跟新当前Id
	*/ 
	public static final String GENERATOR_SQL_UPDATECURRENTID = "UPDATE "
			+ GENERATOR_FILED_TABLENAME + " SET " + GENERATOR_FILED_CURRENTID + "=("
			+ GENERATOR_FILED_CURRENTID + "+?*?) WHERE " + GENERATOR_FILED_SEQUENCENAME
			+ "=? AND " + GENERATOR_FILED_SYSTEMNAME + "='default'";
	/** 
	* @Fields GENERATOR_SQL_QUERYCURRENTID : 查询当前Id
	*/ 
	public static final String GENERATOR_SQL_QUERYCURRENTID = "SELECT "
			+ GENERATOR_FILED_CURRENTID + " FROM " + GENERATOR_FILED_TABLENAME + "  WHERE "
			+ GENERATOR_FILED_SEQUENCENAME + "=? AND " + GENERATOR_FILED_SYSTEMNAME
			+ "='default'";
}
