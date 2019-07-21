package com.qlhx.service.wechat.realize.model;

import java.util.Date;

public class UArea{
	
	private Long primaryId;
    
	private Long id;
	
	private String areaname;//区域名称
	
    private String parentareacode;;
    
    private String level;//级别

    private String bak;

    private String createtime;
    
    private String areacode;
    
    private String companycode;
    
    private String companyName;

    private Date tabtime;

    private Date uploadtime;
    
    private Date native_createDate;
    
    private Date native_updateDate;
    
    private String name;//行政区划
    
    private String policeCode;

    private String policeName;

    private String policeSonCode;

    private String policeSonName;
    
    private String villageAddress;
    
    private Integer isuploadpolice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak == null ? null : bak.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

	public String getParentareacode() {
		return parentareacode;
	}

	public void setParentareacode(String parentareacode) {
		this.parentareacode = parentareacode;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getCompanycode() {
		return companycode;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPoliceCode() {
		return policeCode;
	}

	public void setPoliceCode(String policeCode) {
		this.policeCode = policeCode;
	}

	public String getPoliceName() {
		return policeName;
	}

	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

	public String getPoliceSonCode() {
		return policeSonCode;
	}

	public void setPoliceSonCode(String policeSonCode) {
		this.policeSonCode = policeSonCode;
	}

	public String getPoliceSonName() {
		return policeSonName;
	}

	public void setPoliceSonName(String policeSonName) {
		this.policeSonName = policeSonName;
	}

	public String getVillageAddress() {
		return villageAddress;
	}

	public void setVillageAddress(String villageAddress) {
		this.villageAddress = villageAddress;
	}

	public Long getPrimaryId() {
		return primaryId;
	}

	public void setPrimaryId(Long primaryId) {
		this.primaryId = primaryId;
	}

	public Integer getIsuploadpolice() {
		return isuploadpolice;
	}

	public void setIsuploadpolice(Integer isuploadpolice) {
		this.isuploadpolice = isuploadpolice;
	}

	
    
}