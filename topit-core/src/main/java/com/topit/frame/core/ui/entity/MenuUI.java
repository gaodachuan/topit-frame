package com.topit.frame.core.ui.entity;

/**
 * 
 * @ClassName: MenuUI
 * @Description: 界面显示的菜单
 * @author gaodachuan
 * @date 2014年12月11日 上午11:31:15
 *
 */
public class MenuUI {

	private int moduleId;// 模块Id
	private String menuName; // 菜单名
	private String menuIcon;// 菜单图标
	private String path;// 功能路径

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
