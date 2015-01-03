package com.topit.frame.core.ui.entity;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @ClassName: MenuPanle
 * @Description: 首页菜单
 * @author gaodachuan
 * @date 2014年12月10日 下午2:06:02
 *
 */
public class MenuPanle {
	// Title
	private String title = "";
	// 子菜单
	private List<MenuUI> menus = new ArrayList<MenuUI>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<MenuUI> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuUI> menus) {
		this.menus = menus;
	}
}
