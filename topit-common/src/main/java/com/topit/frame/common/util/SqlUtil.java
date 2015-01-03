package com.topit.frame.common.util;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * 
 * @ClassName: SqlUtil
 * @Description: 创建sql的语句;
 * @author gaodachuan
 * @date 2014年11月27日 上午9:49:14
 *
 */
public class SqlUtil {
	/**
	 * 
	 * @Title: createUpdate   
	 * @Description: 创建跟新语句 
	 * @param tableName
	 * @param column
	 * @param conditions
	 * @param parms
	 * @return
	 */
	public static String createUpdate(String tableName, String[] column,
			String[] conditions) {
		StringBuffer sb = new StringBuffer("UPDATE " + tableName + " SET ");
		for (int i = 0; i < column.length; i++) {
			if (i == (column.length - 1)) {
				sb.append(" " + column[i] + "=:" + column[i] + " WHERE ");
			} else {
				sb.append(" " + column[i] + "=:" + column[i] + ",");
			}

		}
		for (int j = 0; j < conditions.length; j++) {
			
			if (j == (conditions.length - 1)) {
				sb.append(conditions[j] + "=:" + conditions[j]);
			}else{
				sb.append(conditions[j] + "=:" + conditions[j] + " AND ");
			}
		}

		return sb.toString();
	}
}
