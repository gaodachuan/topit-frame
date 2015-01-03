package com.topit.frame.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topit.frame.busniess.imp.SysModuleServiceImp;
import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.common.view.servlet.ResultPageObject;
import com.topit.frame.core.entity.dao.base.IIdGenerator;
import com.topit.frame.core.entity.dao.base.ISysModuleActionDAO;
import com.topit.frame.core.entity.data.SysModule;
import com.topit.frame.core.entity.data.SysModuleAction;
import com.topit.frame.core.entity.data.SysUser;
import com.topit.frame.core.ui.entity.ResultRightObject;
import com.topit.frame.core.util.DataDicDAO;
import com.topit.frame.core.util.entity.Node;

/**
 * @ClassName: SysModuleController
 * @Description:模块维护控制类
 * @author ivan.zhang
 * @date 2014年11月26日 下午2:22:20
 * 
 */
@Controller
public class SysModuleController {

	@Resource(name = "sysModuleService")
	private SysModuleServiceImp sysModuleService;

	@Resource(name = "sysModuleActionDAOImp")
	private ISysModuleActionDAO sysModuleActionDAOImp;

	@Resource(name = "dataDicDAO")
	private DataDicDAO dataDicDAO;

	@Resource(name = "idGenerator")
	private IIdGenerator idGenerator;

	/**
	 * @Title: openModuel
	 * @Description: 页面转发
	 * @return
	 */
	@RequestMapping(value = "/module/module.do")
	public String openModuel() {
		return "/module/module";
	}

	/**
	 * @Title: getList
	 * @Description:分页返回数据查询
	 * @param req
	 * @param reps
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/module/sysmodule.do", params = "method=getList")
	public @ResponseBody ResultRightObject getList(HttpServletRequest req,
			HttpServletResponse reps) throws Exception {
		SysUser sysUser = (SysUser) req.getSession()
				.getAttribute("SysUser");
		int userId=sysUser.getId().intValue();
		String modulePath = "/usergroup/sysusergroup.do";
		List<SysModuleAction> listAction = sysModuleActionDAOImp.getListAction(
				modulePath, userId);
		int counts = sysModuleService.getCount();
		
		// 当前页码
		int page = Integer.parseInt(req.getParameter("page"));
		// 当前每页条数
		int rows = Integer.parseInt(req.getParameter("rows"));
		if(page<=0){
			page=1;
		}
		int offset=(counts/rows)>=page?(page - 1) * rows:(counts/rows)*rows;
		String hql = " from SysModule ";
		List<SysModule> list = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SysModule.class);
		
        if (req.getParameter("moduleType") != null) {  
        	criteria.add(Property.forName("categoryId").eq(Integer.parseInt(req.getParameter("moduleType"))));  
        } 
        if (req.getParameter("moduleName") != null&&(!"".equals((req.getParameter("moduleName"))))) {  
        	criteria.add(Property.forName("name").like("%"+req.getParameter("moduleName")+"%"));  
        } 
		try {
			list = sysModuleService.getListForPage(criteria, offset, rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int maxpage = sysModuleService.getCount();
		ResultPageObject resultPageObject = new ResultPageObject();
		resultPageObject.setRows(list);
		resultPageObject.setTotal(String.valueOf(sysModuleService.getCount()));

		ResultRightObject resultRightObject = new ResultRightObject();
		resultRightObject.setListAction(listAction);
		resultRightObject.setResultPageObject(resultPageObject);
		return resultRightObject;
	}

	/**
	 * @Title: save
	 * @Description: 模块信息保存
	 * @param req
	 * @param reps
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/module/sysmodule.do", params = "method=save")
	public @ResponseBody ResultObject save(HttpServletRequest req,
			HttpServletResponse reps) {

		boolean flag = false;
		ResultObject result = new ResultObject();

		String name = req.getParameter("name");
		int categoryId = Integer.parseInt(req.getParameter("categoryId"));
		String modulePath = req.getParameter("modulePath");
		String description = req.getParameter("description");

		SysModule sysModule = new SysModule();
		sysModule.setId(idGenerator.getNextId("SysModule").intValue());
		sysModule.setName(name);
		sysModule.setCategoryId(categoryId);
		sysModule.setModulePath(modulePath);
		sysModule.setDescription(description);
		sysModule.setCreateTime(new Date(System.currentTimeMillis()));

		try {
			flag = sysModuleService.save(sysModule);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (flag) {
			result.setErrorCode(0);
			result.setErrorDetail("模块新增成功!");
		} else {
			result.setErrorCode(1);
			result.setErrorDetail("模块新增失败!");
		}

		return result;
	}

	/**
	 * @Title: del
	 * @Description: 模块信息删除
	 * @param req
	 * @param reps
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/module/sysmodule.do", params = "method=del")
	public @ResponseBody ResultObject del(HttpServletRequest req,
			HttpServletResponse reps) {
		boolean flag = false;
		ResultObject result = new ResultObject();

		int id = Integer.parseInt(req.getParameter("id"));

		try {
			flag = sysModuleService.del(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (flag) {
			result.setErrorCode(0);
			result.setErrorDetail("模块删除成功!");
		} else {
			result.setErrorCode(1);
			result.setErrorDetail("模块删除失败!");
		}

		return result;
	}

	/**
	 * @Title: edit
	 * @Description: 模块信息修改
	 * @param req
	 * @param reps
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/module/sysmodule.do", params = "method=edit")
	public @ResponseBody ResultObject edit(HttpServletRequest req,
			HttpServletResponse reps) {

		boolean flag = false;
		ResultObject result = new ResultObject();
		String hql = "update SysModule s set s.name = ? , s.categoryId = ? ,s.modulePath = ?,s.description = ? where s.id = ?";

		String name = req.getParameter("name");
		int categoryId = Integer.parseInt(req.getParameter("categoryId"));
		int id = Integer.parseInt(req.getParameter("id"));
		String modulePath = req.getParameter("modulePath");
		String description = req.getParameter("description");

		try {
			flag = sysModuleService.edit(hql, name, categoryId, modulePath,
					description, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (flag) {
			result.setErrorCode(0);
			result.setErrorDetail("模块更新成功!");
		} else {
			result.setErrorCode(1);
			result.setErrorDetail("模块更新失败!");
		}

		return result;
	}
	
	/**
	 * @Title: getData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param req
	 * @param reps
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/module/datadic.do", params = "method=getData")
	public @ResponseBody List<Node> getData(HttpServletRequest req,
			HttpServletResponse reps) throws Exception {
		List<Node> list = dataDicDAO.getDataNode(2);
		return list;
	}
}
