package com.housemanagersystem.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.housemanagersystem.base.IBaseController;
import com.housemanagersystem.domain.ComboxDTO;
import com.housemanagersystem.service.ICustomerInfoService;
import com.housemanagersystem.service.IHouseInfoService;
import com.housemanagersystem.service.impl.ComboxdataServiceImpl;
import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.core.entity.dao.base.IIdGenerator;

@Controller
public class BaseController implements IBaseController {
	@Resource
	protected IHouseInfoService houseInfoServiceImpl;
	@Resource
	protected ICustomerInfoService customerInfoService;
	@Resource
	protected ComboxdataServiceImpl comboxdataServiceImpl;
	@Resource(name = "idGenerator")
	protected IIdGenerator idGenerator;

	@RequestMapping(value = "/getComboxdata", params = "type")
	@ResponseBody
	public List<ComboxDTO> getComboxdata(@RequestParam("type") String type)
			throws Exception {
		String string = new String(type.getBytes("ISO-8859-1"), "UTF-8");
		List<ComboxDTO> list = comboxdataServiceImpl.getComboxdata(string);
		return list;
	}
	public ResultObject creatResult(boolean flag) {
		ResultObject result = new ResultObject();
		if (flag) {
			result.setErrorCode(0);
			result.setErrorDetail("操作成功!");
		} else {
			result.setErrorCode(1);
			result.setErrorDetail("操作失败!");
		}

		return result;
	}
}
