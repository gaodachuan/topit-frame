package com.topit.frame.web.controller;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.housemanagersystem.util.Constant;
import com.topit.frame.busniess.base.IComObjectSortTypeService;
import com.topit.frame.busniess.base.ISysMenuService;
import com.topit.frame.common.util.CategoryConstant;
import com.topit.frame.common.util.MenuConstant;
import com.topit.frame.common.util.ResourceUtils;
import com.topit.frame.common.view.servlet.ImageIcon;
import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.common.view.servlet.ResultPageObject;
import com.topit.frame.core.entity.data.SysMenu;
import com.topit.frame.core.entity.data.SysMenuItem;
import com.topit.frame.core.util.entity.Node;

/**
 * 
 * @ClassName: MenuOptionController
 * @Description:系统菜单设置的的逻辑控制器
 * @author gaodachuan
 * @date 2014年12月1日 下午5:08:31
 *
 */
@Controller
@RequestMapping("/MenuOption")
public class MenuOptionController {
	@Resource
	private ISysMenuService sysMenuService;
	@Resource(name = "comObjectSortTypeService")
	private IComObjectSortTypeService comObjectSortTypeService;

	/**
	 * 
	 * @Title: init
	 * @Description: 初始化菜单设置的界面
	 * @return
	 */
	@RequestMapping("/init")
	public String init() {
		return "/menu/MenuManager";
	}

	@RequestMapping("/getSysMenus")
	@ResponseBody
	public ResultPageObject getSysMenus(HttpServletRequest request)
			throws Exception {
		int currentPage = Integer.parseInt(request
				.getParameter(Constant.Entity_Field.CURRENT_PAGE));
		int pageSize = Integer.parseInt(request
				.getParameter(Constant.Entity_Field.PAGE_SIZE));
		return sysMenuService.getSysMenus((currentPage - 1) * pageSize,
				pageSize);
	}

	@RequestMapping("/createMenu")
	@ResponseBody
	public ResultObject createMenu(SysMenu menu) throws Exception {

		return sysMenuService.createMenu(menu);
	}

	@RequestMapping("/updateMenu")
	@ResponseBody
	public ResultObject updateMenu(SysMenu menu) throws Exception {
		return sysMenuService.updateMenu(menu);
	}

	@RequestMapping("/deleteMenu")
	@ResponseBody
	public ResultObject deleteMenu(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		return sysMenuService.delelteMenu(Integer.parseInt(id));
	}

	@RequestMapping("/changestatus")
	@ResponseBody
	public ResultObject changestatus(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		return sysMenuService.changestatus(Integer.parseInt(id));
	}

	@RequestMapping("/addMenus")
	public ModelAndView openMenuOptionUI(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/menu/MenuOption");

		mv.addObject("menuId",
				sysMenuService.getSysMenuById(Integer.parseInt(id)));
		return mv;
	}

	@RequestMapping("/getMenuIcons")
	@ResponseBody
	public List<ImageIcon> getImageIcon() {
		return ResourceUtils.getIcons(MenuConstant.MENU_INCON_PATH);
	}

	@RequestMapping("/setMenuIcon")
	public void setMenuIcon(HttpServletRequest request) throws  Exception {
		String id = request.getParameter("id");
		String path = request.getParameter("path");
		sysMenuService.setMenuIcon(Integer.parseInt(id),path);
	}

	/**
	 * 
	 * @Title: getMenuOptions
	 * @Description: 获取菜单设置选项树形结构图
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getMenuOptions")
	@ResponseBody
	public List<SysMenuItem> getMenuOptions(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		if (id.contains("#")) {
			id = id.substring(id.indexOf("#") + 1);
			return sysMenuService.getTopMenuById(Integer.parseInt(id));
		}

		return sysMenuService.getMenuTree(new BigInteger(id));
	}

	/**
	 * 
	 * @Title: MoveMenuItem
	 * @Description: 移动菜单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/MoveMenuItem")
	@ResponseBody
	public ResultObject moveMenuItem(HttpServletRequest request) {
		String sourceId = request.getParameter("source");
		String targetId = request.getParameter("target");
		String point = request.getParameter("point");
		ResultObject result = new ResultObject();
		try {
			sysMenuService.moveMenuItem(new BigInteger(sourceId),
					new BigInteger(targetId), point);
			result.setErrorCode(0);
			result.setErrorDetail("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorCode(1);
			result.setErrorDetail("操作失败！");
		}
		return result;
	}

	@RequestMapping(value = "/addMenuItem")
	@ResponseBody
	public ResultObject addMenuItem(HttpServletRequest request) {

		String parentId = request.getParameter("parentId");
		String moduleid = request.getParameter("moduleLid");
		String textname = request.getParameter("textname");
		ResultObject result = new ResultObject();
		try {
			sysMenuService.addMenuItem(new BigInteger(moduleid),
					new BigInteger(parentId), textname);
			result.setErrorCode(0);
			result.setErrorDetail("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorCode(1);
			result.setErrorDetail("操作失败！");

		}
		return result;
	}

	@RequestMapping(value = "/DeleteMenuItem")
	@ResponseBody
	public ResultObject deleteMenuItem(HttpServletRequest request) {
		String currentId = request.getParameter("currentId");
		ResultObject result = new ResultObject();
		try {
			sysMenuService.deleteMenuItem(new BigInteger(currentId));
			result.setErrorCode(0);
			result.setErrorDetail("操作成功！");

		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorCode(1);
			result.setErrorDetail("操作失败！");

		}
		return result;
	}

	@RequestMapping(value = "/LoadMudelList")
	@ResponseBody
	public List<Node> loadMudelList() {
		try {
			List<Node> list = comObjectSortTypeService
					.getTree(CategoryConstant.MOUDLE_TYPE_SQL);
			return list;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
}
