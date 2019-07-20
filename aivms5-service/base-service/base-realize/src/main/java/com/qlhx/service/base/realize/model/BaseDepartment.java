package com.qlhx.service.base.realize.model;

public class BaseDepartment {
    private Integer id;

    private String value;

    private Integer parentId;

    private Integer syncid;

    private Integer syncparentid;

    private String companynum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSyncid() {
        return syncid;
    }

    public void setSyncid(Integer syncid) {
        this.syncid = syncid;
    }

    public Integer getSyncparentid() {
        return syncparentid;
    }

    public void setSyncparentid(Integer syncparentid) {
        this.syncparentid = syncparentid;
    }

    public String getCompanynum() {
        return companynum;
    }

    public void setCompanynum(String companynum) {
        this.companynum = companynum == null ? null : companynum.trim();
    }
}