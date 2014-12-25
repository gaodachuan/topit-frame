package com.topit.frame.core.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;

/**
 * @ClassName: BaseDAO
 * @Description: 基础DAO的实现类
 * @author ivan.zhang
 * @date 2014年11月13日 下午2:45:29
 * 
 * @param <T>
 */
// 方法为增加完整
public class BaseDAO<T> extends HqlUtil implements IBaseDAO<T> {

	/**
	 * @Fields entityClass : 类型
	 */
	protected Class<T> entityClass;

	/**
	 * @Title: BaseDAO
	 * @Description: 构造方法
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDAO() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class) params[0];
	}

	public static Logger logger = Logger.getLogger(BaseDAO.class);

	/**
	 * <p>
	 * Title: save
	 * </p>
	 * <p>
	 * Description:保存对象
	 * </p>
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 * @see com.topit.frame.core.dao.IBaseDAO#save(java.lang.Object)
	 */
	public boolean save(T entity) throws Exception {
		boolean flag = false;
		try {
			this.getHibernateTemplate().save(entity);
			this.getHibernateTemplate().flush();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	/**
	 * <p>
	 * Title: delete
	 * </p>
	 * <p>
	 * Description:删除对象
	 * </p>
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 * @see com.topit.frame.core.dao.IBaseDAO#delete(java.lang.Object)
	 */
	public boolean delete(T entity) throws Exception {

		boolean flag = false;
		try {
			this.getHibernateTemplate().delete(entity);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	/**
	 * <p>
	 * Title: update
	 * </p>
	 * <p>
	 * Description:更新对象
	 * </p>
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 * @see com.topit.frame.core.dao.IBaseDAO#update(java.lang.Object)
	 */
	public boolean update(T entity) throws Exception {
		boolean flag = false;
		try {
			this.getHibernateTemplate().update(entity);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	/**
	 * <p>
	 * Title: findById
	 * </p>
	 * <p>
	 * Description: 根据ID查询对象
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see com.topit.frame.core.dao.IBaseDAO#findById(java.io.Serializable)
	 */
	public T findById(Serializable id) throws Exception {
		T entity = null;
		try {
			entity = this.getHibernateTemplate().get(entityClass, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return entity;
	}

	/**
	 * 根据主键加在对象
	 */
	public T load(Serializable id) throws Exception {

		T entity = null;

		try {
			entity = this.getHibernateTemplate().load(entityClass, id);

		} catch (Exception e) {
			logger.error("");
		}

		return entity;

	}

	/**
	 * <p>
	 * Title: queryAll
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 * @see com.topit.frame.core.dao.IBaseDAO#queryAll()
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryAll() throws Exception {

		List<T> list = null;

		try {
			list = (List<T>) this.getHibernateTemplate().find(
					"from  " + entityClass.getName());
		} catch (Exception e) {
			logger.error("");
		}

		return list;
	}

	/**
	 * 记录总数
	 */
	public int getCount() throws Exception {
		int count = 0;
		try {
			List<T> list = this.queryAll();
			count = list.size();
		} catch (Exception e) {
			logger.error("");
		}
		return count;
	}

	public boolean save(List<T> entityList) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean remove(T entity) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean remove(List<T> entityList) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean refresh(T entity) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(Serializable id) throws Exception {
		boolean flag = false;

		String hql = "delete  " + entityClass.getName() + "  where id =?";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter(0, id);

		int i = query.executeUpdate();
		if (i == 1) {
			flag = true;
		}

		return flag;
	}

	public boolean delete(String where, Object... params) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public T retrieve(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> find(String where, String order, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	// 此方法不适合String类型值为null,int类型值 为0
	public List<T> find(Object object, Object... params) {
		return null;
	}

	public List<T> find(int offset, int limit, String where, String order,
			Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> find(int top, String where, String order, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	public T first(String where, String order, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object min(String field, String where, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object max(String field, String where, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object count(String where, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean exists(String where, Object... params) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * <p>
	 * Title: getListForPage
	 * </p>
	 * <p>
	 * Description: 进行分页查询
	 * </p>
	 * 
	 * @param hql
	 *            hql语句
	 * @param offset
	 *            从第几条记录开始查询
	 * @param length
	 *            查询步长
	 * @return 返回页面记录
	 * @see com.topit.frame.core.dao.IBaseDAO#getListForPage(java.lang.String,
	 *      int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<T> getListForPage(DetachedCriteria criteria, int offset,
			int length) {
		List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(
				criteria, offset, length);
		return list;
	}

	/**
	 * @Title: getListForPage
	 * @Description: 根据hql分页查询
	 * @param hql
	 *            查询语句
	 * @param firstResult
	 *            查询起始索引
	 * @param pageSize
	 *            查询步长
	 * @return
	 * @throws Exception
	 */

	public List<T> getListForPage(String hql, int firstResult, int pageSize)
			throws Exception {
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql);
        if(firstResult!=0 && pageSize!=0)
        {
        	query.setFirstResult(firstResult);
    		query.setMaxResults(pageSize);
        }
		List<T> list = query.list();
		return list;
	}

	/**
	 * <p>
	 * Title: update
	 * </p>
	 * <p>
	 * Description: 根据hql语句更新数据
	 * </p>
	 * 
	 * @param hql
	 *            hql语句
	 * @param params
	 *            查询的限制条件参数
	 * @return
	 * @throws Exception
	 * @see com.topit.frame.core.dao.IBaseDAO#update(java.lang.String,
	 *      java.lang.Object[])
	 */

	public boolean update(String hql, Object... params) throws Exception {
		boolean flag = false;

		Query query = getSessionFactory().getCurrentSession().createQuery(hql);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}

		try {
			query.executeUpdate();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return flag;

	}

}
