package com.housemanagersystem.base;

import java.util.List;


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
    
	
	public ResultObject creatResult(boolean flag);
}
