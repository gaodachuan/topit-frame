/**   
 * @Title: SysUserServiceImp.java 
 * @Package com.topit.frame.busniess.imp 
 * @Description: TODO(��һ�仰�������ļ���ʲô) 
 * @author ivan.zhang
 * @date 2014��11��21�� ����9:28:19 
 * @version V1.0   
 */

package com.topit.frame.busniess.imp;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topit.frame.busniess.base.ISysUserService;
import com.topit.frame.core.entity.dao.base.ISysUserDAO;
import com.topit.frame.core.entity.dao.base.ISysUserGroupDAO;
import com.topit.frame.core.entity.data.SysUser;
import com.topit.frame.core.entity.data.SysUserGroup;

/** 
* @ClassName: SysUserServiceImp 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author ivan.zhang 
* @date 2014年12月9日 下午5:35:26 
*  
*/ 
@Service("sysUserServiceImp")
@Transactional
public class SysUserServiceImp implements ISysUserService {

	/**
	 * @Fields dao :
	 */
	@Resource(name = "com.topit.frame.core.entity.dao.imp.SysUserDAOImpl")
	private ISysUserDAO sysUserImpl;
	@Resource(name = "sysUserGroupServiceImp")
	SysUserGroupServiceImp sysUserGroupServiceImp;//系统用户组的业务实现
    public static Map loginTime; 
	/**
	 * <p>
	 * Title: add
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param user
	 * @return
	 * @see com.topit.frame.busniess.base.SysUserService#add(com.topit.frame.core.entity.data.SysUser)
	 */
	@Transactional
	public boolean add(SysUser user) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
        if(findSysUserByLoginName(user.getLoginName())==null){
        	sysUserImpl.save(user);  
        	flag=true;
        }
		return flag;

	}
	/**
	 * <p>
	 * Title: getCount
	 * </p>
	 * <p>
	 * Description:
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 * @see com.topit.frame.busniess.base.ISysUserService#getCount(com.topit.frame.core.entity.data.SysUser)
	 */
	@Transactional
	public SysUser findById(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		return sysUserImpl.findById(id);

	}

	/**   
	 * <p>Title: find</p>   
	 * <p>Description: </p>   
	 * @param object
	 * @return   
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @see com.topit.frame.busniess.base.ISysUserService#find(java.lang.Object)   
	 */
	@Transactional
	public List<SysUser> find(SysUser object) throws IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		 return sysUserImpl.find(object);
		 
	}

	/**   
	 * <p>Title: findByObejct</p>   
	 * <p>Description: </p>   
	 * @param user
	 * @return   
	 * @throws Exception 
	 * @see com.topit.frame.busniess.base.ISysUserService#findByObejct(com.topit.frame.core.entity.data.SysUser)   
	 */
	@Transactional
	public List<SysUser> findByObejct(SysUser user) throws Exception {
		// TODO Auto-generated method stub
		 return sysUserImpl.findByObject(user);
	}

	/**   
	 * <p>Title: updateSysUser</p>   
	 * <p>Description: </p>   
	 * @param user
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserService#updateSysUser(com.topit.frame.core.entity.data.SysUser)   
	 */
	public boolean updateSysUser(SysUser user) throws Exception {
		// TODO Auto-generated method stub
		 return   sysUserImpl.update(user);
		 
	}

	/**   
	 * <p>Title: deleteSysUser</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserService#deleteSysUser(java.io.Serializable)   
	 */
	public boolean deleteSysUser(SysUser user) throws Exception {
		// TODO Auto-generated method stub
		 return sysUserImpl.delete(user);
		 
	}

	/**   
	 * <p>Title: findSysUserByLoginName</p>   
	 * <p>Description: </p>   
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserService#findSysUserByLoginName()   
	 */
	@Transactional
	public SysUser findSysUserByLoginName(String LoginName) throws Exception {
		// TODO Auto-generated method stub
		 return sysUserImpl.findByLoginName(LoginName);
		 
	}

	/**   
	 * <p>Title: getListForPage</p>   
	 * <p>Description: </p>   
	 * @param criteria
	 * @param offset
	 * @param length
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserService#getListForPage(org.hibernate.criterion.DetachedCriteria, int, int)   
	 */
   @Transactional
	public List<SysUser> getListForPage(DetachedCriteria criteria, int offset,
			int length) throws Exception {
		// TODO Auto-generated method stub
		 return (List<SysUser>)sysUserImpl.getListForPage(criteria, offset, length);
		 
	}

	/**   
	 * <p>Title: getCount</p>   
	 * <p>Description: </p>   
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserService#getCount()   
	 */
	 
	public int getCount() throws Exception {
		// TODO Auto-generated method stub
		return sysUserImpl.getCount();
		 
	}
	/**   
	 * <p>Title: deleteById</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserService#deleteById(java.io.Serializable)   
	 */
	 
	public boolean deleteById(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		 return sysUserImpl.delete(id);
		 
	}
	/**   
	 * <p>Title: loginDoSomeThing</p>   
	 * <p>Description: </p>   
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserService#loginDoSomeThing()   
	 */
    @Transactional
	public void loginDoSomeThing(SysUser user) throws Exception {
		// TODO Auto-generated method stub
            setCurrentLoginTimes(user);
            setLoginTime(user);
	}
	/**  
	 * @Title:  getLoginTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Map <BR>  
	 */
	
	public static Map setLoginTime(SysUser user) {
		String name=user.getLoginName();
		 if(loginTime==null){
         	loginTime=new HashMap();
         }
         loginTime.put(name, getCurrentTime());
		return loginTime;
	}
	public Date getLoginTime(Map map,SysUser user){
		return (Date)map.get(user.getLoginName());
	}
	/**   
     * @Title: getCurrentTime   
     * @Description:获得系统当前时间   
     * @return        
     */ 
    private static Date getCurrentTime(){
    	long currentTime = System.currentTimeMillis();

    	//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

    	Date date = new Date(currentTime);

         return date;
    }
    /**   
     * @Title: getLastLoginTimes   
     * @Description:获取用户上一次的登陆次数  
     * @return        
     */
     
    private int getLastLoginTimes(SysUser user){
    	return user.getLoginTimes()==null?0:user.getLoginTimes();
    }
    /**   
     * @Title: getCurrentLoginTimes   
     * @Description:设置用户的当前的登陆次数   
     * @return        
     * @throws Exception 
     */
     
    private void setCurrentLoginTimes(SysUser user) throws Exception{
    	user.setLoginTimes(getLastLoginTimes(user)+1);
    	sysUserImpl.update(user);
    }
	/**   
	 * <p>Title: loginOutDoThings</p>   
	 * <p>Description: </p>   
	 * @param user
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserService#loginOutDoThings(com.topit.frame.core.entity.data.SysUser)   
	 */
	 
	public void loginOutDoThings(SysUser user) throws Exception {
		// TODO Auto-generated method stub
	    Date date=getLoginTime(loginTime,user);
		user.setLastLoginTime(date);
		sysUserImpl.update(user);
	}
	/**   
	 * <p>Title: getListForPageBysql</p>   
	 * <p>Description: </p>   
	 * @param sql
	 * @param firstResult
	 * @param pageSize
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserService#getListForPageBysql(java.lang.String, int, int)   
	 */
	 
	public List<SysUser> getListForPageBysql(String sql, int firstResult,
			int pageSize) throws Exception {
		// TODO Auto-generated method stub
		 return sysUserImpl.getListForPageBysql(sql, firstResult, pageSize);
		 
	}
	/**   
	 * @Title: getListForCombox   
	 * @Description:获得系统用户组的信息   
	 * @return
	 * @throws Exception        
	 */ 
	public List<SysUserGroup> getListForCombox() throws Exception{
		
		 return sysUserGroupServiceImp.getListForCombox();
	}
	/**   
	 * <p>Title: getSysUserGroupCount</p>   
	 * <p>Description: </p>   
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserService#getSysUserGroupCount()   
	 */
	 
	public int getSysUserGroupCount() throws Exception {
		// TODO Auto-generated method stub
		 return sysUserGroupServiceImp.getCount();
		 
	}
	/**   
	 * <p>Title: getCountBySysUserNameAndGroupId</p>   
	 * <p>Description: </p>   
	 * @param sysUserName
	 * @param groupId
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserService#getCountBySysUserNameAndGroupId(java.lang.String, java.lang.String)   
	 */
	 
	public int getCountBySysUserNameAndGroupId(String sysUserName,
			String groupId) throws Exception {
		// TODO Auto-generated method stub
		 return sysUserImpl.getCountBySysUserNameAndGroupId(sysUserName, groupId);
		 
	}
	/**   
	 * <p>Title: getListBySysUserNameAndGroupId</p>   
	 * <p>Description: </p>   
	 * @param sysUserName
	 * @param groupId
	 * @param firstResult
	 * @param pageSize
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserService#getListBySysUserNameAndGroupId(java.lang.String, java.lang.String, int, int)   
	 */
	 
	public List<SysUser> getListBySysUserNameAndGroupId(String sysUserName,
			String groupId, int firstResult, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		 return sysUserImpl.getListBySysUserNameAndGroupId(sysUserName, groupId, firstResult, pageSize);
		 
	}


}
