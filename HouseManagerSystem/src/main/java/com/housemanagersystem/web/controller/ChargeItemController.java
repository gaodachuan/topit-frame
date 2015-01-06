package com.housemanagersystem.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.housemanagersystem.base.impl.BaseController;
import com.housemanagersystem.domain.Chargeitem;
import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.common.view.servlet.ResultPageObject;
import com.topit.frame.core.ui.entity.RequestRight;

@Controller
@RequestMapping("/chargeitem")
public class ChargeItemController extends BaseController{
	@RequestMapping("/init")
	public String initUI() {
		return "/chargeitem/chargeItems";
	}
	@RequestRight(name="查询",moduleId=30,actionId=4,descrption="查询")
	@RequestMapping("/getChargeitems")
	@ResponseBody
	public ResultPageObject getHouseInfo(HttpServletRequest request) {
		ResultPageObject resultpage = null;
		try {
			resultpage = chargeItemInfoServiceImpl.getEntityinfo(createParam(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultpage;
	}
	@RequestRight(name="新增",moduleId=30,actionId=1,descrption="新增")
	@RequestMapping("/saveChargeitem")
	@ResponseBody
	public ResultObject save(Chargeitem chargeitem) {
		boolean flag = false;
		try {
			chargeitem.setId(idGenerator.getNextId(
					Chargeitem.class.getSimpleName()).intValue());
			flag = chargeItemInfoServiceImpl.createEntity(chargeitem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return creatResult(flag);
	}
	@RequestRight(name="修改",moduleId=30,actionId=3,descrption="修改")
	@RequestMapping("/updateChargeitem")
	@ResponseBody
	public ResultObject update(Chargeitem chargeitem) {
		boolean flag = false;
		try {
			flag = chargeItemInfoServiceImpl.updateEntity(chargeitem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return creatResult(flag);
	}
	@RequestRight(name="删除",moduleId=30,actionId=2,descrption="删除")
	@RequestMapping("/delChargeitem")
	@ResponseBody
	public ResultObject delete(HttpServletRequest request) {
		boolean flag = false;
		try {
			flag = chargeItemInfoServiceImpl.deleteEntity(request.getParameter("id"));
		} catch (Exception e) {

			e.printStackTrace();
		}
		return creatResult(flag);
	}

}
