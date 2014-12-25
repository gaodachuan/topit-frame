package com.topit.frame.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.topit.frame.core.util.IDataTypeQuery;
import com.topit.frame.core.util.SpringContextHolder;

/** 
* @ClassName: SqlQuery 
* @Description: 查询实现
* @author ivan.zhang 
* @date 2014年11月13日 下午2:43:22 
*  
*/ 
@Component("sqlQuery")
public class SqlQuery extends SqlUtil {

	public List<Map<String, Object>> Page(String sql,  int pageNow  , int pageSize){
		String paginationSQL=this.paginationSQL(sql, pageNow, pageSize);
		
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();

		list=this.getJdbcTemplate().queryForList(paginationSQL);

		return list;
		
	}
	
	public List<Map<String, Object>> Page(String sql,  int pageNow  , int pageSize, Object... args){
		String paginationSQL=this.paginationSQL(sql, pageNow, pageSize);
		
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		
		list= this.getJdbcTemplate().queryForList(paginationSQL, args);

		return list;
		
	}
	
	public String paginationSQL(String sql,  int pageNow  , int pageSize){
		IDataTypeQuery dataTypeQuery  =(IDataTypeQuery) SpringContextHolder.getBean(myDataType.toLowerCase()+"Query");
		return dataTypeQuery.pageQuery(sql, pageNow , pageSize);
	}
}
