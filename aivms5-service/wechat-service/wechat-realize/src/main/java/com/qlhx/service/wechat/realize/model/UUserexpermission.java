package com.qlhx.service.wechat.realize.model;

import java.util.Date;

public class UUserexpermission {
    private Integer id;

    private Integer uid;

    private String companycode;

    private String areacode;

    private Integer dayexnum;

    private Integer defualtexnum;

    private Date uploadtime;

    private Date tabtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode == null ? null : companycode.trim();
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode == null ? null : areacode.trim();
    }

    public Integer getDayexnum() {
        return dayexnum;
    }

    public void setDayexnum(Integer dayexnum) {
        this.dayexnum = dayexnum;
    }

    public Integer getDefualtexnum() {
        return defualtexnum;
    }

    public void setDefualtexnum(Integer defualtexnum) {
        this.defualtexnum = defualtexnum;
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public Date getTabtime() {
        return tabtime;
    }

    public void setTabtime(Date tabtime) {
        this.tabtime = tabtime;
    }
}