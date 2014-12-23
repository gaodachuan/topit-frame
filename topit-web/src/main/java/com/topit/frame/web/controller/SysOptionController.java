package com.topit.frame.web.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.topit.frame.busniess.base.ISysOptionService;
import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.core.entity.data.SysOption;
import com.topit.frame.core.util.DataDicDAO;
import com.topit.frame.core.util.entity.DataDic;
import com.topit.frame.core.util.entity.TreeDTO;

/**
 * @ClassName: SysOptionController
 * @Description: 系统选项控制器
 * @author qiugui
 * @date 2014年11月20日 下午3:26:22
 * 
 */
@Controller
@RequestMapping("/SystemOption")
public class SysOptionController {
	@Resource
	private ISysOptionService sysOptionService;
	@Resource
	private DataDicDAO dataDicDAO;

	@RequestMapping("/SystemOption")
	public String SytemOption() {
		return "/option/SystemOption";
	}

	/**
	 * 
	 * @Title: SytemOption
	 * @Description: 加载系统选项的所有类别
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAll")
	@ResponseBody
	public List<TreeDTO> getSytemOptions() throws Exception {
		return sysOptionService.loadAllSysOption();

	}

	/**
	 * 
	 * @Title: SytemOptionList
	 * @Description: 根据系统选项的catagoryId,查询所有的选项
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/SysOptionList", method = RequestMethod.GET, params = { "categoryId" })
	public ModelAndView SytemOptionList(
			@RequestParam("categoryId") String categoryId) throws Exception {
		List<SysOption> list = sysOptionService.loadAllByCategoryId(categoryId);
		ModelAndView mView = new ModelAndView();
		mView.setViewName("/option/SystemOptionList");
		mView.addObject("sysOptions", list);
		mView.addObject("categoryId", categoryId);
		return mView;
	}

	/**
	 * 
	 * @Title: SysOptionCombox
	 * @Description:获取系统选项中的下拉框数据
	 * @param sortTypeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/SysOptionCombox/{sortTypeId}")
	@ResponseBody
	public List<DataDic> SysOptionCombox(
			@PathVariable("sortTypeId") int sortTypeId) throws Exception {
		return dataDicDAO.getData(sortTypeId);

	}

	/**
	 * 
	 * @Title: SysOptionUpdate
	 * @Description: 设置系统设置
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/SysOptionUpdate")
	@ResponseBody
	public ResultObject SysOptionUpdate(HttpServletRequest request) throws Exception {
		Enumeration<String> optionKeys = request.getParameterNames();
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		List<SysOption> list = new ArrayList<SysOption>();
		while (optionKeys.hasMoreElements()) {
			String optionKey = optionKeys.nextElement();
			String optionValue = request.getParameter(optionKey);
			if (!optionKey.equals("categoryId")) {
				SysOption sysOption = new SysOption(categoryId, optionKey,
						optionValue);
				list.add(sysOption);
			}
		}
		ResultObject resultObject=new ResultObject();
		if(sysOptionService.batchUpdate(list))
		{
			resultObject.setErrorCode(0);
			resultObject.setErrorDetail("设置成功");
		}else{
			resultObject.setErrorCode(1);
			resultObject.setErrorDetail("设置失败");
		}
				
		return resultObject;
	}

}
