package com.qlhx.service.wechat.realize.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@SuppressWarnings("serial")
@XmlRootElement
public class SwingCardRecord extends BaseApiModel implements Serializable {
    /** 门禁、验证机刷卡记录表 */
    private Integer id;

    /** 卡号 */
    private String cardnum;

    /** 刷卡时间 */
    private String swingcardtime;

    /** 刷卡人类型（0：员工 1：访客） */
    private Integer type;

    /** 刷卡人ID（员工或来访人员ID） */
    private Integer cardholderid;
    
    /** 刷卡人姓名（员工或来访人员姓名） */
    private Integer cardholderName;

    /** 门禁编号 */
    private String egnum;

    /** 验证机名称 */
    private String proofmachinename;

    /** 进出门标记（0：进门 1：出门 （默认进门）） */
    private Integer inoutflag;

    /**
     * 开门标志<br />
     * 0：未接门禁 1：开门成功 2：开门失败
     */
    private Integer isOpenDoor;

    /**
     * 门禁、验证机刷卡记录表 This method returns the value of the database column
     * u_swing_card_record.id
     *
     * @return the value of u_swing_card_record.id
     *
     * @mbggenerated
     */
    public Integer getId() {
	return id;
    }

    /**
     * 门禁、验证机刷卡记录表 This method sets the value of the database column
     * u_swing_card_record.id
     *
     * @param id
     *            the value for u_swing_card_record.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * 卡号 This method returns the value of the database column
     * u_swing_card_record.cardNum
     *
     * @return the value of u_swing_card_record.cardNum
     *
     * @mbggenerated
     */
    public String getCardnum() {
	return cardnum;
    }

    /**
     * 卡号 This method sets the value of the database column
     * u_swing_card_record.cardNum
     *
     * @param cardnum
     *            the value for u_swing_card_record.cardNum
     *
     * @mbggenerated
     */
    public void setCardnum(String cardnum) {
	this.cardnum = cardnum == null ? null : cardnum.trim();
    }

    /**
     * 刷卡时间 This method returns the value of the database column
     * u_swing_card_record.swingCardTime
     *
     * @return the value of u_swing_card_record.swingCardTime
     *
     * @mbggenerated
     */
    public String getSwingcardtime() {

        return swingcardtime != null ? swingcardtime.replace(".0", "") : null;
    }

    /**
     * 刷卡时间 This method sets the value of the database column
     * u_swing_card_record.swingCardTime
     *
     * @param swingcardtime
     *            the value for u_swing_card_record.swingCardTime
     *
     * @mbggenerated
     */
    public void setSwingcardtime(String swingcardtime) {
	this.swingcardtime = swingcardtime;
    }

    /**
     * 刷卡人类型（0：员工 1：访客） This method returns the value of the database column
     * u_swing_card_record.type
     *
     * @return the value of u_swing_card_record.type
     *
     * @mbggenerated
     */
    public Integer getType() {
	return type;
    }

    /**
     * 刷卡人类型（0：员工 1：访客） This method sets the value of the database column
     * u_swing_card_record.type
     *
     * @param type
     *            the value for u_swing_card_record.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
	this.type = type;
    }

    /**
     * 刷卡人ID（员工或来访人员ID） This method returns the value of the database column
     * u_swing_card_record.cardholderId
     *
     * @return the value of u_swing_card_record.cardholderId
     *
     * @mbggenerated
     */
    public Integer getCardholderid() {
	return cardholderid;
    }

    /**
     * 刷卡人ID（员工或来访人员ID） This method sets the value of the database column
     * u_swing_card_record.cardholderId
     *
     * @param cardholderid
     *            the value for u_swing_card_record.cardholderId
     *
     * @mbggenerated
     */
    public void setCardholderid(Integer cardholderid) {
	this.cardholderid = cardholderid;
    }

    /**
     * 门禁编号 This method returns the value of the database column
     * u_swing_card_record.egNum
     *
     * @return the value of u_swing_card_record.egNum
     *
     * @mbggenerated
     */
    public String getEgnum() {
	return egnum;
    }

    /**
     * 门禁编号 This method sets the value of the database column
     * u_swing_card_record.egNum
     *
     * @param egnum
     *            the value for u_swing_card_record.egNum
     *
     * @mbggenerated
     */
    public void setEgnum(String egnum) {
	this.egnum = egnum == null ? null : egnum.trim();
    }

    /**
     * 验证机名称 This method returns the value of the database column
     * u_swing_card_record.proofMachineName
     *
     * @return the value of u_swing_card_record.proofMachineName
     *
     * @mbggenerated
     */
    public String getProofmachinename() {
	return proofmachinename;
    }

    /**
     * 验证机名称 This method sets the value of the database column
     * u_swing_card_record.proofMachineName
     *
     * @param proofmachinename
     *            the value for u_swing_card_record.proofMachineName
     *
     * @mbggenerated
     */
    public void setProofmachinename(String proofmachinename) {
	this.proofmachinename = proofmachinename == null ? null
		: proofmachinename.trim();
    }

    /**
     * 进出门标记（0：进门 1：出门 （默认进门）） This method returns the value of the database
     * column u_swing_card_record.inOutFlag
     *
     * @return the value of u_swing_card_record.inOutFlag
     *
     * @mbggenerated
     */
    public Integer getInoutflag() {
	return inoutflag;
    }

    /**
     * 进出门标记（0：进门 1：出门 （默认进门）） This method sets the value of the database column
     * u_swing_card_record.inOutFlag
     *
     * @param inoutflag
     *            the value for u_swing_card_record.inOutFlag
     *
     * @mbggenerated
     */
    public void setInoutflag(Integer inoutflag) {
	this.inoutflag = inoutflag;
    }

    /**
     * @return the isOpenDoor
     */
    public Integer getIsOpenDoor() {
	return isOpenDoor;
    }

    /**
     * @param isOpenDoor
     *            the isOpenDoor to set
     */
    public void setIsOpenDoor(Integer isOpenDoor) {
	this.isOpenDoor = isOpenDoor;
    }

	public Integer getCardholderName() {
		return cardholderName;
	}

	public void setCardholderName(Integer cardholderName) {
		this.cardholderName = cardholderName;
	}
    
    

}