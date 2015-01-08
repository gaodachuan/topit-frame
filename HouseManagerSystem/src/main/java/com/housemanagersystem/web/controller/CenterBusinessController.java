package com.housemanagersystem.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.housemanagersystem.base.impl.BaseController;
import com.housemanagersystem.domain.Contact;
import com.housemanagersystem.domain.Houseinfo;
import com.topit.frame.common.view.servlet.FormObj;
import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.core.ui.entity.RequestRight;

/**
 * 
 * @ClassName: HouseInfoController
 * @Description: 房源信息的控制器
 * @author gaodachuan
 * @date 2014年12月15日 下午12:12:09
 *
 */

@Controller
@RequestMapping("/centerBusiness/")
public class CenterBusinessController extends BaseController {
	@RequestMapping("/init")
	public String initUI() {
		return "/centerBusiness/business";
	}
	@RequestRight(name="房屋出租",moduleId=29,actionId=1,descrption="房屋出租")
	@RequestMapping("/centerService")
	@ResponseBody
	public ResultObject centerService(@FormObj("h") Houseinfo houseinfo,
			@FormObj("con") Contact contact,HttpServletRequest request) throws Exception {
		String[] ChargeItems=request.getParameterValues("con.chargeitem");
		boolean flag=centerBusinessServiceImpl.rentHouse(houseinfo, contact,ChargeItems);
		
		return creatResult(flag);
	}
   
	@RequestRight(name="房屋续租",moduleId=29,actionId=2,descrption="房屋续租")
	@RequestMapping("/xuzuService")
	@ResponseBody
	public ResultObject xuzuService(@FormObj("h") Houseinfo houseinfo) throws Exception {
		boolean flag=centerBusinessServiceImpl.xuzuService(houseinfo);
		return creatResult(flag);
	}
}
