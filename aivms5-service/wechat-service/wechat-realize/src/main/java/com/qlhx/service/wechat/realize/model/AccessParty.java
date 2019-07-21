package com.qlhx.service.wechat.realize.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AccessParty implements Serializable {
    /** 接入方表id */
    private Integer apid;

    /** 接入方appkey */
    private String appkey;

    /** 接入方私钥 */
    private String privatekey;

    /** 接入方公钥 */
    private String publickey;

    /** 接入方名称 */
    private String appname;

    /** 是否启用（0：启用 1：禁用），默认启用 */
    private Integer isuse;

    /** 安全ip地址列表，分隔符为英文逗号（,） **/
    private String ipList;

    /** 接入方可发送短信条数 */
    private Integer canSendsmsnum;

    /** 联系人 */
    private String linkMan;

    /** 联系人手机号 */
    private String linkPhone;

    /** 创建时间 */
    private String createTime;

    /**
     * 接入方表id This method returns the value of the database column
     * sms_access_party.apId
     *
     * @return the value of sms_access_party.apId
     *
     * @mbggenerated
     */
    public Integer getApid() {
	return apid;
    }

    /**
     * 接入方表id This method sets the value of the database column
     * sms_access_party.apId
     *
     * @param apid
     *            the value for sms_access_party.apId
     *
     * @mbggenerated
     */
    public void setApid(Integer apid) {
	this.apid = apid;
    }

    /**
     * 接入方appkey This method returns the value of the database column
     * sms_access_party.appkey
     *
     * @return the value of sms_access_party.appkey
     *
     * @mbggenerated
     */
    public String getAppkey() {
	return appkey;
    }

    /**
     * 接入方appkey This method sets the value of the database column
     * sms_access_party.appkey
     *
     * @param appkey
     *            the value for sms_access_party.appkey
     *
     * @mbggenerated
     */
    public void setAppkey(String appkey) {
	this.appkey = appkey == null ? null : appkey.trim();
    }

    /**
     * 接入方私钥 This method returns the value of the database column
     * sms_access_party.privatekey
     *
     * @return the value of sms_access_party.privatekey
     *
     * @mbggenerated
     */
    public String getPrivatekey() {
	return privatekey;
    }

    /**
     * 接入方私钥 This method sets the value of the database column
     * sms_access_party.privatekey
     *
     * @param privatekey
     *            the value for sms_access_party.privatekey
     *
     * @mbggenerated
     */
    public void setPrivatekey(String privatekey) {
	this.privatekey = privatekey == null ? null : privatekey.trim();
    }

    /**
     * 接入方公钥 This method returns the value of the database column
     * sms_access_party.publickey
     *
     * @return the value of sms_access_party.publickey
     *
     * @mbggenerated
     */
    public String getPublickey() {
	return publickey;
    }

    /**
     * 接入方公钥 This method sets the value of the database column
     * sms_access_party.publickey
     *
     * @param publickey
     *            the value for sms_access_party.publickey
     *
     * @mbggenerated
     */
    public void setPublickey(String publickey) {
	this.publickey = publickey == null ? null : publickey.trim();
    }

    /**
     * 接入方名称 This method returns the value of the database column
     * sms_access_party.appname
     *
     * @return the value of sms_access_party.appname
     *
     * @mbggenerated
     */
    public String getAppname() {
	return appname;
    }

    /**
     * 接入方名称 This method sets the value of the database column
     * sms_access_party.appname
     *
     * @param appname
     *            the value for sms_access_party.appname
     *
     * @mbggenerated
     */
    public void setAppname(String appname) {
	this.appname = appname == null ? null : appname.trim();
    }

    /**
     * 是否启用（0：启用 1：禁用），默认启用 This method returns the value of the database column
     * sms_access_party.isuse
     *
     * @return the value of sms_access_party.isuse
     *
     * @mbggenerated
     */
    public Integer getIsuse() {
	return isuse;
    }

    /**
     * 是否启用（0：启用 1：禁用），默认启用 This method sets the value of the database column
     * sms_access_party.isuse
     *
     * @param isuse
     *            the value for sms_access_party.isuse
     *
     * @mbggenerated
     */
    public void setIsuse(Integer isuse) {
	this.isuse = isuse;
    }

    /**
     * <p>
     * Description:安全ip地址列表，分隔符为英文逗号（,）
     * </p>
     * 
     * @return the ipList
     */
    public String getIpList() {
	return ipList;
    }

    /**
     * <p>
     * Description:安全ip地址列表，分隔符为英文逗号（,）
     * </p>
     * 
     * @param ipList
     *            the ipList to set
     */
    public void setIpList(String ipList) {
	this.ipList = ipList;
    }

    /**
     * <p>
     * Description:接入方可发送短信条数
     * </p>
     * 
     * @return the canSendsmsnum
     */
    public Integer getCanSendsmsnum() {
	return canSendsmsnum;
    }

    /**
     * <p>
     * Description:接入方可发送短信条数
     * </p>
     * 
     * @param canSendsmsnum
     *            the canSendsmsnum to set
     */
    public void setCanSendsmsnum(Integer canSendsmsnum) {
	this.canSendsmsnum = canSendsmsnum;
    }

    /**
     * <p>
     * Description:联系人
     * </p>
     * 
     * @return the linkMan
     */
    public String getLinkMan() {
	return linkMan;
    }

    /**
     * <p>
     * Description:联系人
     * </p>
     * 
     * @param linkMan
     *            the linkMan to set
     */
    public void setLinkMan(String linkMan) {
	this.linkMan = linkMan;
    }

    /**
     * <p>
     * Description:联系人手机号
     * </p>
     * 
     * @return the linkPhone
     */
    public String getLinkPhone() {
	return linkPhone;
    }

    /**
     * <p>
     * Description:联系人手机号
     * </p>
     * 
     * @param linkPhone
     *            the linkPhone to set
     */
    public void setLinkPhone(String linkPhone) {
	this.linkPhone = linkPhone;
    }

    /**
     * <p>
     * Description:创建时间
     * </p>
     * 
     * @return the createTime
     */
    public String getCreateTime() {
	return createTime != null ? createTime.replace(".0", "") : null;
    }

    /**
     * <p>
     * Description:创建时间
     * </p>
     * 
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(String createTime) {
	this.createTime = createTime;
    }

}