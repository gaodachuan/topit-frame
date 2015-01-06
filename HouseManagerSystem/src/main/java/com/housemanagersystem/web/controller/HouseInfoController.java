package com.housemanagersystem.web.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.housemanagersystem.base.impl.BaseController;
import com.housemanagersystem.domain.Houseinfo;
import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.common.view.servlet.ResultPageObject;
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
@RequestMapping("/houseinfo/")
public class HouseInfoController extends BaseController{
	@RequestMapping("/init")
	public String initUI() {
		return "/houses/houseinfo";
	}
	@RequestRight(name="查询",moduleId=28,actionId=4,descrption="查询")
	@RequestMapping("/getHouseInfo")
	@ResponseBody
	public ResultPageObject getHouseInfo(HttpServletRequest request) {
		ResultPageObject resultpage = null;
		try {
			resultpage = houseInfoServiceImpl.getEntityinfo(createParam(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultpage;
	}
    @RequestRight(name="新增",moduleId=28,actionId=1,descrption="新增")
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
    @RequestRight(name="修改",moduleId=28,actionId=3,descrption="修改")
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
    @RequestRight(name="删除",moduleId=28,actionId=2,descrption="删除")
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
