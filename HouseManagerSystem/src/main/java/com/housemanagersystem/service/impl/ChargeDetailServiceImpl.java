package com.housemanagersystem.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housemanagersystem.base.impl.BaseService;
import com.housemanagersystem.domain.Chargedetail;
import com.housemanagersystem.service.IChargeDetailService;
@Service
@Transactional
public class ChargeDetailServiceImpl extends BaseService<Chargedetail> implements IChargeDetailService{

}
