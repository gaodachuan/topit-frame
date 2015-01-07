 package com.topit.frame.busniess.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topit.frame.busniess.base.ISysUserGroupActionRightService;
import com.topit.frame.core.entity.dao.base.ISysUserGroupActionRightDAO;
 
@Service(value="sysUserGroupActionRightServiceImp")
@Transactional
public class SysUserGroupActionRightServiceImp implements
		ISysUserGroupActionRightService {

	 @Resource(name="sysUserGroupActionRightDAOImp")
	 ISysUserGroupActionRightDAO sysUserGroupActionRightDAOImp; 
	 
	 /**   
	 * <p>Title: deleteAllAuthorization</p>   
	 * <p>Description: 将对应用户组所选模块的所有权限删除</p>   
	 * @param groupId
	 * @param moduleId
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserGroupActionRightService#deleteAllAuthorization(java.lang.String, java.lang.String)   
	 */
	 
	public void deleteAllAuthorization(String groupId, String moduleId)
				throws Exception {
			
			sysUserGroupActionRightDAOImp.deleteAllAuthorization(groupId, moduleId);
			 
		}
	 
	 /**
		 * <p>
		 * Title: saveAllAuthorization
		 * </p>
		 * <p>
		 * Description: 将所选模块的所有权限赋予用户组
		 * </p>
		 * 
		 * @param groupId
		 * @param moduleId
		 * @throws Exception
		 * @see com.topit.frame.busniess.base.ISysModuleActionService#saveAllAuthorization(java.lang.String,
		 *      java.lang.String)
		 */

		public void saveAllAuthorization(String groupId, String moduleId)
				throws Exception {

			sysUserGroupActionRightDAOImp.saveAllAuthorization(groupId, moduleId);

		}

		/**
		 * <p>
		 * Title: saveCheckedAuthorization
		 * </p>
		 * <p>
		 * Description: 将勾选的模块操作权限赋予用户组
		 * </p>
		 * 
		 * @param groupId
		 * @param moduleIds
		 * @param values
		 * @throws Exception
		 * @see com.topit.frame.busniess.base.ISysModuleActionService#saveCheckedAuthorization(java.lang.String,
		 *      java.lang.String, java.lang.String)
		 */

		public void saveCheckedAuthorization(String groupId, String moduleIds,
				String values) throws Exception {

			sysUserGroupActionRightDAOImp.saveCheckedAuthorization(groupId, moduleIds,
					values);

		}


}

 