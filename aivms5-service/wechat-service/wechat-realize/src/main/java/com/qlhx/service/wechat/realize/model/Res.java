package com.qlhx.service.wechat.realize.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class Res extends BaseApiModel implements Serializable {
    /** 携带物品选项表id */
    private Integer id;

    /** 携带物品名称 */
    private String value;

    /** 是否使用（0：使用 1：不使用） */
    private Integer isuse;

    /**
     * 携带物品选项表id This method returns the value of the database column u_res.id
     *
     * @return the value of u_res.id
     *
     * @mbggenerated
     */
    public Integer getId() {
	return id;
    }

    /**
     * 携带物品选项表id This method sets the value of the database column u_res.id
     *
     * @param id
     *            the value for u_res.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * 携带物品名称 This method returns the value of the database column u_res.value
     *
     * @return the value of u_res.value
     *
     * @mbggenerated
     */
    public String getValue() {
	return value;
    }

    /**
     * 携带物品名称 This method sets the value of the database column u_res.value
     *
     * @param value
     *            the value for u_res.value
     *
     * @mbggenerated
     */
    public void setValue(String value) {
	this.value = value == null ? null : value.trim();
    }

    /**
     * 是否使用（0：使用 1：不使用） This method returns the value of the database column
     * u_res.isuse
     *
     * @return the value of u_res.isuse
     *
     * @mbggenerated
     */
    public Integer getIsuse() {
	return isuse;
    }

    /**
     * 是否使用（0：使用 1：不使用） This method sets the value of the database column
     * u_res.isuse
     *
     * @param isuse
     *            the value for u_res.isuse
     *
     * @mbggenerated
     */
    public void setIsuse(Integer isuse) {
	this.isuse = isuse;
    }
}