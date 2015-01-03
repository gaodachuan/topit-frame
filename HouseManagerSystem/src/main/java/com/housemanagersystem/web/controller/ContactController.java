package com.housemanagersystem.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.housemanagersystem.base.impl.BaseController;
import com.housemanagersystem.domain.Contact;
import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.common.view.servlet.ResultPageObject;
@Controller
@RequestMapping("/contact/")
class ContactController extends BaseController{
	@RequestMapping("/init")
	public String initUI() {
		return "/contacts/contactInfo";
	}
	@RequestMapping("/getContactInfo")
	@ResponseBody
	public ResultPageObject getHouseInfo(HttpServletRequest request) {
		ResultPageObject resultpage = null;
		try {
			resultpage = contactServiceImpl.getEntityinfo(createParam(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultpage;
	}

	@RequestMapping("/saveContactInfo")
	@ResponseBody
	public ResultObject save(Contact contact ) {
		boolean flag = false;
		try {
			contact.setId(idGenerator.getNextId(
					Contact.class.getSimpleName()).intValue());
			flag = contactServiceImpl.createEntity(contact);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return creatResult(flag);
	}

	@RequestMapping("/updateContactInfo")
	@ResponseBody
	public ResultObject update(Contact contact) {
		boolean flag = false;
		try {
			flag = contactServiceImpl.updateEntity(contact);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return creatResult(flag);
	}

	@RequestMapping("/delContactInfo")
	@ResponseBody
	public ResultObject delete(HttpServletRequest request) {
		boolean flag = false;
		try {
			flag = contactServiceImpl.deleteEntity(request.getParameter("id"));
		} catch (Exception e) {

			e.printStackTrace();
		}
		return creatResult(flag);
	}
}
