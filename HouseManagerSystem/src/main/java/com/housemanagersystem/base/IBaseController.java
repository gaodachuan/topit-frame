package com.housemanagersystem.base;

import java.util.List;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.housemanagersystem.domain.ComboxDTO;
import com.topit.frame.common.view.servlet.ResultObject;

public interface IBaseController {
	/**
	 * 获得下拉的列表
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public List<ComboxDTO> getComboxdata(String type) throws Exception;
    /**
     * 封装结果
     * @param flag
     * @return
     */
	
	public ResultObject creatResult(boolean flag);
	/**
	 * 封装请求参数，默认除分页信息以外，全是String类型，如有需要，子类需要重写改方法，改变参数类型
	 * @param request
	 * @return
	 */
	
	public Map<String, Object> createParam(HttpServletRequest request);
}
