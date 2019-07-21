package com.qlhx.service.wechat.realize.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * <p>
 * Title:来访人信息实体类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author alaska
 * @date 2017年8月8日 下午3:56:24
 */

@SuppressWarnings("serial")
@XmlRootElement
public class Visitor extends BaseApiModel implements Serializable {
	
    /** 访客ID */
    private Long id;
    
    /** 访客名称 */
    private String name;

    /** 性别 */
    private String sex;

    /** 民族（编号见民族列表） */
    private String nation;

    /** 出生年月日 */
    private String birthday;

    /** 住址 */
    private String address;

    /** 身份证号 */
    private String idnum;

    /** 身份证照片路径（以身份证号为照片名称） */
    private String photo;
    
    private String sitePhoto;
    
    /** 签发机关 */
    private String issuing;

    /** 有效期，开始时间 */
    private String validitydatestart;

    /** 有效期，结束时间 */
    private String validitydateend;

    /** 访客手机号码 */
    private String phone;
    /** 预约开始时间 **/
    private String startTime;
    /** 预约结束时间 **/
    private String endTime;

    private String pinYin;
    
    //上传公安网时间
    private String uploadPoliceTime;
    
    //上传公安网状态 1成功 0 失败
    private Integer uploadPoliceStatus;
    
    //上传公安网结果
    private String uploadPoliceResult;

    public Integer getIsbindfinger() {
        return isbindfinger;
    }

    public void setIsbindfinger(Integer isbindfinger) {
        this.isbindfinger = isbindfinger;
    }

    private  Integer isbindfinger;

    /**
     * 访客ID This method returns the value of the database column u_visitor.id
     *
     * @return the value of u_visitor.id
     *
     * @mbggenerated
     */
    public Long getId() {
	return id;
    }

    /**
     * 访客ID This method sets the value of the database column u_visitor.id
     *
     * @param id
     *            the value for u_visitor.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * 访客名称 This method returns the value of the database column u_visitor.name
     *
     * @return the value of u_visitor.name
     *
     * @mbggenerated
     */
    public String getName() {
	return name;
    }

    /**
     * 访客名称 This method sets the value of the database column u_visitor.name
     *
     * @param name
     *            the value for u_visitor.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
	this.name = name == null ? null : name.trim();
    }

    /**
     * 性别 This method returns the value of the database column u_visitor.sex
     *
     * @return the value of u_visitor.sex
     *
     * @mbggenerated
     */
    public String getSex() {
	return sex;
    }

    /**
     * 性别 This method sets the value of the database column u_visitor.sex
     *
     * @param sex
     *            the value for u_visitor.sex
     *
     * @mbggenerated
     */
    public void setSex(String sex) {
	this.sex = sex;
    }

    /**
     * 民族（编号见民族列表） This method returns the value of the database column
     * u_visitor.nation
     *
     * @return the value of u_visitor.nation
     *
     * @mbggenerated
     */
    public String getNation() {
	return nation;
    }

    /**
     * 民族（编号见民族列表） This method sets the value of the database column
     * u_visitor.nation
     *
     * @param nation
     *            the value for u_visitor.nation
     *
     * @mbggenerated
     */
    public void setNation(String nation) {
	this.nation = nation == null ? null : nation.trim();
    }

    /**
     * 出生年月日 This method returns the value of the database column
     * u_visitor.birthday
     *
     * @return the value of u_visitor.birthday
     *
     * @mbggenerated
     */
    public String getBirthday() {
	return birthday != null ? birthday.replace(".0", "") : null;
    }

    /**
     * 出生年月日 This method sets the value of the database column
     * u_visitor.birthday
     *
     * @param birthday
     *            the value for u_visitor.birthday
     *
     * @mbggenerated
     */
    public void setBirthday(String birthday) {
	this.birthday = birthday;
    }

    /**
     * 住址 This method returns the value of the database column u_visitor.address
     *
     * @return the value of u_visitor.address
     *
     * @mbggenerated
     */
    public String getAddress() {
	return address;
    }

    /**
     * 住址 This method sets the value of the database column u_visitor.address
     *
     * @param address
     *            the value for u_visitor.address
     *
     * @mbggenerated
     */
    public void setAddress(String address) {
	this.address = address == null ? null : address.trim();
    }

    /**
     * 身份证号 This method returns the value of the database column u_visitor.idNum
     *
     * @return the value of u_visitor.idNum
     *
     * @mbggenerated
     */
    public String getIdnum() {
	return idnum;
    }

