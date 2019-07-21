package com.qlhx.service.wechat.realize.model;

import java.util.Date;

public class BaseNatiom {
    private Integer nationNum;

    private String value;

    private Date tabtime;

    private Date uploadtime;

    public Integer getNationNum() {
        return nationNum;
    }

    public void setNationNum(Integer nationNum) {
        this.nationNum = nationNum;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Date getTabtime() {
        return tabtime;
    }

    public void setTabtime(Date tabtime) {
        this.tabtime = tabtime;
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }
}