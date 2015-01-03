 package com.topit.frame.busniess.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topit.frame.busniess.base.ISysUserErrorLoginLogService;
import com.topit.frame.core.entity.dao.base.ISysUserErrorLoginLogDAO;
import com.topit.frame.core.entity.data.SysUserErrorLoginLog;
 /** 
* @ClassName: SysUserErrorLoginLogServiceImp 
* @Description: 系统用户登陆错误日志服务类 
* @author qiugui 
* @date 2014年11月24日 上午11:48:32 
*  
*/ 
@Service("sysUserErrorLoginLogServiceImp")
@Transactional
public class SysUserErrorLoginLogServiceImp implements ISysUserErrorLoginLogService {

	@Resource(name="sysUserErrorLoginLogDAOImp")
	private ISysUserErrorLoginLogDAO sysUserErrorLoginLogDAOImp;
	
	/**   
	 * <p>Title: save</p>   
	 * <p>Description: 保存系统用户登陆错误日志的信息</p>   
	 * @param sysUserErrorLoginLog
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserErrorLoginLogService#save(com.topit.frame.core.entity.data.SysUserErrorLoginLog)   
	 */
	 
	public boolean save(SysUserErrorLoginLog sysUserErrorLoginLog)
			throws Exception {

		return sysUserErrorLoginLogDAOImp.save(sysUserErrorLoginLog);

	}

}

 