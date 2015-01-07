package com.housemanagersystem.service;

import com.housemanagersystem.base.IBaseService;
import com.housemanagersystem.domain.Chargedetail;

public interface IChargeDetailService extends IBaseService<Chargedetail>{
    /**
     * 收取费用(批量)
     * @param ids
     * @return
     */
	boolean pay(String ids) ;

}
