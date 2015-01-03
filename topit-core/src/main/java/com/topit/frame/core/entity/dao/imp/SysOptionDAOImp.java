package com.topit.frame.core.entity.dao.imp;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.topit.frame.core.dao.BaseDAO;
import com.topit.frame.core.entity.dao.base.ISysOptionDAO;
import com.topit.frame.core.entity.data.SysOption;

/**
 * @ClassName: SysOptionDAOImp
 * @Description: 系统选项DAO接口的实现
 * @author qiugui
 * @date 2014年11月20日 下午2:00:29
 * 
 */
@Repository("sysOptionDAOImp")
public class SysOptionDAOImp extends BaseDAO<SysOption> implements
		ISysOptionDAO {
	/**
	 * <p>
	 * Title: loadAllByCategoryId
	 * </p>
	 * <p>
	 * Description: 根据分类选项id加载数据库中系统选项表的所有当前数据
	 * </p>
	 * 
	 * @param categoryId
	 * @return
	 * @throws Exception
	 * @see com.topit.frame.core.entity.dao.base.ISysOptionDAO#loadAllByCategoryId(java.lang.String)
	 */

	@SuppressWarnings("unchecked")
	public List<SysOption> loadAllByCategoryId(String categoryId)
			throws Exception {
		try {

			return (List<SysOption>) getHibernateTemplate().find(
					"from SysOption where CategoryId=?", categoryId);

		} catch (Exception e) {

			throw new Exception("加载所有 " + SysOption.class.getName() + " 实例时失败",
					e);

		}

	}

	public boolean batchUpdate(String hql, List<SysOption> sysOptions)
			throws Exception {
		int res = 0;
		Query query = null;
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		for (SysOption sysOption : sysOptions) {	
		query = session.createQuery(hql).setParameter("CategoryId",
						sysOption.getCategoryId())
						.setParameter("OptionKey", sysOption.getOptionKey())
						.setParameter("OptionValue", sysOption.getOptionValue());		
			res += query.executeUpdate();
			query = null;
		}
		return res == sysOptions.size();
		 
	}

	
	/**
	 * <p>
	 * Title: batchUpdate
	 * </p>
	 * <p>
	 * Description: 批量更新系统选项
	 * </p>
	 * 
	 * @param sysOptionList
	 * @return
	 * @throws Exception
	 * @see com.topit.frame.core.entity.dao.base.ISysOptionDAO#batchUpdate(java.util.List)
	 */

	public boolean batchUpdate2(String hql,
			List<Map<String, Object>> sysOptionList) throws Exception {

		// 标记是否更改成功
		boolean flag = false;

		// 批量更新entityList中的系统选项数据
		for (Map<String, Object> sysOptionMap : sysOptionList) {

			Object[] params = new Object[sysOptionMap.size()];

			for (int i = 0; i < sysOptionMap.size(); i++) {
				params[i] = sysOptionMap.get(i + "");
			}

			try {
				super.update(hql, params);
			} catch (Exception e) {
				flag = false;
				e.printStackTrace();
				throw new Exception(e);
			}

		}
		return flag;
	}
	

}
