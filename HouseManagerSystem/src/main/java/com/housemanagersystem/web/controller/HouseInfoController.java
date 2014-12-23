package com.housemanagersystem.web.controller;


import java.util.HashMap;

import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import com.housemanagersystem.base.impl.BaseController;

import com.housemanagersystem.domain.Houseinfo;
import com.housemanagersystem.util.Constant;
import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.common.view.servlet.ResultPageObject;


/**
 * 
 * @ClassName: HouseInfoController
 * @Description: 房源信息的控制器
 * @author gaodachuan
 * @date 2014年12月15日 下午12:12:09
 *
 */

@Controller
@RequestMapping("/houseinfo/")
public class HouseInfoController extends BaseController{
	@RequestMapping("/init")
	public String initUI() {
		return "/houses/houseinfo";
	}
	@RequestMapping("/getHouseInfo")
	@ResponseBody
	public ResultPageObject getHouseInfo(HttpServletRequest request) {
		int currentPage = Integer.parseInt(request
				.getParameter(Constant.Entity_Field.CURRENT_PAGE));
		int pageSize = Integer.parseInt(request
				.getParameter(Constant.Entity_Field.PAGE_SIZE));
		String number = request
				.getParameter(Constant.Entity_Field.HOUSEINFO_NUMBER);
		String name = request
				.getParameter(Constant.Entity_Field.HOUSEINFO_NAME);
		Map<String, Object> conditions = new HashMap<String, Object>();
		if (number != null && !"".equals(number.trim())) {
			conditions.put(Constant.Entity_Field.HOUSEINFO_NUMBER, number);
		}
		if (name != null && !"".equals(name.trim())) {
			conditions.put(Constant.Entity_Field.HOUSEINFO_NAME, name);
		}
		ResultPageObject resultpage = null;
		try {
			resultpage = houseInfoServiceImpl.getEntityinfo(currentPage, pageSize, conditions);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultpage;
	}

	@RequestMapping("/saveHouseInfo")
	@ResponseBody
	public ResultObject save(Houseinfo houseinfo) {
		boolean flag = false;
		try {
			houseinfo.setId(idGenerator.getNextId(
					Houseinfo.class.getSimpleName()).intValue());
			flag = houseInfoServiceImpl.createEntity(houseinfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return creatResult(flag);
	}

	@RequestMapping("/updateHouseInfo")
	@ResponseBody
	public ResultObject update(Houseinfo houseinfo) {
		boolean flag = false;
		try {
			flag = houseInfoServiceImpl.updateEntity(houseinfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return creatResult(flag);
	}

	@RequestMapping("/delHouseInfo")
	@ResponseBody
	public ResultObject delete(HttpServletRequest request) {
		boolean flag = false;
		try {
			flag = houseInfoServiceImpl.deleteEntity(request.getParameter("id"));
		} catch (Exception e) {

			e.printStackTrace();
		}
		return creatResult(flag);
	}
}
