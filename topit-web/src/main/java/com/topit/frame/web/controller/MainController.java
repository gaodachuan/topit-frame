package com.topit.frame.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.topit.frame.busniess.base.ISysMenuService;
import com.topit.frame.common.util.MenuConstant;
import com.topit.frame.common.util.ResourceUtils;
import com.topit.frame.core.entity.data.SysUser;
import com.topit.frame.core.ui.entity.MenuPanle;

/**
 * 
 * @ClassName: MainController
 * @Description:主界面控制器，用于初始化主界面
 * @author gaodachuan
 * @date 2014年11月24日 下午3:44:43
 *
 */
@Controller
@RequestMapping("/main")
public class MainController {
	@Resource
	private ISysMenuService sysMenuService;
	
	@RequestMapping("/index")
	public ModelAndView initMainUI(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		SysUser sysUser = (SysUser) request.getSession()
				.getAttribute("SysUser");
		try {
			
			MenuConstant.MENU_INCON_PATH=request.getRealPath("/")+"//icons//menuIcons";
			//刷新图标库
			ResourceUtils.updateResource(MenuConstant.MENU_INCON_PATH);
			//加载主菜单
			List<MenuPanle> list = sysMenuService.getMenusByUserId(sysUser
					.getId().intValue(),MenuConstant.MENU_MAIN);
			mv.setViewName("/main/index");
			mv.addObject("menus", list);

		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("/login/login");
		}
		return mv;
	}

	@RequestMapping("/header")
	public String initHeaderUI() {
		return "/main/header";
	}

	@RequestMapping("/footer")
	public String initFooterUI() {
		return "/main/footer";
	}
}
