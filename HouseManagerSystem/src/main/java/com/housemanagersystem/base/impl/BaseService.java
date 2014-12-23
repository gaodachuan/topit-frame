package com.housemanagersystem.base.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.housemanagersystem.base.IBaseService;
import com.housemanagersystem.util.SqlcreateUtil;
import com.topit.frame.common.view.servlet.ResultPageObject;
import com.topit.frame.core.dao.BaseDAO;

@Transactional
public class BaseService<T> extends BaseDAO<T> implements IBaseService<T> {

	public ResultPageObject getEntityinfo(int currentPage, int pageSize,
			Map<String, Object> conditions) throws Exception {

		ResultPageObject resultPageObject = new ResultPageObject();
		List<T> list = new ArrayList<T>();

		if (!conditions.isEmpty()) {
			String hql = SqlcreateUtil.createSearchHql(
					entityClass.getSimpleName(), conditions);
			list = getListForPage(hql, (currentPage - 1) * pageSize, pageSize);
			int total = getHibernateTemplate().find(hql, null).size();

			resultPageObject.setTotal(String.valueOf(total));
		} else {
			DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
			list = getListForPage(criteria, (currentPage - 1) * pageSize,
					pageSize);
			resultPageObject.setTotal(String.valueOf(getCount()));
		}

		resultPageObject.setRows(list);
		return resultPageObject;

	}

	public boolean createEntity(T entity) throws Exception {
		return save(entity);
	}

	public boolean updateEntity(T entity) throws Exception {
		return update(entity);
	}

	public boolean deleteEntity(String ids) throws Exception {
		String[] id = ids.split(",");
		Integer[] delids = new Integer[id.length];
		for (int i = 0; i < id.length; i++) {
			delids[i] = Integer.parseInt(id[i]);
		}
		String hql = "delete  from " + entityClass.getSimpleName()
				+ " where id in(:ids)";
		int res = getSessionFactory().getCurrentSession().createQuery(hql)
				.setParameterList("ids", delids).executeUpdate();
		return res == id.length;
	}

}
