/**   
* @Title: UserDaoService.java 
* @Package com.topit.frame.busniess.base 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author ivan.zhang
* @date 2014��11��20�� ����5:51:08 
* @version V1.0   
*/ 

 package com.topit.frame.busniess.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;







import com.topit.frame.core.entity.data.SysUser;
 /** 
 * @ClassName: UserDaoService 
 * @Description: 系统用户接口
 * @author doc.zhou 
 * 
 *  
 */

/** 
* @ClassName: ISysUserService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author ivan.zhang 
* @date 2014年12月17日 上午10:37:17 
*  
*/ 
/** 
* @ClassName: ISysUserService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author ivan.zhang 
* @date 2014年12月17日 上午10:37:22 
*  
*/ 
public interface ISysUserService {
     /**   
     * @Title: add   
     * @Description:添加一个系统用户   
     * @return        
     * @throws Exception 
     */
    public boolean add(SysUser user) throws Exception;
    /**   
     * @Title: getCount   
     * @Description:通过用户Id找到系统用户
     * @param entity
     * @return
     * @throws Exception        
     */
     
    public Object findById(Serializable id) throws Exception;
    /**   
     * @Title: find   
     * @Description:通过系统用户的描述属性找到对应的系统用户   （design all by myself）
     * @param object
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException        
     */
     
    public List<SysUser> find(SysUser object)throws IllegalArgumentException, IllegalAccessException;
    /**   
     * @Title: findByObejct   
     * @Description:ͨ通过系统用户的描述属性找到对应的系统用户    
     * @param user
     * @return        
     * @throws Exception 
     */ 
    public List<SysUser> findByObejct(SysUser user) throws Exception;
    /**   
     * @Title: updateSysUser   
     * @Description:根据用户的修改属性更改系统用户
     * @param user
     * @return
     * @throws Exception        
     */
     
    public boolean updateSysUser(SysUser user) throws Exception;
    /**   
     * @Title: deleteSysUser   
     * @Description:根据用户的某些属性删除系统用户 
     * @param id
     * @return
     * @throws Exception        
     */
     
    public boolean deleteSysUser(SysUser user)throws Exception;
    /**   
     * @Title: findSysUserByLoginName   
     * @Description:通过用户的登录名找到对应的系统用户（LoginName唯一）   
     * @return
     * @throws Exception        
     */
     public SysUser findSysUserByLoginName(String LoginName) throws Exception;
     /**   
     * @Title: getListForPage   
     * @Description:分页   
     * @param criteria
     * @param offset
     * @param length
     * @return
     * @throws Exception        
     */
     
    public List<SysUser> getListForPage(final DetachedCriteria criteria,
 			final int offset, final int length) throws Exception;
    /**   
     * @Title: getCount   
     * @Description:得到系统用户的总记录数   
     * @return
     * @throws Exception        
     */
     
    public int getCount() throws Exception;
    /**   
     * @Title: deleteById   
     * @Description:通过用户Id删除一个系统用户   
     * @param id
     * @return
     * @throws Exception        
     */ 
    public boolean deleteById(Serializable id) throws Exception;
    /**   
     * @Title: loginDoSomeThing   
     * @Description:登录时记录登录时间，和登录的次数   
     * @return
     * @throws Exception        
     */ 
    public void loginDoSomeThing(SysUser user)throws Exception;
    /**   
     * @Title: loginOutDoThings   
     * @Description: 用户退出时修改上次登陆记录   
     * @param user
     * @throws Exception        
     */ 
    public void loginOutDoThings(SysUser user)throws Exception; 
    /**   
     * @Title: getListForPageBysql   
     * @Description:sql语句查询分页   
     * @return
     * @throws Exception        
     */
     
    public List<Map<String,Object>> getListForPageBysql(int pageNow,int pageSize)throws Exception;
    /**   
     * @Title: getSysUserGroupCount   
     * @Description: 系统用户组数量   
     * @return
     * @throws Exception        
     */
     
    public int getSysUserGroupCount()throws Exception;
    /**   
     * @Title: getCountBySysUserNameAndGroupId   
     * @Description: 通过用户名和用户组Id查找记录数   
     * @param sysUserName
     * @param groupId
     * @return
     * @throws Exception        
     */
     
    public int getCountBySysUserNameAndGroupId(String sysUserName,String groupId) throws Exception;
    /**   
     * @Title: getListBySysUserNameAndGroupId   
     * @Description:系统用户名，分组Id分页查询   
     * @param sysUserName
     * @param groupId
     * @param firstResult
     * @param pageSize
     * @return
     * @throws Exception        
     */ 
    public List<Map<String,Object>> getListBySysUserNameAndGroupId(String sysUserName,String groupId,int pageNow,int pageSize)throws Exception;
}

 