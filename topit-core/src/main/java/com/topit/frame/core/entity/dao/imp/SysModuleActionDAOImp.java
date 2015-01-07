package com.topit.frame.core.entity.dao.imp;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.topit.frame.common.util.CategoryConstant;
import com.topit.frame.core.dao.BaseDAO;
import com.topit.frame.core.dao.SqlQuery;
import com.topit.frame.core.entity.dao.base.ISysModuleActionDAO;
import com.topit.frame.core.entity.data.SysModuleAction;

/**
 * @ClassName: SysModuleActionDAOImp
 * @Description: 系统模块操作 明细表 DAO接口的实现
 * @author qiugui
 * @date 2014年12月3日 下午2:04:30
 * 
 */
@Repository("sysModuleActionDAOImp")
public class SysModuleActionDAOImp extends BaseDAO<SysModuleAction> implements
		ISysModuleActionDAO {

	@Resource(name = "sqlQuery")
	private SqlQuery sqlQuery;

	/**   
	 * <p>Title: findByModuleIdAndActionId</p>   
	 * <p>Description: 根据模块Id和模块对应的方法Id查询表信息</p>   
	 * @param moduleid
	 * @param actionid
	 * @return
	 * @throws Exception
	 * @see com.topit.frame.core.entity.dao.base.ISysModuleActionDAO#findByModuleIdAndActionId(java.io.Serializable,java.io.Serializable)
	 */

	public SysModuleAction findByModuleIdAndActionId(Serializable moduleid,
			Serializable actionid) throws Exception {

		String hql = "from SysModuleAction where ModuleId = " + moduleid
				+ " and ActionId = " + actionid;

		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql);

		SysModuleAction sysModuleAction = (SysModuleAction) query.list().get(0);

		return sysModuleAction;
	}

	/**
	 * @Title: getListAction
	 * @Description: 根据模块modulePath和用户UserID查询模块权限
	 * @param modulePath
	 * @param userId
	 * @return
	 */
	public List<SysModuleAction> getListAction(String modulePath, int userId)  throws Exception {

		Object[] args = new Object[2];
		args[0] = modulePath;
		args[1] = userId;

		final List<SysModuleAction> list = new ArrayList<SysModuleAction>();
		sqlQuery.getJdbcTemplate().query(CategoryConstant.ACTION_RIGHT, args,
				new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {

						SysModuleAction sysModuleAction = new SysModuleAction();

						sysModuleAction.setActionFunctionName(rs
								.getString("ActionFunctionName"));
						sysModuleAction.setActionId(rs.getInt("ACTIONID"));
						sysModuleAction.setControllerClassName(rs
								.getString("CONTROLLERCLASSNAME"));
						sysModuleAction.setModuleId(rs.getInt("MODULEID"));
						sysModuleAction.setName(rs.getString("NAME"));

						list.add(sysModuleAction);
					}
				});

		return list;
	}

	/**   
	 * <p>Title: getList</p>   
	 * <p>Description: 获得sys_module_action表中，模块操作信息</p>   
	 * @param moduleId
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.core.entity.dao.base.ISysModuleActionDAO#getList(java.lang.String)   
	 */
	 
	public List<Map<String, Object>> getList(String groupId,String moduleId) throws Exception{
		
		String sql="SELECT * FROM sys_module_action A "
				+ "LEFT JOIN "
				+ "(SELECT B.GroupId,B.ModuleId BModuleId,B.ActionId BActionId "
				+ "FROM sys_user_group_action_right B "
				+ "WHERE B.GroupId =?) C "
				+ "ON A.ModuleId=C.BModuleId AND A.ActionId=C.BActionId "
				+ "WHERE A.ModuleId = ?;";
		final List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		sqlQuery.getJdbcTemplate().query(sql,new Object[]{Integer.parseInt(groupId), Integer.parseInt(moduleId)},new RowCallbackHandler() {
			
			public void processRow(ResultSet rs) throws SQLException {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("moduleId", rs.getInt("ModuleId"));
				map.put("actionId", rs.getInt("ActionId"));
				map.put("line", rs.getInt("Line"));
				map.put("name", rs.getString("Name"));
				map.put("description", rs.getString("Description"));
				map.put("controllerClassName", rs.getString("ControllerClassName"));
				map.put("actionFunctionName", rs.getString("ActionFunctionName"));
				map.put("groupId", rs.getInt("GroupId"));
				list.add(map);
			}
		});
		
		return list;
	}


	
	

	


}
