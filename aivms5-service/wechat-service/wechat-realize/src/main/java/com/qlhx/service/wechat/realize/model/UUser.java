package com.qlhx.service.wechat.realize.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * 
 * <p>
 * Title:员工
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:北京钱林恒兴科技股份有限公司
 * </p>
 * 
 * @author 余佳建
 * @date 2017年9月26日 上午9:47:17
 */
@SuppressWarnings("serial")
@XmlRootElement
public class UUser implements Serializable {

    // 0:禁止登录
    public static final Long _0 = new Long(0);
    // 1:有效
    public static final Long _1 = new Long(1);
    
    public static final int SYSTEM_MANAGER = 0;//系统管理员
    
    public static final int COMPANY_MANAGER = 1;//公司管理员
    
    public static final int AREA_MANAGER = 2;//区域管理员

    private Long id;
    
    private String companyCode;
    
    private String companyName;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 邮箱 | 登录帐号
     */
    private String email;
    /**
     * 密码
     */
    private transient String pswd;
    /**
     * 创建时间
     */
    private String createTime;
    
    /**
     * 创建人
     */
    private Long createUid;
    /**
     * 最后登录时间
     */
    private String lastLoginTime;
    /**
     * 1:有效，0:禁止登录
     */
    private Long status;
  
    // 手机号码
    private String phone;
    
 // 手机号码
    private int type;
    
    private String areaCodes;
    
    public UUser() 
    {
    
    }
    
    public UUser(UUser user) 
    {
    	this.id = user.getId();
    	this.nickname = user.getNickname();
    	this.email = user.getEmail();
    	this.pswd = user.getPswd();
    	this.createTime = user.getCreateTime();
    	this.lastLoginTime = user.getLastLoginTime();
    	this.phone = user.getPhone();
        }
    
    // 员工门禁卡编号
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public String getAreaCodes() {
		return areaCodes;
	}

	public void setAreaCodes(String areaCodes) {
		this.areaCodes = areaCodes;
	}

	public Long getCreateUid() {
		return createUid;
	}

	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}

	public String getCompanyName() {
		return companyName == null ?"":companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	

	
}