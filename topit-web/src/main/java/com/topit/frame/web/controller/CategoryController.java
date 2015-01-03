package com.topit.frame.web.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topit.frame.busniess.base.IComObjectSortCategoryService;
import com.topit.frame.busniess.base.IComObjectSortTypeService;
import com.topit.frame.common.util.CategoryConstant;
import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.core.entity.dao.base.IIdGenerator;
import com.topit.frame.core.entity.data.ComObjectSortCategory;
import com.topit.frame.core.entity.data.ComObjectSortType;
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
public class CategoryController {

	@Resource(name = "comObjectSortTypeService")
	private IComObjectSortTypeService comObjectSortTypeService;
	@Resource(name = "comObjectSortCategoryService")
	private IComObjectSortCategoryService comObjectSortCategoryService;
	@Resource(name = "idGenerator")
	private IIdGenerator idGenerator;
	@Resource(name = "dataDicDAO")
	private DataDicDAO dataDicDAO;

	/**
	 * @Title: openModuel
	 * @Description: 页面转发
	 * @return
	 */
	@RequestMapping(value = "/category/category.do")
	public String openModuel() {
		return "/category/category";
	}

	/**
	 * @Title: getList
	 * @Description:分页返回数据查询
	 * @param req
	 * @param reps
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/category/category.do", params = "method=tree")
	public @ResponseBody List<Node> getList(HttpServletRequest req,
			HttpServletResponse reps) throws Exception {
		List<Node> list = new ArrayList<Node>();
		list = comObjectSortTypeService
				.getTree(CategoryConstant.CATEGORY_TYPE_SQL);
		return list;
	}

	@RequestMapping(value = "/category/category.do", params = "method=getTree")
	public @ResponseBody List<Node> getTree(HttpServletRequest req,
			HttpServletResponse reps) throws Exception {

		String tabId = req.getParameter("tabId");
		String tablename = req.getParameter("tablename");
		List<Node> list = new ArrayList<Node>();
		if ("COM_OBJECT_SORT_TYPE".equals(tablename)) {
			list = comObjectSortCategoryService.getListTree(
					CategoryConstant.CATEGORY_TREE_SQL, tabId);
		}
		return list;
	}

	@RequestMapping(value = "/category/category.do", params = "method=move")
	public @ResponseBody ResultRightObject move(HttpServletRequest req,
			HttpServletResponse reps) throws Exception {
		Integer args = Integer.parseInt(req.getParameter("args"));
		Integer up = Integer.parseInt(req.getParameter("up"));
		Integer type = Integer.parseInt(req.getParameter("type"));
		Integer line = Integer.parseInt(req.getParameter("line"));
		String hql1 = "update ComObjectSortCategory a  set  a.line =?   where a.sortXtypeId = ? and  a.line =?";
		String hql2 = "update ComObjectSortCategory a  set  a.line =?   where a.sortXtypeId = ? and  a.id =?";

		boolean flag1 = comObjectSortCategoryService.update(hql1, line, type,
				up);
		boolean flag2 = comObjectSortCategoryService.update(hql2, up, type,
				args);

		ResultRightObject resultRightObject = new ResultRightObject();
		ResultObject resultObject = new ResultObject();
		if (flag1 && flag2) {
			resultObject.setErrorCode(0);
			resultObject.setErrorDetail("更新成功");
		} else {
			resultObject.setErrorCode(1);
			resultObject.setErrorDetail("更新失败");
		}
		resultRightObject.setResultObject(resultObject);
		return resultRightObject;

	}

	@RequestMapping(value = "/category/category.do", params = "method=save")
	public @ResponseBody ResultObject save(HttpServletRequest req,
			HttpServletResponse reps) throws Exception {
		boolean flag = false;

		Integer sortXtypeId = Integer
				.parseInt(req.getParameter("categoryType"));
		Integer parentLid = Integer.parseInt(req.getParameter("parentLid"));
		String remark = req.getParameter("remark");
		String name = req.getParameter("name");

		ComObjectSortCategory comObjectSortCategory = new ComObjectSortCategory();
		comObjectSortCategory.setId(idGenerator.getNextId(
				"ComObjectSortCategory.id").intValue());
		comObjectSortCategory.setSortXtypeId(sortXtypeId);
		comObjectSortCategory.setItemId(idGenerator.getNextId(
				"ComObjectSortCategory.itemId").intValue());
		comObjectSortCategory.setCategoryCode("001");
		comObjectSortCategory.setCategoryName(name);
		comObjectSortCategory.setCategoryPinyin("PINYIN");
		comObjectSortCategory.setParentLid(parentLid);
		comObjectSortCategory.setLine(idGenerator.getNextId(
				"ComObjectSortCategory.line").intValue());
		comObjectSortCategory.setLevelNumber(0);
		comObjectSortCategory.setWebBackColor(" ");
		comObjectSortCategory.setWebForeColor(" ");
		comObjectSortCategory.setOtherOption(0);
		comObjectSortCategory.setRemark(remark);
		comObjectSortCategory.setInactive(0);
		comObjectSortCategory.setIsSystemDefine(0);
		comObjectSortCategory.setCreateTime(new Timestamp(System
				.currentTimeMillis()));
		comObjectSortCategory.setLastEditTime(new Timestamp(System
				.currentTimeMillis()));
		comObjectSortCategory.setVersion(1);

		flag = comObjectSortCategoryService.save(comObjectSortCategory);

		ResultObject result = new ResultObject();

		if (flag) {
			result.setErrorCode(0);
			result.setErrorDetail("类型新增成功!");
		} else {
			result.setErrorCode(1);
			result.setErrorDetail("类型新增失败!");
		}

		return result;

	}

	@RequestMapping(value = "/category/category.do", params = "method=update")
	public @ResponseBody ResultObject update(HttpServletRequest req,
			HttpServletResponse reps) throws Exception {
		boolean flag = false;

		Integer id = Integer.parseInt(req.getParameter("categoryType"));
		String remark = req.getParameter("remark");
		String name = req.getParameter("name");

		ComObjectSortCategory comObjectSortCategory = new ComObjectSortCategory();
		comObjectSortCategory.setId(id);
		comObjectSortCategory.setCategoryName(name);
		comObjectSortCategory.setRemark(remark);
		comObjectSortCategory.setVersion(1);

		String updatesql = "update ComObjectSortCategory set categoryName=? , remark=? where id =?";
		flag = comObjectSortCategoryService.update(updatesql, name, remark, id);
		ResultObject result = new ResultObject();

		if (flag) {
			result.setErrorCode(0);
			result.setErrorDetail("类型新增成功!");
		} else {
			result.setErrorCode(1);
			result.setErrorDetail("类型新增失败!");
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
	@RequestMapping(value = "/category/datadic.do", params = "method=getData")
	public @ResponseBody List<Node> getData(HttpServletRequest req,
			HttpServletResponse reps) throws Exception {
		List<Node> list = dataDicDAO.getDataNode(1);
		return list;
	}

	@RequestMapping(value = "/category/category.do", params = "method=saveType")
	public @ResponseBody ResultObject saveType(HttpServletRequest req,
			HttpServletResponse reps) throws Exception {
		boolean flag = false;

		Integer sortXtypeId = Integer
				.parseInt(req.getParameter("categoryType"));
		String remark = req.getParameter("remark");
		String name = req.getParameter("name");

		ComObjectSortType comObjectSortType = new ComObjectSortType();
		comObjectSortType.setId(idGenerator.getNextId("ComObjectSortType.id")
				.intValue());

		comObjectSortType.setName(name);
		comObjectSortType.setLine(idGenerator.getNextId(
				"ComObjectSortType.line").intValue());
		comObjectSortType.setCategoryId(sortXtypeId);
		comObjectSortType.setDescription(remark);
		comObjectSortType.setAssemblePath("  ");
		comObjectSortType.setClassName("  ");

		comObjectSortType.setIsTreeStruct(0);
		comObjectSortType.setCurrentId(0);
		comObjectSortType.setInCrementSize(0);
		comObjectSortType.setIdType(0);
		comObjectSortType.setStartId(0);
		comObjectSortType.setVisible(0);
		comObjectSortType.setEditable(0);
		comObjectSortType.setCodeLevelChar("  ");
		comObjectSortType.setCodeLengthRule("  ");
		comObjectSortType.setIsAutoCode(0);
		comObjectSortType.setIsSystemDefine(0);

		comObjectSortType.setCreator(0);
		comObjectSortType.setCreateTime(new Timestamp(System
				.currentTimeMillis()));
		comObjectSortType.setLastEditTime(new Timestamp(System
				.currentTimeMillis()));
		comObjectSortType.setVersion(1);

		flag = comObjectSortTypeService.save(comObjectSortType);

		ResultObject result = new ResultObject();

		if (flag) {
			result.setErrorCode(0);
			result.setErrorDetail("类型新增成功!");
		} else {
			result.setErrorCode(1);
			result.setErrorDetail("类型新增失败!");
		}

		return result;

	}

	@RequestMapping(value = "/category/category.do", params = "method=updateType")
	public @ResponseBody ResultObject updateType(HttpServletRequest req,
			HttpServletResponse reps) throws Exception {
		boolean flag = false;
		Integer id = Integer.parseInt(req.getParameter("id"));
		Integer sortXtypeId = Integer
				.parseInt(req.getParameter("categoryType"));
		String remark = req.getParameter("remark");
		String name = req.getParameter("name");

		String updatesql = "update ComObjectSortType set name=? , description=?,categoryId=? where id =?";
		flag = comObjectSortTypeService.update(updatesql, name, remark,
				sortXtypeId, id);
		ResultObject result = new ResultObject();

		if (flag) {
			result.setErrorCode(0);
			result.setErrorDetail("类型更新成功!");
		} else {
			result.setErrorCode(1);
			result.setErrorDetail("类型更新失败!");
		}

		return result;
	}

	@RequestMapping(value = "/category/category.do", params = "method=find")
	public @ResponseBody ComObjectSortType find(HttpServletRequest req,
			HttpServletResponse reps) throws Exception {
		Integer id = Integer.parseInt(req.getParameter("id"));
		ComObjectSortType comObjectSortType = comObjectSortTypeService.find(id);
		return comObjectSortType;
	}

}