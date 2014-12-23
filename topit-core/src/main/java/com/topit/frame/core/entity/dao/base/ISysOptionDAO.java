 package com.topit.frame.core.entity.dao.base;

import java.util.List;
import java.util.Map;

import com.topit.frame.core.dao.IBaseDAO;
import com.topit.frame.core.entity.data.SysOption;
 /** 
* @ClassName: ISysOption 
* @Description: 系统选项DAO接口 
* @author qiugui 
* @date 2014年11月20日 下午1:40:51 
*  
*/ 
public interface ISysOptionDAO extends IBaseDAO<SysOption> {

	/**   
	 * @Title: batchUpdate   
	 * @Description: 批量更新对象   
	 * @param entityList
	 * @return
	 * @throws Exception        
	 */
	 
	public boolean batchUpdate(String hql,List<SysOption> entityList) throws Exception;
	

	/**   
	 * @Title: batchUpdate   
	 * @Description: 批量更新对象   
	 * @param entityList
	 * @return
	 * @throws Exception        
	 */
	 
	public boolean batchUpdate2(String hql,List<Map<String, Object>> sysOptionList) throws Exception;
	
	/**   
	 * @Title: loadAllByCategoryId   
	 * @Description: 根据分类选项id加载数据库中系统选项表的所有当前数据    
	 * @param categoryId
	 * @return
	 * @throws Exception        
	 */
	 
	public List<SysOption> loadAllByCategoryId(String categoryId) throws Exception;
	
}

 