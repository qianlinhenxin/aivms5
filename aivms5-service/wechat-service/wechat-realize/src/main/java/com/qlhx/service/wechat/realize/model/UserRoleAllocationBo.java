package com.qlhx.service.wechat.realize.model;

import java.io.Serializable;

/**
 * 用户角色分配 查询列表BO
 * @author zhou-baicheng
 *
 */
public class UserRoleAllocationBo extends UUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Role Name列转行，以,分割
	private String roleNames;
	//Role Id列转行，以‘,’分割
	private String roleIds;
	private String deptname;
	private String areaNames;
	private String areaCodes;
	private String companyName;
	public String getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getAreaNames() {
		return areaNames;
	}
	public void setAreaNames(String areaNames) {
		this.areaNames = areaNames;
	}
	public String getAreaCodes() {
		return areaCodes;
	}
	public void setAreaCodes(String areaCodes) {
		this.areaCodes = areaCodes;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}
