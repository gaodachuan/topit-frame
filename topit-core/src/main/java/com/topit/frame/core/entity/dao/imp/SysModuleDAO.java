package com.topit.frame.core.entity.dao.imp;

import org.springframework.stereotype.Repository;

import com.topit.frame.core.dao.BaseDAO;
import com.topit.frame.core.entity.dao.base.ISysModuleDAO;
import com.topit.frame.core.entity.data.SysModule;

/**
 * @ClassName: SysModulesImp
 * @Description: 模块DAO实现类
 * @author ivan.zhang
 * @date 2014年11月13日 下午2:29:15
 * 
 */

@SuppressWarnings("rawtypes")
@Repository("sysModuleDAO")
public class SysModuleDAO extends BaseDAO<SysModule> implements ISysModuleDAO {

}
