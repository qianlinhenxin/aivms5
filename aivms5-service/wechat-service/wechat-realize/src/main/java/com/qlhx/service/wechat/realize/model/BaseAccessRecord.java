package com.qlhx.service.wechat.realize.model;


import java.util.Date;

public class BaseAccessRecord {
    private Integer id;

    private Integer parentid;

    private String rcode;

    private Integer vid;

    private Integer uid;

    private Date createtime;

    private String starttime;

    private String endtime;

    private String logofftime;

    private Integer status;

    private String reasons;

    private String unit;

    private Integer num;

    private Integer type;

    private Integer isprintvoucher;

    private Integer ispullcard;

    private Integer cardtype;

    private String cardnum;

    private String sitephoto;

    private Integer isupload;

    private Integer yyid;

    private String companycode;

    private String terminalcode;

    private String zhuxiaoterminalcode;

    private Integer terminalmanagerid;

    private Integer zhuxiaomanagerid;

    private Date tabtime;

    private Date uploadtime;

    private String syncid;
    
    private String uname;
    
    private String vname;
    
    private BaseVisitor visitor;
    
    private BaseMember user;
    
    private Integer pageNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getRcode() {
        return rcode;
    }

    public void setRcode(String rcode) {
        this.rcode = rcode == null ? null : rcode.trim();
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getLogofftime() {
        return logofftime;
    }

    public void setLogofftime(String logofftime) {
        this.logofftime = logofftime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons == null ? null : reasons.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsprintvoucher() {
        return isprintvoucher;
    }

    public void setIsprintvoucher(Integer isprintvoucher) {
        this.isprintvoucher = isprintvoucher;
    }

    public Integer getIspullcard() {
        return ispullcard;
    }

    public void setIspullcard(Integer ispullcard) {
        this.ispullcard = ispullcard;
    }

    public Integer getCardtype() {
        return cardtype;
    }

    public void setCardtype(Integer cardtype) {
        this.cardtype = cardtype;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum == null ? null : cardnum.trim();
    }

    public String getSitephoto() {
        return sitephoto;
    }

    public void setSitephoto(String sitephoto) {
        this.sitephoto = sitephoto == null ? null : sitephoto.trim();
    }

    public Integer getIsupload() {
        return isupload;
    }

    public void setIsupload(Integer isupload) {
        this.isupload = isupload;
    }

    public Integer getYyid() {
        return yyid;
    }

    public void setYyid(Integer yyid) {
        this.yyid = yyid;
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode == null ? null : companycode.trim();
    }

    public String getTerminalcode() {
        return terminalcode;
    }

    public void setTerminalcode(String terminalcode) {
        this.terminalcode = terminalcode == null ? null : terminalcode.trim();
    }

    public String getZhuxiaoterminalcode() {
        return zhuxiaoterminalcode;
    }

    public void setZhuxiaoterminalcode(String zhuxiaoterminalcode) {
        this.zhuxiaoterminalcode = zhuxiaoterminalcode == null ? null : zhuxiaoterminalcode.trim();
    }

    public Integer getTerminalmanagerid() {
        return terminalmanagerid;
    }

    public void setTerminalmanagerid(Integer terminalmanagerid) {
        this.terminalmanagerid = terminalmanagerid;
    }

    public Integer getZhuxiaomanagerid() {
        return zhuxiaomanagerid;
    }

    public void setZhuxiaomanagerid(Integer zhuxiaomanagerid) {
        this.zhuxiaomanagerid = zhuxiaomanagerid;
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

    public String getSyncid() {
        return syncid;
    }

    public void setSyncid(String syncid) {
        this.syncid = syncid == null ? null : syncid.trim();
    }

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public BaseVisitor getVisitor() {
		return visitor;
	}

	public void setVisitor(BaseVisitor visitor) {
		this.visitor = visitor;
	}

	public BaseMember getUser() {
		return user;
	}

	public void setUser(BaseMember user) {
		this.user = user;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}


    // 来访车辆
    private BaseVisitorCar vCar;

    public BaseVisitorCar getvCar() {
        return vCar;
    }

    public void setvCar(BaseVisitorCar vCar) {
        this.vCar = vCar;
    }
}