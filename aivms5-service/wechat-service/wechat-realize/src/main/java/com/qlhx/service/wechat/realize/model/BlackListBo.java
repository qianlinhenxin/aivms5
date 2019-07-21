package com.qlhx.service.wechat.realize.model;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by rongcan on 2017/6/28.
 */
public class BlackListBo extends Visitor {
    /**  */
    private String bid;

    private String ifblack;
    /**
     * 访客黑名单原因
     */
    private String reason;

    private boolean isBlacklist;

    private String vId;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getIfblack() {
        return ifblack;
    }

    public void setIfblack(String ifblack) {
        this.ifblack = ifblack;
    }

    public boolean isBlacklist() {
        isBlacklist = StringUtils.equals(vId, ifblack);
        return isBlacklist;
    }

    public String getvId() {
        return vId;
    }

    public void setvId(String vId) {
        this.vId = vId;
    }

}

