package com.housemanagersystem.web.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.housemanagersystem.base.impl.BaseController;
import com.housemanagersystem.domain.Customer;
import com.housemanagersystem.domain.Houseinfo;
import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.common.view.servlet.ResultPageObject;

/**
 * 客户信息管理
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/customer/")
public class CustomerInfoController extends BaseController {

	@RequestMapping("/init")
	public String initUI() {
		return "/customer/customerinfo";
	}

	@RequestMapping("/getCustomerInfo")
	@ResponseBody
	public ResultPageObject getHouseInfo(HttpServletRequest request) {
		ResultPageObject resultpage = null;
		try {
			resultpage = customerInfoService
					.getEntityinfo(createParam(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultpage;
	}

	@RequestMapping("/saveCustomerInfo")
	@ResponseBody
	public ResultObject save(Customer customer) {
		boolean flag = false;
		try {
			customer.setId(idGenerator.getNextId(
					Houseinfo.class.getSimpleName()).intValue());
			flag = customerInfoService.createEntity(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return creatResult(flag);
	}

	@RequestMapping("/updateCustomerInfo")
	@ResponseBody
	public ResultObject update(Customer customer) {
		boolean flag = false;
		try {
			flag = customerInfoService.updateEntity(customer);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return creatResult(flag);
	}

	@RequestMapping("/delCustomerInfo")
	@ResponseBody
	public ResultObject delete(HttpServletRequest request) {
		boolean flag = false;
		try {
			flag = customerInfoService.deleteEntity(request.getParameter("id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return creatResult(flag);
	}

}
