package com.qlhx.service.wechat.realize.model;

public class Thoroughfare extends BaseApiModel{

    public Thoroughfare() {

    }

    public Thoroughfare(Integer _uid, Integer _egid) {
        uid = _uid;
        egid = _egid;
    }

    /**
     * 通道表ID
     */
    private Integer id;

    /**
     * 员工ID
     */
    private Integer uid;

    /**
     * 门禁ID
     */
    private Integer egid;
    
    
    private String password;

    /**
     * 通道表ID This method returns the value of the database column
     * u_thoroughfare.id
     *
     * @return the value of u_thoroughfare.id
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 通道表ID This method sets the value of the database column u_thoroughfare.id
     *
     * @param id the value for u_thoroughfare.id
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 员工ID This method returns the value of the database column
     * u_thoroughfare.uId
     *
     * @return the value of u_thoroughfare.uId
     * @mbggenerated
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 员工ID This method sets the value of the database column u_thoroughfare.uId
     *
     * @param uid the value for u_thoroughfare.uId
     * @mbggenerated
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 门禁ID This method returns the value of the database column
     * u_thoroughfare.egId
     *
     * @return the value of u_thoroughfare.egId
     * @mbggenerated
     */
    public Integer getEgid() {
        return egid;
    }

    /**
     * 门禁ID This method sets the value of the database column
     * u_thoroughfare.egId
     *
     * @param egid the value for u_thoroughfare.egId
     * @mbggenerated
     */
    public void setEgid(Integer egid) {
        this.egid = egid;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
}