 package com.topit.frame.core.entity.dao.base;

import java.io.Serializable;
import java.util.List;

import com.topit.frame.core.dao.IBaseDAO;
import com.topit.frame.core.entity.data.SysUserUserGroup;
 
/** 
* @ClassName: ISysUserUserGroup 
* @Description: 系统用户  与 系统用户组 关联表DAO接口
* @author qiugui 
* @date 2014年12月3日 下午1:33:08 
*  
*/ 
public interface ISysUserUserGroupDAO extends IBaseDAO<SysUserUserGroup> {

	/**   
	 * @Title: findByUserId   
	 * @Description: 根据用户id查询所属用户组   
	 * @param userid
	 * @return
	 * @throws Exception        
	 */
	 
	public List<SysUserUserGroup> findByUserId(Serializable userid) throws Exception;
}

 