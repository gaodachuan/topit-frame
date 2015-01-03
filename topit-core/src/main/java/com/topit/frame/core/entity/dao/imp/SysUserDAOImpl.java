/**   
* @Title: SysUserDAO.java 
* @Package com.topit.frame.core.entity.dao.imp 
* @Description: TODO(用一句话描述该文件做什么) 
* @author ivan.zhang
* @date 2014年11月20日 下午4:54:40 
* @version V1.0   
*/ 

 package com.topit.frame.core.entity.dao.imp;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.topit.frame.core.dao.BaseDAO;
import com.topit.frame.core.dao.SqlQuery;
import com.topit.frame.core.entity.dao.base.ISysUserDAO;
import com.topit.frame.core.entity.data.SysUser;
 /** 
 * @ClassName: SysUserDAO 
 * @Description: 系统用户Dao
 * @author doc.zhou 
 * @date 2014年11月20日 下午4:54:40 
 *  
 */

@Repository("com.topit.frame.core.entity.dao.imp.SysUserDAOImpl")
@Scope("prototype")
public class SysUserDAOImpl extends BaseDAO<SysUser> implements ISysUserDAO{

	/**   
	 * <p>Title: save</p>   
	 * <p>Description:保存一个用户 </p>   
	 * @param entity
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.core.dao.BaseDAO#save(java.lang.Object)   
	 */
	@Resource(name="sqlQuery")
	SqlQuery sqlQuery;
	public boolean save(SysUser entity) throws Exception {
		// TODO Auto-generated method stub
		 return super.save(entity);
		 
	}

	/**   
	 * <p>Title: getCount</p>   
	 * <p>Description:查找指定用户
	 * @param id
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.core.dao.BaseDAO#getCount(java.lang.Object)   
	 */
	 
	@Override
	public SysUser findById(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		 return super.findById(id);
		 
	}

		public List<SysUser> find(SysUser object) throws IllegalArgumentException, IllegalAccessException{
			List<SysUser> list = null;
			String hql= "from sys_user u where 1=1";
			int index=0;
			List parameters=new ArrayList();//存放参数
			Class type=object.getClass();
		       if(object!=null){
		    	   Field[]declaredFields=type.getDeclaredFields();
		    	   for(int i=0;i<declaredFields.length;i++){
		    		   Field filed=declaredFields[i];
		    		   filed.setAccessible(true);
		    		   String column=filed.getName();
		    		   Object parameter=null;
		    		   parameter=filed.get(object);
		    		   if(parameter!=null&&parameter.hashCode()!=0&&!column.equals("serialVersionUID")){
		    			  parameters.add(parameter); 
		    			  hql+=" and u."+column+"=?";
		    			  System.out.println(hql);
		    		   }
		    		  
		    	   }
		       } 
		       Object[]str=new Object[parameters.size()];
		       for(Object ob:parameters){
		    	   str[index]=ob;
		    	   index++;
		       }
		       list=(List<SysUser>)this.getHibernateTemplate().find(hql,str);
		         return list;
		}

	 
	public List<SysUser> findByObject(SysUser object) {
		// TODO Auto-generated method stub
		 return this.getHibernateTemplate().findByExample(object); 
	}
    /**   
     * @Title: update   
     * @Description:更新系统用户   
     * @return        
     * @throws Exception 
     */
     
    public boolean update(SysUser object) throws Exception{
       return super.update(object);
    }
    /**   
     * <p>Title: delete</p>   
     * <p>Description:根据id删除用户   
     * @param object
     * @return
     * @throws Exception   
     * @see com.topit.frame.core.dao.BaseDAO#delete(java.lang.Object)   
     */
     
    public boolean delete(SysUser object) throws Exception{
    	return super.delete(object);
    }

	/**   
	 * <p>Title: findByLoginName</p>   
	 * <p>Description: </p>   
	 * @param loginName
	 * @return   
	 * @throws Exception 
	 * @see com.topit.frame.core.entity.dao.base.ISysUserDAO#findByLoginName(java.lang.String)   
	 */
	 
	public SysUser findByLoginName(String loginName) throws Exception {
		// TODO Auto-generated method stub
		try{
		String hql="from SysUser u where u.loginName=?";
		SysUser user=null;
		
		List<SysUser> list=(List<SysUser>) this.getHibernateTemplate().find(hql,loginName);
		   if(list.size()>0){
			user=list.get(0);
		    }	
		   return user;
		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception();
		}
	}

