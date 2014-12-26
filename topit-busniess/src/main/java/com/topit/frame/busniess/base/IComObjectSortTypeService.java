package com.topit.frame.busniess.base;

import java.util.List;

import com.topit.frame.core.entity.data.ComObjectSortType;
import com.topit.frame.core.util.entity.Node;

public interface IComObjectSortTypeService {

	public List<Node> getTree(String sql)
			throws Exception;
	
	public List<Node> getTreeWithModule(String sql) throws Exception;
	
	
	public boolean save(ComObjectSortType comObjectSortType) throws Exception;
	
	public boolean update(String hql, Object... params) throws Exception;
	
	public ComObjectSortType find(int id) throws Exception;
}
