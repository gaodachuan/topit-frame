package com.topit.frame.busniess.imp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.topit.frame.busniess.base.IComObjectSortCategoryService;
import com.topit.frame.core.entity.dao.base.IComObjectSortCategoryDAO;
import com.topit.frame.core.entity.data.ComObjectSortCategory;
import com.topit.frame.core.util.entity.Node;
import com.topit.frame.core.util.entity.TreeUtils;

@Repository("comObjectSortCategoryService")
@Transactional
public class ComObjectSortCategoryService implements
		IComObjectSortCategoryService {
	@Resource(name = "comObjectSortCategoryDAO")
	private IComObjectSortCategoryDAO comObjectSortCategoryDAO;

	public List<Node> getListTree(String sql, Object... params)
			throws Exception {
		List<Node> list = new ArrayList<Node>();
		list = comObjectSortCategoryDAO.getListTree(sql, params);
		list = TreeUtils.buildTree(list);
		return list;
	}

	public boolean update(String hql, Object... params) throws Exception {
		boolean flag = false;
		flag = comObjectSortCategoryDAO.update(hql, params);
		return flag;
	}

	public boolean save(ComObjectSortCategory comObjectSortCategory)
			throws Exception {
		boolean flag= false;
		flag =comObjectSortCategoryDAO.save(comObjectSortCategory);
		return flag;
	}

}
