package com.topit.frame.web.interceptor;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.topit.frame.core.entity.data.SysUser;

public class ControllerInterceptor implements HandlerInterceptor {
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		 String url=request.getRequestURI();
		 String query=request.getQueryString();
         if(!url.endsWith("login.do")){
    	 SysUser user=findUserBySession(request,response);
   	         sendRedirectByUser(user,request,response);
		  }
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}
	/**   
	 * @Title: localUrlAndPara   
	 * @Description:找出session的用户   
	 * @return        
	 */
	   private SysUser findUserBySession(HttpServletRequest request,HttpServletResponse response){
		SysUser user=null;

	    HttpSession session=request.getSession();
		user=(SysUser)session.getAttribute("SysUser");
		return user;
	}
      /**   
     * @Title: sendRedirectByUser   
     * @Description:系统用户决定页面跳转   
     * @param user
     * @param response
     * @throws IOException        
     */
     
    private void sendRedirectByUser(SysUser user,HttpServletRequest request,HttpServletResponse response) throws IOException{
    	  if(user==null){
    		   response.sendRedirect(request.getContextPath()+"/login/login.do?");
    	 	}
       }
}
