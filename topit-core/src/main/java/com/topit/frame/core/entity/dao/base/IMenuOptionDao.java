package com.topit.frame.core.entity.dao.base;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.topit.frame.core.dao.IBaseDAO;
import com.topit.frame.core.entity.data.SysMenu;
import com.topit.frame.core.entity.data.SysMenuItem;

/**
 * 
 * @ClassName: IMenuOptionDao
 * @Description: 菜单设置持久层接口
 * @author gaodachuan
 * @date 2014年12月1日 下午5:33:21
 *
 */
public interface IMenuOptionDao extends IBaseDAO<SysMenuItem> {
	/**
	 * 
	 * @Title: getMenuTree
	 * @Description: 返回系统的菜单设置结构（树形结构）
	 * @param MenuItemId
	 *            菜单文件夹节点（第一次是根节点，以后就是文件夹的节点）
	 * @return
	 */
	public List<SysMenuItem> getMenuTree(BigInteger MenuGroupId)
			throws Exception;

	/**
	 * 
	 * @Title: addMenuItem
	 * @Description: 向指定菜单分组中添加菜单（实际上是系统模块）
	 * @param moduleId
	 *            模块ID
	 * @param MenuGroupId
	 *            菜单分组Id
	 * @return
	 */
	public boolean addMenuItem(BigInteger moduleId, BigInteger MenuGroupId,
			String textName) throws Exception;

	/**
	 * 
	 * @Title: deleteMenuItem
	 * @Description: 删除
	 * @param menuItemId
	 *            所属组的Id
	 * @return
	 */
	public boolean deleteMenuItem(BigInteger menuItemId, BigInteger MenuGroupId)
			throws Exception;

	/**
	 * 
	 * @Title: InsertBeforeTarget
	 * @Description: 插入到节点前
	 * @param source
	 *            源
	 * @param target
	 *            目标
	 * @throws Exception
	 */
	public void InsertBeforeTarget(SysMenuItem source, SysMenuItem target)
			throws Exception;

	/**
	 * 
	 * @Title: InsertAfterTarget
	 * @Description: 插入到节点后
	 * @param source
	 *            源
	 * @param target
	 *            目标
	 * @throws Exception
	 */
	public void InsertAfterTarget(SysMenuItem source, SysMenuItem target)
			throws Exception;

	/**
	 * 
	 * @Title: append
	 * @Description: 插入到文件夹
	 * @param source
	 *            源
	 * @param target
	 *            目标
	 * @throws Exception
	 */
	public void append(SysMenuItem source, SysMenuItem target) throws Exception;

	/**
	 * 
	 * @Title: UpdateSourceNodes
	 * @Description: 跟新源节点原位置的相关节点
	 * @param source
	 * @throws Exception
	 */
	public void UpdateSourceNodes(SysMenuItem source) throws Exception;

	/**
	 * 
	 * @Title: getMenusItemByModuleIds
	 * @Description: 根据模块Ids获取菜单集合
	 * @param modules
	 * @return
	 * @throws Exception
	 */
	public List<SysMenuItem> getMenusItemByModuleIds(Integer[] modules)
			throws Exception;

	/**
	 * 
	 * @Title: getMenuGroups
	 * @Description: 获取菜单文件夹
	 * @return
	 * @throws Exception
	 */
	public List<SysMenuItem> getMenuGroups() throws Exception;

	/**
	 * 根据用户的Id加载菜单
	 * 
	 * @param userId
	 * @param menuId 菜单号
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getMenuItemsByUserId(int userId, int menuId)
			throws Exception;

	public Session getSession() throws Exception;
}
