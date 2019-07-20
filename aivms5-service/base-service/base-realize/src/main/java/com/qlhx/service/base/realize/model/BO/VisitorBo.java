package com.qlhx.service.base.realize.model.BO;


import com.qlhx.service.base.realize.model.BaseVisitor;

/**
 * Created by yxn on 2018-01-22.
 */
public class VisitorBo extends BaseVisitor {
    private  String rCode;

    public String getrCode() {
        return rCode;
    }

    public void setrCode(String rCode) {
        this.rCode = rCode;
    }

    public String getCardNum() {
        return CardNum;
    }

    public void setCardNum(String cardNum) {
        CardNum = cardNum;
    }

    private  String CardNum;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private  String uid;
}
