package com.topit.frame.core.entity.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.topit.frame.core.dao.HqlUtil;
import com.topit.frame.core.entity.dao.base.IAuthorityCheck;

/**
 * @ClassName: AuthorityCheck
 * @Description: 权限检查的实现
 * @author qiugui
 * @date 2014年12月3日 下午5:14:08
 * 
 */
@Repository("authorityCheck")
public class AuthorityCheck extends HqlUtil implements IAuthorityCheck {

	/**
	 * <p>
	 * Title: check
	 * </p>
	 * <p>
	 * Description: 通过传入用户id和对应的控制器类名、方法名判断用户是否具有该操作权限
	 * </p>
	 * 
	 * @param userid
	 *            用户id
	 * @param controllerClassName
	 *            控制器类名
	 * @param actionFunctionName
	 *            控制器的方法名
	 * @return
	 * @throws Exception
	 * @see com.topit.frame.core.entity.dao.base.IAuthorityCheck#check(int,
	 *      java.lang.String, java.lang.String)
	 */
    @Transactional
	@SuppressWarnings("unchecked")
	public boolean check(int userid, String controllerClassName,
			String actionFunctionName) throws Exception {

		boolean flag = false;

		String hql = "FROM SysUserUserGroup a,SysUserGroup b,SysUserGroupActionRight c,SysModuleAction d"
				+ " WHERE b.inactive=1 AND a.groupId=c.groupId AND"
				+ " a.groupId=b.id AND c.moduleId=d.moduleId AND"
				+ " c.actionId=d.actionId AND"
				+ " a.userId=:userid AND d.controllerClassName=:controllerClassName"
				+ " AND d.actionFunctionName=:actionFunctionName";

		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql);

		query.setParameter("userid", userid)
				.setParameter("controllerClassName", controllerClassName)
				.setParameter("actionFunctionName", actionFunctionName);

		List<Object> list = query.list();

		if (list.size() > 0) {
			flag = true;
		} else {
			flag = false;
		}

		return flag;

	}

}
