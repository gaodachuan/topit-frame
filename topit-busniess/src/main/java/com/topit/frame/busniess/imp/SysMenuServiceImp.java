package com.topit.frame.busniess.imp;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.topit.frame.busniess.base.ISysMenuService;
import com.topit.frame.common.util.MenuConstant;
import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.common.view.servlet.ResultPageObject;
import com.topit.frame.core.entity.dao.base.IMenuOptionDao;
import com.topit.frame.core.entity.dao.base.ISysMenuDao;
import com.topit.frame.core.entity.dao.base.ISysModuleDAO;
import com.topit.frame.core.entity.dao.base.ISysUserGroupModuleRightDAO;
import com.topit.frame.core.entity.dao.base.ISysUserUserGroupDAO;
import com.topit.frame.core.entity.dao.imp.IdGenerator;
import com.topit.frame.core.entity.data.SysMenu;
import com.topit.frame.core.entity.data.SysMenuItem;
import com.topit.frame.core.entity.data.SysModule;
import com.topit.frame.core.ui.entity.MenuPanle;
import com.topit.frame.core.ui.entity.MenuUI;

@Service("sysMenuService")
@Transactional
public class SysMenuServiceImp implements ISysMenuService {

	@Resource
	private ISysMenuDao sysMenuDao;
	@Resource
	private IMenuOptionDao menuOptionDao;
	@Resource
	private ISysModuleDAO sysModuleDAO;
	@Resource
	private IdGenerator idGenerator;
	@Resource
	private ISysUserUserGroupDAO sysUserUserGroupDAOImp;
	@Resource
	private ISysUserGroupModuleRightDAO sysUserGroupModuleRightDAOImpl;
	static SysMenuItem sortItem = null;

	public ResultObject wrapResult(boolean flag) {
		ResultObject resultObject = new ResultObject();
		if (flag) {
			resultObject.setErrorCode(0);
			resultObject.setErrorDetail("操作成功");
		} else {
			resultObject.setErrorCode(1);
			resultObject.setErrorDetail("操作失败");
		}
		return resultObject;
	}

	public SysMenu getSysMenuById(Serializable id) throws Exception {

		return sysMenuDao.findById(id);
	}

