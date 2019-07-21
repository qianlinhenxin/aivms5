package com.qlhx.service.wechat.realize.model;

public class UCompany {
    private Long id;

    private String companycode;

    private String companyname;

    private String address;

    private String tel;

    private String bak;

    private Long createuser;

    private String createtime;
    
    private String index;
    
    private String areaName;

    private String policeCode;

    private String policeName;

    private String policeSonCode;

    private String policeSonName;

    private Integer isuploadpolice;
    
    private String province;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode == null ? null : companycode.trim();
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak == null ? null : bak.trim();
    }

    public Long getCreateuser() {
        return createuser;
    }

    public void setCreateuser(Long createuser) {
        this.createuser = createuser;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public Integer getIsuploadpolice() {
		return isuploadpolice;
	}

	public void setIsuploadpolice(Integer isuploadpolice) {
		this.isuploadpolice = isuploadpolice;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	
    
}