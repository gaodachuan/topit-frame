package com.topit.frame.core.entity.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.topit.frame.core.dao.BaseDAO;
import com.topit.frame.core.dao.SqlQuery;
import com.topit.frame.core.entity.dao.base.IComObjectSortTypeDAO;
import com.topit.frame.core.entity.data.ComObjectSortType;
import com.topit.frame.core.util.entity.CategoryNode;
import com.topit.frame.core.util.entity.Node;

@Repository("comObjectSortTypeDAO")
public class ComObjectSortTypeDAO extends BaseDAO<ComObjectSortType> implements
		IComObjectSortTypeDAO {

	@Resource(name = "sqlQuery")
	private SqlQuery sqlQuery;

	/**
	 * @Title: getList
	 * @Description: 查询类型列表
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<Node> getListTree(String sql) throws Exception {
		final List<Node> list = new ArrayList<Node>();

		sqlQuery.getJdbcTemplate().query(sql, new RowCallbackHandler() {
			/**
			 * <p>
			 * Title: processRow
			 * </p>
			 * <p>
			 * Description:对查询的对象进行组装
			 * </p>
			 * 
			 * @param arg0
			 * @throws SQLException
			 * @see org.springframework.jdbc.core.RowCallbackHandler#processRow(java.sql.ResultSet)
			 */
			public void processRow(ResultSet rs) throws SQLException {

				CategoryNode node = new CategoryNode();

				node.setObj(null);
				node.setChildren(null);
				node.setId(rs.getInt("CODE"));
				node.setLine(rs.getInt("LINE"));
				node.setTabId(rs.getInt("ID"));
				node.setIconCls(rs.getString("ICONCLS"));
				node.setNodeCode(rs.getString("CODE"));
				node.setText(rs.getString("NAME"));
				node.setNodeParent(rs.getInt("PARENT"));
				node.setTablename(rs.getString("TABLENAME"));

				list.add(node);

			}
		});

		return list;
	}

}
