package com.qlhx.service.wechat.realize.model;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@SuppressWarnings("serial")
@XmlRootElement
public class DraginRes extends BaseApiModel implements Serializable {
    /** 访客带入物品记录ID */
    private Integer id;

    /** 访客带入物品名称 */
    private String resname;

    /** 带入物品数量 */
    private Integer num;

    /** 带入物品照片路径 */
    private String pic;

    /** 访客ID */
    private Integer visitorid;
    
    /** 访客记录ID */
    private Integer arId;

    /**
     * 访客带入物品记录ID This method returns the value of the database column
     * u_dragin_res.id
     *
     * @return the value of u_dragin_res.id
     *
     * @mbggenerated
     */
    public Integer getId() {
	return id;
    }

    /**
     * 访客带入物品记录ID This method sets the value of the database column
     * u_dragin_res.id
     *
     * @param id
     *            the value for u_dragin_res.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * 访客带入物品名称 This method returns the value of the database column
     * u_dragin_res.resname
     *
     * @return the value of u_dragin_res.resname
     *
     * @mbggenerated
     */
    public String getResname() {
	return resname;
    }

    /**
     * 访客带入物品名称 This method sets the value of the database column
     * u_dragin_res.resname
     *
     * @param resname
     *            the value for u_dragin_res.resname
     *
     * @mbggenerated
     */
    public void setResname(String resname) {
	this.resname = resname == null ? null : resname.trim();
    }

    /**
     * 带入物品数量 This method returns the value of the database column
     * u_dragin_res.num
     *
     * @return the value of u_dragin_res.num
     *
     * @mbggenerated
     */
    public Integer getNum() {
	return num;
    }

    /**
     * 带入物品数量 This method sets the value of the database column u_dragin_res.num
     *
     * @param num
     *            the value for u_dragin_res.num
     *
     * @mbggenerated
     */
    public void setNum(Integer num) {
	this.num = num;
    }

    /**
     * 带入物品照片路径 This method returns the value of the database column
     * u_dragin_res.pic
     *
     * @return the value of u_dragin_res.pic
     *
     * @mbggenerated
     */
    public String getPic() {
	return pic;
    }

    /**
     * 带入物品照片路径 This method sets the value of the database column
     * u_dragin_res.pic
     *
     * @param pic
     *            the value for u_dragin_res.pic
     *
     * @mbggenerated
     */
    public void setPic(String pic) {
	this.pic = pic == null ? null : pic.trim();
    }

    /**
     * @return the arId
     * 访客ID This method returns the value of the database column
     * u_dragin_res.visitorId
     *
     * @return the value of u_dragin_res.visitorId
     *
     * @mbggenerated
     */
    public Integer getArId() {
	return arId;
    }

    /**
     * @param arId
     *            the arId to set
     * 访客ID This method sets the value of the database column
     * u_dragin_res.visitorId
     *
     * @param
     *            the value for u_dragin_res.visitorId
     *
     * @mbggenerated
     */
    public void setArId(Integer arId) {
	this.arId = arId;
    }

}