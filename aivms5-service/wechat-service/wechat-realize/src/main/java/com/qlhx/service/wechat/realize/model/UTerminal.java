package com.qlhx.service.wechat.realize.model;

import java.util.Date;

public class UTerminal{
    private Long id;

    private String terminalname;

    private String mac;

    private String ip;

    private String bak;

    private String lastonlinedate;

    private String areacode;

    private String companycode;
    
    private String terminalcode;

    private String model;

    private Integer status;

    private Integer onlineStatus;//在线状态  1在线  0离线
    
    private Date tabtime;
    /** 上传标记 */
    private Date uploadtime;
    
    private Date native_createDate;
    
    private Date native_updateDate;
    
    private String companyName;
    
    private String areaName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
 
	public String getTerminalname() {
		return terminalname;
	}

	public void setTerminalname(String terminalname) {
		this.terminalname = terminalname;
	}

	public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak == null ? null : bak.trim();
    }

    public String getLastonlinedate() {
        return lastonlinedate;
    }

    public void setLastonlinedate(String lastonlinedate) {
        this.lastonlinedate = lastonlinedate;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode == null ? null : areacode.trim();
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode == null ? null : companycode.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public Integer getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(Integer onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public String getTerminalcode() {
		return terminalcode;
	}

	public void setTerminalcode(String terminalcode) {
		this.terminalcode = terminalcode;
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

	public Date getNative_createDate() {
		return native_createDate;
	}

	public void setNative_createDate(Date native_createDate) {
		this.native_createDate = native_createDate;
	}

	public Date getNative_updateDate() {
		return native_updateDate;
	}

	public void setNative_updateDate(Date native_updateDate) {
		this.native_updateDate = native_updateDate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
    
    

}