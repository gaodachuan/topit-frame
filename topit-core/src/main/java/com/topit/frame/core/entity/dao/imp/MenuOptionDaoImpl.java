package com.topit.frame.core.entity.dao.imp;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.topit.frame.core.dao.BaseDAO;
import com.topit.frame.core.dao.SqlQuery;
import com.topit.frame.core.entity.dao.base.IMenuOptionDao;
import com.topit.frame.core.entity.dao.base.ISysModuleDAO;
import com.topit.frame.core.entity.data.SysMenuItem;
import com.topit.frame.core.entity.data.SysModule;

/**
 * 
 * @ClassName: MenuOptionDaoImpl
 * @Description: 菜单设置选项持久层实现类
 * @author gaodachuan
 * @date 2014年12月3日 下午2:23:06
 *
 */
@SuppressWarnings("unchecked")
@Repository("menuOptionDao")
public class MenuOptionDaoImpl extends BaseDAO<SysMenuItem> implements
		IMenuOptionDao {
	@Resource
	private ISysModuleDAO sysModuleDAO;
	@Resource
	private IdGenerator idGenerator;
	@Resource
	private SqlQuery sqlQuery;
	private SysMenuItem preItem;

	public List<SysMenuItem> getMenuTree(BigInteger MenuGroupId)
			throws Exception {
		String hql = "From " + entityClass.getSimpleName()
				+ " WHERE ParentId=?";
		Iterator<SysMenuItem> iterator = getSession().createQuery(hql)
				.setParameter(0, MenuGroupId.intValue()).iterate();
		List<SysMenuItem> list = new ArrayList<SysMenuItem>();
		while (iterator.hasNext()) {
			SysMenuItem sysMenuItem = iterator.next();
			if (sysMenuItem.getModuleid() == -1
					&& sysMenuItem.getLeftchildid() == -1) {
				sysMenuItem.setState("closed");
			}
			list.add(sysMenuItem);
		}
		return list;
	}

	public boolean addMenuItem(BigInteger moduleId, BigInteger MenuGroupId,
			String textName) throws Exception {
		boolean flag = false;
		// 得到添加的模块的详细信息
		SysModule sysModule = sysModuleDAO.findById(moduleId);
		// 新建菜单信息
		SysMenuItem sysMenuItem = new SysMenuItem(sysModule);
		sysMenuItem.setId(idGenerator.getNextId(entityClass.getSimpleName())
				.intValue());
		// 得到插入组位置的信息
		SysMenuItem targetMenuItem = findById(MenuGroupId);
		// 判断目标位置的类型
		if (sysMenuItem.getModuleid() == -1)// 文件夹
		{
			// 设置父Id
			sysMenuItem.setParentId(MenuGroupId.intValue());
			if (sysMenuItem.getLeftchildid() == -1)// 无左孩子
			{
				targetMenuItem.setLeftchildid(sysMenuItem.getId());
				// 保存
				update(targetMenuItem);
			} else {
				// 插入到最后
				InsertAsLastBrother(MenuGroupId.intValue(), sysMenuItem.getId());
			}
		} else {// 普通菜单
			// 设置父节点
			sysMenuItem.setParentId(targetMenuItem.getParentId());
			// 设置左孩子
			sysMenuItem.setLeftchildid(targetMenuItem.getLeftchildid());
			// 设置目标左海字
			targetMenuItem.setLeftchildid(sysMenuItem.getId());
			// 跟新
			update(targetMenuItem);
		}
		// 保存
		save(sysMenuItem);
		flag = true;
		return flag;

	}

	/**
	 * 
	 * @Title: InsertAsLastBrother
	 * @Description: 插入到文件夹的最后一个孩子
	 * @param MenuGroupId
	 *            父ID
	 * @param sysMenuItem
	 * @throws Exception
	 */
	public void InsertAsLastBrother(int MenuGroupId, int sourceId)
			throws Exception {
		String s = "From " + entityClass.getSimpleName()
				+ " where ParentId=? and Nextbrotherid=-1";

		// 设置为最后的孩子
		/*
		 * SysMenuItem lastSon=(SysMenuItem) getSession().createQuery(s)
		 * .setParameter(0, MenuGroupId) .uniqueResult();
		 */
		List<SysMenuItem> list = getSession().createQuery(s)
				.setInteger(0, MenuGroupId).list();
		SysMenuItem lastSon = list.get(0);
		lastSon.setNextbrotherid(sourceId);

		// 保存
		update(lastSon);
	}

	/**
	 * 
	 * @Title: getPreBortherItem
	 * @Description: 得到前一个兄弟
	 * @param MenuGroupId
	 * @param sourceId
	 * @return
	 * @throws Exception
	 */
	public SysMenuItem getPreBortherItem(int MenuGroupId, int sourceId)
			throws Exception {
		String s = "From " + entityClass.getSimpleName()
				+ " where ParentId=? and Nextbrotherid=?";

		SysMenuItem pre = (SysMenuItem) getSession().createQuery(s)
				.setParameter(0, MenuGroupId).setParameter(1, sourceId).list()
				.get(0);
		return pre;
	}

	public boolean deleteMenuItem(BigInteger menuItemId, BigInteger MenuGroupId)
			throws Exception {
		return false;

	}

	public void InsertBeforeTarget(SysMenuItem source, SysMenuItem target)
			throws Exception {
		source.setParentId(target.getParentId());
		SysMenuItem parent = findById(source.getParentId());
		if (parent.getLeftchildid() == target.getId())// 成为左孩子
		{
			int temp = parent.getLeftchildid();
			parent.setLeftchildid(source.getId());
			source.setNextbrotherid(temp);
			update(parent);
		} else {
			SysMenuItem preItem = getPreBortherItem(target.getParentId(),
					target.getId());
			int nextBortherId = preItem.getNextbrotherid();

			preItem.setNextbrotherid(source.getId());
			source.setNextbrotherid(nextBortherId);
			update(preItem);
		}

	}

	public void InsertAfterTarget(SysMenuItem source, SysMenuItem target)
			throws Exception {
		source.setParentId(target.getParentId());
		int temp = target.getNextbrotherid();
		target.setNextbrotherid(source.getId());
		source.setNextbrotherid(temp);

	}

	public void append(SysMenuItem source, SysMenuItem target) throws Exception {
		source.setParentId(target.getId());
		if (target.getLeftchildid() == -1)// 空文件夹
		{
			target.setLeftchildid(source.getId());// 成为左孩子
		} else {
			// 得到目标节点的最后一个孩子
			InsertAsLastBrother(target.getId(), source.getId());

			source.setNextbrotherid(-1);
		}

	}

	public void UpdateSourceNodes(SysMenuItem source) throws Exception {
		// 处理移动前的位置
		SysMenuItem parent = findById(source.getParentId());
		preItem = null;
		if (parent.getLeftchildid() == source.getId())// 是左孩子
		{
			if (source.getNextbrotherid() == -1)// 是唯一的孩子
			{
				parent.setLeftchildid(-1);
			} else {
				parent.setLeftchildid(source.getNextbrotherid());
			}
			update(parent);// 跟新父节点
		} else if (parent.getLeftchildid() != -1
				&& (source.getNextbrotherid() == -1))// 最后一个孩子
		{
			preItem = getPreBortherItem(parent.getId(), source.getId());
			preItem.setNextbrotherid(source.getNextbrotherid());
			update(preItem);
		}

		else if (parent.getLeftchildid() != -1
				&& (source.getNextbrotherid() != -1)) {// 是中间节点
			preItem = getPreBortherItem(parent.getId(), source.getId());
			preItem.setNextbrotherid(source.getNextbrotherid());
			update(preItem);
		}

	}

	public Session getSession() throws Exception {

		return getSessionFactory().getCurrentSession();

	}

	public List<SysMenuItem> getMenusItemByModuleIds(Integer[] moduleIds)
			throws Exception {

		String hql = "from SysMenuItem where moduleid in(:moduleIds) ";

		return getSessionFactory().getCurrentSession().createQuery(hql)
				.setParameterList("moduleIds", moduleIds).list();

	}

	public List<SysMenuItem> getMenuGroups() throws Exception {
		String hql = "from SysMenuItem where moduleid =-1 ";

		return getSessionFactory().getCurrentSession().createQuery(hql).list();

	}

	public List<Map<String, Object>> getMenuItemsByUserId(int userId, int menuId)
			throws Exception {
		String sql = "select A.id,A.name,A.IconFile,A.parentId,A.moduleid,B.modulepath "
				+ "from"
				+ "(select id,parentId,name,IconFile,moduleid from sys_menu_item where moduleid "
				+ "in"
				+ "( select distinct(moduleid) from sys_user_group_module_right where GroupId "
				+ "in"
				+ "(select distinct(groupid) from sys_user_user_group where UserId =?)) and menuId=?) A,sys_module B"
				+ " where " + "A.moduleid=B.id";

		final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		sqlQuery.getJdbcTemplate().query(sql, new Object[] { userId, menuId },
				new RowCallbackHandler() {

					public void processRow(ResultSet rs) throws SQLException {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", rs.getInt("id"));
						map.put("name", rs.getString("name"));
						map.put("parentId", rs.getString("parentId"));
						map.put("IconFile", rs.getString("IconFile"));
						map.put("moduleid", rs.getInt("moduleid"));
						map.put("modulepath", rs.getString("modulepath"));
						list.add(map);
					}
				});
		return list;

	}

}
