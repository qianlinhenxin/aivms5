package com.qlhx.service.base.realize.model;


import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@SuppressWarnings("serial")
@XmlRootElement
public class UUserDep extends BaseDepartment implements Serializable {

    private Integer usercount;

    public Integer getUsercount() {
        return usercount;
    }

    public void setUsercount(Integer usercount) {
        this.usercount = usercount;
    }
}