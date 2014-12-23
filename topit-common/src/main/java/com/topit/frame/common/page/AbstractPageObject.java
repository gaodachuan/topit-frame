package com.topit.frame.common.page;

/**
 * @ClassName: AbstractPageObject
 * @Description: 分页对象抽象类
 * @author ivan.zhang
 * @date 2014年11月13日 下午2:55:16
 * 
 */
public class AbstractPageObject {

	/**
	 * @Fields count : 总记录数
	 */
	private int count;
	/**
	 * @Fields pageIndex : 当前页数
	 */
	private int pageIndex;
	/**
	 * @Fields pageSize : 每页显示记录数
	 */
	private int pageSize;
	/**
	 * @Fields pageCount :总页数
	 */
	private int pageCount;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

}
