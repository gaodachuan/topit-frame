package com.housemanagersystem.service.impl;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;











import com.housemanagersystem.domain.Chargedetail;
import com.housemanagersystem.domain.Chargeitem;
import com.housemanagersystem.domain.Contact;
import com.housemanagersystem.domain.Houseinfo;
import com.housemanagersystem.service.ICenterBusinessService;
import com.housemanagersystem.service.IChargeDetailService;
import com.housemanagersystem.service.IContactService;
import com.housemanagersystem.service.IHouseInfoService;
import com.housemanagersystem.util.Constant;
import com.housemanagersystem.util.DateUtils;
import com.topit.frame.core.entity.dao.base.IIdGenerator;
@Service
@Transactional
public class CenterBusinessServiceImpl implements ICenterBusinessService{
	@Resource
	private IHouseInfoService houseInfoServiceImpl;
	@Resource
	private IContactService contactServiceImpl;
	@Resource
	private ChargeItemInfoServiceImpl chargeItemInfoServiceImpl;
	@Resource
	private IChargeDetailService chargeDetailServiceImpl;
	@Resource(name = "idGenerator")
	private IIdGenerator idGenerator;
	@Transactional
	public boolean rentHouse(Houseinfo houseinfo, Contact contact,String[] chargeItems)
			 {
		boolean flag=false;
		contact.setChargeitem(chargeItems.toString());
		//完整合同和房源信息
		CompleteHouseInfo(houseinfo,contact);
		//生成收费明细单
		CreateChargeDetails(contact,houseinfo,chargeItems);
		List<Chargedetail> list=CreateChargeDetails(contact, houseinfo, chargeItems);
		try {
			houseInfoServiceImpl.update(houseinfo);
			contactServiceImpl.save(contact);
			chargeDetailServiceImpl.save(list);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
    /**
     * 根据合同生成收费明细单
     * @param contact
     * @param chargeItems2 
     */
	private List<Chargedetail> CreateChargeDetails(Contact contact,Houseinfo houseinfo, String[] chargeItems) {
	    List<BigInteger> chargeItemsIds=idGenerator.getNextIds(Chargeitem.class.getSimpleName(), chargeItems.length);
		List<Chargeitem> charges=chargeItemInfoServiceImpl.find("name IN:names", new String[]{"names"},new Object[]{chargeItems} );
		List<Chargedetail> list=new ArrayList<Chargedetail>();
		for(int i=0;i<chargeItemsIds.size();i++)
		{
			Chargedetail chargedetail=new Chargedetail(contact, charges.get(i));
			chargedetail.setId(chargeItemsIds.get(i).intValue());
			chargedetail.setDealid(Constant.Product.OGNIZATION+chargedetail.getId());
			list.add(chargedetail);
		}
         return list;
	}
     /**
      * 完成房源和合同信息的生成
      * @param houseinfo
      * @param contact
      */
	private void CompleteHouseInfo(Houseinfo houseinfo, Contact contact) {
		
		houseinfo.setRentstart(contact.getContactstart());
	   	houseinfo.setRentend(contact.getContactend());
        houseinfo.setStatus(Constant.Comboxdata.HOUSEINFO_STATUS_1);
        houseinfo.setDays(DateUtils.getDiffDay(contact.getContactstart(),contact.getContactend()));
        houseinfo.setMonths(DateUtils.getDiffMonth(contact.getContactstart(),contact.getContactend()));
        contact.setId(idGenerator.getNextId(Contact.class.getSimpleName()).intValue());
        contact.setElectricstart(houseinfo.getElectricStart());
        contact.setWaterstart(houseinfo.getWaterStart());
        contact.setHouseequipment(houseinfo.getEquipment());
        contact.setHouseid(houseinfo.getNumber());
        contact.setHousename(houseinfo.getName());
        contact.setHousetype(houseinfo.getHousetype());
<<<<<<< HEAD
        contact.setHouseArea(houseinfo.getArea());
        contact.setHouseDecorate(houseinfo.getDecorate());
        contact.setHouseUseType(houseinfo.getUsetype());
        contact.setHouseDaypri(houseinfo.getDayrentpri());
        contact.setHouseMonthpri(houseinfo.getMonthrentpri());
=======
        
>>>>>>> 07280d9f254d6281d2dd82fdff1830d2c99d603b
	}

}
