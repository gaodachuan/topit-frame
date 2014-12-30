package com.topit.frame.busniess.base;

import java.util.List;
import java.util.Map;

import com.topit.frame.core.entity.data.SysModuleAction;

/**
 * @ClassName: ISysModuleActionService
 * @Description: 系统模块操作 服务类
 * @author qiugui
 * @date 2014年12月11日 上午11:25:38
 * 
 */
public interface ISysModuleActionService {

	/**
	 * @Title: getListAction
	 * @Description: 根据模块modulePath和用户UserID查询模块权限
	 * @param modulePath
	 * @param userId
	 * @return
	 * @throws Exception
	 */

	public List<SysModuleAction> getListAction(String modulePath, int userId)
			throws Exception;

	/**
	 * @Title: getList
	 * @Description: 根据moduleId获取该模块的操作
	 * @param moduleId
	 * @return
	 * @throws Exception
	 */

	public List<Map<String, Object>> getList(String groupId, String moduleId)
			throws Exception;

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

	/**
	 * @Title: getCount
	 * @Description: 系统模块操作的记录数
	 * @return
	 * @throws Exception
	 */

	public int getCount() throws Exception;
}
