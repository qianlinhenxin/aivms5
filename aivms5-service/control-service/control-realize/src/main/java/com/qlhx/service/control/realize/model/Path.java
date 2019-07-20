package com.qlhx.service.control.realize.model;

import java.util.Date;

public class Path {
    private Integer id;

    private String pathName;

    private String pathDescribe;

    private Date createdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName == null ? null : pathName.trim();
    }

    public String getPathDescribe() {
        return pathDescribe;
    }

    public void setPathDescribe(String pathDescribe) {
        this.pathDescribe = pathDescribe == null ? null : pathDescribe.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}