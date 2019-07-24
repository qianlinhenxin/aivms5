package com.qlhx.base.session;

import java.io.Serializable;
import java.util.Date;

public class UserSession implements Serializable{
	// 用户会话唯一key
	private String authCacheKey;
	/**
	 * 用户主键ID
	 */
	private Long userSid;

	/**
	 * 员工编码
	 */
	private String userCode;

	/**
	 * 用户ID(登录名)
	 */
	private String userId="zhongx3456";

	/**
	 * 姓名
	 */
	private String userName = "钟翔";

	/**
	 * 账号类型
	 */
	private String acctType;
	
	/** 
	 * 用户登录选择的部门岗位SID
	 */
	private Long orgPositionSid=1L;
	
	/** 
	 * 用户登录选择的部门岗位对应的 岗位主数据SID
	 */
	private Long positionSid=1L;
	
	/**
	 * 部门岗位编码
	 */
	private String orgPositionCode="20040010PN19040000216";
	
	/** 
	 * 用户登录选择的部门岗位名称
	 */
	private String orgPositonName="部长默认";

	/**
	 * 用户登录选择的部门岗位类型（1普通 2管理员 3超级管理员）
	 */
	private Integer orgPositionType;

	/**  集团主数据 公司code  */
    private String jituan_companyCode = "HEB";
    
    /**  集团主数据 公司名字  */
    private String jituan_companyName ="HEB";
    
    /** 公司性质 对应 小代码 ORG_TAG */
    private String companyTag = "10";
	
	// 公司别
	private Long companySid =4307L;

	// 公司别  公司工贸简码 因为很多模块已经调用这个属性获取简码了，所以只能强制把这个属性设置成简码值
	private String companyCode="1B";

	// 公司别 公司工贸简称    因为很多模块已经调用这个属性获取简码了，所以只能强制把这个属性设置成简码值
	private String companyName="1B";
	
	//公司工贸简码
	private String companyGMJCCode="1B";
	
	//公司工贸简称
	private String companyGMJCName="HEBJC";

	// 部门id
	private Long deptSid = 0L;

	// 部门code
	private String deptCode = "123";

	// 部门名称
	private String deptName = "测试";
	
	/** 存储用户登录时候，页面选择的登录日期   */
	private Date loginDate = new Date() ;

	//  业务会计年月
	private Integer business_periodYear = 2019;
	private Integer business_periodMonth = 4;
	
	//  总账会计年月
	private Integer finance_periodYear =2019;
	private Integer finance_periodMonth = 4;

	// 分库ID
	private Long partitionId;

	//用户能看到的部门列表
	private String deptCodeIn;

	public String getAuthCacheKey() {
		return authCacheKey;
	}

	public void setAuthCacheKey(String authCacheKey) {
		this.authCacheKey = authCacheKey;
	}

	public Long getUserSid() {
		return userSid;
	}

