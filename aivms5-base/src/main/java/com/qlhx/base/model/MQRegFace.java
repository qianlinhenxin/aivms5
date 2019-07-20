package com.qlhx.base.model;

public class MQRegFace {

    public static final String ROUTING_KEY = "notify.regface";

    private String deviceIds;

    private String name;

    private String cardNum;

    private String photo;


    public MQRegFace(String deviceIds, String name, String cardNum, String photo) {
        super();
        this.deviceIds = deviceIds;
        this.name = name;
        this.cardNum = cardNum;
        this.photo = photo;
    }


    public String getDeviceIds() {
        return deviceIds;
    }


    public void setDeviceIds(String deviceIds) {
        this.deviceIds = deviceIds;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


}
