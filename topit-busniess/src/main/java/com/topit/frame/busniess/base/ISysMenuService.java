package com.topit.frame.busniess.base;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.common.view.servlet.ResultPageObject;
import com.topit.frame.core.entity.data.SysMenu;
import com.topit.frame.core.entity.data.SysMenuItem;
import com.topit.frame.core.ui.entity.MenuPanle;

public interface ISysMenuService {
	
	public List<SysMenuItem>  getTopMenuById(Serializable id) throws Exception;
	/**
	 * 通过id获取菜单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysMenu getSysMenuById(Serializable id) throws Exception;
	/**
	 * 返回系统菜单
	 * @return
	 * @throws Exception
	 */
	public  ResultPageObject getSysMenus(int offset, int length) throws Exception;
	
	/**
	 * 添加一个菜单
	 * @param sysMenu
	 * @return
	 * @throws Exception
	 */
	public ResultObject createMenu(SysMenu sysMenu) throws Exception;
		
	
	/**
	 * 跟新一个菜单
	 * @param sysMenu
	 * @return
	 * @throws Exception
	 */
	public ResultObject updateMenu(SysMenu sysMenu) throws Exception;
	
	/**
	 * 删除一个菜单
	 * @param sysMenu
	 * @return
	 * @throws Exception
	 */
	public ResultObject delelteMenu(int id) throws Exception;
	
	/**
	 * 开启关闭菜单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ResultObject changestatus(int id) throws Exception;
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
	 * @param textname 
	 * @return
	 */
	public boolean addMenuItem(BigInteger moduleId, BigInteger MenuGroupId, String textname)
			throws Exception;

	/**
	 * 
	 * @Title: moveMenuItem
	 * @Description: 移动指定菜单到目标菜单的前后位置
	 * @param souceMenuItemId
	 *            原位置
	 * @param targetMenuItemId
	 *            目标位置
	 * @param point
	 *            移动方式（append,bottom,top）
	 * @return
	 */
	public boolean moveMenuItem(BigInteger souceMenuItemId,
			BigInteger targetMenuItemId, String point) throws Exception;

	/**
	 * 
	 * @Title: deleteMenuItem
	 * @Description: 删除
	 * @param menuItemId
	 *            所属组的Id
	 * @return
	 */
	public boolean deleteMenuItem(BigInteger menuItemId)
			throws Exception;
	/**
	 * 根据用户Id加载用户特有菜单 
	 * @param id 用户Id
	 * @param menuId 菜单号
	 * @return
	 * @throws Exception
	 */
	public  List<MenuPanle> getMenusByUserId(Serializable id,int menuId) throws Exception;
	/**
	 * 修改菜单图标
	 * @param parseInt
	 * @param path
	 * @throws Exception 
	 */
	public void setMenuIcon(int id, String path) throws Exception;
}
