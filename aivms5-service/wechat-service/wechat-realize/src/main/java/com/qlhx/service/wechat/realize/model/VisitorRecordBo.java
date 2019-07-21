package com.qlhx.service.wechat.realize.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rongcan on 2017/6/26.
 */
public class VisitorRecordBo extends AccessRecord implements Serializable {

    /**
     * 访客ID
     */
    private Integer vid;

    /**
     * 访客名称
     */
    private String name;

    /**
     * 性别（0： 1：）
     */
    private String sex;

    /**
     * 民族（编号见民族列表）
     */
    private String nation;

    /**
     * 出生年月日
     */
    private String birthday;

    /**
     * 住址
     */
    private String address;

    /**
     * 身份证号
     */
    private String idnum;

    /**
     * 身份证照片路径（以身份证号为照片名称）
     */
    private String photo;

    /**
     * 签发机关
     */
    private String issuing;

    /**
     * 有效期，开始时间
     */
    private String validitydatestart;

    /**
     * 有效期，结束时间
     */
    private String validitydateend;

    /**
     * 访客手机号码
     */
    private String phone;

    /**
     * 被访人姓名
     */
    private String nickname;

    /**
     * 随行人员数量
     */
    private Integer childCount;


    private boolean istimeout;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return this.nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNum() {
        return this.idnum;
    }

    public void setIdNum(String idNum) {
        this.idnum = idNum;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIssuing() {
        return this.issuing;
    }

    public void setIssuing(String issuing) {
        this.issuing = issuing;
    }

    public String getValidityDateStart() {
        return this.validitydatestart;
    }

    public void setValidityDateStart(String validityDateStart) {
        this.validitydatestart = validityDateStart;
    }

    public String getValidityDateEnd() {
        return this.validitydateend;
    }

    public void setValidityDateEnd(String validityDateEnd) {
        this.validitydateend = validityDateEnd;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }
    
 

	public boolean isIstimeout() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(super.getEndtime());
            Date dt2 = df.parse(df.format(new Date()));
            if (dt1.getTime() > dt2.getTime()) {
                return false;
            }
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return true;
        }
    }

    public void setIstimeout(boolean istimeout) {
        this.istimeout = istimeout;
    }
}
