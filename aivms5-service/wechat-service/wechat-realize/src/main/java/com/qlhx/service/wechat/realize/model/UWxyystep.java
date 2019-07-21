package com.qlhx.service.wechat.realize.model;

import java.util.Date;

public class UWxyystep {
    private Integer primaryid;

    private Integer id;

    private Integer wxyystatus;

    private String wxyyremark;

    private String syncid;

    private String xxyyitemid;

    private String visitorrecordid;

    private String companycode;

    private String areacode;

    private Date tabtime;

    private Date uploadtime;

    private Integer yytype;

    public Integer getPrimaryid() {
        return primaryid;
    }

    public void setPrimaryid(Integer primaryid) {
        this.primaryid = primaryid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWxyystatus() {
        return wxyystatus;
    }

    public void setWxyystatus(Integer wxyystatus) {
        this.wxyystatus = wxyystatus;
    }

    public String getWxyyremark() {
        return wxyyremark;
    }

    public void setWxyyremark(String wxyyremark) {
        this.wxyyremark = wxyyremark == null ? null : wxyyremark.trim();
    }

    public String getSyncid() {
        return syncid;
    }

    public void setSyncid(String syncid) {
        this.syncid = syncid == null ? null : syncid.trim();
    }

    public String getXxyyitemid() {
        return xxyyitemid;
    }

    public void setXxyyitemid(String xxyyitemid) {
        this.xxyyitemid = xxyyitemid == null ? null : xxyyitemid.trim();
    }

    public String getVisitorrecordid() {
        return visitorrecordid;
    }

    public void setVisitorrecordid(String visitorrecordid) {
        this.visitorrecordid = visitorrecordid == null ? null : visitorrecordid.trim();
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

    public Integer getYytype() {
        return yytype;
    }

    public void setYytype(Integer yytype) {
        this.yytype = yytype;
    }
}