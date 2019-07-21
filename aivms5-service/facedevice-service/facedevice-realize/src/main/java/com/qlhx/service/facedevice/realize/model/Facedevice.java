package com.qlhx.service.facedevice.realize.model;

import java.util.Date;

public class Facedevice {
    private Integer id;

    private String sn;

    private String ip;

    private String port;

    private String name;

    private String driver;

    private String model;

    private String producer;

    private Date usetime;

    private String position;

    private String gis;

    private String manager;

    private Date createdate;

    private Integer onlineStatus;

    private Integer runStatus;

    private Date lastOnlineTime;

    private String brand;

    private String username;

    private String pwd;

    private  String callurl;

    //门不接门禁时，区分进出。1进2出 .默认进门
    private Integer inorout=1;

    public Integer getInorout() {
        return inorout;
    }

    public void setInorout(Integer inout) {
        this.inorout = inout;
    }

    private  Integer isReg;//海康设备特有，是否注册 1已注册，0 未注册
    
    private  Integer isAlarm =0;//海康设备特有，是否开启刷脸监控 1已开启，0 未开启

    public String getCallurl() {
        return callurl;
    }

    public void setCallurl(String callurl) {
        this.callurl = callurl;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver == null ? null : driver.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer == null ? null : producer.trim();
    }

    public Date getUsetime() {
        return usetime;
    }

    public void setUsetime(Date usetime) {
        this.usetime = usetime;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getGis() {
        return gis;
    }

    public void setGis(String gis) {
        this.gis = gis == null ? null : gis.trim();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Integer getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(Integer runStatus) {
        this.runStatus = runStatus;
    }

    public Date getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(Date lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

	public Integer getIsReg() {
		return isReg;
	}

	public void setIsReg(Integer isReg) {
		this.isReg = isReg;
	}

	public Integer getIsAlarm() {
		return isAlarm;
	}

	public void setIsAlarm(Integer isAlarm) {
		this.isAlarm = isAlarm;
	}
    
    
}