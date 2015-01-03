 package com.topit.frame.busniess.base;

import com.topit.frame.core.entity.data.SysUserErrorLoginLog;
 
 
 /** 
* @ClassName: ISysUserErrorLoginLogService 
* @Description: 系统用户登陆错误日志服务接口 
* @author qiugui 
* @date 2014年11月24日 上午11:41:30 
*  
*/ 
public interface ISysUserErrorLoginLogService {

	/**   
	 * @Title: save   
	 * @Description: 保存错误登陆的用户信息   
	 * @param sysUserErrorLoginLog
	 * @return
	 * @throws Exception        
	 */
	 
	public boolean save(SysUserErrorLoginLog sysUserErrorLoginLog) throws Exception;
}

 