package com.qlhx.service.wechat.realize.model;

import java.io.Serializable;

/**
 * select2下拉框数据格式 Created by rongcan on 2017/6/30.
 */
@SuppressWarnings("serial")
public class ComboResult implements Serializable {
    private String id;
    private String text;
    private String parent;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public String getParent() {
	return parent;
    }

    public void setParent(String parent) {
	this.parent = parent;
    }
}
