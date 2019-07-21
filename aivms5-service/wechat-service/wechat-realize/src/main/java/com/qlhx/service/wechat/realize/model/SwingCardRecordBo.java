package com.qlhx.service.wechat.realize.model;

/**
 * Created by rongcan on 2017/7/11.
 */
public class SwingCardRecordBo extends SwingCardRecord {
    private String sname;
    private String egName;
    private String egIP;
    private String egPort;

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getEgName() {
        return egName;
    }

    public void setEgName(String egName) {
        this.egName = egName;
    }

    public String getEgIP() {
        return egIP;
    }

    public void setEgIP(String egIP) {
        this.egIP = egIP;
    }

    public String getEgPort() {
        return egPort;
    }

    public void setEgPort(String egPort) {
        this.egPort = egPort;
    }
}
