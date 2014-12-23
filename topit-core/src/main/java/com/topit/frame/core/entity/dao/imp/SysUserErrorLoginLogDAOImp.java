 package com.topit.frame.core.entity.dao.imp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.topit.frame.core.dao.BaseDAO;
import com.topit.frame.core.entity.dao.base.ISysUserErrorLoginLogDAO;
import com.topit.frame.core.entity.data.SysUserErrorLoginLog;
 /** 
* @ClassName: SysUserErrorLoginLogDAOImp 
* @Description: 系统用户登陆错误日志DAO实现 
* @author qiugui 
* @date 2014年11月24日 上午11:37:00 
*  
*/ 
@Repository("sysUserErrorLoginLogDAOImp")
@Scope("prototype")
public class SysUserErrorLoginLogDAOImp extends BaseDAO<SysUserErrorLoginLog> implements
		ISysUserErrorLoginLogDAO {

	/**   
	 * <p>Title: save</p>   
	 * <p>Description: 保存错误登陆的用户信息</p>   
	 * @param entity
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.core.dao.BaseDAO#save(java.lang.Object)   
	 */
	 
	public boolean save(SysUserErrorLoginLog entity) throws Exception {

		return super.save(entity);

	}

}

 