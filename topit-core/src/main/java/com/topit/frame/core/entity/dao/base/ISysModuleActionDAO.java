 package com.topit.frame.core.entity.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.topit.frame.core.dao.IBaseDAO;
import com.topit.frame.core.entity.data.SysModuleAction;
 /** 
* @ClassName: ISysModuleActionDAO 
* @Description: 系统模块操作 明细表 DAO接口 
* @author qiugui 
* @date 2014年12月3日 下午2:02:17 
*  
*/ 
public interface ISysModuleActionDAO extends IBaseDAO<SysModuleAction> {

	 /**   
	 * @Title: findByModuleIdAndActionId   
	 * @Description: 根据模块Id和模块对应的方法Id查询表   
	 * @param moduleid
	 * @param actionid
	 * @return
	 * @throws Exception        
	 */
	 
	public SysModuleAction findByModuleIdAndActionId(Serializable moduleid,Serializable actionid) throws Exception;
	
	
	/**   
	 * @Title: saveAllAuthorization   
	 * @Description: 将所选模块的所有权限赋予用户组   
	 * @param groupId
	 * @param moduleId
	 * @throws Exception        
	 */
	 
	public void saveAllAuthorization(String groupId, String moduleId) throws Exception;
	
	/**   
	 * @Title: saveCheckedAuthorization   
	 * @Description: 将选定的权限保存到数据库中   
	 * @param groupId
	 * @param values
	 * @throws Exception        
	 */
	 
	public void saveCheckedAuthorization(String groupId,String moduleIds,String values) throws Exception;
	
	public List<Map<String, Object>> getList(String groupId,String moduleId) throws Exception;
	
	public List<SysModuleAction> getListAction(String modulePath, int userId) throws Exception;
}

 