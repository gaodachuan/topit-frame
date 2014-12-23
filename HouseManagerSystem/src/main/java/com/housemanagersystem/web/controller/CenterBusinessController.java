package com.housemanagersystem.web.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.housemanagersystem.base.impl.BaseController;



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
public class CenterBusinessController extends BaseController{
	@RequestMapping("/init")
	public String initUI() {
		return "/centerBusiness/business";
	}
	
	

	
}
