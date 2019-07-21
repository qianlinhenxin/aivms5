package com.qlhx.service.wechat.realize.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class VisitReasons extends BaseApiModel implements Serializable {
    /** 来访事由选项表id */
    private Integer id;

    /** 来访事由 */
    private String value;

    /** 是否使用（0：使用 1：不使用） */
    private Integer isuse;

    /**
     * 来访事由选项表id This method returns the value of the database column
     * u_visit_reasons.id
     *
     * @return the value of u_visit_reasons.id
     *
     * @mbggenerated
     */
    public Integer getId() {
	return id;
    }

    /**
     * 来访事由选项表id This method sets the value of the database column
     * u_visit_reasons.id
     *
     * @param id
     *            the value for u_visit_reasons.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * 来访事由 This method returns the value of the database column
     * u_visit_reasons.value
     *
     * @return the value of u_visit_reasons.value
     *
     * @mbggenerated
     */
    public String getValue() {
	return value;
    }

    /**
     * 来访事由 This method sets the value of the database column
     * u_visit_reasons.value
     *
     * @param value
     *            the value for u_visit_reasons.value
     *
     * @mbggenerated
     */
    public void setValue(String value) {
	this.value = value == null ? null : value.trim();
    }

    /**
     * 是否使用（0：使用 1：不使用） This method returns the value of the database column
     * u_visit_reasons.isuse
     *
     * @return the value of u_visit_reasons.isuse
     *
     * @mbggenerated
     */
    public Integer getIsuse() {
	return isuse;
    }

    /**
     * 是否使用（0：使用 1：不使用） This method sets the value of the database column
     * u_visit_reasons.isuse
     *
     * @param isuse
     *            the value for u_visit_reasons.isuse
     *
     * @mbggenerated
     */
    public void setIsuse(Integer isuse) {
	this.isuse = isuse;
    }
}