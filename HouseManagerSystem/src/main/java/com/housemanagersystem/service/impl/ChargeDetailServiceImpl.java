package com.housemanagersystem.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housemanagersystem.base.impl.BaseService;
import com.housemanagersystem.domain.Chargedetail;
import com.housemanagersystem.service.IChargeDetailService;
@Service
@Transactional
public class ChargeDetailServiceImpl extends BaseService<Chargedetail> implements IChargeDetailService{

	public boolean pay(String ids) {
		
		String[] id = ids.split(",");
		Integer[] delids = new Integer[id.length];
		for (int i = 0; i < id.length; i++) {
			delids[i] = Integer.parseInt(id[i]);
		}
		String hql = "UPDATE " + entityClass.getSimpleName()
				+ " SET isComplete='已完成' where id in(:ids)";
		int res = getSessionFactory().getCurrentSession().createQuery(hql)
				.setParameterList("ids", delids).executeUpdate();
		return res == id.length;
	}

}
