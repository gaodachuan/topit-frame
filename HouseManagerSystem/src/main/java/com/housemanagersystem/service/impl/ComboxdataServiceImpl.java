package com.housemanagersystem.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.housemanagersystem.base.impl.BaseService;

import com.housemanagersystem.domain.ComboxDTO;
import com.housemanagersystem.util.SqlcreateUtil;

@Repository
public class ComboxdataServiceImpl extends BaseService<ComboxDTO> {
	@SuppressWarnings("unchecked")
	@Transactional
	public List<ComboxDTO> getComboxdata(String type) {

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("type", type);
		String sql = SqlcreateUtil.createSearchHql(
				ComboxDTO.class.getSimpleName(), condition);

		return getSessionFactory().getCurrentSession().createQuery(sql).list();

	}
}
