package com.topit.frame.core.dao;

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

	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
