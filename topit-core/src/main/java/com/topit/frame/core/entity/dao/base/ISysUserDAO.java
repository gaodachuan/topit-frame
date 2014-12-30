/**   
* @Title: ISysUserDAO.java 
* @Package com.topit.frame.core.entity.dao.base 
* @Description: TODO(用一句话描述该文件做什么) 
* @author ivan.zhang
* @date 2014年11月21日 下午1:26:12 
* @version V1.0   
*/ 

 package com.topit.frame.core.entity.dao.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.topit.frame.core.dao.IBaseDAO;
import com.topit.frame.core.entity.data.SysOption;
import com.topit.frame.core.entity.data.SysUser;
 /** 
 * @ClassName: ISysUserDAO 
 * @Description: 系统用户Dao接口
 * @author doc.zhou 
 * @date 2014年11月21日 下午1:26:12 
 *  
 */

public interface ISysUserDAO extends IBaseDAO<SysUser>{


		/**   
		 * @Title: find   
		 * @Description:通过封装好的实体类，查找对应的记录（此方法不适合String类型值为null,int类型值 为0...）  
		 * @param object
		 * @return
		 * @throws IllegalArgumentException
		 * @throws IllegalAccessException        
		 */
		 
		public List<SysUser> find(SysUser object) throws IllegalArgumentException, IllegalAccessException;
        /**   
         * @Title: findByObject   
         * @Description:通过封装好的实体类，查找对应的记录  
         * @param object
         * @return        
         */
         
        public List<SysUser> findByObject(SysUser object)throws Exception;
        /**   
         * @Title: findByLoginName   
         * @Description:通过系统用户的登录名 查找对应的系统用户  
         * @return        
         */
         
        public SysUser findByLoginName(String loginName)throws Exception;
        /**   
         * @Title: getListForPageBysql   
         * @Description: 通过sql语句，分页查询。  
         * @param sql
         * @param firstResult
         * @param pageSize
         * @return
         * @throws Exception        
         */
         
        public List<Map<String, Object>>  getListForPageBysql(int pageNow,int pageSize)throws Exception;
        /**   
         * @Title: getCountBySysUserNameAndGroupId   
         * @Description:查询用户名和用户组获得查询的记录总数   
         * @return
         * @throws Exception        
         */ 
        public int getCountBySysUserNameAndGroupId(String sysUserName,String groupId)throws Exception;
        /**   
         * @Title: getListBySysUserNameAndGroupId   
         * @Description: 根据用户名和用户组Id分页查询
         * @param sysUserName
         * @param groupId
         * @param firstResult
         * @param pageSize
         * @return
         * @throws Exception        
         */ 
        public List<Map<String, Object>> getListBySysUserNameAndGroupId(String sysUserName,String groupId,int pageNow,int pageSize)throws Exception;

}