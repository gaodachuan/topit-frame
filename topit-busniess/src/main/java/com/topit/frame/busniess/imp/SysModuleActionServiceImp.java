 package com.topit.frame.busniess.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topit.frame.busniess.base.ISysModuleActionService;
import com.topit.frame.core.entity.dao.base.ISysModuleActionDAO;
import com.topit.frame.core.entity.data.SysModuleAction;

@Service(value="sysModuleActionServiceImp") 
@Transactional
public class SysModuleActionServiceImp implements ISysModuleActionService {

	 @Resource(name="sysModuleActionDAOImp")
	 ISysModuleActionDAO sysModuleActionDAOImp;
	
	 public List<Map<String, Object>> getList(String groupId,String moduleId) throws Exception {
		
		
		
		 return sysModuleActionDAOImp.getList(groupId,moduleId);
		 
	}
	 
	 
	 /**   
	 * <p>Title: getListAction</p>   
	 * <p>Description: 根据模块modulePath和用户UserID查询模块权限</p>   
	 * @param modulePath
	 * @param userId
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysModuleActionService#getListAction(java.lang.String, int)   
	 */
	 
	public List<SysModuleAction> getListAction(String modulePath, int userId)
				throws Exception {
			
			 return sysModuleActionDAOImp.getListAction(modulePath, userId);
			 
		}

	public int getCount() throws Exception {
		 
		 return sysModuleActionDAOImp.getCount();
		 
	}

	/**   
	 * <p>Title: saveAllAuthorization</p>   
	 * <p>Description: 将所选模块的所有权限赋予用户组</p>   
	 * @param groupId
	 * @param moduleId
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysModuleActionService#saveAllAuthorization(java.lang.String, java.lang.String)   
	 */
	 
	public void saveAllAuthorization(String groupId, String moduleId)
			throws Exception {
		
		sysModuleActionDAOImp.saveAllAuthorization(groupId, moduleId);
	}

	public void saveCheckedAuthorization(String groupId, String moduleIds,String values)
			throws Exception {
		sysModuleActionDAOImp.saveCheckedAuthorization(groupId,moduleIds, values);
		 
	}

	


}

 