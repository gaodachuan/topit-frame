package com.topit.frame.busniess.base;

import java.util.List;

import com.topit.frame.core.entity.data.ComObjectSortType;
import com.topit.frame.core.util.entity.Node;

public interface IComObjectSortTypeService {

	public List<Node> getTree(String sql)
			throws Exception;
	
	public List<Node> getTreeWithModule(String sql) throws Exception;
}