	/**   
	 * <p>Title: getListForPageBysql</p>   
	 * <p>Description: </p>   
	 * @param sql
	 * @param firstResult
	 * @param pageSize
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.core.entity.dao.base.ISysUserDAO#getListForPageBysql(java.lang.String, int, int)   
	 */
	 
	public List<Map<String, Object>>  getListForPageBysql( int pageNow,
			int pageSize) throws Exception {
		// TODO Auto-generated method stub
		String sql="select A.*,GROUP_CONCAT(E.Name) as GroupName,GROUP_CONCAT(E.GroupId) as GroupIds from sys_user A "
				+ "left join (select B.GroupId,B.`UserId`,C.Name "
				+ "from `sys_user_user_group` B inner join `sys_user_group` C "
				+ "on B.GroupId=C.Id	) as E on A.`Id`=E.`UserId` "
				+ "group by A.`Id` ";
		List<Map<String, Object>> list = sqlQuery.Page(sql, pageNow, pageSize);
		return list;
	}

	/**   
	 * <p>Title: getCountBySysUserNameAndGroupId</p>   
	 * <p>Description: </p>   
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.core.entity.dao.base.ISysUserDAO#getCountBySysUserNameAndGroupId()   
	 */
	 
	public int getCountBySysUserNameAndGroupId(String sysUserName,String groupId) throws Exception {
		// TODO Auto-generated method stub
		final List list=new ArrayList() ;
		StringBuffer sql=new StringBuffer();
		sql.append("   select SUM(idtotal.marktotal) as wanttotal from");
		sql.append("  (select count(ok.total) as marktotal from");
		sql.append("  (select A.`Id` as total  from sys_user  A left join ");
		sql.append("  (select B.GroupId,B.`UserId`,C.Name from `sys_user_user_group` B inner join `sys_user_group` C ");
		sql.append("  on B.GroupId=C.Id 	) as E ON A.`Id`=E.`UserId` where 1=1");
		if(groupId!=null&&!("").equals(groupId)){
			sql.append("  and E.GroupId ="+groupId+"");
		}
		if(sysUserName!=null&&!("").equals(sysUserName)){
			sysUserName="'"+"%"+sysUserName+"%"+"'";
			sql.append("  and A.LoginName like"+sysUserName+"");
		}
		sql.append("  group by A.`Id`)ok  group by ok.total) idtotal group by idtotal.marktotal");
	   sqlQuery.getJdbcTemplate().query(sql.toString(),new Object[]{},new RowCallbackHandler() {
			
			public void processRow(ResultSet rs) throws SQLException {
				
                 list.add(rs.getInt("wanttotal"));
				}
			
		});
		int result=0;
	
				 try{
					result=(Integer) list.get(0); 
				 }
		          catch(Exception ex){
		        	  //ex.getStackTrace();
		        	  result=0;
		          }
				 return result;	
		 
	}

	/**   
	 * <p>Title: getListBySysUserNameAndGroupId</p>   
	 * <p>Description: </p>   
	 * @param sysUserName
	 * @param groupId
	 * @param firstResult
	 * @param pageSize
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.core.entity.dao.base.ISysUserDAO#getListBySysUserNameAndGroupId(java.lang.String, java.lang.String, int, int)   
	 */
	 
	public List<Map<String,Object>> getListBySysUserNameAndGroupId(String sysUserName,
			String groupId, int pageNow, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		sql.append("  select A.*,GROUP_CONCAT(E.Name) as GroupName,GROUP_CONCAT(E.GroupId) as GroupIds from sys_user A");
		sql.append("  left join (select B.GroupId,B.`UserId`,C.Name");
		sql.append("  from `sys_user_user_group` B inner join `sys_user_group` C");
		sql.append("  on B.GroupId=C.Id	) as E on A.`Id`=E.`UserId` where 1=1 ");
		if(!("").equals(groupId)&&groupId!=null){
			sql.append("  and E.GroupId ="+groupId+"");
		}
		if(sysUserName!=null&&!("").equals(sysUserName)){
			sysUserName="'"+"%"+sysUserName+"%"+"'";
			sql.append("  and A.LoginName like"+sysUserName+"");
		}
		sql.append("  group by A.`Id` ");
		List<Map<String, Object>> list = sqlQuery.Page(sql.toString(), pageNow, pageSize);
		return list;
		
	} 
}

 