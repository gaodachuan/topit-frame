package com.topit.frame.busniess.base;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.topit.frame.core.entity.data.SysModule;

/**
 * @ClassName: ISysModuleService
 * @Description: 模块维护接口
 * @author ivan.zhang
 * @date 2014年11月20日 下午6:22:49
 * 
 */
public interface ISysModuleService {

	public boolean save(SysModule sysModule) throws Exception;

	public List<SysModule> getListForPage(final DetachedCriteria criteria,
			final int offset, final int length) throws Exception;

	public int getCount() throws Exception;

	public boolean edit(String hql, Object... params) throws Exception;

	public boolean del(int id) throws Exception;
}
