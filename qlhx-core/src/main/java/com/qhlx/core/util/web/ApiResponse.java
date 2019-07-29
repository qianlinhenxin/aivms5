package com.qhlx.core.util.web;

import com.alibaba.fastjson.JSON;
import com.qhlx.core.bean.Code;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author
 * @param <T> 泛型参数
 */
public class ApiResponse<T> implements Serializable {
	private static final long serialVersionUID = -6495911812654393168L;

	/**
	 * 返回code
	 */
	private String retCode = Code.SUCCESS.getCode().toString();

	/**
	 * 返回消息
	 */
	private String retMsg = Code.SUCCESS.getMsgCn();

	/**
	 * 响应头
	 */
	private Map<String,Object> headers = new HashMap<>();

	/**
	 * 返回描述
	 */
	private String desc;

	/**
	 * 错误信息
	 */
	private String errorMsg;

	/**
	 * 返回数据
	 */
	private T retContent;

	/**
	 * 地理位置
	 */
	private Locale locale = Locale.CHINA;

	public ApiResponse(Locale locale) {
		this.locale = locale;
	}

	public ApiResponse() {

	}


	public String getRetCode() {
		return retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public Map<String, Object> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, Object> headers) {
		this.headers = headers;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public T getRetContent() {
		return retContent;
	}

	public void setRetContent(T retContent) {
		this.retContent = retContent;
	}

	public void setCode(Code code){
		if(locale.getLanguage().equals("zh")){
			this.retMsg = code.getMsgCn();
		}else {
			this.retMsg = code.getMsg();
		}
		this.retCode = code.getCode().toString();
	}
}
