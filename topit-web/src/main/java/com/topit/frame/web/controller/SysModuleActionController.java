 package com.topit.frame.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.topit.frame.busniess.base.ISysModuleActionService;
import com.topit.frame.common.view.servlet.ResultObject;

 @Controller
 @RequestMapping(value="/sysmoduleaction")
 public class SysModuleActionController {

	 @Resource(name="sysModuleActionServiceImp")
	 ISysModuleActionService sysModuleActionServiceImp;
	 
	 /**   
	 * @Title: getCurrentDateTime   
	 * @Description: 获取系统当前时间   
	 * @return        
	 */
		 
	public String getCurrentDateTime(){
		Date date=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}
	 
	 @RequestMapping(value="/getlist")
	 @ResponseBody
	 public List<Map<String, Object>> getList(HttpServletRequest request,HttpServletResponse response){
		 
		 String groupId=request.getParameter("groupId");
		 String moduleId=request.getParameter("tabId");
		 List<Map<String, Object>> list=null;
		 
		 try {
			list=sysModuleActionServiceImp.getList(groupId,moduleId);
		} catch (Exception e) {
			 e.printStackTrace();			 
		}
		 
		 return list;
		 
	 }
	 
	 @SuppressWarnings("unchecked")
	@RequestMapping(value="/saveCheckedAuthorization")
	 @ResponseBody
	 public ResultObject saveCheckedAuthorization(HttpServletRequest request,HttpServletResponse response){
		 ResultObject resultObject=new ResultObject();
		 ObjectMapper mapper=new ObjectMapper();
		 
		 String data=request.getParameter("getChecked");
		 String groupId=request.getParameter("groupId");
		 String moduleIds=request.getParameter("treeSelected");
		 
		 try {
			List<LinkedHashMap<String, Object>> list=mapper.readValue(data, List.class);
			String values="";
			for (int i = 0; i < list.size(); i++) {
	            Map<String, Object> map = list.get(i);
	            String string="";
	            for(Entry<String, Object> entry : map.entrySet()){
	            	string+=entry.getValue()+",";
	            }
	            string="("+string.substring(0, string.length()-1)+",0,'"+this.getCurrentDateTime()+"'),";
	            values += string;
	        }
			//将插入结果保存到values中
			if ("[]".equals(data)){
				values="";
			}else{
				values = values.substring(0, values.length()-1);
			}
			moduleIds="("+moduleIds+")";
			sysModuleActionServiceImp.saveCheckedAuthorization(groupId, moduleIds,values);
			resultObject.setErrorCode(0);
			resultObject.setErrorDetail("权限更改成功！");
		} catch (Exception e) {
			resultObject.setErrorCode(1);
			resultObject.setErrorDetail("权限更改失败！"); 
			e.printStackTrace();	 
		} 
		 
		 return resultObject;
	 }
	 
	 @RequestMapping(value="/saveAllAuthorization")
	 @ResponseBody
	 public ResultObject saveAllAuthorization(HttpServletRequest request,HttpServletResponse response){
		 
		 ResultObject resultObject=new ResultObject();
		 
		 
		 try {
			
			 String groupId=request.getParameter("groupId");
			 String moduleId=request.getParameter("tabId");
			 
			 sysModuleActionServiceImp.saveAllAuthorization(groupId, moduleId);
			 
			 
			resultObject.setErrorCode(0);
			resultObject.setErrorDetail("权限更改成功！");
		} catch (Exception e) {;
			resultObject.setErrorCode(1);
			resultObject.setErrorDetail("权限更改失败！");
			e.printStackTrace();
		}
		 
		 return resultObject;
		 
	 }
}

 