package com.qlhx.service.wechat.realize.model;

import java.util.Date;


/**
 * 
 * @author ldh
 *
 */
public class BaseApiModel {
	
    private Long primaryId;

	private String companyCode;

	private String companyName;

	private String areaCode;
	
	private String areaName;

	private String terminalCode;
	
	private Date tabTime;
	
	private Date uploadTime;
	
    private Date native_createDate;
    
    private Date native_updateDate;
    
    private int operateStatus; //0新增  1修改  2删除 3查询

	
	public Long getPrimaryId() {
		return primaryId;
	}

	public void setPrimaryId(Long primaryId) {
		this.primaryId = primaryId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public Date getNative_createDate() {
		return native_createDate;
	}

	public void setNative_createDate(Date native_createDate) {
		this.native_createDate = native_createDate;
	}

	public Date getNative_updateDate() {
		return native_updateDate;
	}

	public void setNative_updateDate(Date native_updateDate) {
		this.native_updateDate = native_updateDate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getTabTime() {
		return tabTime;
	}

	public void setTabTime(Date tabTime) {
		this.tabTime = tabTime;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(int operateStatus) {
		this.operateStatus = operateStatus;
	}
}
