package com.qlhx.service.wechat.realize.model;

import java.util.Date;

public class Archives {
    /** 归档表ID */
    private Integer archivesid;

    /** 归档年月 */
    private String yt;

    /** 归档状态（0未归档 1已归档，默认未归档） */
    private Integer status;

    /** 归档人ID */
    private Integer userid;

    /** 归档人 */
    private UUser user;

    /** 归档时间 */
    private String recordtime;

    /**
     * 归档表ID This method returns the value of the database column
     * u_archives.archivesId
     *
     * @return the value of u_archives.archivesId
     *
     * @mbggenerated
     */
    public Integer getArchivesid() {
	return archivesid;
    }

    /**
     * 归档表ID This method sets the value of the database column
     * u_archives.archivesId
     *
     * @param archivesid
     *            the value for u_archives.archivesId
     *
     * @mbggenerated
     */
    public void setArchivesid(Integer archivesid) {
	this.archivesid = archivesid;
    }

    /**
     * 归档年月 This method returns the value of the database column u_archives.YT
     *
     * @return the value of u_archives.YT
     *
     * @mbggenerated
     */
    public String getYt() {
	return yt;
    }

    /**
     * 归档年月 This method sets the value of the database column u_archives.YT
     *
     * @param yt
     *            the value for u_archives.YT
     *
     * @mbggenerated
     */
    public void setYt(String yt) {
	this.yt = yt == null ? null : yt.trim();
    }

    /**
     * 归档状态（0未归档 1已归档，默认未归档） This method returns the value of the database
     * column u_archives.status
     *
     * @return the value of u_archives.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
	return status;
    }

    /**
     * 归档状态（0未归档 1已归档，默认未归档） This method sets the value of the database column
     * u_archives.status
     *
     * @param status
     *            the value for u_archives.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
	this.status = status;
    }

    /**
     * 归档人ID This method returns the value of the database column
     * u_archives.userId
     *
     * @return the value of u_archives.userId
     *
     * @mbggenerated
     */
    public Integer getUserid() {
	return userid;
    }

    /**
     * 归档人ID This method sets the value of the database column u_archives.userId
     *
     * @param userid
     *            the value for u_archives.userId
     *
     * @mbggenerated
     */
    public void setUserid(Integer userid) {
	this.userid = userid;
    }

    /**
     * 归档时间 This method returns the value of the database column
     * u_archives.recordTime
     *
     * @return the value of u_archives.recordTime
     *
     * @mbggenerated
     */
    public String getRecordtime() {
	return recordtime != null ? recordtime.replace(".0", "") : null;
    }

    /**
     * 归档时间 This method sets the value of the database column
     * u_archives.recordTime
     *
     * @param recordtime
     *            the value for u_archives.recordTime
     *
     * @mbggenerated
     */
    public void setRecordtime(String recordtime) {
	this.recordtime = recordtime;
    }

    /**
     * 归档人
     * 
     * @return the user
     */
    public UUser getUser() {
	return user;
    }

    /**
     * 归档人
     * 
     * @param user
     *            the user to set
     */
    public void setUser(UUser user) {
	this.user = user;
    }

}