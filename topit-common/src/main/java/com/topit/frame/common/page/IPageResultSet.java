package com.topit.frame.common.page;

import java.sql.ResultSet;

public interface IPageResultSet extends ResultSet {

	/**   
	 * @Title: getPageCount   
	 * @Description: 总页数   
	 * @return
	 * @throws Exception        
	 */
	public int getPageCount() throws Exception;

	/**
	 * 转到指定页
	 * 
	 * @throws Exception
	 */
	public void gotoPage(int pageIndex) throws Exception;
	/**
	 * 转到下一页
	 * 
	 * @throws Exception
	 */
	public void nextPage(int pageIndex) throws Exception;
	/**
	 * 转到上一页
	 * 
	 * @throws Exception
	 */
	public void prePage(int pageIndex) throws Exception;
}
