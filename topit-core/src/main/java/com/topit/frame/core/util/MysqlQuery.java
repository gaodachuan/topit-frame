package com.topit.frame.core.util;

import org.springframework.stereotype.Repository;
@Repository("mysqlQuery")
public class MysqlQuery  implements IDataTypeQuery{

	public String pageQuery(String sql,  int pageNow  , int pageSize) {
		
		String paginationSQL="SELECT * FROM ("+sql+") T LIMIT "+((pageNow-1)*pageSize)+" , "+pageSize;
		
		return paginationSQL;
	}

}
