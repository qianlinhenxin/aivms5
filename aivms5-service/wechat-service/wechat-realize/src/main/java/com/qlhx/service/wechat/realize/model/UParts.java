package com.qlhx.service.wechat.realize.model;

import java.util.Date;

public class UParts {
    private Long primaryid;

    private Long id;

    private String partcode;

    private String partname;

    private String model;

    private String companycode;

    private String areacode;
    
    private Date tabtime;

    private Date uploadtime;

    private Date nativeCreatedate;

    private Date nativeUpdatedate;

    public Long getPrimaryid() {
        return primaryid;
    }

    public void setPrimaryid(Long primaryid) {
        this.primaryid = primaryid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartcode() {
        return partcode;
    }

    public void setPartcode(String partcode) {
        this.partcode = partcode == null ? null : partcode.trim();
    }

    public String getPartname() {
        return partname;
    }

    public void setPartname(String partname) {
        this.partname = partname == null ? null : partname.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
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

    public Date getNativeCreatedate() {
        return nativeCreatedate;
    }

    public void setNativeCreatedate(Date nativeCreatedate) {
        this.nativeCreatedate = nativeCreatedate;
    }

    public Date getNativeUpdatedate() {
        return nativeUpdatedate;
    }

    public void setNativeUpdatedate(Date nativeUpdatedate) {
        this.nativeUpdatedate = nativeUpdatedate;
    }

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
    
    
}