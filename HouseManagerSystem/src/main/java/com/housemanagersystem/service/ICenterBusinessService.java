package com.housemanagersystem.service;

import com.housemanagersystem.domain.Contact;
import com.housemanagersystem.domain.Houseinfo;


public interface ICenterBusinessService {
    /**
     * 租房
     * @param houseinfo 房源
     * @param contact  合同
     * @param chargeItems 收费
     * @return
     * @throws Exception
     */
	public boolean  rentHouse(Houseinfo houseinfo,Contact contact, String[] chargeItems);
}
