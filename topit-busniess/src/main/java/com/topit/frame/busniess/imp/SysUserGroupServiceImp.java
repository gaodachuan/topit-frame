package com.topit.frame.busniess.imp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topit.frame.busniess.base.ISysUserGroupService;
import com.topit.frame.core.entity.dao.base.ISysUserGroupDAO;
import com.topit.frame.core.entity.dao.base.ISysUserGroupModuleRightDAO;
import com.topit.frame.core.entity.data.SysUserGroup;
import com.topit.frame.core.entity.data.SysUserGroupModuleRight;

/**
 * @ClassName: SysUserGroupServiceImp
 * @Description: 系统用户组服务类接口的实现
 * @author qiugui
 * @date 2014年11月24日 下午2:49:22
 * 
 */
@Service("sysUserGroupServiceImp")
@Transactional
public class SysUserGroupServiceImp implements ISysUserGroupService {

	@Resource(name = "sysUserGroupDAOImp")
	private ISysUserGroupDAO sysUserGroupDAOImp;

	@Resource(name= "sysUserGroupModuleRightDAOImpl")
	private ISysUserGroupModuleRightDAO sysUserGroupModuleRightDAOImpl;
	
	/**
	 * <p>
	 * Title: save
	 * </p>
	 * <p>
	 * Description: 保存该系统用户组信息
	 * </p>
	 * 
	 * @param sysUserGroup
	 * @return
	 * @throws Exception
	 * @see com.topit.frame.busniess.base.ISysUserGroupService#save(com.topit.frame.core.entity.data.SysUserGroup)
	 */

	public boolean save(SysUserGroup sysUserGroup) throws Exception {

		return sysUserGroupDAOImp.save(sysUserGroup);

	}

	/**
	 * <p>
	 * Title: delete
	 * </p>
	 * <p>
	 * Description: 删除该系统用户组信息
	 * </p>
	 * 
	 * @param sysUserGroup
	 * @return
	 * @throws Exception
	 * @see com.topit.frame.busniess.base.ISysUserGroupService#delete(com.topit.frame.core.entity.data.SysUserGroup)
	 */

	public boolean delete(SysUserGroup sysUserGroup) throws Exception {

		return sysUserGroupDAOImp.delete(sysUserGroup);

	}

	/**   
	 * <p>Title: deleteById</p>   
	 * <p>Description: 根据id删除记录</p>   
	 * @param id
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserGroupService#deleteById(int)   
	 */
	 
	public boolean deleteById(int id) throws Exception {
		
		return sysUserGroupDAOImp.deleteById(id);
	}

	/**
	 * <p>
	 * Title: update
	 * </p>
	 * <p>
	 * Description: 更新该系统用户组的数据
	 * </p>
	 * 
	 * @param sysUserGroup
	 * @return
	 * @throws Exception
	 * @see com.topit.frame.busniess.base.ISysUserGroupService#update(com.topit.frame.core.entity.data.SysUserGroup)
	 */

	public boolean update(SysUserGroup sysUserGroup) throws Exception {

		return sysUserGroupDAOImp.update(sysUserGroup);

	}

	/**
	 * <p>
	 * Title: findById
	 * </p>
	 * <p>
	 * Description: 根据系统用户角色ID查找对应的系统用户组信息
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see com.topit.frame.busniess.base.ISysUserGroupService#findById(java.io.Serializable)
	 */

	public SysUserGroup findById(Serializable id) throws Exception {

		return sysUserGroupDAOImp.findById(id);

	}

	/**   
	 * <p>Title: getListForPage</p>   
	 * <p>Description: 根据对象进行表的分页查询</p>   
	 * @param criteria
	 * @param offset
	 * @param length
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserGroupService#getListForPage(org.hibernate.criterion.DetachedCriteria, int, int)   
	 */
	 
	public List<SysUserGroup> getListForPage(DetachedCriteria criteria,
			int offset, int length) throws Exception {

		return sysUserGroupDAOImp.getListForPage(criteria, offset, length);

	}
	
	/**   
	 * <p>Title: getListForPage</p>   
	 * <p>Description: 通过hql进行分页查询</p>   
	 * @param hql
	 * @param firstResult
	 * @param pageSize
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserGroupService#getListForPage(java.lang.String, int, int)   
	 */
	 
	public List<?> getListForPage(String hql, int firstResult, int pageSize)
			throws Exception {
		
		 return sysUserGroupDAOImp.getListForPage(hql, firstResult, pageSize);
		 
	}

	/**   
	 * <p>Title: getListForPageBySql</p>   
	 * <p>Description: 使用jdbctemplate创建多表关联的分页查询</p>   
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserGroupService#getListForPageBySql(java.lang.String, int, int)   
	 */
	 
	public List<Map<String, Object>> getListForPageBySql(int pageNow, int pageSize) throws Exception {
		
		return sysUserGroupDAOImp.getListForPageBySql(pageNow, pageSize);
		
	}
	
	/**   
	 * <p>Title: getCount</p>   
	 * <p>Description:  获得表记录总条数</p>   
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserGroupService#getCount()   
	 */
	 
	public int getCount() throws Exception {
		
		return sysUserGroupDAOImp.getCount();

	}

	/**   
	 * <p>Title: changestatus</p>   
	 * <p>Description: 更改系统用户组的活动状态</p>   
	 * @param sysUserGroup
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserGroupService#changestatus(com.topit.frame.core.entity.data.SysUserGroup)   
	 */
	 
	public boolean changestatus(SysUserGroup sysUserGroup) throws Exception {
		
		return sysUserGroupDAOImp.changestatus(sysUserGroup);

	}

	/**   
	 * <p>Title: getListForCombox</p>   
	 * <p>Description: </p>   
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.ISysUserGroupService#getListForCombox()   
	 */
	 
	public List<SysUserGroup> getListForCombox() throws Exception {

		 return sysUserGroupDAOImp.getListForCombox();
		 
	}

	public List<SysUserGroupModuleRight> LoadModuleByGroupid(String groupId)
			throws Exception {
		List<Integer> list=new ArrayList<Integer>();
		list.add(Integer.parseInt(groupId));
		 
		return sysUserGroupModuleRightDAOImpl.findByGroupIds(list);
		 
	}
	
}
