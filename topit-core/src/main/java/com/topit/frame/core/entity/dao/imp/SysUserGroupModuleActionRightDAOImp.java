package com.topit.frame.core.entity.dao.imp;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.topit.frame.core.dao.BaseDAO;
import com.topit.frame.core.entity.dao.base.ISysUserGroupModuleActionRightDAO;
import com.topit.frame.core.entity.data.SysUserGroupModuleActionRight;

/**
 * @ClassName: SysUserGroupModuleActionRightDAOImp
 * @Description: 系统用户组 模块操作明细表 DAO接口的实现
 * @author qiugui
 * @date 2014年12月3日 下午2:01:46
 * 
 */
@Repository("sysUserGroupModuleActionRightDAOImp")
public class SysUserGroupModuleActionRightDAOImp extends
		BaseDAO<SysUserGroupModuleActionRight> implements
		ISysUserGroupModuleActionRightDAO {

	/**
	 * <p>
	 * Title: findByGroupId
	 * </p>
	 * <p>
	 * Description: 根据用户组id查询该用户组的拥有的模块权限
	 * </p>
	 * 
	 * @param groupid
	 * @return
	 * @throws Exception
	 * @see com.topit.frame.core.entity.dao.base.ISysUserGroupModuleActionRightDAO#findByGroupId(java.io.Serializable)
	 */

	@SuppressWarnings("unchecked")
	public List<SysUserGroupModuleActionRight> findByGroupId(
			Serializable groupid) throws Exception {

		String hql = "from SysUserGroupModuleActionRight where GroupId = "
				+ groupid;

		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql);

		List<SysUserGroupModuleActionRight> list = query.list();

		return list;

	}

	
}
