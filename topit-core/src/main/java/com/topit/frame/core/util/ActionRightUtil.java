package com.topit.frame.core.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.topit.frame.core.entity.data.SysModuleAction;
import com.topit.frame.core.ui.entity.RequestRight;

/**
 * 扫描生成系统操作数据工具类
 * 
 * @author Administrator
 *
 */
public class ActionRightUtil {

	private static Set<Class<?>> controllers = new HashSet<Class<?>>();

	/**
	 * 获取包下的所有类
	 *
	 * @param packageNames
	 * @return
	 */
	private static Set<Class<?>> initClasses(String[] packageNames) {
		for (String packageName : packageNames) {
			String packageDirName = packageName.replace('.', '/');
			Enumeration<URL> dirs;
			try {
				dirs = Thread.currentThread().getContextClassLoader()
						.getResources(packageDirName);
				while (dirs.hasMoreElements()) {
					URL url = dirs.nextElement();
					String protocol = url.getProtocol();
					if ("file".equals(protocol)) {
						String filePath = URLDecoder.decode(url.getFile(),
								"UTF-8");
						getClassByFile(packageName, filePath);
					} else if ("jar".equals(protocol)) {
						getClassInJar(url, packageDirName, packageName);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
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

	private static void getClassInJar(URL url, String packageDirName,
			String packageName) {
		// 如果是jar包文件
		// 定义一个JarFile
		System.err.println("jar类型的扫描");
		JarFile jar;
		// 获取jar
		try {
			jar = ((JarURLConnection) url.openConnection()).getJarFile();
			// 从此jar包 得到一个枚举类
			Enumeration<JarEntry> entries = jar.entries();
			// 同样的进行循环迭代
			while (entries.hasMoreElements()) {
				// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
				JarEntry entry = entries.nextElement();
				String name = entry.getName();
				// 如果是以/开头的
				if (name.charAt(0) == '/') {
					// 获取后面的字符串
					name = name.substring(1);
				}
				// 如果前半部分和定义的包名相同
				if (name.startsWith(packageDirName)) {
					int idx = name.lastIndexOf('/');
					// 如果以"/"结尾 是一个包
					if (idx != -1) {
						// 获取包名 把"/"替换成"."
						packageName = name.substring(0, idx).replace('/', '.');
					}
					// 如果可以迭代下去 并且是一个包
					if ((idx != -1)) {

						// 如果是一个.class文件 而且不是目录
						if (name.endsWith(".class") && !entry.isDirectory()) {
							// 去掉后面的".class" 获取真正的类名
							String className = name
									.substring(packageName.length() + 1,
											name.length() - 6);
							try {
								// 添加到classes
								controllers.add(Class.forName(packageName + '.'
										+ className));
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
						}

					}

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Set<SysModuleAction> readAllActions(String[] packageNames) {
		Set<SysModuleAction> actions = new HashSet<SysModuleAction>();
		Set<Class<?>> classes = initClasses(packageNames);
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
