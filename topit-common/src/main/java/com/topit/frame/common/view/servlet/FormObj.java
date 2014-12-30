package com.topit.frame.common.view.servlet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，实现多个自定义类参数的数据绑定
 * 
 * @author Administrator
 *
 */
@Target({ ElementType.PACKAGE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FormObj {
	// 参数别名
	String value() default "";
    //是否展示
	boolean show() default true;
}
