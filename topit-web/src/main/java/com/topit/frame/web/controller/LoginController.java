/**   
* @Title: LoginController.java 
* @Package com.topit.frame.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author ivan.zhang
* @date 2014年12月3日 下午4:15:00 
* @version V1.0   
*/ 

 package com.topit.frame.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topit.frame.busniess.imp.SysUserServiceImp;
import com.topit.frame.common.util.MD5Encrypt;
import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.core.entity.data.SysUser;
 /** 
 * @ClassName: LoginController 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author doc.zhou 
 * @date 2014年12月3日 下午4:15:00 
 *  
 */
@Controller
//@RequestMapping("/users")
public class LoginController {
	@Resource(name="sysUserServiceImp")
	SysUserServiceImp sysUserServiceImpl;
	
	/**   
	 * @Title: login   
	 * @Description:验证用户登录   
	 * @param request
	 * @param response
	 * @return        
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "login/login.do", params = "method=login")
	@ResponseBody
	public ResultObject login(HttpServletRequest request, HttpServletResponse response){
		ResultObject result = new ResultObject();
		String loginname=request.getParameter("loginname");
		String password=request.getParameter("password");
		       password=MD5Encrypt.encipher(password);//Md5加密
		   HttpSession session=request.getSession();
	
		try {
			SysUser user=getLoginUser(loginname,password);
			if(user==null){
			   result.setErrorCode(1);	
			   result.setErrorDetail("登录失败！"); 
			}else{
				sysUserServiceImpl.loginDoSomeThing(user);
				session.setAttribute("SysUser", user);
			
				 result.setErrorCode(0);
				 result.setErrorDetail("登陆成功！"); 
			}
		} catch (Exception e) {
	
			 result.setErrorCode(1);
			 result.setErrorDetail("登录失败！"); 
			 e.printStackTrace();
			 
		}
		return result;
	}
	/**   
	 * @Title: load   
	 * @Description:登录成功后重定向页面   
	 * @return        
	 */ 
	@RequestMapping(value = "login/success.do",method =RequestMethod.GET)
	public String load(){
		return  "/main/index";
	}
	/**   
	 * @Title: loginOut   
	 * @Description:退出登陆操作  
	 * @param request
	 * @param response
	 * @return        
	 */
	@RequestMapping(value = "main/loginOut.do",method =RequestMethod.GET)
	public String loginOut(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		SysUser user=(SysUser)session.getAttribute("SysUser");
		try {
		 sysUserServiceImpl.loginOutDoThings(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
	      }
		session.removeAttribute("SysUser");
		return "/login/login";
	}
	@RequestMapping(value = "login/back.do",method =RequestMethod.GET)
	public String back(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		//session.removeAttribute("SysUser");
		return "/login/login";
	}
	/**   
	 * @Title: forwart   
	 * @Description:跳转到登录页面   
	 * @return        
	 */
	 
	@RequestMapping(value="login/login.do")
	public String forwart(){
		return "/login/login";
	}
	/**   
	 * @Title: getLoginUser   
	 * @Description:通过用户名和密码获得当前登录用户   
	 * @return        
	 * @throws Exception 
	 */
	private SysUser getLoginUser(String loginname,String password) throws Exception{
		SysUser user=new SysUser();
		user.setLoginName(loginname);
		user.setPassWord(password);
	    List<SysUser> list=null;
	    list=sysUserServiceImpl.findByObejct(user);
	    if(list.size()!=0){
	    	user=list.get(0);
	    }else{
	    	user=null;
	    }
		return user;
	}
}

 