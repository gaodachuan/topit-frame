package com.topit.frame.core.dao;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SqlUtil
 * @Description: 注入jdbcTemplate
 * @author ivan.zhang
 * @date 2014年11月13日 下午2:43:35
 * 
 */
@Component("sqlUtil")
public abstract class SqlUtil {

	protected String myDataType;

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) throws SQLException {
		this.jdbcTemplate = jdbcTemplate;
		DatabaseMetaData md = jdbcTemplate.getDataSource().getConnection()
				.getMetaData();
		myDataType = md.getDatabaseProductName();
	}

	public String getMyDataType() {
		return myDataType;
	}

	public void setMyDataType(String myDataType) {
		this.myDataType = myDataType;
	}

}
