package com.topit.frame.common.util;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.topit.frame.common.view.servlet.ImageIcon;

/**
 * 资源管理工具
 * 
 * @author Administrator
 *
 */
public class ResourceUtils {
	private static List<ImageIcon> iconList = new ArrayList<ImageIcon>();
	private static String[] accept = { ".png" };
	private static boolean isFirst = true;

	public static List<ImageIcon> getIcons(String path) {
		if (isFirst) {
			sweep(path);		
		}
		return iconList;
	}

	public static  void updateResource(String path) {
		iconList.clear();
		sweep(path);
	}
    
	private static void isImageIcon(File file) {
		if (isAccept(file)) {
			ImageIcon imageIcon = new ImageIcon();				
			imageIcon.setName(file.getName());
			imageIcon.setPath(file.getPath());
			if(!iconList.contains(imageIcon))
			{
				iconList.add(imageIcon);
			}
			
		}
	}

	private static boolean isAccept(File file) {
		String name = file.getName().substring(file.getName().lastIndexOf("."));
		for (String s : accept) {
			if (s.endsWith(name)) {
				return true;
			}
		}

		return false;
	}

	private  static void sweep(String path) {
		isFirst = false;
		//File file=new File(URI.create(path));
		File target = new File(path);
		File[] listFiles = target.listFiles();
		for (File temp:listFiles) {
			if (temp.isDirectory()) {
				sweep(temp.getPath());
			} else {
				isImageIcon(temp);
			}
		}

	}

}
