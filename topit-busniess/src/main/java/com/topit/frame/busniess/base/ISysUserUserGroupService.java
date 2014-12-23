/**   
* @Title: ISysUserUserGroupService.java 
* @Package com.topit.frame.busniess.base 
* @Description: TODO(用一句话描述该文件做什么) 
* @author ivan.zhang
* @date 2014年12月10日 上午11:27:55 
* @version V1.0   
*/ 

 package com.topit.frame.busniess.base;

import java.io.Serializable;
import java.util.List;

import com.topit.frame.core.entity.data.SysUserUserGroup;
 /** 
 * @ClassName: ISysUserUserGroupService 
 * @Description:系统用户用户组接口
 * @author doc.zhou 
 * @date 2014年12月10日 上午11:27:55 
 *  
 */

public interface ISysUserUserGroupService {
	/**   
	 * @Title: findByUserId   
	 * @Description:通过用户Id查找用户的用户组   
	 * @param userid
	 * @return        
	 */
	 
	List<SysUserUserGroup> findByUserId(Serializable userid) throws Exception;
}

 