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

	@RequestMapping("/centerService")
	@ResponseBody
	public ResultObject centerService(@FormObj("h") Houseinfo houseinfo,
			@FormObj("con") Contact contact,HttpServletRequest request) throws Exception {
		String[] ChargeItems=request.getParameterValues("con.chargeitem");
		boolean flag=centerBusinessServiceImpl.rentHouse(houseinfo, contact,ChargeItems);
		return creatResult(flag);
	}

}
