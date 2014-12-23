 package com.topit.frame.core.entity.dao.base;
 /** 
* @ClassName: AuthorityCheck 
* @Description: 权限检查接口 
* @author qiugui 
* @date 2014年12月3日 下午5:10:00 
*  
*/ 
public interface IAuthorityCheck {

	/**   
	 * @Title: check   
	 * @Description: 通过传入用户id和对应的控制器类名、方法名判断用户是否具有该操作权限   
	 * @param userid
	 * @param controllerClassName
	 * @param actionFunctionName
	 * @return
	 * @throws Exception        
	 */
	 
	public boolean check(int userid,String controllerClassName,String actionFunctionName) throws Exception;
}

 