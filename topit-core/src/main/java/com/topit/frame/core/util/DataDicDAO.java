package com.topit.frame.core.util;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.topit.frame.core.dao.HqlUtil;
import com.topit.frame.core.entity.data.SysModule;
import com.topit.frame.core.util.entity.DataDic;

/**
 * @ClassName: DataDicDAO
 * @Description: 页面下拉列表数据查询类
 * @author ivan.zhang
 * @date 2014年11月25日 下午3:33:40
 * 
 */
@Repository("dataDicDAO")
public class DataDicDAO extends HqlUtil {
	/**
	 * @Title: getData
	 * @Description: 根绝分类类型编号和类型代码查询分类名称
	 * @param type
	 *            分类类型编号
	 * @param value
	 *            类型代码
	 * @return
	 */
	@SuppressWarnings("null")
	public DataDic getData(int type, String value) {
		DataDic dataDic = null;
		String hql = "select new com.topit.frame.core.util.entity.DataDic(a.categoryCode ,a.categoryName)  from  ComObjectSortCategory a where a.sortXtypeId =? and a.categoryCode=?";
		@SuppressWarnings("unchecked")
		List<DataDic> list = (List<DataDic>) getHibernateTemplate().find(hql,
				type, value);
		if (list.size() > 0) {
			dataDic = list.get(0);

		} else {
			dataDic.setId(value);
			dataDic.setName(value);
		}
		return dataDic;
	}

	/**
	 * @Title: getData
	 * @Description: 根据分类类型编码查询分类类型的分类代码和分类名称
	 * @param type
	 *            分类类型编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	public List<DataDic> getData(int type) {
		String hql = "select new com.topit.frame.core.util.entity.DataDic(a.categoryCode ,a.categoryName)  from  ComObjectSortCategory a where a.sortXtypeId =?";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createQuery(hql);
		query.setParameter(0, type);
		List<DataDic> list = (List<DataDic>) query.list();
		return list;
	}
}