	public ResultPageObject getSysMenus(int offset, int length)
			throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(SysMenu.class);
		ResultPageObject res = new ResultPageObject();
		res.setRows(sysMenuDao.getListForPage(criteria, offset, length));
		res.setTotal(sysMenuDao.getCount() + "");
		return res;
	}

	public ResultObject createMenu(SysMenu sysMenu) throws Exception {
		sysMenu.setId(idGenerator.getNextId(SysMenu.class.getSimpleName())
				.intValue());
		SysMenuItem sysMenuItem = new SysMenuItem();
		sysMenuItem.setId(idGenerator.getNextId(
				SysMenuItem.class.getSimpleName()).intValue());
		sysMenuItem.setMenuId(sysMenu.getId());
		sysMenuItem.setModuleid(-1);
		sysMenuItem.setParentId(-1);
		sysMenuItem.setName(sysMenu.getName());
		sysMenuItem.setState("closed");
		sysMenu.setRootitemid(sysMenuItem.getId());
		menuOptionDao.save(sysMenuItem);
		return wrapResult(sysMenuDao.save(sysMenu));
	}

	public ResultObject updateMenu(SysMenu sysMenu) throws Exception {
		return wrapResult(sysMenuDao.update(sysMenu));

	}

	public ResultObject delelteMenu(int id) throws Exception {

		return wrapResult(sysMenuDao.delete(id));
	}

	public ResultObject changestatus(int id) throws Exception {
		SysMenu sysMenu = sysMenuDao.load(id);
		sysMenu.setInactive(sysMenu.getInactive() == 0 ? 1 : 0);
		return wrapResult(sysMenuDao.update(sysMenu));
	}

	public List<SysMenuItem> getMenuTree(BigInteger MenuGroupId)
			throws Exception {

		List<SysMenuItem> list = menuOptionDao.getMenuTree(MenuGroupId);
		if (list.size() > 1) {
			for (SysMenuItem s : list) {
				if (s.getNextbrotherid() == -1) {
					sortItem = s;
					break;
				}
			}
			list.remove(sortItem);
			List<SysMenuItem> newlist = new ArrayList<SysMenuItem>();
			newlist.add(sortItem);
			sort(list, newlist);
			Collections.reverse(newlist);
			return newlist;
		}
		return list;

	}

	/**
	 * 
	 * @Title: sort
	 * @Description: 节点排序
	 * @param list
	 * @param newList
	 */

	public static void sort(List<SysMenuItem> list, List<SysMenuItem> newList) {
		if (list.size() != 0) {

			ListIterator<SysMenuItem> it = list.listIterator();
			while (it.hasNext()) {
				SysMenuItem item = it.next();
				if (item.getNextbrotherid() == sortItem.getId()) {
					sortItem = item;
					newList.add(item);
					it.remove();
					break;
				}
			}
			sort(list, newList);
		}

	}

	public boolean addMenuItem(BigInteger moduleId, BigInteger targetId,
			String textName) throws Exception {

		boolean flag = false;
		SysMenuItem sysMenuItem = null;
		if (moduleId.intValue() != -1) {

			// 得到添加的模块的详细信息

			SysModule sysModule = sysModuleDAO.findById(moduleId.intValue());
			// 新建菜单信息
			sysMenuItem = new SysMenuItem(sysModule);
			sysMenuItem.setId(idGenerator.getNextId(
					SysMenuItem.class.getSimpleName()).intValue());
			sysMenuItem.setIconfile(MenuConstant.DEFAULT_MENU_ICON);

		} else {
			sysMenuItem = new SysMenuItem();
			sysMenuItem.setId(idGenerator.getNextId(
					SysMenuItem.class.getSimpleName()).intValue());
			sysMenuItem.setModuleid(-1);
			sysMenuItem.setName(textName);
			sysMenuItem.setTip(textName);
		}
		// 得到插入组位置的信息
		SysMenuItem targetMenuItem = menuOptionDao
				.findById(targetId.intValue());
		
		// 判断目标位置的类型
		if (targetMenuItem.getModuleid() == -1)// 文件夹
		{
			menuOptionDao.append(sysMenuItem, targetMenuItem);
		} else {// 普通菜单,直接插入到菜单后
			menuOptionDao.InsertAfterTarget(sysMenuItem, targetMenuItem);
		}
		sysMenuItem.setMenuId(targetMenuItem.getMenuId());
		// 保存
		menuOptionDao.save(sysMenuItem);
		menuOptionDao.update(targetMenuItem);
		flag = true;
		return flag;

	}

	/**
	 * 
	 * <p>
	 * Title: moveMenuItem
	 * </p>
	 * <p>
	 * 移动节点
	 * </p>
	 * 
	 * @param souceMenuItemId
	 *            源
	 * @param targetMenuItemId
	 *            目标
	 * @param point
	 *            方式
	 * @return
	 * @throws Exception
	 * @see com.topit.frame.busniess.base.ISysMenuService#moveMenuItem(java.math.BigInteger,
	 *      java.math.BigInteger, java.lang.String)
	 */
	public boolean moveMenuItem(BigInteger souceMenuItemId,
			BigInteger targetMenuItemId, String point) throws Exception {
		boolean flag = false;
		SysMenuItem source = menuOptionDao.findById(souceMenuItemId.intValue());
		SysMenuItem target = menuOptionDao
				.findById(targetMenuItemId.intValue());
		// 处理原节点原位置的相关节点
		menuOptionDao.UpdateSourceNodes(source);
		menuOptionDao.getSession().evict(source);
		menuOptionDao.getSession().evict(target);
		if (point.equals("append")) {// 插入到文件夹中
			menuOptionDao.append(source, target);
		} else if (point.equals("top")) {// 插入到节点的前面
			menuOptionDao.InsertBeforeTarget(source, target);
		} else { // 放在节点下面
			menuOptionDao.InsertAfterTarget(source, target);
		}
		// 跟新节点
		menuOptionDao.update(source);
		menuOptionDao.update(target);
		flag = true;
		return flag;

	}

	/**
	 * 
	 * <p>
	 * Title: deleteMenuItem
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param menuItemId
	 * @param MenuGroupId
	 * @return
	 * @throws Exception
	 * @see com.topit.frame.busniess.base.ISysMenuService#deleteMenuItem(java.math.BigInteger,
	 *      java.math.BigInteger)
	 */
	public boolean deleteMenuItem(BigInteger menuItemId) throws Exception {
		boolean flag = false;
		SysMenuItem source = menuOptionDao.findById(menuItemId.intValue());
		// 跟新源节点周围的链接状态
		menuOptionDao.UpdateSourceNodes(source);
		menuOptionDao.delete(menuItemId.intValue());
		flag = true;
		return flag;

	}

	public List<MenuPanle> getMenusByUserId(Serializable id,int menuId) throws Exception {
		List<Map<String, Object>> list = menuOptionDao
				.getMenuItemsByUserId((Integer) id,menuId);
		List<Integer> parentIds = new ArrayList<Integer>();
		for (Map<String, Object> m : list) {
			if (!parentIds.contains(m.get("parentId"))) {
				parentIds.add(Integer.valueOf((String) m.get("parentId")));
			}

		}
		Set<Integer> set = new HashSet<Integer>(parentIds);
		//当前菜单
		SysMenu currMenu=sysMenuDao.findById(menuId);
		// 首先对父菜单进行排序
		List<SysMenuItem> groups = getMenuTree(new BigInteger(currMenu.getRootitemid()+""));
		// 排序后的父节点
		List<SysMenuItem> parentGroups = new ArrayList<SysMenuItem>();

		for (SysMenuItem sysMenuItem : groups) {
			for (Integer i : set) {
				if (sysMenuItem.getId() == i) {
					parentGroups.add(sysMenuItem);
				}
			}

		}
		List<MenuPanle> res = createMenuList(list, parentGroups);
		return res;

	}

	/**
	 * 
	 * @Title: createMenuList
	 * @Description: 包装菜单集合
	 * @param list
	 * @param parentGroups
	 * @return
	 * @throws Exception
	 */
	private List<MenuPanle> createMenuList(List<Map<String, Object>> list,
			List<SysMenuItem> parentGroups) throws Exception {
		List<MenuPanle> res = new ArrayList<MenuPanle>();
		MenuPanle temp = null;
		for (SysMenuItem parent : parentGroups) {
			temp = new MenuPanle();
			temp.setTitle(parent.getName());
			// 对子节点进行排序
			List<SysMenuItem> group = getMenuTree(new BigInteger(parent.getId()
					+ ""));
			for (SysMenuItem sysMenuItem : group) {

				for (Map<String, Object> s : list) {
					if (sysMenuItem.getId() == (Integer) s.get("id")) {
						MenuUI menuUI = new MenuUI();					
					    String simpleName=sysMenuItem.getIconfile().substring(0, sysMenuItem.getIconfile().lastIndexOf("."));	
						menuUI.setMenuIcon(simpleName);
						menuUI.setMenuName(sysMenuItem.getName());
						menuUI.setModuleId(sysMenuItem.getModuleid());
						menuUI.setPath(s.get("modulepath").toString());
						temp.getMenus().add(menuUI);
					}

				}

			}
			res.add(temp);

		}
		return res;
	}

	public List<SysMenuItem> getTopMenuById(Serializable id) throws Exception {
		SysMenuItem menu = menuOptionDao.findById(id);
		List<SysMenuItem> list = new ArrayList<SysMenuItem>();
		list.add(menu);
		return list;
	}

	public void setMenuIcon(int id, String path) throws Exception {
		SysMenuItem menuItem=menuOptionDao.findById(id);
		menuItem.setIconfile(path);
		menuOptionDao.update(menuItem);
	}

}
