package com.housemanagersystem.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.housemanagersystem.base.impl.BaseController;
import com.housemanagersystem.domain.Chargedetail;
import com.housemanagersystem.domain.Contact;
import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.common.view.servlet.ResultPageObject;
@Controller
@RequestMapping("chargeDetail")
public class ChargeDetailController extends BaseController{
	@RequestMapping("/init")
	public String initUI() {
		return "/chargeDetails/chargeDetailInfo";
	}
	@RequestMapping("/getChargedetailInfo")
	@ResponseBody
	public ResultPageObject getHouseInfo(HttpServletRequest request) {
		ResultPageObject resultpage = null;
		try {
			resultpage = chargeDetailServiceImpl.getEntityinfo(createParam(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultpage;
	}

	@RequestMapping("/saveChargedetailInfo")
	@ResponseBody
	public ResultObject save(Chargedetail chargedetail ) {
		boolean flag = false;
		try {
			chargedetail.setId(idGenerator.getNextId(
					Contact.class.getSimpleName()).intValue());
			flag = chargeDetailServiceImpl.createEntity(chargedetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return creatResult(flag);
	}

	@RequestMapping("/updateChargedetailInfo")
	@ResponseBody
	public ResultObject update(Chargedetail contact) {
		boolean flag = false;
		try {
			flag = chargeDetailServiceImpl.updateEntity(contact);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return creatResult(flag);
	}

	@RequestMapping("/delChargeDetailInfo")
	@ResponseBody
	public ResultObject delete(HttpServletRequest request) {
		boolean flag = false;
		try {
			flag = chargeDetailServiceImpl.deleteEntity(request.getParameter("id"));
		} catch (Exception e) {

			e.printStackTrace();
		}
		return creatResult(flag);
	}
}
