package com.qlhx.service.base.api.vo.record;


import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class UUserDepVO extends BaseDepartmentVO {

    private Integer usercount;

    public Integer getUsercount() {
        return usercount;
    }

    public void setUsercount(Integer usercount) {
        this.usercount = usercount;
    }
}