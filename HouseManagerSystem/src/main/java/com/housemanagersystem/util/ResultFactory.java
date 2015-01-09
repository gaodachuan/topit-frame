package com.housemanagersystem.util;

import com.topit.frame.common.view.servlet.ResultObject;

public class ResultFactory {
	private ResultFactory() {
	}

	public static ResultObject createResult(int code, String msg) {

		ResultObject res = new ResultObject();
		res.setErrorCode(code);
		res.setErrorDetail(msg);
		return res;
	}
}