	public void setUserSid(Long userSid) {
		this.userSid = userSid;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public Long getCompanySid() {
		return companySid;
	}

	public void setCompanySid(Long companySid) {
		this.companySid = companySid;
	}

	public String getCompanyCode() {
		return companyGMJCCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyGMJCCode = companyCode;
	}

	public String getCompanyName() {
		return companyGMJCName;
	}

	public void setCompanyName(String companyName) {
		this.companyGMJCName = companyName;
	}

	public Long getDeptSid() {
		return deptSid;
	}

	public void setDeptSid(Long deptSid) {
		this.deptSid = deptSid;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	


	public Integer getBusiness_periodYear() {
		return business_periodYear;
	}

	public void setBusiness_periodYear(Integer business_periodYear) {
		this.business_periodYear = business_periodYear;
	}

	public Integer getBusiness_periodMonth() {
		return business_periodMonth;
	}

	public void setBusiness_periodMonth(Integer business_periodMonth) {
		this.business_periodMonth = business_periodMonth;
	}

	public Integer getFinance_periodYear() {
		return finance_periodYear;
	}

	public void setFinance_periodYear(Integer finance_periodYear) {
		this.finance_periodYear = finance_periodYear;
	}

	public Integer getFinance_periodMonth() {
		return finance_periodMonth;
	}

	public void setFinance_periodMonth(Integer finance_periodMonth) {
		this.finance_periodMonth = finance_periodMonth;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Long getPartitionId() {
		return partitionId;
	}

	public void setPartitionId(Long partitionId) {
		this.partitionId = partitionId;
	}

	/** 获取部门岗位SID  */
	public Long getOrgPositionSid() {
		return orgPositionSid;
	}

	public void setOrgPositionSid(Long orgPositionSid) {
		this.orgPositionSid = orgPositionSid;
	}

	/** 获取部门岗位名称  */
	public String getOrgPositonName() {
		return orgPositonName;
	}

	public void setOrgPositonName(String orgPositonName) {
		this.orgPositonName = orgPositonName;
	}

	/**
	 * 获取 部门岗位编码
	 * @return
	 */
	public String getOrgPositionCode() {
		return orgPositionCode;
	}

	public void setOrgPositionCode(String orgPositionCode) {
		this.orgPositionCode = orgPositionCode;
	}

	public String getCompanyGMJCCode() {
		return companyGMJCCode;
	}

	public void setCompanyGMJCCode(String companyGMJCCode) {
		this.companyGMJCCode = companyGMJCCode;
	}

	public String getCompanyGMJCName() {
		return companyGMJCName;
	}

	public void setCompanyGMJCName(String companyGMJCName) {
		this.companyGMJCName = companyGMJCName;
	}

	/**
	 * 获取 用户登录选择的部门岗位类型（1普通 2管理员 3超级管理员）
	 * @return
	 */
	public Integer getOrgPositionType() {
		return orgPositionType;
	}

	public void setOrgPositionType(Integer orgPositionType) {
		this.orgPositionType = orgPositionType;
	}

	
	/**  获取集团主数据约定的公司Code*/
	public String getJituan_companyCode() {
		return jituan_companyCode;
	}

	public void setJituan_companyCode(String jituan_companyCode) {
		this.jituan_companyCode = jituan_companyCode;
	}
	
	/**  获取集团主数据约定的公司名字*/
	public String getJituan_companyName() {
		return jituan_companyName;
	}

	public void setJituan_companyName(String jituan_companyName) {
		this.jituan_companyName = jituan_companyName;
	}

	public String getCompanyTag() {
		return companyTag;
	}

	public void setCompanyTag(String companyTag) {
		this.companyTag = companyTag;
	}

	public Long getPositionSid() {
		return positionSid;
	}

	public void setPositionSid(Long positionSid) {
		this.positionSid = positionSid;
	}

	public String getDeptCodeIn() {
		return deptCodeIn;
	}

	public void setDeptCodeIn(String deptCodeIn) {
		this.deptCodeIn = deptCodeIn;
	}

	@Override
	public String toString() {
		return "UserSession{" +
				"authCacheKey='" + authCacheKey + '\'' +
				", userSid=" + userSid +
				", userCode='" + userCode + '\'' +
				", userId='" + userId + '\'' +
				", userName='" + userName + '\'' +
				", acctType='" + acctType + '\'' +
				", orgPositionSid=" + orgPositionSid +
				", positionSid=" + positionSid +
				", orgPositionCode='" + orgPositionCode + '\'' +
				", orgPositonName='" + orgPositonName + '\'' +
				", orgPositionType=" + orgPositionType +
				", jituan_companyCode='" + jituan_companyCode + '\'' +
				", jituan_companyName='" + jituan_companyName + '\'' +
				", companyTag='" + companyTag + '\'' +
				", companySid=" + companySid +
				", companyCode='" + companyCode + '\'' +
				", companyName='" + companyName + '\'' +
				", companyGMJCCode='" + companyGMJCCode + '\'' +
				", companyGMJCName='" + companyGMJCName + '\'' +
				", deptSid=" + deptSid +
				", deptCode='" + deptCode + '\'' +
				", deptName='" + deptName + '\'' +
				", loginDate=" + loginDate +
				", business_periodYear=" + business_periodYear +
				", business_periodMonth=" + business_periodMonth +
				", finance_periodYear=" + finance_periodYear +
				", finance_periodMonth=" + finance_periodMonth +
				", partitionId=" + partitionId +
				'}';
	}
}
