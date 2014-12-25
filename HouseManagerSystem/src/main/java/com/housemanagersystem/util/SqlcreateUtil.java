package com.housemanagersystem.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * 
 * @ClassName: SqlcreateUtil
 * @Description: 生成sql/hql的工具类
 * @author gaodachuan
 * @date 2014年12月16日 下午1:30:57
 *
 */
public class SqlcreateUtil {

	public static String createSearchHql(String tableName,
			Map<String, Object> condition) {
		StringBuffer sql = new StringBuffer("FROM " + tableName
				+ " where 1=1 ");
		Set<Entry<String, Object>> set = condition.entrySet();
		Iterator<Entry<String, Object>> io = set.iterator();
		while (io.hasNext()) {
			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io
					.next();
			
				String valueType = me.getValue().getClass().getSimpleName();
				if (valueType.equals("String")&&!valueType.equals("")) {
					sql.append(" and " + me.getKey() + " like '%" + me.getValue()
							+ "%'");
				}
				if (valueType.equals("Integer")) {
					sql.append(" and " + me.getKey() + " = '" + me.getValue() + "'");

				}
				if ("startTime".equals(me.getKey()) && !"".equals(me.getValue())) {
					sql.append(" and " + me.getKey() + " >= '" + me.getValue()
							+ "'");
				}
				if ("endTime".equals(me.getKey()) && !"".equals(me.getValue())) {
					sql.append(" and " + me.getKey() + " <= '" + me.getValue()
							+ "'");
				}
				if ("sort".equals(me.getKey()) && !"".equals(me.getValue())) {
					sql.append(" order by " + me.getValue());
				}
				if ("order".equals(me.getKey()) && !"".equals(me.getValue())) {
					sql.append(" " + me.getValue());
				}	
			}
			
		
		return sql.toString();
	}
}
