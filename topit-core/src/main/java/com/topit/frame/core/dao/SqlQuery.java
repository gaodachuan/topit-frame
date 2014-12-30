package com.topit.frame.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.topit.frame.core.util.IDataTypeQuery;
import com.topit.frame.core.util.SpringContextHolder;

/**
 * @ClassName: SqlQuery
 * @Description: SQL查询实现
 * @author ivan.zhang
 * @date 2014年11月13日 下午2:43:22
 * 
 */
@Component("sqlQuery")
public class SqlQuery extends SqlUtil {

	/**
	 * @Title: Page
	 * @Description: SQL语句不带参数的分页查询方法
	 * @param sql
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */

	public List<Map<String, Object>> Page(String sql, int pageNow, int pageSize) {
		String paginationSQL = this.paginationSQL(sql, pageNow, pageSize);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		list = this.getJdbcTemplate().queryForList(paginationSQL);

		return list;

	}
	/**
	 * @Title: Page
	 * @Description: SQL语句带参数的分页查询方法
	 * @param sql
	 * @param pageNow
	 * @param pageSize
	 * @param args
	 * @return
	 */

	public List<Map<String, Object>> Page(String sql, int pageNow,
			int pageSize, Object... args) {
		String paginationSQL = this.paginationSQL(sql, pageNow, pageSize);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		list = this.getJdbcTemplate().queryForList(paginationSQL, args);

		return list;

	}

	/**
	 * @Title: paginationSQL
	 * @Description: 将查询结果的SQL语句，拼装成相对应数据库 的查询分页语句
	 * @param sql
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */

	public String paginationSQL(String sql, int pageNow, int pageSize) {

		IDataTypeQuery dataTypeQuery = (IDataTypeQuery) SpringContextHolder
				.getBean(myDataType.toLowerCase() + "Query");

		return dataTypeQuery.pageQuery(sql, pageNow, pageSize);

	}

}
