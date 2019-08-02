package com.qhlx.core.session;

import com.qhlx.core.annotation.BeanField;

import java.io.Serializable;
import java.util.Date;

public class UserSession implements Serializable{

	@BeanField(desc="用户sid")
	private Long userSid;

	@BeanField(desc="用户token")
	private String token;

	@BeanField(desc="用户昵称")
	private String nikeName;

	@BeanField(desc="用户号")
	private Long userNo;

	@BeanField(desc="用户名")
	private String userName;

	@BeanField(desc="状态  1 启用 2 禁用 3 其他状态")
	private String status;

	@BeanField(desc="性别")
	private Integer gender;

	@BeanField(desc="邮箱")
	private String email;

	@BeanField(desc="手机号")
	private Long phone;

	@BeanField(desc="出生日期")
	private Date bornDate;

	@BeanField(desc="地址")
	private String address;

	@BeanField(desc="头像")
	private String avatar;

	@BeanField(desc="签名")
	private String signature;

	@BeanField(desc="登录设备code")
	private String deviceCode;

	@BeanField(desc="登录设备mac")
	private String deviceMac;

	@BeanField(desc="登录区域")
	private String loginArea;

	@BeanField(desc="登录区域")
	private String loginIp;

	@BeanField(desc="登录时间")
	private Date loginTime;

	public Long getUserSid() {
		return userSid;
	}

	public void setUserSid(Long userSid) {
		this.userSid = userSid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNikeName() {
		return nikeName;
	}

	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public String getLoginArea() {
		return loginArea;
	}

	public void setLoginArea(String loginArea) {
		this.loginArea = loginArea;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}
