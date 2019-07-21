package com.qlhx.service.wechat.realize.model;

import java.util.Date;

public class WechatPreapplygoup {
    private Integer id;

    private Integer applyuserid;

    private String applyopenid;

    private String companycode;

    private String reson;

    private Date createtime;

    private Integer status;

    private String responsemsg;

    private Integer uid;

    private String syncid;

    private Integer type;

    private Date starttime;

    private Date endtime;

    private Date tabtime;

    private Date uploadtime;

    private String unit;

    private Integer isteam;

    private Integer jionpersonnum;

    private String activitylogo;

    private String activitytitle;

    private String subtitle;

    private String address;

    private String organizer;

    private String content;

    private Integer isactive;

    private Integer maxpersonnum;

    private String city;

    private Integer launchtype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplyuserid() {
        return applyuserid;
    }

    public void setApplyuserid(Integer applyuserid) {
        this.applyuserid = applyuserid;
    }

    public String getApplyopenid() {
        return applyopenid;
    }

    public void setApplyopenid(String applyopenid) {
        this.applyopenid = applyopenid == null ? null : applyopenid.trim();
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode == null ? null : companycode.trim();
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson == null ? null : reson.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getResponsemsg() {
        return responsemsg;
    }

    public void setResponsemsg(String responsemsg) {
        this.responsemsg = responsemsg == null ? null : responsemsg.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getSyncid() {
        return syncid;
    }

    public void setSyncid(String syncid) {
        this.syncid = syncid == null ? null : syncid.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getIsteam() {
        return isteam;
    }

    public void setIsteam(Integer isteam) {
        this.isteam = isteam;
    }

    public Integer getJionpersonnum() {
        return jionpersonnum;
    }

    public void setJionpersonnum(Integer jionpersonnum) {
        this.jionpersonnum = jionpersonnum;
    }

    public String getActivitylogo() {
        return activitylogo;
    }

    public void setActivitylogo(String activitylogo) {
        this.activitylogo = activitylogo == null ? null : activitylogo.trim();
    }

    public String getActivitytitle() {
        return activitytitle;
    }

    public void setActivitytitle(String activitytitle) {
        this.activitytitle = activitytitle == null ? null : activitytitle.trim();
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer == null ? null : organizer.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }

    public Integer getMaxpersonnum() {
        return maxpersonnum;
    }

    public void setMaxpersonnum(Integer maxpersonnum) {
        this.maxpersonnum = maxpersonnum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getLaunchtype() {
        return launchtype;
    }

    public void setLaunchtype(Integer launchtype) {
        this.launchtype = launchtype;
    }
}