 package com.topit.frame.core.entity.dao.base;

import java.io.Serializable;
import java.util.List;

import com.topit.frame.core.dao.IBaseDAO;
import com.topit.frame.core.entity.data.SysUserGroupActionRight;
 /** 
* @ClassName: ISysUserGroupActionRightDAO 
* @Description: 系统用户组 模块操作明细表 DAO接口
* @author qiugui 
* @date 2014年12月3日 下午1:52:45 
*  
*/ 
public interface ISysUserGroupActionRightDAO extends IBaseDAO<SysUserGroupActionRight> {

	
	/**   
	 * @Title: findByGroupId   
	 * @Description: 根据用户组id查询该用户组的拥有的模块权限   
	 * @param groupid
	 * @return
	 * @throws Exception        
	 */
	 
	public List<SysUserGroupActionRight> findByGroupId(Serializable groupid) throws Exception;
}

 