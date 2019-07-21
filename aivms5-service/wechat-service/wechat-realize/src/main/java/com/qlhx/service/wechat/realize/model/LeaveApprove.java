package com.qlhx.service.wechat.realize.model;

public class LeaveApprove {
    private Integer id;

    private Integer leaveid;

    private Integer approveruser;

    private Short index;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLeaveid() {
        return leaveid;
    }

    public void setLeaveid(Integer leaveid) {
        this.leaveid = leaveid;
    }

    public Integer getApproveruser() {
        return approveruser;
    }

    public void setApproveruser(Integer approveruser) {
        this.approveruser = approveruser;
    }

    public Short getIndex() {
        return index;
    }

    public void setIndex(Short index) {
        this.index = index;
    }
}