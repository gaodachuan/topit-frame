 package com.topit.frame.core.entity.dao.base;

import java.util.List;
import java.util.Map;

import com.topit.frame.core.dao.IBaseDAO;
import com.topit.frame.core.entity.data.SysUserGroup;
 /** 
* @ClassName: ISysUserGroupDAO 
* @Description: 系统用户组DAO接口 
* @author qiugui 
* @date 2014年11月24日 下午2:19:39 
*  
*/ 
public interface ISysUserGroupDAO extends IBaseDAO<SysUserGroup> {

	/**   
	 * @Title: deleteById   
	 * @Description: 根据用户角色id删除记录   
	 * @param id
	 * @return
	 * @throws Exception        
	 */
	 
	public boolean deleteById(int id) throws Exception;
	
	
	/**   
	 * @Title: changestatus   
	 * @Description: 更改用户组活动状态   
	 * @param sysUserGroup
	 * @return
	 * @throws Exception        
	 */
	 
	public boolean changestatus(SysUserGroup sysUserGroup) throws Exception;
	
	/**   
	 * @Title: getListForPageBySql   
	 * @Description: 使用JDBCTemplate进行多张表的关联分页查询   
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception        
	 */
	 
	public List<Map<String, Object>> getListForPageBySql(final int pageNow,final int pageSize) throws Exception;
	/**   
	 * @Title: getListForCombox   
	 * @Description:查询所有的系统用户组   
	 * @return
	 * @throws Exception        
	 */ 
	public List<SysUserGroup> getListForCombox()throws Exception;
}

 