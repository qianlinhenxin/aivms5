package com.qlhx.service.wechat.realize.model;

public class WorkTime {
    /** 表ID */
    private Integer wordid;

    /** 部门ID */
    private Integer depid;

    /** 部门 */
    private Department dep;

    /** 年月 */
    private String yt;

    /** 上班时间 */
    private String workstarttime;

    /** 下班时间 */
    private String workendtime;

    /** 记录时间 */
    private String recordtime;

    /**
     * 表ID This method returns the value of the database column
     * u_work_time.wordId
     *
     * @return the value of u_work_time.wordId
     *
     * @mbggenerated
     */
    public Integer getWordid() {
	return wordid;
    }

    /**
     * 表ID This method sets the value of the database column u_work_time.wordId
     *
     * @param wordid
     *            the value for u_work_time.wordId
     *
     * @mbggenerated
     */
    public void setWordid(Integer wordid) {
	this.wordid = wordid;
    }

    /**
     * 部门ID This method returns the value of the database column
     * u_work_time.depID
     *
     * @return the value of u_work_time.depID
     *
     * @mbggenerated
     */
    public Integer getDepid() {
	return depid;
    }

    /**
     * 部门ID This method sets the value of the database column u_work_time.depID
     *
     * @param depid
     *            the value for u_work_time.depID
     *
     * @mbggenerated
     */
    public void setDepid(Integer depid) {
	this.depid = depid;
    }

    /**
     * 年月 This method returns the value of the database column u_work_time.YT
     *
     * @return the value of u_work_time.YT
     *
     * @mbggenerated
     */
    public String getYt() {
	return yt;
    }

    /**
     * 年月 This method sets the value of the database column u_work_time.YT
     *
     * @param yt
     *            the value for u_work_time.YT
     *
     * @mbggenerated
     */
    public void setYt(String yt) {
	this.yt = yt == null ? null : yt.trim();
    }

    /**
     * 上班时间 This method returns the value of the database column
     * u_work_time.workStartTime
     *
     * @return the value of u_work_time.workStartTime
     *
     * @mbggenerated
     */
    public String getWorkstarttime() {
	return workstarttime != null ? workstarttime.replace(".0", "") : null;
    }

    /**
     * 上班时间 This method sets the value of the database column
     * u_work_time.workStartTime
     *
     * @param workstarttime
     *            the value for u_work_time.workStartTime
     *
     * @mbggenerated
     */
    public void setWorkstarttime(String workstarttime) {
	this.workstarttime = workstarttime;
    }

    /**
     * 下班时间 This method returns the value of the database column
     * u_work_time.workEndTime
     *
     * @return the value of u_work_time.workEndTime
     *
     * @mbggenerated
     */
    public String getWorkendtime() {
	return workendtime != null ? workendtime.replace(".0", "") : null;
    }

    /**
     * 下班时间 This method sets the value of the database column
     * u_work_time.workEndTime
     *
     * @param workendtime
     *            the value for u_work_time.workEndTime
     *
     * @mbggenerated
     */
    public void setWorkendtime(String workendtime) {
	this.workendtime = workendtime;
    }

    /**
     * 记录时间 This method returns the value of the database column
     * u_work_time.recordTime
     *
     * @return the value of u_work_time.recordTime
     *
     * @mbggenerated
     */
    public String getRecordtime() {
	return recordtime != null ? recordtime.replace(".0", "") : null;
    }

    /**
     * 记录时间 This method sets the value of the database column
     * u_work_time.recordTime
     *
     * @param recordtime
     *            the value for u_work_time.recordTime
     *
     * @mbggenerated
     */
    public void setRecordtime(String recordtime) {
	this.recordtime = recordtime;
    }

    /**
     * 部门
     * 
     * @return the dep
     */
    public Department getDep() {
	return dep;
    }

    /**
     * 部门
     * 
     * @param dep
     *            the dep to set
     */
    public void setDep(Department dep) {
	this.dep = dep;
    }

}