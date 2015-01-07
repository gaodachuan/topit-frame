package com.topit.frame.web.interceptor;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.topit.frame.core.entity.dao.imp.AuthorityCheck;
import com.topit.frame.core.entity.data.SysUser;
import com.topit.frame.core.ui.entity.RequestRight;

/**
 * 
 * @author gaodachuan 权限拦截
 *
 */
public class CheckRightInterceptor implements HandlerInterceptor {
	@Resource
	private AuthorityCheck authorityCheck;
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception arg3)
			throws Exception {
	}
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView mv)
			throws Exception {

	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		boolean res = true;
		Method method = handlerMethod.getMethod();
		SysUser user = (SysUser) request.getSession().getAttribute("SysUser");
		if (user == null) {
			String loginurl = request.getContextPath() + "/login/login.do";
			String url = request.getRequestURL().toString();
			if (url.endsWith(loginurl)) {
				return true;
			} else {
				request.getRequestDispatcher("/WEB-INF/view/login/login.jsp")
						.forward(request, response);
				return false;
			}
		} else {
			RequestRight annotation = method.getAnnotation(RequestRight.class);
			if (annotation != null) {
				res = authorityCheck.check(user.getId().intValue(), method
						.getDeclaringClass().getSimpleName(), method.getName());
			}
		}
		// 没有注解通过拦截
		return res;
	}

}
