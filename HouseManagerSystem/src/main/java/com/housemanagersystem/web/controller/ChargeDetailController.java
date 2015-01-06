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
import com.topit.frame.core.ui.entity.RequestRight;
@Controller
@RequestMapping("chargeDetail")
public class ChargeDetailController extends BaseController{
	@RequestMapping("/init")
	public String initUI() {
		return "/chargeDetails/chargeDetailInfo";
	}
	@RequestRight(name="查询",moduleId=32,actionId=4,descrption="查询")
	@RequestMapping("/getChargedetailInfo")
	@ResponseBody
	public ResultPageObject getChargedetailInfo(HttpServletRequest request) {
		ResultPageObject resultpage = null;
		try {
			resultpage = chargeDetailServiceImpl.getEntityinfo(createParam(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultpage;
	}
	@RequestRight(name="新增",moduleId=32,actionId=1,descrption="新增")
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
	@RequestRight(name="修改",moduleId=32,actionId=3,descrption="修改")
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
	@RequestRight(name="删除",moduleId=32,actionId=2,descrption="删除")
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
