package com.qlhx.service.control.realize.model;

import java.util.Date;


public class Key {
    private Integer id;

    private String keyName;

    private String biometrics;

    private String card;

    private String keyDescribe;

    private Date createdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName == null ? null : keyName.trim();
    }

    public String getBiometrics() {
        return biometrics;
    }

    public void setBiometrics(String biometrics) {
        this.biometrics = biometrics == null ? null : biometrics.trim();
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card == null ? null : card.trim();
    }

    public String getKeyDescribe() {
        return keyDescribe;
    }

    public void setKeyDescribe(String keyDescribe) {
        this.keyDescribe = keyDescribe == null ? null : keyDescribe.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}