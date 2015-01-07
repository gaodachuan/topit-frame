 package com.topit.frame.busniess.base;
 /** 
* @ClassName: ISysUserGroupActionRightService 
* @Description: 系统用户组模块操作 服务类接口 
* @author qiugui 
* @date 2015年1月7日 上午10:39:41 
*  
*/ 
public interface ISysUserGroupActionRightService {
	 
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

 