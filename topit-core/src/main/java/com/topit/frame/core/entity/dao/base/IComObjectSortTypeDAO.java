package com.topit.frame.core.entity.dao.base;

import java.util.List;

import com.topit.frame.core.dao.IBaseDAO;
import com.topit.frame.core.entity.data.ComObjectSortType;
import com.topit.frame.core.util.entity.Node;

public interface IComObjectSortTypeDAO extends IBaseDAO<ComObjectSortType> {

	public List<Node> getListTree(String sql) throws Exception;
}
