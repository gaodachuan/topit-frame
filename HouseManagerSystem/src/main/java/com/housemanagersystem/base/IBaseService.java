package com.housemanagersystem.base;

import java.util.Map;

import com.topit.frame.common.view.servlet.ResultPageObject;
import com.topit.frame.core.dao.IBaseDAO;

public interface IBaseService<T> extends IBaseDAO<T> {
	/**
	 * 
	 * @Title: getHouseinfos   
	 * @Description:分页搜索查询房源列表  
	 * @param currentPage 当前页
	 * @param pageSize 每一页的条数
	 * @param conditions 搜索条件
	 * @return
	 * @throws Exception 
	 */
	public ResultPageObject getEntityinfo(Map<String ,Object> conditions) throws Exception;
    /**
     * 
     * @param houseinfo
     * @return
     * @throws Exception 
     */
	public boolean createEntity(T entity) throws Exception;
	/**
     * s
     * @param houseinfo
     * @return
     */
	public boolean updateEntity(T entity) throws Exception;
	
	public boolean deleteEntity(String ids) throws Exception;
}
