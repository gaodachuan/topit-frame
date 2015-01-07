package com.topit.frame.core.entity.dao.imp;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.topit.frame.core.dao.BaseDAO;
import com.topit.frame.core.dao.SqlQuery;
import com.topit.frame.core.entity.dao.base.ISysUserGroupActionRightDAO;
import com.topit.frame.core.entity.data.SysUserGroupActionRight;

/**
 * @ClassName: SysUserGroupActionRightDAOImp
 * @Description: 系统用户组 模块操作明细表 DAO接口的实现
 * @author qiugui
 * @date 2014年12月3日 下午2:01:46
 * 
 */
@Repository("sysUserGroupActionRightDAOImp")
public class SysUserGroupActionRightDAOImp extends
		BaseDAO<SysUserGroupActionRight> implements
		ISysUserGroupActionRightDAO {

	@Resource(name = "sqlQuery")
	private SqlQuery sqlQuery;
	
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
	 * @see com.topit.frame.core.entity.dao.base.ISysUserGroupActionRightDAO#findByGroupId(java.io.Serializable)
	 */

	@SuppressWarnings("unchecked")
	public List<SysUserGroupActionRight> findByGroupId(
			Serializable groupid) throws Exception {

		String hql = "from SysUserGroupActionRight where GroupId = "
				+ groupid;

		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql);

		List<SysUserGroupActionRight> list = query.list();

		return list;

	}

	/**   
	 * <p>Title: deleteAllAuthorization</p>   
	 * <p>Description: 将对应用户组所选模块的所有权限删除</p>   
	 * @param groupId
	 * @param moduleId
	 * @throws Exception   
	 * @see com.topit.frame.core.entity.dao.base.ISysUserGroupActionRightDAO#deleteAllAuthorization(java.lang.String, java.lang.String)   
	 */
	 
	public void deleteAllAuthorization(String groupId, String moduleId)
			throws Exception {
		
		String hql="DELETE FROM SysUserGroupActionRight WHERE groupId="+groupId+" AND moduleId="+moduleId;
		
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		session.createQuery(hql).executeUpdate();
	}
	
	/**   
	 * <p>Title: saveAllAuthorization</p>   
	 * <p>Description: 将所选模块的所有权限赋予用户组</p>   
	 * @param groupId
	 * @param moduleId
	 * @throws Exception   
	 * @see com.topit.frame.core.entity.dao.base.ISysModuleActionDAO#saveAllAuthorization(java.lang.String, java.lang.String)   
	 */
	 
	public void saveAllAuthorization(String groupId, String moduleId)
			throws Exception {
		
		 String sql="INSERT INTO sys_user_group_action_right "
		 		+ "SELECT "+groupId+" AS GroupId,A.ModuleId,A.ActionId,0,sysdate() "
		 		+ "FROM sys_module_action A LEFT JOIN ("
		 		+ "SELECT * FROM sys_user_group_action_right WHERE GroupId = "+groupId+") B "
		 		+ "ON A.ModuleId=B.ModuleId AND A.ActionId=B.ActionId "
		 		+ "WHERE A.ModuleId = "+moduleId+" AND B.GroupId is null;";
		 
		 sqlQuery.getJdbcTemplate().execute(sql);
	}
	
	/**   
	 * <p>Title: saveCheckedAuthorization</p>   
	 * <p>Description: 将选定的权限赋给相应用户组</p>   
	 * @param groupId
	 * @param values
	 * @throws Exception   
	 * @see com.topit.frame.core.entity.dao.base.ISysModuleActionDAO#saveCheckedAuthorization(java.lang.String, java.lang.String)   
	 */
	 
	public void saveCheckedAuthorization(String groupId,String moduleIds, String values)
			throws Exception {

		String hql="DELETE FROM SysUserGroupActionRight WHERE groupId="+groupId+" AND moduleId IN "+moduleIds;
		
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		session.createQuery(hql).executeUpdate();
		if(!"".equals(values)){
			String sql="INSERT INTO sys_user_group_action_right VALUES "+values+";";
			
			session.createSQLQuery(sql).executeUpdate();
		}
		
		 
	}
	
}
