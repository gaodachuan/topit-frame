package com.housemanagersystem.util;

import java.util.Date;

public class DateUtils {
    /**
     * 计算两个日期之间的天数
     * @param start
     * @param end
     * @return
     */
	public static  int getDiffDay(Date start,Date end){
		long a=start.getTime();
		long b=end.getTime();	
		return (int)((Math.abs(a-b))/1000/60/60/24);
	}
	/**
	 * 计算两个日期之间的月份数
	 * @param start
	 * @param end
	 * @return
	 */
	public static  int getDiffMonth(Date start,Date end){
		long a=start.getTime();
		long b=end.getTime();	
		return (int)((Math.abs(a-b))/1000/60/60/24/30);
	}
	
}
