package com.topit.frame.core.util;

import org.springframework.stereotype.Repository;

/**
 * @ClassName: MysqlQuery
 * @Description: MySql数据库的查询语句
 * @author qiugui
 * @date 2014年12月25日 下午4:25:11
 * 
 */
@Repository("mysqlQuery")
public class MysqlQuery implements IDataTypeQuery {

	/**
	 * <p>
	 * Title: pageQuery
	 * </p>
	 * <p>
	 * Description: 将查询结果拼装成MySql数据库的分页语句
	 * </p>
	 * 
	 * @param sql
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @see com.topit.frame.core.util.IDataTypeQuery#pageQuery(java.lang.String,
	 *      int, int)
	 */

	public String pageQuery(String sql, int pageNow, int pageSize) {

		String paginationSQL = "SELECT * FROM (" + sql + ") T LIMIT "
				+ ((pageNow - 1) * pageSize) + " , " + pageSize;

		return paginationSQL;

	}

}
