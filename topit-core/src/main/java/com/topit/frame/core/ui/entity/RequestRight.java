package com.topit.frame.core.ui.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName: requetRight
 * @Description: 权限注解
 * @author gaodachuan
 * @date 2015年1月5日 下午2:26:34
 *
 */
@Target({ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestRight {
	/**
	 * 
	 * @Title: name
	 * @Description:操作名
	 * @return
	 */
	String name() default "";

	/**
	 * 
	 * @Title: moduleId
	 * @Description: 所属模块
	 * @return
	 */
	int moduleId() default -1;

	/**
	 * 
	 * @Title: actionId
	 * @Description: 模块对应下的操作权限编号
	 * @return
	 */
	int actionId() default -1;
	
	/**
	 * 
	 * @Title: line   
	 * @Description:排序 
	 * @return
	 */
	int line() default 1;

	/**
	 * 
	 * @Title: descrption
	 * @Description: 操作描述
	 * @return
	 */
	String descrption() default "";

}
