/**   
* @Title: SysUserController.java 
* @Package com.topit.frame.web.controller 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author ivan.zhang
* @date 2014��11��27�� ����11:46:45 
* @version V1.0   
*/ 

 package com.topit.frame.web.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.topit.frame.busniess.base.ISysUserService;
import com.topit.frame.busniess.imp.SysUserServiceImp;
import com.topit.frame.common.util.MD5Encrypt;
import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.common.view.servlet.ResultPageObject;
import com.topit.frame.core.entity.dao.base.IIdGenerator;
import com.topit.frame.core.entity.data.SysModule;
import com.topit.frame.core.entity.data.SysUser;
import com.topit.frame.core.entity.data.SysUserGroup;
import com.topit.frame.core.util.DataDicDAO;
 /** 
 * @ClassName: SysUserController 
 * @Description: ϵͳ�û�������
 * @author doc.zhou
 * @date 2014��11��27�� ����11:46:45 
 *  
 */
 @Controller
 @RequestMapping("/users")
public class SysUserController {
	@Resource(name="sysUserServiceImp")
	SysUserServiceImp sysUserServiceImpl;
	@Resource(name = "dataDicDAO")
	private DataDicDAO dataDicDAO;
	//id生成策略
	@Resource(name="idGenerator")
	IIdGenerator idGenerator;
	@RequestMapping("/sysuser")
	public String load(){
		return "/users/sysuser";
	}
	
