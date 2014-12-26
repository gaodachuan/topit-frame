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
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.topit.frame.busniess.base.ISysModuleActionService;
import com.topit.frame.busniess.base.ISysUserService;
import com.topit.frame.busniess.imp.SysUserServiceImp;
import com.topit.frame.busniess.imp.SysUserUserGroupServiceImp;
import com.topit.frame.common.util.MD5Encrypt;
import com.topit.frame.common.view.servlet.ResultObject;
import com.topit.frame.common.view.servlet.ResultPageObject;
import com.topit.frame.core.entity.dao.base.IIdGenerator;
import com.topit.frame.core.entity.dao.base.ISysUserUserGroupDAO;
import com.topit.frame.core.entity.data.SysModule;
import com.topit.frame.core.entity.data.SysModuleAction;
import com.topit.frame.core.entity.data.SysUser;
import com.topit.frame.core.entity.data.SysUserGroup;
import com.topit.frame.core.entity.data.SysUserUserGroup;
import com.topit.frame.core.ui.entity.ResultRightObject;
import com.topit.frame.core.util.DataDicDAO;
 /** 
 * @ClassName: SysUserController 
 * @Description: 系统用户控制器
 * @author doc.zhou
 * @date 2014��11��27�� ����11:46:45 
 *  
*/ 
/** 
* @ClassName: SysUserController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author ivan.zhang 
* @date 2014年12月22日 下午6:29:32 
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
	
    @Resource(name="sysUserUserGroupServiceImp")
    SysUserUserGroupServiceImp sysUserUserGroupServiceImp;
    
    @Resource(name="sysModuleActionServiceImp")
	ISysModuleActionService sysModuleActionServiceImp;
	@RequestMapping("/sysuser")
	public String load(){
		return "/users/sysuser";
	}
	
	@SuppressWarnings("unused")
	//@RequestMapping(value = "/sysuser.do", params = "method=getList")
	public @ResponseBody ResultPageObject getList(HttpServletRequest req,
			HttpServletResponse reps) throws Exception {
		int page = Integer.parseInt(req.getParameter("page"));
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
	public @ResponseBody ResultRightObject getListBySql(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResultPageObject resultPageObject = new ResultPageObject();
		ResultRightObject resultRightObject=new ResultRightObject();
		//当前用户Id
		SysUser sysUser=(SysUser) request.getSession().getAttribute("SysUser");
		
		int userId=sysUser.getId().intValue();
		
		String modulePath="/users/sysuser.do";
		
		int pageNow = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		String sysUserName=request.getParameter("sysUserName");
		String sysUserGroupId=request.getParameter("sysUserGroupId");
		int firstResult = (pageNow - 1) * pageSize;
		
		List<SysModuleAction> sysModuleActionList=null;
	   if((sysUserName!=null||sysUserGroupId!=null)){
		   if(!("").equals(sysUserName)||!("全部").equals(sysUserGroupId)){
		   return getlistByUserNameAndGroupId( sysUserName, sysUserGroupId,firstResult,pageSize);
		   }
	   }
		List<SysUser> list = null;
		String sql="select A.*,GROUP_CONCAT(E.Name) as GroupName,GROUP_CONCAT(E.GroupId) as GroupIds from sys_user A "
				+ "left join (select B.GroupId,B.`UserId`,C.Name "
				+ "from `sys_user_user_group` B inner join `sys_user_group` C "
				+ "on B.GroupId=C.Id	) as E on A.`Id`=E.`UserId` "
				+ "group by A.`Id` LIMIT ?,?";

		try {
			sysModuleActionList= sysModuleActionServiceImp.getListAction(modulePath, userId);
			list = sysUserServiceImpl.getListForPageBysql(sql, firstResult, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}

		resultPageObject.setRows(list);
		resultPageObject.setTotal(String.valueOf(sysUserServiceImpl.getCount()));
		resultRightObject.setListAction(sysModuleActionList);
		resultRightObject.setResultPageObject(resultPageObject);
		return resultRightObject;
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
	@Transactional
	public ResultObject save(HttpServletRequest request, HttpServletResponse response,ModelMap map) {
		Map maps=null;
		ResultObject result = new ResultObject();
		try {
			maps = getFrontSource(request,response);
			SysUser user=saveOrUpdateUser(maps);
			if(user==null){
				result.setErrorCode(1);
				result.setErrorDetail("用户名重复！");
				return result;
			}
		  if(saveSysUserUserGroupId( maps, user , request,  response)){
			  if(sysUserServiceImpl.add(user)){
				  result.setErrorCode(0);
				  result.setErrorDetail("添加成功！");  
			  }  
		  }
			else{
				  result.setErrorCode(1);
			      result.setErrorDetail("添加失败！");
			  }
		} catch (Exception e1) {
			e1.printStackTrace();	
			result.setErrorCode(1);
			result.setErrorDetail("添加失败！");
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
	@Transactional
	public ResultObject delete(HttpServletRequest request, HttpServletResponse response){
		ResultObject result=null;
		try {
			String[]ids=request.getParameter("ids").split(",");
			 result=new ResultObject();
			for(int i=0;i<ids.length;i++){
				int id=Integer.parseInt(ids[i]);
				BigInteger element=new BigInteger(ids[i]);
			List<SysUserUserGroup>list =sysUserUserGroupServiceImp.findByUserId(id);
		     for(SysUserUserGroup entity:list){
		    	 sysUserUserGroupServiceImp.deleteSysUserUserGroup(entity);
		     }
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
	@Transactional
    public ResultObject update(HttpServletRequest request, HttpServletResponse response){
		Map maps = null;
		ResultObject result=null;
		boolean flag=false; 
		try {
			result=new ResultObject();
			maps = getFrontSource(request,response);
			SysUser user=saveOrUpdateUser(maps);
			if(user==null){
			  result.setErrorCode(1);
			  result.setErrorDetail("用户名重复！");
			  return result;
			}
	     if(updateSysUserGroupId( maps, user , request,  response)){
		 if(sysUserServiceImpl.updateSysUser(user)){
			  flag=true; 
		    }
	      }
	     if(flag){
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
	 
	private SysUser saveOrUpdateUser(Map map){
		String id=(String)map.get("id");
		String loginName =(String)map.get("loginName");
		String password = (String)map.get("password");
		String realName=(String)map.get("realName");
		String remark=(String)map.get("remark");
		String AllowLoginWeekDay[]=(String[])map.get("AllowLoginWeekDay");
		String str=(String)map.get("AllowLoginTime1");
		String str2=(String)map.get("AllowLoginTime2");
		SysUser user=null;
		try {
			
			
		if(id!=null&&!id.equals("")){
			
				 user=sysUserServiceImpl.findById(new BigInteger(id));
		
		}else{
		 user=new SysUser();
		//设置用户Id
		 setUserId(id,user);
		 user.setVersion(21);
		}
		//设置用户密码
		 setUserPassword(password, user);
		 SysUser usercheck=sysUserServiceImpl.findSysUserByLoginName(loginName);
		 //判断用户或修改时用户名是否重复
		 if(id.equals("")||id==null){
			 if(usercheck==null){
				user.setLoginName(loginName); 
			 }else{
				 return null;
			 }
		 }else{
			 if( usercheck==null){
				 user.setLoginName(loginName);
			 }else{
				 if(!(id).equals((usercheck.getId()+""))){
					return null; 
				 }
			 }
		 }
		 if(usercheck==null||id.equals("")){
			 
		 }
		 if(!(id).equals((usercheck.getId()+""))){
			 if(sysUserServiceImpl.findSysUserByLoginName(loginName)==null){
				 user.setLoginName(loginName); 
			 }else{
				 return null;
			 }	
		 }
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
			 
		}
		
        user.setRealName(realName);
        //允许登陆的星期
        setAllowLoginWeekDay(AllowLoginWeekDay, user);
        //设置允许的开始时间
        Time allowLoginTime1=null;
        if(!str.equals("")&&str!=null){
    		allowLoginTime1=Time.valueOf((str+":00"));
    		
    		}
        user.setAllowLoginTime1(allowLoginTime1);
        //设置允许的结束时间
        Time allowLoginTime2=null;
        if(!str2.equals("")&&str2!=null){
    	   allowLoginTime2=Time.valueOf((str2+":00"));
    	
    		}
        user.setAllowLoginTime2(allowLoginTime2);
        //设置备注
        setRemark(remark, user);
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
	 * @Title: setUserId   
	 * @Description:设置用户Id  
	 * @param id
	 * @param user        
	 */ 
	private void setUserId(String id,SysUser user){
		BigInteger element=null;
		if(id==null||id.equals("")){
			element=idGenerator.getNextId("SysUser.id");
		}else{
			element = new BigInteger(id);
		}
	    user.setId(element);
	} 
	/**   
	 * @Title: setUserPassword   
	 * @Description:设置用户密码   
	 * @param password
	 * @param user        
	 */ 
	private void setUserPassword(String password,SysUser user){
		 if(password.length()!=32){
				password=MD5Encrypt.encipher(password);
			    }
				user.setPassWord(password);
	}
	
	/**   
	 * @Title: setAllowLoginWeekDay   
	 * @Description: 设置允许用户登陆的星期时间           
	 */ 
	private void setAllowLoginWeekDay(String[]AllowLoginWeekDay,SysUser user){
		  if(AllowLoginWeekDay!=null){
	        	String loginWeekDay="";
	        	for(int i=0;i<AllowLoginWeekDay.length;i++){
	        		loginWeekDay+=AllowLoginWeekDay[i]+",";
	        	}
	            loginWeekDay=loginWeekDay.substring(0,loginWeekDay.length()-1);
	            user.setAllowLoginWeekDay(loginWeekDay);	
	        }
	}	
	/**   
	 * @Title: setSysUserGroupId   
	 * @Description:设置系统用户组Id   
	 * @param SysUserGroup        
	 * @throws Exception 
	 */ 
	private Boolean saveSysUserUserGroupId(Map map,SysUser user,HttpServletRequest request, HttpServletResponse response) throws Exception{
		String SysUserGroup[]=(String[])map.get("SysUserGroup");
		BigInteger id=user.getId();//需要添加的用户的id
		BigInteger localSysUserId= getSessionSysUser(request,response).getId();
		//Date date=getCurrentTime();
		 boolean flag=false;
		//System.out.println(date);
		//List<SysUserUserGroup> sysUserUserGroup=new ArrayList();
		SysUserUserGroup sysUserUsergroup=new SysUserUserGroup();
		for(int i=0;i<SysUserGroup.length;i++){
			
			 sysUserUsergroup.setUserId(Integer.parseInt(id+""));
			 sysUserUsergroup.setGroupId(Integer.parseInt(SysUserGroup[i]));
			 sysUserUsergroup.setCreator(Integer.parseInt(localSysUserId+""));
			 sysUserUsergroup.setCreateTime(getCurrentTime());
			 sysUserUserGroupServiceImp.saveSysUserUserGroup(sysUserUsergroup);
			 flag=true;
		}
	   
		// flag=sysUserUserGroupServiceImp.saveSysUserUserGroup(sysUserUserGroup);
		// System.out.println(flag);
		 return flag;
	}
	/**   
	 * @Title: updateSysUserGroupId   
	 * @Description: TODO(修改用户组)   
	 * @param map
	 * @param user
	 * @param request
	 * @param response
	 * @return        
	 * @throws Exception 
	 */
	@Transactional 
	private Boolean updateSysUserGroupId(Map map,SysUser user,HttpServletRequest request, HttpServletResponse response) throws Exception{
		String SysUserGroup[]=(String[])map.get("SysUserGroup");
		 boolean flag=true;
		BigInteger id=user.getId();//需要修改的用户的id
		List<SysUserUserGroup>list=sysUserUserGroupServiceImp.findByUserId(id);
		//删除系统用户用户组
		if(list.size()>0){
		for(SysUserUserGroup sysUserUserGroup:list){
			System.out.println("sysUserUserGroup"+sysUserUserGroup.getGroupId());
			sysUserUserGroupServiceImp.deleteSysUserUserGroup(sysUserUserGroup);
		}
		}
		BigInteger localSysUserId= getSessionSysUser(request,response).getId();
		//Date date=getCurrentTime();
		
		SysUserUserGroup sysUserUsergroup=new SysUserUserGroup();
		sysUserUsergroup.setCreator(Integer.parseInt(localSysUserId+""));
		sysUserUsergroup.setCreateTime(getCurrentTime());
	    sysUserUsergroup.setUserId(Integer.parseInt(id+""));
		for(int i=0;i<SysUserGroup.length;i++){
			 sysUserUsergroup.setGroupId(Integer.parseInt(SysUserGroup[i]));
			 if(!sysUserUserGroupServiceImp.saveSysUserUserGroup(sysUserUsergroup)){
				   return false;
		 }
			
		}
		 
	   
		// flag=sysUserUserGroupServiceImp.saveSysUserUserGroup(sysUserUserGroup);
		// System.out.println(flag);
		 return flag;
	}
    /**   
     * @Title: setRemark   
     * @Description:设置备注   
     * @param remark        
     */ 
    private void setRemark(String remark,SysUser user){
    	  if(remark!=null&&!remark.equals("")){
    	        user.setRemark(remark);
    	        }
    }
	/**   
	 * @Title: 根据条件查询分页（系统户名，系统用户分组ID）   
	 * @Description:    
	 * @return        
	 */ 
	private  ResultRightObject  getlistByUserNameAndGroupId(String sysUserName,String sysUserGroupId, int firstResult,int pageSize){
		List<SysUser> list = null;
		ResultRightObject rightObject=new ResultRightObject();
		ResultPageObject resultPageObject=null;
		try {
			list = sysUserServiceImpl.getListBySysUserNameAndGroupId(sysUserName, sysUserGroupId, firstResult, pageSize);
		    resultPageObject = new ResultPageObject();
			resultPageObject.setRows(list);
			resultPageObject.setTotal(String.valueOf(sysUserServiceImpl.getCountBySysUserNameAndGroupId(sysUserName, sysUserGroupId)));
			rightObject.setResultPageObject(resultPageObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		}
		return rightObject;
		
	}
	/**   
	 * @Title: getFrontSource   
	 * @Description:获取前端数据来源   
	 * @return        
	 * @throws UnsupportedEncodingException 
	 */
	 
	 private Map getFrontSource(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		Map maps=new HashMap();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String id=request.getParameter("sysUserId");
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		String realName=request.getParameter("realName");
		String remark=request.getParameter("remark");
		String AllowLoginWeekDay[]=request.getParameterValues("AllowLoginWeekDay");
		String AllowLoginTime1=request.getParameter("AllowLoginTime1");
		String AllowLoginTime2=request.getParameter("AllowLoginTime2");
		String SysUserGroup[]=request.getParameterValues("SysUserGroup");
		String version=request.getParameter("vers");
		maps.put("id", id);
		maps.put("loginName",loginName);
		maps.put("password", password);
		maps.put("realName",realName);
		maps.put("remark", remark);
		maps.put("AllowLoginWeekDay",AllowLoginWeekDay);
		maps.put("AllowLoginTime1",AllowLoginTime1);
		maps.put("AllowLoginTime2",AllowLoginTime2);
		maps.put("SysUserGroup",SysUserGroup);
		maps.put("version",version);
		return maps;
	}
	 /**   
	 * @Title: StringToDate   
	 * @Description: String转换Date  
	 * @param dateString
	 * @return        
	 */
	private Date StringToDate(String dateString){
		     Date date=null;
		     SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");  
		     try {
		    	 if(dateString!=null&&!dateString.equals("")){
			   date = sdf.parse(dateString);
			   }else{
				 date=new Date();
			   }
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				 e.printStackTrace(); 
			}  
		     return date;
	 }
	 /**   
	 * @Title: getCurrentTime   
	 * @Description:获得系统当前时间   
	 * @return        
	 */
	private  Date getCurrentTime(){
	    	long currentTime = System.currentTimeMillis();
	    	Date date = new Date(currentTime);
	         return date;
	    }
	/**   
	 * @Title: getSessionSysUser   
	 * @Description:获得当前登录用户   
	 * @return        
	 */
	 
	private SysUser getSessionSysUser(HttpServletRequest request, HttpServletResponse response){
	    HttpSession session=request.getSession();
	    SysUser sysUser=(SysUser)session.getAttribute("SysUser");
		return sysUser;
	}
}

 