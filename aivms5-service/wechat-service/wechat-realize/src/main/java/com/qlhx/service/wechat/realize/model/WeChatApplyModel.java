package com.qlhx.service.wechat.realize.model;

public class WeChatApplyModel {
	
	private int pageNo;
	private int pageSize;
	
	private Integer status;
	
	private Integer isTeam;
	
	private Integer launchType;//0 个人主动发起 1 邀约  2 代预约
	
//	private Integer showYaoYue;//0:不显示  1 显示

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsTeam() {
		return isTeam;
	}

	public void setIsTeam(Integer isTeam) {
		this.isTeam = isTeam;
	}

	public Integer getLaunchType() {
		return launchType;
	}

	public void setLaunchType(Integer launchType) {
		this.launchType = launchType;
	}
	
	

}
