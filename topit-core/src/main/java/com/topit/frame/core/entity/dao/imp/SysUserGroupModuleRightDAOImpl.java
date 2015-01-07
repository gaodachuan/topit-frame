package com.topit.frame.core.entity.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.topit.frame.core.dao.BaseDAO;
import com.topit.frame.core.entity.dao.base.ISysUserGroupModuleRightDAO;
import com.topit.frame.core.entity.data.SysUserGroupModuleRight;
@Repository(value="sysUserGroupModuleRightDAOImpl")
public class SysUserGroupModuleRightDAOImpl extends
		BaseDAO<SysUserGroupModuleRight> implements ISysUserGroupModuleRightDAO {
	@SuppressWarnings("unchecked")
	public List<SysUserGroupModuleRight> findByGroupIds(List<Integer> groupids)
			throws Exception {
		String hql = "from SysUserGroupModuleRight where groupid IN (:groupids) ";
		List<SysUserGroupModuleRight> list=getSessionFactory().getCurrentSession().createQuery(hql)
				.setParameterList("groupids", groupids).list();
		return list;

	}

	public boolean add(SysUserGroupModuleRight sysUserGroupModuleRight) throws Exception {
		
		return super.save(sysUserGroupModuleRight);
	}

	public boolean delete(SysUserGroupModuleRight sysUserGroupModuleRight) throws Exception {
		
		String hql= " DELETE SysUserGroupModuleRight a WHERE a.groupid=? and a.moduleid=?";
		boolean flag = false;
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery(hql);
			query.setParameter(0, sysUserGroupModuleRight.getGroupid());
			query.setParameter(1, sysUserGroupModuleRight.getModuleid());
			query.executeUpdate();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}
}