    /**
     * 身份证号 This method sets the value of the database column u_visitor.idNum
     *
     * @param idnum
     *            the value for u_visitor.idNum
     *
     * @mbggenerated
     */
    public void setIdnum(String idnum) {
	this.idnum = idnum == null ? null : idnum.trim();
    }

    /**
     * 身份证照片路径（以身份证号为照片名称） This method returns the value of the database column
     * u_visitor.photo
     *
     * @return the value of u_visitor.photo
     *
     * @mbggenerated
     */
    public String getPhoto() {
	return  photo;
    }

    /**
     * 身份证照片路径（以身份证号为照片名称） This method sets the value of the database column
     * u_visitor.photo
     *
     * @param photo
     *            the value for u_visitor.photo
     *
     * @mbggenerated
     */
    public void setPhoto(String photo) {
	this.photo = photo == null ? null : photo.trim();
    }

    /**
     * 签发机关 This method returns the value of the database column
     * u_visitor.issuing
     *
     * @return the value of u_visitor.issuing
     *
     * @mbggenerated
     */
    public String getIssuing() {
	return issuing;
    }

    /**
     * 签发机关 This method sets the value of the database column u_visitor.issuing
     *
     * @param issuing
     *            the value for u_visitor.issuing
     *
     * @mbggenerated
     */
    public void setIssuing(String issuing) {
	this.issuing = issuing == null ? null : issuing.trim();
    }

    /**
     * 有效期，开始时间 This method returns the value of the database column
     * u_visitor.validityDateStart
     *
     * @return the value of u_visitor.validityDateStart
     *
     * @mbggenerated
     */
    public String getValiditydatestart() {
	return validitydatestart != null ? validitydatestart.replace(".0", "")
		: null;
    }

    /**
     * 有效期，开始时间 This method sets the value of the database column
     * u_visitor.validityDateStart
     *
     * @param validitydatestart
     *            the value for u_visitor.validityDateStart
     *
     * @mbggenerated
     */
    public void setValiditydatestart(String validitydatestart) {
	this.validitydatestart = validitydatestart;
    }

    /**
     * 有效期，结束时间 This method returns the value of the database column
     * u_visitor.validityDateEnd
     *
     * @return the value of u_visitor.validityDateEnd
     *
     * @mbggenerated
     */
    public String getValiditydateend() {
	return validitydateend != null ? validitydateend.replace(".0", "")
		: null;
    }

    /**
     * 有效期，结束时间 This method sets the value of the database column
     * u_visitor.validityDateEnd
     *
     * @param validitydateend
     *            the value for u_visitor.validityDateEnd
     *
     * @mbggenerated
     */
    public void setValiditydateend(String validitydateend) {
	this.validitydateend = validitydateend;
    }

    /**
     * 访客手机号码 This method returns the value of the database column
     * u_visitor.phone
     *
     * @return the value of u_visitor.phone
     *
     * @mbggenerated
     */
    public String getPhone() {
	return phone;
    }

    /**
     * 访客手机号码 This method sets the value of the database column u_visitor.phone
     *
     * @param phone
     *            the value for u_visitor.phone
     *
     * @mbggenerated
     */
    public void setPhone(String phone) {
	this.phone = phone == null ? null : phone.trim();
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
	return startTime;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(String startTime) {
	this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
	return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(String endTime) {
	this.endTime = endTime;
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

    public String getSitephoto() {
        return sitePhoto;
    }

    public void setSitephoto(String sitephoto) {
        this.sitePhoto = sitephoto;
    }

	public String getUploadPoliceTime() {
		return StringUtils.isBlank(uploadPoliceTime)?null:uploadPoliceTime.replace(".0", "") ;
	}

	public void setUploadPoliceTime(String uploadPoliceTime) {
		this.uploadPoliceTime = uploadPoliceTime;
	}

	public Integer getUploadPoliceStatus() {
		return uploadPoliceStatus;
	}

	public void setUploadPoliceStatus(Integer uploadPoliceStatus) {
		this.uploadPoliceStatus = uploadPoliceStatus;
	}

	public String getUploadPoliceResult() {
		return uploadPoliceResult;
	}

	public void setUploadPoliceResult(String uploadPoliceResult) {
		this.uploadPoliceResult = uploadPoliceResult;
	}
    
    
    
    
	
}