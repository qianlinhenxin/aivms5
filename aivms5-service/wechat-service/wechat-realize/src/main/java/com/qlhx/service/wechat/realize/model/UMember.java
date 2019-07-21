package com.qlhx.service.wechat.realize.model;


import net.sf.json.JSONObject;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class UMember extends BaseApiModel implements Serializable {


    private Long id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 邮箱 | 登录帐号
     */
    private String email;
    /**
     * 密码
     */
    private transient String pswd;
    /**
     * 创建时间
     */
    private String createTime;
    
    private String create_time;//前端页面使用
    /**
     * 最后登录时间
     */
    private String lastLoginTime;
    /**
     * 1:有效，0:禁止登录
     */
    private Long status;
    /**
     * 性别
     */
    private Integer sex;

    /* 民族编码 */
    private Integer nation;

//    @ApiModelProperty(value = "民族编码对象")
//    private UUserNation nations;

    // 出生日期
    private String birthday;
    // 住址
    private String address;
    // 身份证号码
    private String idNum;
    // 身份证头像图片地址
    private String photo;
    // 身份证头像图片base64
    private String photoBase64;
    // 身份证签发机构
    private String issuing;
    // 身份证签发时间
    private String validityDateStart;
    // 身份证过期时间
    private String validityDateEnd;
    // 手机号码
    private String phone;
    // 部门ID
//    @ApiModelProperty(value = "部门实体对象")
//    private UUserDep dep;
    private Integer depID;
    
    private String deptname;
    // 座机号码
    private String telephone;
    // 员工门禁卡编号
    private String ecardNum;
    // 员工门禁卡有效截止时间
    private String ecardEndTime;
    // 员工名称首字母
    private String pinYin;

    private String syncId;

    private String companyNum;

    private String sta;
    
    private String tip;//被预约时的提示

    public UMember() {
    }

    public UMember(UMember user) {
	this.id = user.getId();
	this.nickname = user.getNickname();
	this.email = user.getEmail();
	this.pswd = user.getPswd();
	this.createTime = user.getCreateTime();
	this.lastLoginTime = user.getLastLoginTime();
	this.sex = user.getSex();
//	this.nations = user.getNations();
	this.birthday = user.getBirthday();
	this.address = user.getAddress();
	this.idNum = user.getIdNum();
	this.photo = user.getPhoto();
	this.issuing = user.getIssuing();
	this.validityDateStart = user.getValidityDateStart();
	this.validityDateEnd = user.getValidityDateEnd();
	this.phone = user.getPhone();
//	this.dep = user.getDep();
	this.telephone = user.getTelephone();
	this.nation = user.getNation();
	this.depID = user.getDepID();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getNickname() {
	return nickname;
    }

    public void setNickname(String nickname) {
	this.nickname = nickname;
    }

    public String getEmail() {
	return email;
    }

    public Long getStatus() {
	return status;
    }

    public void setStatus(Long status) {
	this.status = status;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPswd() {
	return pswd;
    }

    public void setPswd(String pswd) {
	this.pswd = pswd;
    }

    public String getCreateTime() {
	return createTime != null ? createTime.replace(".0", "") : null;
    }

    public void setCreateTime(String createTime) {
	this.createTime = createTime;
    }

    public String getLastLoginTime() {
	return lastLoginTime != null ? lastLoginTime.replace(".0", "") : null;
    }

    public void setLastLoginTime(String lastLoginTime) {
	this.lastLoginTime = lastLoginTime;
    }

    public String toString() {
	return JSONObject.fromObject(this).toString();

    }

    /**
     * @return the sex
     */
    public Integer getSex() {
	return sex;
    }

    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(Integer sex) {
	this.sex = sex;
    }

    /**
     * @return the nation
     */
//    public UUserNation getNations() {
//	return nations;
//    }
//
//    /**
//     * @param nation
//     *            the nation to set
//     */
//    public void setNations(UUserNation nations) {
//	this.nations = nations;
//    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
	return birthday;
    }

    /**
     * @param birthday
     *            the birthday to set
     */
    public void setBirthday(String birthday) {
	this.birthday = birthday;
    }

    /**
     * @return the address
     */
    public String getAddress() {
	return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
	this.address = address;
    }

    /**
     * @return the idNum
     */
    public String getIdNum() {
	return idNum;
    }

    /**
     * @param idNum
     *            the idNum to set
     */
    public void setIdNum(String idNum) {
	this.idNum = idNum;
    }

    /**
     * @return the photo
     */
    public String getPhoto() {
    	return photo;
    }

    /**
     * @param photo
     *            the photo to set
     */
    public void setPhoto(String photo) {
	this.photo = photo;
    }

    /**
     * @return the issuing
     */
    public String getIssuing() {
	return issuing;
    }

    /**
     * @param issuing
     *            the issuing to set
     */
    public void setIssuing(String issuing) {
	this.issuing = issuing;
    }

    /**
     * @return the validityDateStart
     */
    public String getValidityDateStart() {

	return validityDateStart != null ? validityDateStart.replace(".0", "")
		: null;
    }

    /**
     * @param validityDateStart
     *            the validityDateStart to set
     */
    public void setValidityDateStart(String validityDateStart) {
	this.validityDateStart = validityDateStart;
    }

    /**
     * @return the validityDateEnd
     */
    public String getValidityDateEnd() {
	return validityDateEnd;
    }

    /**
     * @param validityDateEnd
     *            the validityDateEnd to set
     */
    public void setValidityDateEnd(String validityDateEnd) {
	this.validityDateEnd = validityDateEnd;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
	   return phone;
    }

    /**
     * @param phone
     *            the phone to set
     */
    public void setPhone(String phone) {
	this.phone = phone;
    }

    /**
     * @return the depID
     */
//    public UUserDep getDep() {
//	return dep;
//    }
//
//    /**
//     * @param depID
//     *            the depID to set
//     */
//    public void setDep(UUserDep dep) {
//	this.dep = dep;
//    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
	return telephone;
    }

    /**
     * @param telephone
     *            the telephone to set
     */
    public void setTelephone(String telephone) {
	this.telephone = telephone;
    }

    /**
     * @return the nation
     */
    public Integer getNation() {
	return nation;
    }

    /**
     * @param nation
     *            the nation to set
     */
    public void setNation(Integer nation) {
	this.nation = nation;
    }

    /**
     * @return the depID
     */
    public Integer getDepID() {
	return depID;
    }

    /**
     * @param depID
     *            the depID to set
     */
    public void setDepID(Integer depID) {
	this.depID = depID;
    }

    /**
     * @return the ecardNum
     */
    public String getEcardNum() {
	return ecardNum;
    }

    /**
     * @param ecardNum
     *            the ecardNum to set
     */
    public void setEcardNum(String ecardNum) {
	this.ecardNum = ecardNum;
    }

    /**
     * @return the ecardEndTime
     */
    public String getEcardEndTime() {
	return ecardEndTime != null ? ecardEndTime.replace(".0", "") : null;
    }

    /**
     * @param ecardEndTime
     *            the ecardEndTime to set
     */
    public void setEcardEndTime(String ecardEndTime) {
	this.ecardEndTime = ecardEndTime;
    }

    /**
     * @return the pinYin
     */
    public String getPinYin() {
	return pinYin;
    }

    /**
     * @param pinYin
     *            the pinYin to set
     */
    public void setPinYin(String pinYin) {
	this.pinYin = pinYin;
    }

    /**
     * @return the syncId
     */
    public String getSyncId() {
	return null;
    }

    /**
     * @param syncId
     *            the syncId to set
     */
    public void setSyncId(String syncId) {
	this.syncId = syncId;
    }

    /**
     * @return the companyNum
     */
    public String getCompanyNum() {
	return companyNum;
    }

    /**
     * @param companyNum
     *            the companyNum to set
     */
    public void setCompanyNum(String companyNum) {
	this.companyNum = companyNum;
    }

    /**
     * @return the sta
     */
    public String getSta() {
	return sta;
    }

    /**
     * @param sta
     *            the sta to set
     */
    public void setSta(String sta) {
	this.sta = sta;
    }

	public String getPhotoBase64() {
		return photoBase64;
	}

	public void setPhotoBase64(String photoBase64) {
		this.photoBase64 = photoBase64;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
    
	
    

}