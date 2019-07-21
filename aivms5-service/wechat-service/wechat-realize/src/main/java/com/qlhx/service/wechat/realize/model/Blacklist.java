package com.qlhx.service.wechat.realize.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


@SuppressWarnings("serial")
@XmlRootElement
public class Blacklist extends BaseApiModel implements Serializable {
    /**  */
    private Integer id;

    /** 访客ID */
    private Integer vid;

    /** 访客黑名单原因 */
    private String reason;
    
    /** 访客姓名 */
    private String vname;
    
   

    /**
     * 
     * This method returns the value of the database column u_blacklist.id
     *
     * @return the value of u_blacklist.id
     *
     * @mbggenerated
     */
    public Integer getId() {
	return id;
    }

    /**
     * 
     * This method sets the value of the database column u_blacklist.id
     *
     * @param id
     *            the value for u_blacklist.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * 访客ID This method returns the value of the database column u_blacklist.vId
     *
     * @return the value of u_blacklist.vId
     *
     * @mbggenerated
     */
    public Integer getVid() {
	return vid;
    }

    /**
     * 访客ID This method sets the value of the database column u_blacklist.vId
     *
     * @param vid
     *            the value for u_blacklist.vId
     *
     * @mbggenerated
     */
    public void setVid(Integer vid) {
	this.vid = vid;
    }

    /**
     * 访客黑名单原因 This method returns the value of the database column
     * u_blacklist.reason
     *
     * @return the value of u_blacklist.reason
     *
     * @mbggenerated
     */
    public String getReason() {
	return reason;
    }

    /**
     * 访客黑名单原因 This method sets the value of the database column
     * u_blacklist.reason
     *
     * @param reason
     *            the value for u_blacklist.reason
     *
     * @mbggenerated
     */
    public void setReason(String reason) {
	this.reason = reason == null ? null : reason.trim();
    }

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	
    
    
}