package com.qlhx.service.wechat.realize.model;

import java.io.Serializable;
import java.util.Date;

public class UManager implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1444851707856871156L;

	private Long id;

    private String showname;

    private String username;

    private String password;

    private String companylist;

    private String arealist;

    private String terminallist;

    private Short accounttype;

    private Short status;

    private Long createuser;

    private Date createtime;

    private Date lastlogintime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname == null ? null : showname.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getCompanylist() {
        return companylist;
    }

    public void setCompanylist(String companylist) {
        this.companylist = companylist == null ? null : companylist.trim();
    }

    public String getArealist() {
        return arealist;
    }

    public void setArealist(String arealist) {
        this.arealist = arealist == null ? null : arealist.trim();
    }

    public String getTerminallist() {
        return terminallist;
    }

    public void setTerminallist(String terminallist) {
        this.terminallist = terminallist == null ? null : terminallist.trim();
    }

    public Short getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(Short accounttype) {
        this.accounttype = accounttype;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Long getCreateuser() {
        return createuser;
    }

    public void setCreateuser(Long createuser) {
        this.createuser = createuser;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }
}