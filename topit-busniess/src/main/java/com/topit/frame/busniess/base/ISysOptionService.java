package com.topit.frame.busniess.base;

import java.util.List;
import java.util.Map;

import com.topit.frame.core.entity.data.SysOption;
import com.topit.frame.core.util.entity.TreeDTO;

/**
 * @ClassName: ISysOptionService
 * @Description: 系统选项的服务类接口
 * @author qiugui
 * @date 2014年11月20日 下午3:23:34
 * 
 */
public interface ISysOptionService {

	/**
	 * @Title: batchUpdate
	 * @Description: 批量更新系统选项
	 * @param sysOptionList
	 * @return
	 */
	public boolean batchUpdate2(List<Map<String, Object>> sysOptionList)
			throws Exception;

	/**
	 * @Title: loadAllByCategoryId
	 * @Description: 根据分类选项id加载数据库中系统选项表的所有当前数据
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */

	public List<SysOption> loadAllByCategoryId(String categoryId)
			throws Exception;

	/**
	 * 
	 * @Title: loadAllSysOption
	 * @Description:返回所有的系统选项，以树形结构展示
	 * @return
	 * @throws Exception
	 */
	public List<TreeDTO> loadAllSysOption() throws Exception;

	/**
	 * @Title: batchUpdate
	 * @Description: 批量更新系统选项
	 * @param sysOptions
	 * @return
	 * @throws Exception
	 */

	public boolean batchUpdate(List<SysOption> sysOptions) throws Exception;

}