	@SuppressWarnings("unused")
	//@RequestMapping(value = "/sysuser.do", params = "method=getList")
	public @ResponseBody ResultPageObject getList(HttpServletRequest req,
			HttpServletResponse reps) throws Exception {
		// ��ǰҳ��
		int page = Integer.parseInt(req.getParameter("page"));
//		// ��ǰÿҳ����
		int rows = Integer.parseInt(req.getParameter("rows"));
		int offset = (page - 1) * rows;
		List<SysUser> list = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SysUser.class);
		try {
			list = sysUserServiceImpl.getListForPage(criteria, offset, rows);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResultPageObject resultPageObject = new ResultPageObject();
		resultPageObject.setRows(list);
		resultPageObject.setTotal(String.valueOf(sysUserServiceImpl.getCount()));
		return resultPageObject;
		//return null;
	}
	/**   
	 * @Title: getListBySql   
	 * @Description:通过Sql语句查询分页   
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception        
	 */ 
	@RequestMapping(value = "/sysuser.do", params = "method=getList")
	public @ResponseBody ResultPageObject getListBySql(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int pageNow = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		String sysUserName=request.getParameter("sysUserName");
		String sysUserGroupId=request.getParameter("sysUserGroupId");
		int firstResult = (pageNow - 1) * pageSize;
	   if((sysUserName!=null||sysUserGroupId!=null)){
		   if(!("").equals(sysUserName)||!("全部").equals(sysUserGroupId)){
		   return getlistByUserNameAndGroupId( sysUserName, sysUserGroupId,firstResult,pageSize);
		   }
	   }
		List<SysUser> list = null;
		String sql="select A.*,GROUP_CONCAT(E.Name) as GroupName from sys_user A "
				+ "left join (select B.GroupId,B.`UserId`,C.Name "
				+ "from `sys_user_user_group` B inner join `sys_user_group` C "
				+ "on B.GroupId=C.Id	) as E on A.`Id`=E.`UserId` "
				+ "group by A.`Id` LIMIT ?,?";

		try {
			list = sysUserServiceImpl.getListForPageBysql(sql, firstResult, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ResultPageObject resultPageObject = new ResultPageObject();
		resultPageObject.setRows(list);
		resultPageObject.setTotal(String.valueOf(sysUserServiceImpl.getCount()));
		return resultPageObject;
	}
	
	
	/**   
	 * @Title: save   
	 * @Description:添加一个系统用户   
	 * @param req
	 * @param reps
	 * @param map
	 * @return        
	 * @throws UnsupportedEncodingException 
	 */
	 
	@SuppressWarnings("unused")
	@RequestMapping(value = "/sysuser.do", params = "method=save")
	@ResponseBody
	public ResultObject save(HttpServletRequest req, HttpServletResponse reps,
			ModelMap map) throws UnsupportedEncodingException {
		
		req.setCharacterEncoding("utf-8");
		reps.setContentType("text/html;charset=utf-8");
		String loginName = req.getParameter("loginName");
		System.out.println("loginName"+loginName);
		String password = req.getParameter("password");
		String realName=req.getParameter("realName");
		String remark=req.getParameter("remark");
		//String quni=req.getParameter("qunimei");
		//System.out.println("quni"+quni);
		String AllowLoginWeekDay[]=req.getParameterValues("AllowLoginWeekDay");
		for(String str : AllowLoginWeekDay){
			System.out.println("ji"+str);
		}
		System.out.println("wodenagequ"+AllowLoginWeekDay.length);
		SysUser user=saveOrUpdateUser(null,password, loginName, realName, remark);
        ResultObject result = new ResultObject();
		try {
		  if(sysUserServiceImpl.add(user)){
			  result.setErrorCode(0);
			  result.setErrorDetail("添加成功！");  
		  }else{
			  result.setErrorCode(1);
		      result.setErrorDetail("添加失败！");
		  }
		} catch (Exception e) {
			result.setErrorCode(1);
			result.setErrorDetail("添加失败！");
			e.printStackTrace();
		}
		return result;
	}
	/**   
	 * @Title: delete   
	 * @Description:删除一个或多个系统用户   
	 * @param request
	 * @param response
	 * @return        
	 */
	 
	@SuppressWarnings("unused")
	@RequestMapping(value = "/sysuser.do", params = "method=delete")
	@ResponseBody
	public ResultObject delete(HttpServletRequest request, HttpServletResponse response){
		ResultObject result=null;
		try {
			String[]ids=request.getParameter("ids").split(",");
			 result=new ResultObject();
			for(int i=0;i<ids.length;i++){
				BigInteger element=new BigInteger(ids[i]);
				sysUserServiceImpl.deleteById(element);
				
			}
			 result.setErrorCode(0);
			 result.setErrorDetail("删除成功！");  
		
		} catch (Exception e) {
			// TODO: handle exception
			 result.setErrorCode(1);
			 result.setErrorDetail("删除失败！");  
		}
		return result;
	}
	/**   
	 * @Title: update   
	 * @Description:更新一个系统用户   
	 * @param request
	 * @param response
	 * @return        
	 */
	 
	@SuppressWarnings("unused")
	@RequestMapping(value = "/sysuser.do", params = "method=update")
	@ResponseBody
    public ResultObject update(HttpServletRequest request, HttpServletResponse response){
		String id=request.getParameter("id");
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		String realName=request.getParameter("realName");
		String remark=request.getParameter("remark");
		SysUser user=saveOrUpdateUser(id, password, loginName, realName, remark);
        ResultObject result = new ResultObject();
		
		try {
		  if(sysUserServiceImpl.updateSysUser(user)){
			  result.setErrorCode(0);
			  result.setErrorDetail("修改成功！");  
		  }else{
			  result.setErrorCode(1);
		      result.setErrorDetail("修改失败！");
		  }
		} catch (Exception e) {
			result.setErrorCode(1);
			result.setErrorDetail("修改失败！");
			e.printStackTrace();
		}
		return result;
   }
	/**   
	 * @Title: setUser   
	 * @Description:设置需要保存或修改的用户信息   
	 * @return        
	 */
	 
	private SysUser saveOrUpdateUser(String id,String password,String loginName,String realName,String remark){
		SysUser user=new SysUser();
		BigInteger element=null;
		if(id==null||id.equals("")){
			element=idGenerator.getNextId("SysUser.id");
		}else{
			element = new BigInteger(id);
		}
	    user.setId(element);
	    if(password.length()!=32){
		password=MD5Encrypt.encipher(password);
	    }
		user.setPassWord(password);
		user.setLoginName(loginName);
        user.setRealName(realName);
        if(remark!=null&&!remark.equals("")){
        System.out.println("update:"+remark);
        user.setRemark(remark);
        }
        user.setVersion(21);
        return user;
	}
	/**   
	 * @Title: bindSysUserGroupCombox   
	 * @Description: 系统用户组下拉列表显示           
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/sysUserGroupCombox.do")
	@ResponseBody
	public List<SysUserGroup> bindSysUserGroupCombox(){
		List<SysUserGroup> list=null;
		try {
			list = sysUserServiceImpl.getListForCombox();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
			 
		}
		return list;
	}
	/**   
	 * @Title: 根据条件查询分页（系统户名，系统用户分组ID）   
	 * @Description:    
	 * @return        
	 */ 
	private  ResultPageObject getlistByUserNameAndGroupId(String sysUserName,String sysUserGroupId, int firstResult,int pageSize){
		List<SysUser> list = null;
		try {
			list = sysUserServiceImpl.getListBySysUserNameAndGroupId(sysUserName, sysUserGroupId, firstResult, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ResultPageObject resultPageObject = new ResultPageObject();
		resultPageObject.setRows(list);
		try {
			resultPageObject.setTotal(String.valueOf(sysUserServiceImpl.getCountBySysUserNameAndGroupId(sysUserName, sysUserGroupId)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
			 
		}
		return resultPageObject;
		
	}
}

 