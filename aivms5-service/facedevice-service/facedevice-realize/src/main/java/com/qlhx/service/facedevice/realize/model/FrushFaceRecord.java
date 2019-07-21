package com.qlhx.service.facedevice.realize.model;

/**
 * Created by Paris on 2018/11/5.
 * 刷脸调RMS接口实体类
 */
public class FrushFaceRecord {
    public FrushFaceRecord(Integer faceDeviceId , Integer inorout , String time , String cardNo)
    {
        this.faceDeviceId = faceDeviceId;
        this.inorout = inorout;
        this.time = time;
        this.cardNo = cardNo;
    }

    /**
     * 设备ID
     */
    private Integer faceDeviceId ;
    /**
     * 进出状态 1进2出。
     */
    private Integer inorout;
    /**
     * 卡号
     */
    private String cardNo;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getFaceDeviceId() {
        return faceDeviceId;
    }

    public void setFaceDeviceId(Integer faceDeviceId) {
        this.faceDeviceId = faceDeviceId;
    }

    public Integer getInorout() {
        return inorout;
    }

    public void setInorout(Integer inorout) {
        this.inorout = inorout;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 刷脸时间

     */
    private String time;
}
