package com.qlhx.service.wechat.realize.model;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class UUserNation implements Serializable {

    /** 民族编号 */
    private Integer nationNum;

    /** 民族名称 */
    private String value;

    /**
     * 民族编号 This method returns the value of the database column
     * u_natiom.nationNum
     *
     * @return the value of u_natiom.nationNum
     *
     * @mbggenerated
     */
    public Integer getNationNum() {
	return nationNum;
    }

    /**
     * 民族编号 This method sets the value of the database column u_natiom.nationNum
     *
     * @param
     *
     * @mbggenerated
     */
    public void setNationNum(Integer nationNum) {
	this.nationNum = nationNum;
    }

    /**
     * 民族名称 This method returns the value of the database column u_natiom.value
     *
     * @return the value of u_natiom.value
     *
     * @mbggenerated
     */
    public String getValue() {
	return value;
    }

    /**
     * 民族名称 This method sets the value of the database column u_natiom.value
     *
     * @param value
     *            the value for u_natiom.value
     *
     * @mbggenerated
     */
    public void setValue(String value) {
	this.value = value == null ? null : value.trim();
    }
}