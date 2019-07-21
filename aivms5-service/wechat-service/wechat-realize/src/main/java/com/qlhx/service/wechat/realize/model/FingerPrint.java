package com.qlhx.service.wechat.realize.model;

public class FingerPrint {
    /**  */
    private Integer id;

    /**  */
    private Integer uid;

    /**  */
    private String fingerprintid;

    /** 0 �ÿ� 1Ա�� */
    private Integer utype;

    /**
     * 
     * This method returns the value of the database column u_user_fingerprints.id
     *
     * @return the value of u_user_fingerprints.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * This method sets the value of the database column u_user_fingerprints.id
     *
     * @param id the value for u_user_fingerprints.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * This method returns the value of the database column u_user_fingerprints.uid
     *
     * @return the value of u_user_fingerprints.uid
     *
     * @mbggenerated
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 
     * This method sets the value of the database column u_user_fingerprints.uid
     *
     * @param uid the value for u_user_fingerprints.uid
     *
     * @mbggenerated
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 
     * This method returns the value of the database column u_user_fingerprints.fingerprintid
     *
     * @return the value of u_user_fingerprints.fingerprintid
     *
     * @mbggenerated
     */
    public String getFingerprintid() {
        return fingerprintid;
    }

    /**
     * 
     * This method sets the value of the database column u_user_fingerprints.fingerprintid
     *
     * @param fingerprintid the value for u_user_fingerprints.fingerprintid
     *
     * @mbggenerated
     */
    public void setFingerprintid(String fingerprintid) {
        this.fingerprintid = fingerprintid == null ? null : fingerprintid.trim();
    }

    /**
     * 0 �ÿ� 1Ա��
     * This method returns the value of the database column u_user_fingerprints.utype
     *
     * @return the value of u_user_fingerprints.utype
     *
     * @mbggenerated
     */
    public Integer getUtype() {
        return utype;
    }

    /**
     * 0 �ÿ� 1Ա��
     * This method sets the value of the database column u_user_fingerprints.utype
     *
     * @param utype the value for u_user_fingerprints.utype
     *
     * @mbggenerated
     */
    public void setUtype(Integer utype) {
        this.utype = utype;
    }
}