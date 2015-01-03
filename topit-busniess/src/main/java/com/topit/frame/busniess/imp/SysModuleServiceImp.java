package com.topit.frame.busniess.imp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.topit.frame.busniess.base.ISysModuleService;
import com.topit.frame.core.entity.dao.base.ISysModuleDAO;
import com.topit.frame.core.entity.data.SysModule;

/**
 * @ClassName: SysModuleServiceImp
 * @Description: 模块维护服务实现类
 * @author ivan.zhang
 * @date 2014年11月20日 下午6:23:26
 * 
 */
@Repository("sysModuleService")
@Transactional
public class SysModuleServiceImp implements ISysModuleService {

	/**
	 * @Fields sysModuleDAO :注入模块维护DAO
	 */
	@Resource(name = "sysModuleDAO")
	private ISysModuleDAO sysModuleDAO;

	/**
	 * @Title: save
	 * @Description: 事务处理模块维护新增
	 * @param sysModule
	 * @throws Exception
	 */
	@Transactional
	public boolean save(SysModule sysModule) throws Exception {
		return sysModuleDAO.save(sysModule);
	}

	@Transactional
	public List<SysModule> getListForPage(final DetachedCriteria criteria,
			final int offset, final int length) throws Exception {
		return (List<SysModule>) sysModuleDAO.getListForPage(criteria, offset,
				length);
	}

	@Transactional
	public int getCount() throws Exception {
		return sysModuleDAO.getCount();
	}

	@Transactional
	public boolean del(int id) throws Exception {
		return sysModuleDAO.delete(id);
	}

	@Transactional
	public boolean edit(String hql, Object... params) throws Exception {
		return sysModuleDAO.update(hql, params);
	}

}
