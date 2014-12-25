package com.topit.frame.core.util;

import org.springframework.stereotype.Repository;
@Repository("oracleQuery")
public class OracleQuery  implements  IDataTypeQuery{

	public String pageQuery(String sql,  int pageNow  , int pageSize) {
		
		String paginationSQL="SELECT * FROM ( SELECT T.* , ROWNUM RN FROM ("+sql+") T WHERE ROWNUM <= "+(pageNow*pageSize) 
				+ ") WHERE RN >= "+((pageNow-1)*pageSize+1);
		return paginationSQL;
	}

}
