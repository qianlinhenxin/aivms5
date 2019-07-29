package com.qhlx.core.util.web;

import com.alibaba.fastjson.JSON;
import com.qhlx.core.bean.Code;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @param <T> 泛型参数
 */
public class WebResponse<T> implements Serializable {
	/**
	 * 返回code
	 */
	private String code = Code.SUCCESS.getCode().toString();

	/**
	 * 返回消息
	 */
	private String msg = Code.SUCCESS.getMsgCn();

	/**
	 * 响应头
	 */
	private Map<String,Object> headers = new HashMap<>();

	/**
	 * 返回描述
	 */
	private String desc;

	/**
	 * 返回数据
	 */
	private T body;

	public WebResponse() {
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
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

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public void setCode(Code code){
		this.code = code.getCode().toString();
		this.msg = code.getMsg();
	}
}
