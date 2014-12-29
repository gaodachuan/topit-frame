package com.housemanagersystem.base.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.housemanagersystem.base.IBaseController;
import com.housemanagersystem.domain.ComboxDTO;
import com.housemanagersystem.service.ICustomerInfoService;
import com.housemanagersystem.service.IHouseInfoService;
import com.housemanagersystem.service.impl.ChargeItemInfoServiceImpl;
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
	@Resource
	protected ChargeItemInfoServiceImpl chargeItemInfoServiceImpl;
	@Resource(name = "idGenerator")
	protected IIdGenerator idGenerator;
	
	
<<<<<<< HEAD
=======
	
	
	@InitBinder   
    public void initBinder(WebDataBinder binder) {   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        dateFormat.setLenient(true);   
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
    }
	
	
	
>>>>>>> 017b2250e8c8403bf3dedaa29a925815faf19b5d

	@RequestMapping(value = "/getComboxdata", params = "type")
	@ResponseBody
	public List<ComboxDTO> getComboxdata(@RequestParam("type") String type)
			throws Exception {
		/*String string = new String(type.getBytes("ISO-8859-1"), "UTF-8");*/
		List<ComboxDTO> list = comboxdataServiceImpl.getComboxdata(type);
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

	public Map<String, Object> createParam(HttpServletRequest request) {

		@SuppressWarnings("unchecked") 
		Map<String, String[]> parmMap = request.getParameterMap();
		Map<String, Object> conditions = new HashMap<String, Object>();
		Set<Entry<String, String[]>> set = parmMap.entrySet();
		for (Entry<String, String[]> temp : set) {
			String key = temp.getKey();
			String value = temp.getValue()[0];
			if (value != null && !"".equals(value.trim())) {
				conditions.put(key, value);
			}
		}
		return conditions;
	}
}
