 package com.topit.frame.busniess.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.topit.frame.core.entity.data.SysUserGroup;
 import com.topit.frame.core.entity.data.SysUserGroupModuleRight;
/** 
* @ClassName: ISysUserGroupService 
* @Description: 系统用户组的服务类接口 
* @author qiugui 
* @date 2014年11月24日 下午2:36:50 
*  
*/ 
public interface ISysUserGroupService {

	/**   
	 * @Title: save   
	 * @Description: 保存一条系统用户组信息   
	 * @param sysUserGroup
	 * @return
	 * @throws Exception        
	 */
	 
	public boolean save(SysUserGroup sysUserGroup) throws Exception;
	
	
	/**   
	 * @Title: deleteById   
	 * @Description: 根据id删除记录   
	 * @param id
	 * @return
	 * @throws Exception        
	 */
	 
	public boolean deleteById(int id) throws Exception;
	
	/**   
	 * @Title: delete   
	 * @Description: 删除该系统用户组信息   
	 * @param sysUserGroup
	 * @return
	 * @throws Exception        
	 */
	
	public boolean delete(SysUserGroup sysUserGroup) throws Exception;
	
	/**   
	 * @Title: update   
	 * @Description: 更新该系统用户组信息  
	 * @param sysUserGroup
	 * @return
	 * @throws Exception        
	 */
	 
	public boolean update(SysUserGroup sysUserGroup) throws Exception;
	
	/**   
	 * @Title: findById   
	 * @Description: 通过用户角色ID查找该系统用户组  数据 
	 * @param id
	 * @return
	 * @throws Exception        
	 */
	 
	public SysUserGroup findById(Serializable id) throws Exception;
	
	/**   
	 * @Title: getListForPage   
	 * @Description: 根据对象进行表的分页查询   
	 * @param criteria
	 * @param offset
	 * @param length
	 * @return
	 * @throws Exception        
	 */
	 
	public List<SysUserGroup> getListForPage(final DetachedCriteria criteria,
			final int offset, final int length) throws Exception; 
	
	/**   
	 * @Title: getListForPage   
	 * @Description: 通过hql进行分页查询   
	 * @param hql
	 * @param firstResult
	 * @param pageSize
	 * @return
	 * @throws Exception        
	 */
	 
	public List<?> getListForPage(String hql,
			final int firstResult, final int pageSize) throws Exception; 
	
	/**   
	 * @Title: getListForPageBySql   
	 * @Description: 使用jdbctemplate创建多表关联的分页查询   
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception        
	 */
	 
	public List<Map<String, Object>> getListForPageBySql(final int pageNow,final int pageSize) throws Exception;
	
	/**   
	 * @Title: getCount   
	 * @Description: 获得表记录总条数   
	 * @return
	 * @throws Exception        
	 */
	 
	public int getCount() throws Exception;
	
	/**   
	 * @Title: changestatus   
	 * @Description: 更改系统用户组的活动状态   
	 * @param sysUserGroup
	 * @return
	 * @throws Exception        
	 */
	public boolean changestatus(SysUserGroup sysUserGroup) throws Exception;
	/**   
	 * @Title: getListForCombox   
	 * @Description:列出系统用户组的所有信息   
	 * @return
	 * @throws Exception        
	 */
	public List<SysUserGroup> getListForCombox() throws Exception;
	
	/**   
	 * @Title: LoadModuleByGroupid   
	 * @Description: 根据用户组id加载其对应的模块信息   
	 * @param groupId
	 * @return
	 * @throws Exception        
	 */ 
	public List<SysUserGroupModuleRight> LoadModuleByGroupid(String groupId) throws Exception;
}

 