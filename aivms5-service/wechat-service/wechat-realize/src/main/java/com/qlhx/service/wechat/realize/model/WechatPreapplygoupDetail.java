package com.qlhx.service.wechat.realize.model;

import java.util.Date;

public class WechatPreapplygoupDetail {
    private Integer id;

    private String applyid;

    private String openid;

    private String utel;

    private String uname;

    private Date jointime;

    private Integer rstatus;

    private Integer accessrecordid;

    private String syncid;

    private Integer type;

    private String companycode;

    private Date tabtime;

    private Date uploadtime;

    private String cardnum;

    private String inviteropenid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplyid() {
        return applyid;
    }

    public void setApplyid(String applyid) {
        this.applyid = applyid == null ? null : applyid.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getUtel() {
        return utel;
    }

    public void setUtel(String utel) {
        this.utel = utel == null ? null : utel.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public Date getJointime() {
        return jointime;
    }

    public void setJointime(Date jointime) {
        this.jointime = jointime;
    }

    public Integer getRstatus() {
        return rstatus;
    }

    public void setRstatus(Integer rstatus) {
        this.rstatus = rstatus;
    }

    public Integer getAccessrecordid() {
        return accessrecordid;
    }

    public void setAccessrecordid(Integer accessrecordid) {
        this.accessrecordid = accessrecordid;
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

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode == null ? null : companycode.trim();
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

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum == null ? null : cardnum.trim();
    }

    public String getInviteropenid() {
        return inviteropenid;
    }

    public void setInviteropenid(String inviteropenid) {
        this.inviteropenid = inviteropenid == null ? null : inviteropenid.trim();
    }


    private WechatWxuser wxuser;

    public WechatWxuser getWxuser() {
        return wxuser;
    }

    public void setWxuser(WechatWxuser wxuser) {
        this.wxuser = wxuser;
    }
}