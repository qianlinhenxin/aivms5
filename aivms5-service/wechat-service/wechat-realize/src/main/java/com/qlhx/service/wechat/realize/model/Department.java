package com.qlhx.service.wechat.realize.model;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

/**
 * Created by rongcan on 2017/7/4.
 */
@SuppressWarnings("serial")
@XmlRootElement
public class Department extends BaseApiModel implements Serializable {

    private Integer id;

    private String value;

    private Integer parentId;

    private Integer syncId;

    private Integer syncParentId;

    private String companyNum;

    private String sta;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

    public Integer getParentId() {
	return parentId;
    }

    public void setParentId(Integer parentId) {
	this.parentId = parentId;
    }

    /**
     * 同步部门ID
     * 
     * @return the syncId
     */
    public Integer getSyncId() {
	return syncId;
    }

    /**
     * 同步部门ID
     * 
     * @param syncId
     *            the syncId to set
     */
    public void setSyncId(Integer syncId) {
	this.syncId = syncId;
    }

    /**
     * 同步部门父级ID
     * 
     * @return the syncParentId
     */
    public Integer getSyncParentId() {
	return syncParentId;
    }

    /**
     * 同步部门父级ID
     * 
     * @param syncParentId
     *            the syncParentId to set
     */
    public void setSyncParentId(Integer syncParentId) {
	this.syncParentId = syncParentId;
    }

    /**
     * 企业编号
     * 
     * @return the companyNum
     */
    public String getCompanyNum() {
	return companyNum;
    }

    /**
     * 企业编号
     * 
     * @param companyNum
     *            the companyNum to set
     */
    public void setCompanyNum(String companyNum) {
	this.companyNum = companyNum;
    }

    /**
     * 数据状态，0：自动识别（新增或更新） 1：新增 2：更新 3：删除
     * 
     * @return the status
     */
    public String getSta() {
	return sta;
    }

    /**
     * 数据状态，0：自动识别（新增或更新） 1：新增 2：更新 3：删除
     * 
     * @param
     *            the status to set
     */
    public void setSta(String sta) {
	this.sta = sta;
    }

}
