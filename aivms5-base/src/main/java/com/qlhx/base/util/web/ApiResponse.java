package com.qlhx.base.util.web;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author
 * @param <T> 泛型参数
 */
public class ApiResponse<T> implements Serializable {
	private static final long serialVersionUID = -6495911812654393168L;
	public static final String SUCCESS_CODE = "0";
	public static final String FAIL_CODE="1";
	public static final String SUCCESS_CODE_TEMP = "1000";
	public static final String SUCCESS_MESSAGE = "OK";
	private String retCode = SUCCESS_CODE;
	private String retMessage = SUCCESS_MESSAGE;
	private boolean hasError = false;
	private Exception exception = null;
	private T retContent;


	public boolean isHasError() {
		return hasError;
	}

	public String getErrorMessage() {
		if (this.hasError)
			return this.exception.getMessage();
		return "";
	}

	public void setException(Exception exception) {
		this.hasError = true;
		this.retCode = FAIL_CODE;
		this.exception = exception;
	}

	public String getRetCode() {
		return this.retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
		
	}

	public void setResponseCode(String retCode){
		this.retCode = retCode;

	}

	
	public void setRetCode(String retCode,Object [] args){
		this.retCode = retCode;
	}

	public String getRetMessage() {
		return this.retMessage;
	}

	public void setRetMessage(String retMessage) {
		this.retMessage = retMessage;
	}

	public T getRetContent() {
		return this.retContent;
	}

	public void setRetContent(T retContent) {
		this.retContent = retContent;
	}

	public boolean isSuccessful() {
		return "0".equals(getRetCode());
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
