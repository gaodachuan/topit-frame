package com.topit.frame.common.view.servlet;

import java.io.Serializable;

/** 
* @ClassName: ResultObject 
* @Description: 业务类进行数据流操作的时候，返回的操作结果类。
* @author ivan.zhang 
* @date 2014年11月20日 下午2:40:41 
*/ 
public class ResultObject implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 
	* @Fields success : 业务操作是否成功
	*/ 
	private boolean success = true;
	/** 
	* @Fields errorCode : 错误代码  如果错误代码为0，表示业务操作成功，否则表示操作失败。
	*/ 
	private int errorCode = 0;
	/** 
	* @Fields errorMessage : 错误信息 
	*/ 
	private String errorMessage;
	/** 
	* @Fields errorDetail : 错误详细信息   一般页面显示的信息
	*/ 
	private String errorDetail;
	/** 
	* @Fields contextObject : 存放页面需要的对象
	*/ 
	private Object contextObject;
	
	public boolean getSuccess() {
		return this.success;
	}

	@SuppressWarnings("unused")
	private void setSuccess(boolean success) {
		this.success = success;
		this.errorCode= success ? 0:(this.errorCode==0) ? -1:this.errorCode;
	}

	public int getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
		this.success = (this.errorCode == 0);
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	private void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDetail() {
		return this.errorDetail;
	}

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}
	
	public Object getContextObject() {
		return contextObject;
	}

	public void setContextObject(Object contextObject) {
		this.contextObject = contextObject;
	}


	public void error(int errorCode, String errorMessage, String errorDetail) {
		this.setErrorCode(errorCode);
		this.setErrorMessage(errorMessage);
		this.setErrorDetail(errorDetail);
	}

	public void error(int errorCode, String errorMessage) {
		this.error(errorCode, errorMessage, null);
	}

	public void error(String errorMessage) {
		this.error(-1, errorMessage);
	}

	@Override
	public String toString() {
		return "Result [success=" + success + ", errorCode=" + errorCode
				+ ", errorMessage=" + errorMessage + ", errorDetail="
				+ errorDetail + "]";
	}
}
