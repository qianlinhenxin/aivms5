package com.qlhx.service.door.realize.qlhx.model;

import java.util.Date;

public class USwingCardRecord {
    private Integer id;

	private String sn;

	private String cardno;

	private Integer doorno;

	private Integer direction;

	private Date createdate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn == null ? null : sn.trim();
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno == null ? null : cardno.trim();
	}

	public Integer getDoorno() {
		return doorno;
	}

	public void setDoorno(Integer doorno) {
		this.doorno = doorno;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

}