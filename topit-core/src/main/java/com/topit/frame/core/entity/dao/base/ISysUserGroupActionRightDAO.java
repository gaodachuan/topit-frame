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
	
	/**   
	 * @Title: deleteAllAuthorization   
	 * @Description: 将对应用户组所选模块的所有权限删除   
	 * @param groupId
	 * @param moduleId
	 * @throws Exception        
	 */
	 
	public void deleteAllAuthorization(String groupId, String moduleId) throws Exception;
	
	/**
	 * @Title: saveAllAuthorization
	 * @Description: 将所选模块的所有权限赋予用户组
	 * @param groupId
	 * @param moduleId
	 * @throws Exception
	 */

	public void saveAllAuthorization(String groupId, String moduleId)
			throws Exception;
	
	/**
	 * @Title: saveCheckedAuthorization
	 * @Description: 将选定的权限保存到数据库中
	 * @param groupId
	 * @param values
	 * @throws Exception
	 */

	public void saveCheckedAuthorization(String groupId, String moduleIds,
			String values) throws Exception;
}

 