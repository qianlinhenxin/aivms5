package com.qlhx.service.wechat.realize.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class VisitorCar extends BaseApiModel implements Serializable {
    /** 来访车辆记录ID */
    private Integer id;

    /** 车牌号码 */
    private String cardnum;

    /** 车辆颜色 */
    private String cardcolor;

    /** 数量 */
    private Integer num;

    /** 来访记录ID（u_access_record） */
    private Integer arid;

    /**
     * 来访车辆记录ID This method returns the value of the database column
     * u_visitor_car.id
     *
     * @return the value of u_visitor_car.id
     *
     * @mbggenerated
     */
    public Integer getId() {
	return id;
    }

    /**
     * 来访车辆记录ID This method sets the value of the database column
     * u_visitor_car.id
     *
     * @param id
     *            the value for u_visitor_car.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * 车牌号码 This method returns the value of the database column
     * u_visitor_car.cardNum
     *
     * @return the value of u_visitor_car.cardNum
     *
     * @mbggenerated
     */
    public String getCardnum() {
	return cardnum;
    }

    /**
     * 车牌号码 This method sets the value of the database column
     * u_visitor_car.cardNum
     *
     * @param cardnum
     *            the value for u_visitor_car.cardNum
     *
     * @mbggenerated
     */
    public void setCardnum(String cardnum) {
	this.cardnum = cardnum == null ? null : cardnum.trim();
    }

    /**
     * 车辆颜色 This method returns the value of the database column
     * u_visitor_car.cardColor
     *
     * @return the value of u_visitor_car.cardColor
     *
     * @mbggenerated
     */
    public String getCardcolor() {
	return cardcolor;
    }

    /**
     * 车辆颜色 This method sets the value of the database column
     * u_visitor_car.cardColor
     *
     * @param cardcolor
     *            the value for u_visitor_car.cardColor
     *
     * @mbggenerated
     */
    public void setCardcolor(String cardcolor) {
	this.cardcolor = cardcolor == null ? null : cardcolor.trim();
    }

    /**
     * 数量 This method returns the value of the database column u_visitor_car.num
     *
     * @return the value of u_visitor_car.num
     *
     * @mbggenerated
     */
    public Integer getNum() {
	return num;
    }

    /**
     * 数量 This method sets the value of the database column u_visitor_car.num
     *
     * @param num
     *            the value for u_visitor_car.num
     *
     * @mbggenerated
     */
    public void setNum(Integer num) {
	this.num = num;
    }

    /**
     * 来访记录ID（u_access_record） This method returns the value of the database
     * column u_visitor_car.arID
     *
     * @return the value of u_visitor_car.arID
     *
     * @mbggenerated
     */
    public Integer getArid() {
	return arid;
    }

    /**
     * 来访记录ID（u_access_record） This method sets the value of the database column
     * u_visitor_car.arID
     *
     * @param arid
     *            the value for u_visitor_car.arID
     *
     * @mbggenerated
     */
    public void setArid(Integer arid) {
	this.arid = arid;
    }
}