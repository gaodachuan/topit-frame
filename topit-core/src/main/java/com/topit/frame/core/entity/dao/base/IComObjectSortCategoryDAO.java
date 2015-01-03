package com.topit.frame.core.entity.dao.base;

import java.util.List;

import com.topit.frame.core.dao.IBaseDAO;
import com.topit.frame.core.entity.data.ComObjectSortCategory;
import com.topit.frame.core.util.entity.Node;

public interface IComObjectSortCategoryDAO extends
		IBaseDAO<ComObjectSortCategory> {

	public List<ComObjectSortCategory> getList(int type) throws Exception;

	public List<Node> getListTree(String sql, Object... params)
			throws Exception;
}
