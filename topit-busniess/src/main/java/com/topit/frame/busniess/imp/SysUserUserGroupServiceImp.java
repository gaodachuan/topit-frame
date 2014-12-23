/**   
* @Title: SysUserUserGroupServiceImp.java 
* @Package com.topit.frame.busniess.imp 
* @Description: TODO(用一句话描述该文件做什么) 
* @author ivan.zhang
* @date 2014年12月10日 上午11:32:59 
* @version V1.0   
*/ 

 package com.topit.frame.busniess.imp;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topit.frame.busniess.base.ISysUserUserGroupService;
import com.topit.frame.core.entity.dao.base.ISysUserUserGroupDAO;
import com.topit.frame.core.entity.data.SysUserUserGroup;
 /** 
 * @ClassName: SysUserUserGroupServiceImp 
 * @Description:系统用户用户组接口实现 
 * @author ivan.zhang 
 * @date 2014年12月10日 上午11:32:59 
 *  
 */
@Service("sysUserUserGroupServiceImp")
@Transactional
public class SysUserUserGroupServiceImp implements ISysUserUserGroupService{
    @Resource(name="sysUserUserGroupDAOImp")
	private ISysUserUserGroupDAO sysUserUserGroupDaoImp;
	/**   
	 * <p>Title: findByUserId</p>   
	 * <p>Description: </p>   
	 * @param userid
	 * @return   
	 * @throws Exception 
	 * @see com.topit.frame.busniess.base.ISysUserUserGroupService#findByUserId(java.io.Serializable)   
	 */
	 
	public List<SysUserUserGroup> findByUserId(Serializable userid) throws Exception {
		// TODO Auto-generated method stub
		 return sysUserUserGroupDaoImp.findByUserId(userid);
		 
	}

}

 