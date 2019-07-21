package com.qlhx.service.base.api.vo.record;

import com.qlhx.base.util.bean.ObjectUtil;
import com.qlhx.base.vo.BaseVO;

import java.util.Date;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/21 15:30
 * @Description desc:
 */
public class BaseVisitorVO extends BaseVO {

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
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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
        this.address = address;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIssuing() {
        return issuing;
    }

    public void setIssuing(String issuing) {
        this.issuing = issuing;
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
        this.validitydateend = validitydateend;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        this.code = code;
    }

    public String toString() {
        return ObjectUtil.toString(this);
    }
}
