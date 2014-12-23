package com.topit.frame.core.entity.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.topit.frame.core.dao.BaseDAO;
import com.topit.frame.core.dao.SqlQuery;
import com.topit.frame.core.entity.dao.base.IComObjectSortCategoryDAO;
import com.topit.frame.core.entity.data.ComObjectSortCategory;
import com.topit.frame.core.util.entity.CategoryNode;
import com.topit.frame.core.util.entity.CategoryTreeNode;
import com.topit.frame.core.util.entity.Node;

@Repository(value = "comObjectSortCategoryDAO")
public class ComObjectSortCategoryDAO extends BaseDAO<ComObjectSortCategory>
		implements IComObjectSortCategoryDAO {

	@Resource(name = "sqlQuery")
	private SqlQuery sqlQuery;

	@SuppressWarnings("unchecked")
	public List<ComObjectSortCategory> getList(int type) throws Exception {
		String hql = "from ComObjectSortCategory a where  a.sortXtypeId = ?";
		List<ComObjectSortCategory> list = (List<ComObjectSortCategory>) getHibernateTemplate()
				.find(hql, type);
		return list;
	}

	public List<Node> getListTree(String sql, Object... params)
			throws Exception {
		final List<Node> list = new ArrayList<Node>();
		sqlQuery.getJdbcTemplate().query(sql, params, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {

				CategoryTreeNode node = new CategoryTreeNode();
				// node 元素
				node.setObj(null);
				node.setId(rs.getInt("ITEMID"));
				node.setNodeCode(rs.getString("ITEMID"));
				node.setText(rs.getString("CATEGORYNAME"));
				node.setNodeParent(rs.getInt("PARENTLID"));
				// category元素
				node.setTabId(rs.getInt("ID"));
				node.setSortXtypeId(rs.getInt("SORTXTYPEID"));
				node.setItemId(rs.getInt("ITEMID"));
				node.setCategoryCode(rs.getString("CATEGORYCODE"));
				node.setCategoryName(rs.getString("CATEGORYNAME"));
				node.setCategoryPinyin(rs.getString("CATEGORYPINYIN"));
				node.setParentLid(rs.getInt("PARENTLID"));
				node.setLine(rs.getInt("LINE"));
				node.setLevelNumber(rs.getInt("LEVELNUMBER"));
				node.setWebForeColor(rs.getString("WEBFORECOLOR"));
				node.setWebBackColor(rs.getString("WEBBACKCOLOR"));
				node.setOtherOption(rs.getInt("OTHEROPTION"));
				node.setRemark(rs.getString("REMARK"));
				node.setInactive(rs.getInt("INACTIVE"));
				node.setIsSystemDefine(rs.getInt("ISSYSTEMDEFINE"));
				node.setCreator(rs.getInt("CREATOR"));
				node.setCreateTime(rs.getTimestamp("CREATETIME"));
				node.setLastEditor(rs.getInt("LASTEDITOR"));
				node.setLastEditTime(rs.getTimestamp("LASTEDITTIME"));
				node.setVersion(rs.getInt("VERSION"));

				list.add(node);

			}
		});

		return list;
	}

}
