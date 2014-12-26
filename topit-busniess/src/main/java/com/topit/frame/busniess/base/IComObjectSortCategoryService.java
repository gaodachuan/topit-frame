package com.topit.frame.busniess.base;

import java.util.List;

import com.topit.frame.core.entity.data.ComObjectSortCategory;
import com.topit.frame.core.util.entity.Node;

public interface IComObjectSortCategoryService {

	public List<Node> getListTree(String sql, Object... params)
			throws Exception;

	public boolean update(String hql, Object... params) throws Exception;

	public boolean save(ComObjectSortCategory comObjectSortCategory)
			throws Exception;
}
