 package com.topit.frame.core.entity.dao.base;

import java.util.List;

import com.topit.frame.core.entity.data.SysUserGroupModuleRight;
 public interface ISysUserGroupModuleRightDAO {
	 /**
	     * 
	     * @Title: findByGroupIds   
	     * @Description: 根据用户组id集合获取模块权限（去重复）  
	     * @param groupids
	     * @return
	     * @throws Exception
	     */
		public List<SysUserGroupModuleRight> findByGroupIds(Integer[] groupids) throws Exception;
}

 