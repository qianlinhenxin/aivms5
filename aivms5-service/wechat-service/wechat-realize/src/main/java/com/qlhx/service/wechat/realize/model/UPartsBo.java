package com.qlhx.service.wechat.realize.model;

public class UPartsBo extends UParts {
	
	private int relevanceStatus ;//外设关联状态（0：关联 1：废弃关联）
	
	private String lastRecordTime;//最后状态时间
	
	private int partsStatus; //状态（0：故障 1：正常）

	public int getRelevanceStatus() {
		return relevanceStatus;
	}

	public void setRelevanceStatus(int relevanceStatus) {
		this.relevanceStatus = relevanceStatus;
	}

	public String getLastRecordTime() {
		return lastRecordTime;
	}

	public void setLastRecordTime(String lastRecordTime) {
		this.lastRecordTime = lastRecordTime;
	}

	public int getPartsStatus() {
		return partsStatus;
	}

	public void setPartsStatus(int partsStatus) {
		this.partsStatus = partsStatus;
	}
	
	

}
