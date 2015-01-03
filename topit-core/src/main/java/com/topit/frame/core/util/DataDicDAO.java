package com.topit.frame.core.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.topit.frame.core.dao.HqlUtil;
import com.topit.frame.core.dao.SqlQuery;
import com.topit.frame.core.entity.data.SysModule;
import com.topit.frame.core.util.entity.CategoryNode;
import com.topit.frame.core.util.entity.DataDic;
import com.topit.frame.core.util.entity.Node;
import com.topit.frame.core.util.entity.TreeUtils;

/**
 * @ClassName: DataDicDAO
 * @Description: 页面下拉列表数据查询类
 * @author ivan.zhang
 * @date 2014年11月25日 下午3:33:40
 * 
 */
@Repository("dataDicDAO")
public class DataDicDAO extends HqlUtil {
	@Resource(name = "sqlQuery")
	private SqlQuery sqlQuery;

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
	@Transactional(propagation = Propagation.NEVER)
	public DataDic getName(int type) {
		DataDic dataDic = null;
		String hql = "select new com.topit.frame.core.util.entity.DataDic(a.id ,a.categoryName)  from  ComObjectSortCategory a where a.itemId =? ";
		@SuppressWarnings("unchecked")
		List<DataDic> list = (List<DataDic>) getHibernateTemplate().find(hql,
				type);
		if (list.size() > 0) {
			dataDic = list.get(0);

		} else {
			dataDic.setId(0);
			dataDic.setName(String.valueOf(type));
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
		String hql = "select new com.topit.frame.core.util.entity.DataDic(a.id,a.categoryName)  from  ComObjectSortCategory a where a.sortXtypeId =?";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createQuery(hql);
		query.setParameter(0, type);
		List<DataDic> list = (List<DataDic>) query.list();
		return list;
	}

	@SuppressWarnings("null")
	@Transactional(propagation = Propagation.NEVER)
	public List<Node> getDataNode(int type) {
		Object[] args = new Object[1];
		args[0]=type;
		final List<Node> list = new ArrayList<Node>();
		String sql = "select *  from  Com_Object_Sort_Category a where a.sortXtypeId =?";
		sqlQuery.getJdbcTemplate().query(sql, args,new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				Node node = new Node();
				node.setId(rs.getInt("ID"));
				node.setNodeCode(rs.getString("ID"));
				node.setText(rs.getString("CATEGORYNAME"));
				node.setNodeParent(rs.getInt("PARENTLID"));
				list.add(node);
			}
		});
		List<Node> listTree = new ArrayList<Node>();
		listTree = TreeUtils.buildTree(list);
		return listTree;
	}

}
