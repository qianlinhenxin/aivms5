package com.qlhx.service.wechat.realize.model;

public class FaceModels {
    /**  */
    private Integer id;

    /** �û�id */
    private Integer uid;

    /** ��������id */
    private String facemodelid;

    public Integer getFacetype() {
        return facetype;
    }

    public void setFacetype(Integer facetype) {
        this.facetype = facetype;
    }

    private Integer facetype;

    /**
     * 
     * This method returns the value of the database column u_user_facemodels.id
     *
     * @return the value of u_user_facemodels.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * This method sets the value of the database column u_user_facemodels.id
     *
     * @param id the value for u_user_facemodels.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * �û�id
     * This method returns the value of the database column u_user_facemodels.uid
     *
     * @return the value of u_user_facemodels.uid
     *
     * @mbggenerated
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * �û�id
     * This method sets the value of the database column u_user_facemodels.uid
     *
     * @param uid the value for u_user_facemodels.uid
     *
     * @mbggenerated
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * ��������id
     * This method returns the value of the database column u_user_facemodels.facemodelid
     *
     * @return the value of u_user_facemodels.facemodelid
     *
     * @mbggenerated
     */
    public String getFacemodelid() {
        return facemodelid;
    }

    /**
     * ��������id
     * This method sets the value of the database column u_user_facemodels.facemodelid
     *
     * @param facemodelid the value for u_user_facemodels.facemodelid
     *
     * @mbggenerated
     */
    public void setFacemodelid(String facemodelid) {
        this.facemodelid = facemodelid == null ? null : facemodelid.trim();
    }
}