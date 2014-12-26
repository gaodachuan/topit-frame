package com.topit.frame.core.util;

/**
 * @ClassName: IDataTypeQuery
 * @Description: 不依赖数据库的SQL查询功能 的接口
 * @author qiugui
 * @date 2014年12月25日 下午4:20:59
 * 
 */
public interface IDataTypeQuery {

	/**
	 * @Title: pageQuery
	 * @Description: 根据所用数据库的不同，将查询结果的SQL语句转成相对应的分页查询语句
	 * @param sql
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */

	public String pageQuery(String sql, int pageNow, int pageSize);
}
