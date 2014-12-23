package com.topit.frame.web.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topit.frame.busniess.base.IComObjectSortTypeService;
import com.topit.frame.busniess.base.ISysUserGroupService;
import com.topit.frame.common.util.CategoryConstant;
import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.common.view.servlet.ResultPageObject;
import com.topit.frame.core.entity.dao.base.IIdGenerator;
import com.topit.frame.core.entity.data.SysUser;
import com.topit.frame.core.entity.data.SysUserGroup;
import com.topit.frame.core.util.entity.Node;

/** 
* @ClassName: SysUserGroupController 
* @Description: 系统用户组 控制器 
* @author qiugui 
* @date 2014年12月3日 上午10:01:31 
*  
*/ 
@Controller
@RequestMapping(value = "/usergroup")
public class SysUserGroupController {

	@Resource(name = "sysUserGroupServiceImp")
	ISysUserGroupService sysUserGroupServiceImp;
	
	@Resource(name="idGenerator")
	IIdGenerator idGenerator;
	
	@Resource(name = "comObjectSortTypeService")
	private IComObjectSortTypeService comObjectSortTypeService;
	
	/**   
	 * @Title: getCurrentDateTime   
	 * @Description: 获取系统当前时间   
	 * @return        
	 */
	 
	public Date getCurrentDateTime(){
		Date date=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		simpleDateFormat.format(date);
		return date;
	}
	
	/**   
	 * @Title: openSysUserGroup   
	 * @Description: 返回 系统用户组 前台页面   
	 * @return        
	 */
	 
	@RequestMapping(value = "/sysusergroup")
	public String openSysUserGroup() {
		return "/usergroup/sysusergroup";
	}

	/**   
	 * @Title: getList   
	 * @Description: 根据分页获得 系统用户组 所有数据   
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception        
	 */
	 
	@RequestMapping(value = "/sysusergroup", params = "method=getList")
	public @ResponseBody ResultPageObject getList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SysUser sysUser=(SysUser) request.getSession().getAttribute("SysUser");
		
		System.out.println(sysUser.getId());
		
		
		int pageNow = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		int firstResult = (pageNow - 1) * pageSize;

		List<Map<String, Object>> list = null;

		try {
			list = sysUserGroupServiceImp.getListForPageBySql(firstResult, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ResultPageObject resultPageObject = new ResultPageObject();
		resultPageObject.setRows(list);
		resultPageObject.setTotal(String.valueOf(sysUserGroupServiceImp
				.getCount()));
		return resultPageObject;
	}
	
	/**   
	 * @Title: changeStatus   
	 * @Description: 更改  系统用户组 是否启用 
	 * @param id
	 * @return        
	 */
	 
	@RequestMapping(value = "/changestatus")
	@ResponseBody
	public ResultObject changeStatus(HttpServletRequest request,HttpServletResponse response) {

		ResultObject resultObject = new ResultObject();
		SysUserGroup sysUserGroup=null;
		int id =Integer.parseInt(request.getParameter("id"));

		try {
			
			sysUserGroup = sysUserGroupServiceImp.findById(id);
			if(sysUserGroup.getInactive()==1){
				sysUserGroup.setInactive(0);
				sysUserGroupServiceImp.changestatus(sysUserGroup);
			} else{
				sysUserGroup.setInactive(1);
				sysUserGroupServiceImp.changestatus(sysUserGroup);
			}
			resultObject.setErrorCode(0);
			resultObject.setErrorDetail("权限状态更改成功！");
		} catch (Exception e) {
			resultObject.setErrorCode(1);
			resultObject.setErrorDetail("权限状态更改失败！");
			e.printStackTrace();
		}
		return resultObject;
	}
	
	
	/**   
	 * @Title: save   
	 * @Description: 新增一条 系统用户组 信息    
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException        
	 */
	 
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResultObject save(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ResultObject resultObject=new ResultObject();
		String name=request.getParameter("name");
		String discription=request.getParameter("description");
		
		SysUserGroup sysUserGroup=new SysUserGroup();
		
		sysUserGroup.setId(idGenerator.getNextId("SysUserGroup.id").intValue());
		sysUserGroup.setName(name);
		sysUserGroup.setDescription(discription);
		sysUserGroup.setCreateTime(this.getCurrentDateTime());
		sysUserGroup.setLastEditTime(this.getCurrentDateTime());
		sysUserGroup.setCreator(1);
		sysUserGroup.setLastEditor(1);
		sysUserGroup.setLastRightEditTime(this.getCurrentDateTime());
		
		try {
			sysUserGroupServiceImp.save(sysUserGroup);
			resultObject.setErrorCode(0);
			resultObject.setErrorDetail("添加用户组成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultObject.setErrorCode(1);
			resultObject.setErrorDetail("添加用户组失败！");
		}
		
		return resultObject;
	}
	
	/**   
	 * @Title: delete   
	 * @Description: 删除一条 系统用户组 记录   
	 * @param ids
	 * @return        
	 */
	 
	@SuppressWarnings("unused")
	@RequestMapping(value = "/delete/{ids}")
	@ResponseBody
	public ResultObject delete(@PathVariable("ids") String ids) {

		ResultObject resultObject = new ResultObject();
		boolean res;
		String[] deleteIds = ids.split(",");
		try {
			for (String id : deleteIds) {
				res = sysUserGroupServiceImp.deleteById(Integer.parseInt(id));
			}
			resultObject.setErrorCode(0);
			resultObject.setErrorDetail("删除用户组成功！");
		} catch (Exception e) {
			resultObject.setErrorCode(1);
			resultObject.setErrorDetail("删除用户组失败！");
			e.printStackTrace();
		}
		return resultObject;
	}
	
	/**   
	 * @Title: edit   
	 * @Description: 更改一条 系统用户组 记录   
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException        
	 */
	 
	@RequestMapping(value = "/edit")
	@ResponseBody
	public ResultObject edit(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ResultObject resultObject=new ResultObject();
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String discription=request.getParameter("description");
		
		SysUserGroup sysUserGroup=new SysUserGroup();
		
		sysUserGroup.setId(id);
		sysUserGroup.setName(name);
		sysUserGroup.setDescription(discription);
		sysUserGroup.setLastEditTime(this.getCurrentDateTime());
		sysUserGroup.setLastEditor(1);
		
		try {
			sysUserGroupServiceImp.update(sysUserGroup);
			resultObject.setErrorCode(0);
			resultObject.setErrorDetail("用户组信息修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultObject.setErrorCode(1);
			resultObject.setErrorDetail("用户组信息修改失败！");
		}
		
		return resultObject;
	}
	
	/**   
	 * @Title: loadMudelList   
	 * @Description: 加载模块树   
	 * @return        
	 */
	 
	
	/**   
	 * @Title: loadMudelList   
	 * @Description: 只加载包含模块的树的节点  
	 * @return        
	 */
	 
	@RequestMapping(value="/LoadMudelList")
	@ResponseBody
	public List<Node> loadMudelList(){
		List<Node> list;
		try {
			list = comObjectSortTypeService.getTreeWithModule(CategoryConstant.MOUDLE_TYPE_SQL);
			return list;
		} catch (Exception e) {
			 e.printStackTrace();
			 
		}
		return null;
	}
	
	
}
