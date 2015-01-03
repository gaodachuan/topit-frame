package com.topit.frame.core.entity.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.topit.frame.core.dao.BaseDAO;
import com.topit.frame.core.entity.dao.base.ISysUserGroupModuleRightDAO;
import com.topit.frame.core.entity.data.SysUserGroupModuleRight;
@Repository(value="sysUserGroupModuleRightDAOImpl")
public class SysUserGroupModuleRightDAOImpl extends
		BaseDAO<SysUserGroupModuleRight> implements ISysUserGroupModuleRightDAO {
	@SuppressWarnings("unchecked")
	public List<SysUserGroupModuleRight> findByGroupIds(Integer[] groupids)
			throws Exception {
		String hql = "from SysUserGroupModuleRight where GroupId in(:groupids) ";

		return getSessionFactory().getCurrentSession().createQuery(hql)
				.setParameterList("groupids", groupids).list();

	}
}
