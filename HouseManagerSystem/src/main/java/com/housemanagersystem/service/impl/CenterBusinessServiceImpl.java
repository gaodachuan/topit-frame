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
import com.housemanagersystem.util.ResultFactory;
import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.core.entity.dao.base.IIdGenerator;

@Service
@Transactional(rollbackFor = Exception.class)
public class CenterBusinessServiceImpl implements ICenterBusinessService {
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

	public boolean rentHouse(Houseinfo houseinfo, Contact contact,
			String[] chargeItems) {
		boolean flag = false;
		if (contact.getContactstart().before(contact.getContactend())) {
			StringBuffer items = new StringBuffer("");
			for (String s : chargeItems) {
				items.append(s + ",");
			}
			String res = items.toString();
			res = res.substring(0, res.length() - 1);
			contact.setChargeitem(res);
			// 完整合同和房源信息
			CompleteHouseInfo(houseinfo, contact);
			// 生成收费明细单
			CreateChargeDetails(contact, houseinfo, chargeItems);
			List<Chargedetail> list = CreateChargeDetails(contact, houseinfo,
					chargeItems);
			try {
				houseInfoServiceImpl.update(houseinfo);
				contactServiceImpl.save(contact);
				chargeDetailServiceImpl.save(list);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	/**
	 * 根据合同生成收费明细单
	 * 
	 * @param contact
	 * @param chargeItems2
	 */
	private List<Chargedetail> CreateChargeDetails(Contact contact,
			Houseinfo houseinfo, String[] chargeItems) {
		List<BigInteger> chargeItemsIds = idGenerator.getNextIds(
				Chargeitem.class.getSimpleName(), chargeItems.length);
		List<Chargeitem> charges = chargeItemInfoServiceImpl.find(
				"name IN:names", new String[] { "names" },
				new Object[] { chargeItems });
		List<Chargedetail> list = new ArrayList<Chargedetail>();
		for (int i = 0; i < chargeItemsIds.size(); i++) {
			Chargedetail chargedetail = new Chargedetail(contact,
					charges.get(i));
			chargedetail.setId(chargeItemsIds.get(i).intValue());
			chargedetail.setDealid(Constant.Product.OGNIZATION
					+ chargedetail.getId());
			list.add(chargedetail);
		}
		return list;
	}

	/**
	 * 完成房源和合同信息的生成
	 * 
	 * @param houseinfo
	 * @param contact
	 */
	private void CompleteHouseInfo(Houseinfo houseinfo, Contact contact) {

		houseinfo.setRentstart(contact.getContactstart());
		houseinfo.setRentend(contact.getContactend());
		houseinfo.setStatus(Constant.Comboxdata.HOUSEINFO_STATUS_1);
		houseinfo.setDays(DateUtils.getDiffDay(contact.getContactstart(),
				contact.getContactend()));
		houseinfo.setMonths(DateUtils.getDiffMonth(contact.getContactstart(),
				contact.getContactend()));
		contact.setId(idGenerator.getNextId(Contact.class.getSimpleName())
				.intValue());
		contact.setElectricstart(houseinfo.getElectricStart());
		contact.setWaterstart(houseinfo.getWaterStart());
		contact.setHouseequipment(houseinfo.getEquipment());
		contact.setHouseid(houseinfo.getNumber());
		contact.setHousename(houseinfo.getName());
		contact.setHousetype(houseinfo.getHousetype());

		contact.setHouseArea(houseinfo.getArea());
		contact.setHouseDecorate(houseinfo.getDecorate());
		contact.setHouseUseType(houseinfo.getUsetype());
		contact.setHouseDaypri(houseinfo.getDayrentpri());
		contact.setHouseMonthpri(houseinfo.getMonthrentpri());

	}

	public boolean xuzuService(Houseinfo houseinfo) {
		boolean flag = false;
		try {
			Houseinfo presist_house = houseInfoServiceImpl.findById(houseinfo
					.getId());
			if (houseinfo.getRentend().after(presist_house.getRentend())) {
				presist_house.setRentend(houseinfo.getRentend());
				presist_house.setDays(DateUtils.getDiffDay(
						houseinfo.getRentstart(), houseinfo.getRentend()));
				Contact presist_contact = contactServiceImpl
						.find("houseid=:houseid and housename=:housename and isVariable=:isVariable",
								new String[] { "houseid", "housename",
										"isVariable" },
								new Object[] { houseinfo.getNumber(),
										houseinfo.getName(), "有效" }).get(0);
				presist_contact.setContactend(houseinfo.getRentend());
				contactServiceImpl.updateEntity(presist_contact);
				houseInfoServiceImpl.updateEntity(presist_house);

				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public ResultObject tuizuService(int id) {
		
		try {
			Houseinfo presist_house = houseInfoServiceImpl.findById(id);
			List<Chargedetail> qianfeiItems=chargeDetailServiceImpl
					.find("houseid=:houseid and housename=:housename and isComplete='未完成'",
							new String[] { "houseid", "housename" },
							new Object[] { presist_house.getNumber(),
									presist_house.getName() });
			if(qianfeiItems.size()!=0)//还有欠费，不能退租
			{
				return ResultFactory.createResult(1, "用户还有欠费，请结算缴费！");
			}else{
				presist_house.setStatus("空闲");
				Contact presist_contact = contactServiceImpl
						.find("houseid=:houseid and housename=:housename and isVariable=:isVariable",
								new String[] { "houseid", "housename",
										"isVariable" },
								new Object[] { presist_house.getNumber(),
								presist_house.getName(), "有效" }).get(0);
				presist_contact.setIsVariable("无效");
				contactServiceImpl.updateEntity(presist_contact);
				houseInfoServiceImpl.updateEntity(presist_house);
				return ResultFactory.createResult(0, "退租成功");
			}		
		} catch (Exception e) {
			e.printStackTrace();
			 return ResultFactory.createResult(1, "操作失败！");
		}
		
	}
	
	
}

