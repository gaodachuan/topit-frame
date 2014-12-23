 package com.topit.frame.core.entity.dao.imp;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.topit.frame.core.dao.BaseDAO;
import com.topit.frame.core.dao.SqlQuery;
import com.topit.frame.core.entity.dao.base.ISysUserGroupDAO;
import com.topit.frame.core.entity.data.SysUser;
import com.topit.frame.core.entity.data.SysUserGroup;
 /** 
* @ClassName: SysUserGroupDAOImp 
* @Description: 系统用户组DAO接口的实现 
* @author qiugui 
* @date 2014年11月24日 下午2:26:35 
*  
*/ 
@Repository("sysUserGroupDAOImp")
@Scope("prototype")
public class SysUserGroupDAOImp extends BaseDAO<SysUserGroup> implements ISysUserGroupDAO {

	@Resource(name="sqlQuery")
	SqlQuery sqlQuery;

	/**   
	 * <p>Title: save</p>   
	 * <p>Description: 保存一条 系统用户组 数据</p>   
	 * @param entity
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.core.dao.BaseDAO#save(java.lang.Object)   
	 */
	 
	public boolean save(SysUserGroup entity) throws Exception {

		return super.save(entity);

	}

	/**   
	 * <p>Title: delete</p>   
	 * <p>Description: 删除一条 系统用户组 数据</p>   
	 * @param entity
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.core.dao.BaseDAO#delete(java.lang.Object)   
	 */
	 
	public boolean delete(SysUserGroup entity) throws Exception {

		return super.delete(entity);

	}
	
	/**   
	 * <p>Title: deleteById</p>   
	 * <p>Description: 根据用户角色id删除记录</p>   
	 * @param id
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.core.entity.dao.base.ISysUserGroupDAO#deleteById(int)   
	 */
	 
	public boolean deleteById(int id) throws Exception{
		boolean flag=false;
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		Query query=session.createQuery("delete SysUserGroup where id="+id);
		
		try {
			query.executeUpdate();
			flag=true;
		} catch (Exception e) {
			flag=false;
			throw new RuntimeException(e.getMessage());
		}
		return flag;
	}

	/**   
	 * <p>Title: update</p>   
	 * <p>Description: 更新 该系统用户组信息</p>   
	 * @param entity
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.core.dao.BaseDAO#update(java.lang.Object)   
	 */
	 
	public boolean update(SysUserGroup entity) throws Exception {

		String hql="update SysUserGroup set Name=?,Description=?,LastEditTime=?,LastEditor=? where Id=?";
		
		Object params[]=new Object[5];
		
		params[0]=entity.getName();
		params[1]=entity.getDescription();
		params[2]=entity.getLastEditTime();
		params[3]=entity.getLastEditor();
		params[4]=entity.getId();
		
		return super.update(hql, params);

	}

	/**   
	 * <p>Title: findById</p>   
	 * <p>Description: 通过用户角色ID查找相应的用户组信息</p>   
	 * @param id
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.core.dao.BaseDAO#findById(java.io.Serializable)   
	 */
	 
	public SysUserGroup findById(Serializable id) throws Exception {
		
		return super.findById(id);

	}

	/**   
	 * <p>Title: changestatus</p>   
	 * <p>Description: 更改用户组的活动状态</p>   
	 * @param sysUserGroup
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.core.entity.dao.base.ISysUserGroupDAO#changestatus(com.topit.frame.core.entity.data.SysUserGroup)   
	 */
	 
	public boolean changestatus(SysUserGroup sysUserGroup) throws Exception {
		return super.update(sysUserGroup);
	}

	/**   
	 * <p>Title: getListForPageBySql</p>   
	 * <p>Description: 使用JDBCTemplate进行多张表的关联分页查询</p>   
	 * @param firstResult
	 * @param pageSize
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.core.entity.dao.base.ISysUserGroupDAO#getListForPageBySql(java.lang.String, int, int)   
	 */
	 
	public List<Map<String, Object>> getListForPageBySql(int firstResult, int pageSize) throws Exception {
		String sql="SELECT A.id,A.name,A.description,A.lastRightEditTime,A.createTime,COUNT(B.userId) AS usernum,A.inactive"
				+ " FROM sys_user_group AS A LEFT JOIN sys_user_user_group AS B "
				+ "ON A.id=B.groupId "
				+ "GROUP BY A.id LIMIT ?,?";
		final List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		
		sqlQuery.getJdbcTemplate().query(sql,new Object[]{firstResult, pageSize},new RowCallbackHandler() {
			
			public void processRow(ResultSet rs) throws SQLException {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("id", rs.getInt("Id"));
				map.put("name", rs.getString("Name"));
				map.put("description", rs.getString("Description"));
				map.put("lastRightEditTime", rs.getTimestamp("LastRightEditTime"));
				map.put("createTime", rs.getTimestamp("CreateTime"));
				map.put("usernum", rs.getInt("usernum"));
				map.put("inactive", rs.getInt("Inactive"));
				list.add(map);
			}
		});
		
		 return list;
		 
	}

	/**   
	 * <p>Title: getListForCombox</p>   
	 * <p>Description: </p>   
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.core.entity.dao.base.ISysUserGroupDAO#getListForCombox()   
	 */
	 
	public List<SysUserGroup> getListForCombox() throws Exception {
		// TODO Auto-generated method stub
		List<SysUserGroup> list=new ArrayList();
		String hql= "from SysUserGroup ";
	     list=(List<SysUserGroup>)this.getHibernateTemplate().find(hql);
		 return list; 
	}

	


}

 