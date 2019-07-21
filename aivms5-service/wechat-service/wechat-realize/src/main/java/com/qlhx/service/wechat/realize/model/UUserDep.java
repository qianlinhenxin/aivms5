package com.qlhx.service.wechat.realize.model;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class UUserDep extends Department implements Serializable {

    private Integer usercount;

    public Integer getUsercount() {
	return usercount;
    }

    public void setUsercount(Integer usercount) {
	this.usercount = usercount;
    }
}