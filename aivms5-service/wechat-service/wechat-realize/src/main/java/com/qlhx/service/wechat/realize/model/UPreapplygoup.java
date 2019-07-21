package com.qlhx.service.wechat.realize.model;

import com.qlhx.base.util.IConfig;

import java.util.Date;
import java.util.List;



public class UPreapplygoup {
    private Integer id;

    private Integer applyuserid;

    private String applyopenid;
    
    private String applyname; //申请人，数据库中没有
    
    private String toname;//被访人，数据库中没有
    
    private String fromname;//来访人，数据库中没有

    private String companycode;

    private String areacode;
    
    private String reson;

    private String createtime;

    private Integer status;

    private String responsemsg;

    private Integer uid;

    private String syncid;

    private Integer type;

    private String starttime;

    private String endtime;

    private Date tabtime;

    private Date uploadtime;

    private String unit;
    
    private String toUnit;//拜访单位，数据库无此字段，方便查询结果映射
    
    private List preapplygoupdetail;
    
    private List userList;
    
    private Integer applyCount;//数据库无此字段，方便查询结果映射
    
    private String  cardNum;//卡号
    
    private Integer isteam;//是否是团队预约 1 是 0 否
    
    private Integer maxpersonnum;
    
    private Integer jionpersonnum;
    
    private String activitylogo;
    
    private String activitylogoAbsolutePath;//活动图片的绝对路径,数据库没有，方便前端显示
    
    private String activityTitle;
    
    private String subtitle;
    
    private String address;
    
    private String organizer;
    
    private String content;
    
    private String city;
    
    private Integer isActive;
    
    private Integer launchType;//0 个人主动发起 1 邀约  2 代预约
    
    private Integer isJoin;//数据库无此字段，方便查询结果映射,是否加入活动 1 是 0 否
    
    private String applyId;//根据不同的openId查询出子表的applyId
    
    private UPreapplygoupdetail detail;
    
    private final String imageServer_preview = IConfig.getInstance().get("imageServer_preview");

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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
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

	public List getPreapplygoupdetail() {
		return preapplygoupdetail;
	}

	public void setPreapplygoupdetail(List preapplygoupdetail) {
		this.preapplygoupdetail = preapplygoupdetail;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public Integer getApplyCount() {
		return applyCount;
	}

	public void setApplyCount(Integer applyCount) {
		this.applyCount = applyCount;
	}

	public String getApplyname() {
		return applyname;
	}

	public void setApplyname(String applyname) {
		this.applyname = applyname;
	}

	public String getToname() {
		return toname;
	}

	public void setToname(String toname) {
		this.toname = toname;
	}

	public List getUserList() {
		return userList;
	}

	public void setUserList(List userList) {
		this.userList = userList;
	}

	public Integer getIsteam() {
		return isteam;
	}

	public void setIsteam(Integer isteam) {
		this.isteam = isteam;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public Integer getMaxpersonnum() {
		return maxpersonnum;
	}

	public void setMaxpersonnum(Integer maxpersonnum) {
		this.maxpersonnum = maxpersonnum;
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
		
		this.activitylogo = activitylogo;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getIsJoin() {
		return (isJoin == null )? 0 :isJoin;
	}

	public void setIsJoin(Integer isJoin) {
		this.isJoin = isJoin;
	}

	public String getActivitylogoAbsolutePath() {
		if(this.activitylogo != null && !this.activitylogo.trim().equals(""))
		{
			activitylogoAbsolutePath = imageServer_preview + this.activitylogo;
		}
		return activitylogoAbsolutePath;
	}

	public void setActivitylogoAbsolutePath(String activitylogoAbsolutePath) {
		this.activitylogoAbsolutePath = activitylogoAbsolutePath;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public Integer getLaunchType() {
		return launchType;
	}

	public void setLaunchType(Integer launchType) {
		this.launchType = launchType;
	}

	public UPreapplygoupdetail getDetail() {
		return detail;
	}

	public void setDetail(UPreapplygoupdetail detail) {
		this.detail = detail;
	}

	public String getFromname() {
		return fromname;
	}

	public void setFromname(String fromname) {
		this.fromname = fromname;
	}

	public String getToUnit() {
		return toUnit;
	}

	public void setToUnit(String toUnit) {
		this.toUnit = toUnit;
	}
    
}