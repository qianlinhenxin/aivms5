package com.qlhx.service.wechat.realize.model;

import java.util.Date;

public class BaseVisitor {
    private Integer id;

    private String name;

    private String sex;

    private String nation;

    private Date birthday;

    private String address;

    private String idnum;

    private String photo;

    private String issuing;

    private Date validitydatestart;

    private String validitydateend;

    private String phone;

    private String pinYin;

    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum == null ? null : idnum.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getIssuing() {
        return issuing;
    }

    public void setIssuing(String issuing) {
        this.issuing = issuing == null ? null : issuing.trim();
    }

    public Date getValiditydatestart() {
        return validitydatestart;
    }

    public void setValiditydatestart(Date validitydatestart) {
        this.validitydatestart = validitydatestart;
    }

    public String getValiditydateend() {
        return validitydateend;
    }

    public void setValiditydateend(String validitydateend) {
        this.validitydateend = validitydateend == null ? null : validitydateend.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPinYin() {
		return pinYin;
	}

	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}