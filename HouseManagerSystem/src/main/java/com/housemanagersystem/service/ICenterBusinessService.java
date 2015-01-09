package com.housemanagersystem.service;

import com.housemanagersystem.domain.Contact;
import com.housemanagersystem.domain.Houseinfo;
import com.topit.frame.common.view.servlet.ResultObject;


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
     /**
      * 房屋续租
      * @param houseinfo 房源
      * @return
      */
	public boolean xuzuService(Houseinfo houseinfo);
	/**
	 * 房屋退租
	 * @param id
	 * @return
	 */
	public ResultObject tuizuService(int id);
}
