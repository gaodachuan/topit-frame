package com.topit.frame.common.page;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class PageResultSet implements IPageResultSet {

	public PageObject pageObject;
	public ResultSet resultSet;

	public PageObject getPageObject() {
		return pageObject;
	}

	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public PageResultSet(ResultSet resultSet, int pageSize) throws Exception {
		if (resultSet == null) {
			throw new SQLException(" ResultSet is NULL");
		} else {
			resultSet.last();
			this.pageObject.setCount(resultSet.getRow());
			resultSet.beforeFirst();
		}
		this.pageObject.setPageSize(pageSize);
		this.resultSet = resultSet;
	}

	public PageResultSet(ResultSet resultSet, int pageSize, int PageIndex)
			throws Exception {
		if (resultSet == null) {
			throw new SQLException(" ResultSet is NULL");
		} else {
			resultSet.last();
			this.pageObject.setCount(resultSet.getRow());
			resultSet.beforeFirst();
		}
		this.pageObject.setPageSize(pageSize);
		this.resultSet = resultSet;
	}

	/**
	 * 返回总页数
	 * 
	 * @throws SQLException
	 */
	public int getPageCount() throws Exception {
		int pageCount = 0;
		if (pageObject.getCount() == 0) {
			return pageCount;
		}
		if (pageObject.getPageSize() == 0) {
			throw new Exception("pagesize  is  not 0");
		}

		pageCount = pageObject.getCount() / pageObject.getPageSize();
		if (pageObject.getCount() / pageObject.getPageSize() > 0) {
			pageCount = pageCount + 1;
		}
		return pageCount;
	};

	/**
	 * 转到指定页
	 * 
	 * @throws Exception
	 */
	public void gotoPage(int pageIndex) throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		if (pageIndex > getPageCount()) {
			pageIndex = getPageCount();
		}
		int row = (pageIndex - 1) * (pageObject.getPageSize()) + 1;

		try {
			resultSet.absolute(row);
			pageObject.setPageIndex(pageIndex);
		} catch (java.sql.SQLException e) {
		}
	}

	public void nextPage(int pageIndex) throws Exception {
		gotoPage(pageIndex + 1);
	}

	public void prePage(int pageIndex) throws Exception {
		gotoPage(pageIndex - 1);
	}
}
