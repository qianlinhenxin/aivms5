package com.qlhx.service.wechat.realize.model;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@SuppressWarnings("serial")
@XmlRootElement
public class TakeoutRes extends BaseApiModel implements Serializable {
    /** 带出物品记录ID */
    private Integer id;

    /** 带出物品名称 */
    private String resname;

    /** 带出物品数量 */
    private Integer num;

    /** 带出物品照片路径 */
    private String pic;

    /** 访客记录ID */
    private Integer arID;

    /**
     * 带出物品记录ID This method returns the value of the database column
     * u_takeout_res.id
     *
     * @return the value of u_takeout_res.id
     *
     * @mbggenerated
     */
    public Integer getId() {
	return id;
    }

    /**
     * 带出物品记录ID This method sets the value of the database column
     * u_takeout_res.id
     *
     * @param id
     *            the value for u_takeout_res.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * 带出物品名称 This method returns the value of the database column
     * u_takeout_res.resName
     *
     * @return the value of u_takeout_res.resName
     *
     * @mbggenerated
     */
    public String getResname() {
	return resname;
    }

    /**
     * 带出物品名称 This method sets the value of the database column
     * u_takeout_res.resName
     *
     * @param resname
     *            the value for u_takeout_res.resName
     *
     * @mbggenerated
     */
    public void setResname(String resname) {
	this.resname = resname == null ? null : resname.trim();
    }

    /**
     * 带出物品数量 This method returns the value of the database column
     * u_takeout_res.num
     *
     * @return the value of u_takeout_res.num
     *
     * @mbggenerated
     */
    public Integer getNum() {
	return num;
    }

    /**
     * 带出物品数量 This method sets the value of the database column
     * u_takeout_res.num
     *
     * @param num
     *            the value for u_takeout_res.num
     *
     * @mbggenerated
     */
    public void setNum(Integer num) {
	this.num = num;
    }

    /**
     * 带出物品照片路径 This method returns the value of the database column
     * u_takeout_res.pic
     *
     * @return the value of u_takeout_res.pic
     *
     * @mbggenerated
     */
    public String getPic() {
	return pic;
    }

    /**
     * 带出物品照片路径 This method sets the value of the database column
     * u_takeout_res.pic
     *
     * @param pic
     *            the value for u_takeout_res.pic
     *
     * @mbggenerated
     */
    public void setPic(String pic) {
	this.pic = pic == null ? null : pic.trim();
    }

    /**
     * @return the arID
     */
    public Integer getArID() {
	return arID;
    }

    /**
     * @param arID
     *            the arID to set
     */
    public void setArID(Integer arID) {
	this.arID = arID;
    }

}