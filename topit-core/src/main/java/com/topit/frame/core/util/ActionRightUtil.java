package com.topit.frame.core.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import com.topit.frame.core.entity.data.SysModuleAction;
import com.topit.frame.core.ui.entity.RequestRight;

public class ActionRightUtil {

	private static Set<Class<?>> controllers = new HashSet<Class<?>>();
	

	/**
	 * 获取包下的所有类
	 *
	 * @param pack
	 * @return
	 */
	private static Set<Class<?>> initClasses(String pack) {
		String packageName = pack;
		String packageDirName = packageName.replace('.', '/');
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader()
					.getResources(packageDirName);
			while (dirs.hasMoreElements()) {
				URL url = dirs.nextElement();
				String protocol = url.getProtocol();
				if ("file".equals(protocol)) {
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					getClassByFile(packageName, filePath);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return controllers;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param pkgName
	 *            包名
	 * @param pkgPath
	 *            包路径
	 * @param recursive
	 *            是否迭代
	 */
	private static void getClassByFile(String pkgName, String pkgPath) {
		File dir = new File(pkgPath);
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}

		File[] dirfiles = dir.listFiles(new FileFilter() {

			public boolean accept(File file) {
				return (file.isDirectory())
						|| (file.getName().endsWith(".class"));
			}
		});

		for (File file : dirfiles) {
			// 是目录则继续迭代
			if (file.isDirectory()) {
				getClassByFile(pkgName + "." + file.getName(),
						file.getAbsolutePath());
			} else {
				String className = file.getName().substring(0,
						file.getName().length() - 6);
				String res = pkgName + "." + className;
				try {
					controllers.add(Thread.currentThread()
							.getContextClassLoader().loadClass(res));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Set<SysModuleAction> readAllActions(String packageName) {
		Set<SysModuleAction> actions = new HashSet<SysModuleAction>();
		Set<Class<?>> classes = initClasses(packageName);
		for (Class class1 : classes) {
			Method[] methods = class1.getDeclaredMethods();
			for (Method method : methods) {
				if (method.getAnnotation(RequestRight.class) != null) {
					RequestRight requetRight = method
							.getAnnotation(RequestRight.class);
					SysModuleAction sAction = new SysModuleAction();
					sAction.setActionFunctionName(method.getName());
					sAction.setControllerClassName(class1.getSimpleName());
					sAction.setDescription(requetRight.descrption());
					sAction.setModuleId(requetRight.moduleId());
					sAction.setName(requetRight.name());
					sAction.setLine(requetRight.line());
					sAction.setActionId(requetRight.actionId());
					actions.add(sAction);

				}
			}
		}

		return actions;
	}

}
