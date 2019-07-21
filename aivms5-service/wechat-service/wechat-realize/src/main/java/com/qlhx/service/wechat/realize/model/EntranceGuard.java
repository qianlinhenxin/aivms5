package com.qlhx.service.wechat.realize.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class EntranceGuard extends BaseApiModel implements Serializable {
    /** 门禁表ID */
    private Integer id;

    /** 门禁编号 */
    private String egnum;

    /** 门禁名称 */
    private String egname;

    /** 门禁IP */
    private String egip;

    /** 门禁端口 */
    private Integer egport;

    /** 门禁启用（0：启用 1：禁用） */
    private Integer isuse;

    /**
     * 门禁表ID This method returns the value of the database column
     * u_entrance_guard.id
     *
     * @return the value of u_entrance_guard.id
     *
     * @mbggenerated
     */
    public Integer getId() {
	return id;
    }

    /**
     * 门禁表ID This method sets the value of the database column
     * u_entrance_guard.id
     *
     * @param id
     *            the value for u_entrance_guard.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * 门禁编号 This method returns the value of the database column
     * u_entrance_guard.egNum
     *
     * @return the value of u_entrance_guard.egNum
     *
     * @mbggenerated
     */
    public String getEgnum() {
	return egnum;
    }

    /**
     * 门禁编号 This method sets the value of the database column
     * u_entrance_guard.egNum
     *
     * @param egnum
     *            the value for u_entrance_guard.egNum
     *
     * @mbggenerated
     */
    public void setEgnum(String egnum) {
	this.egnum = egnum == null ? null : egnum.trim();
    }

    /**
     * 门禁名称 This method returns the value of the database column
     * u_entrance_guard.egName
     *
     * @return the value of u_entrance_guard.egName
     *
     * @mbggenerated
     */
    public String getEgname() {
	return egname;
    }

    /**
     * 门禁名称 This method sets the value of the database column
     * u_entrance_guard.egName
     *
     * @param egname
     *            the value for u_entrance_guard.egName
     *
     * @mbggenerated
     */
    public void setEgname(String egname) {
	this.egname = egname == null ? null : egname.trim();
    }

    /**
     * 门禁IP This method returns the value of the database column
     * u_entrance_guard.egIP
     *
     * @return the value of u_entrance_guard.egIP
     *
     * @mbggenerated
     */
    public String getEgip() {
	return egip;
    }

    /**
     * 门禁IP This method sets the value of the database column
     * u_entrance_guard.egIP
     *
     * @param egip
     *            the value for u_entrance_guard.egIP
     *
     * @mbggenerated
     */
    public void setEgip(String egip) {
	this.egip = egip == null ? null : egip.trim();
    }

    /**
     * 门禁端口 This method returns the value of the database column
     * u_entrance_guard.egPort
     *
     * @return the value of u_entrance_guard.egPort
     *
     * @mbggenerated
     */
    public Integer getEgport() {
	return egport;
    }

    /**
     * 门禁端口 This method sets the value of the database column
     * u_entrance_guard.egPort
     *
     * @param egport
     *            the value for u_entrance_guard.egPort
     *
     * @mbggenerated
     */
    public void setEgport(Integer egport) {
	this.egport = egport;
    }

    /**
     * 门径启用（0：启用 1：禁用） This method returns the value of the database column
     * u_entrance_guard.isUse
     *
     * @return the value of u_entrance_guard.isUse
     *
     * @mbggenerated
     */
    public Integer getIsuse() {
	return isuse;
    }

    /**
     * 门径启用（0：启用 1：禁用） This method sets the value of the database column
     * u_entrance_guard.isUse
     *
     * @param isuse
     *            the value for u_entrance_guard.isUse
     *
     * @mbggenerated
     */
    public void setIsuse(Integer isuse) {
	this.isuse = isuse;
    }
}