package com.topit.frame.core.entity.dao.base;

import java.math.BigInteger;



import java.util.List;

import com.topit.frame.core.entity.data.SysSequence;

/**
 * 
 * @ClassName: IIdGenerator
 * @Description: id生成接口
 * @author gaodachuan
 * @date 2014年11月18日 下午5:11:25
 *
 */
public interface IIdGenerator {
	/**
	 * 
	 * @Title: createGenerator
	 * @Description: 创建一个id生成序列
	 * @param sequence 策略
	 * @return
	 */
	public boolean createGenerator(SysSequence sequence);

	/**
	 * 
	 * @Title: getNextId
	 * @Description: 获取指定表名的下一个id值
	 * @param sequenceName
	 *            指定对象名
	 * @return id
	 */
	public BigInteger getNextId(String sequenceName);
	/**
	 * 
	 * @Title: getNextIds  
	 * @Description:批量获取Id
	 * @param sequenceName 指定对象名
	 * @param Size 获取id的数量
	 * @return
	 */
	public List<BigInteger> getNextIds(String sequenceName,int Size);
	/**
	 * 
	 * @Title: getNextIds   
	 * @Description: 批量生成Id  
	 * @param sequenceName 指定对象名
	 * @param Size 获取id的数量
	 * @param IncreamentSize 指定步长
	 * @return
	 */
	public List<BigInteger> getNextIds(String sequenceName,int Size,int IncreamentSize);

	/**
	 * 
	 * @Title: deleteGenerator
	 * @Description: 删除指定表名的主键生成策略
	 * @param SeQuenceName
	 */
	public boolean deleteGenerator(String SeQuenceName);

	/**
	 * 
	 * @Title: reSetToZero
	 * @Description: 将id清零
	 * @param SeQuenceName
	 * @return
	 */
	public boolean reSetToZero(String SeQuenceName);

	/**
	 * 
	 * @Title: reSetToGoalId
	 * @Description: 设置当前id到指定起始序列
	 * @param SeQuenceName 指定对象名
	 * @param goal
	 * @return
	 */
	public boolean reSetToGoalId(String SeQuenceName, BigInteger goal);

	/**
	 * 
	 * @Title: reSetIncrementSize
	 * @Description: 重新设置步长
	 * @param IncrementSize
	 * @return
	 */
	public boolean reSetIncrementSize(String SeQuenceName, int IncrementSize);

}
