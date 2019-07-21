package com.qlhx.service.wechat.realize.model;


import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * 开发公司：itboy.net<br/>
 * 版权：itboy.net<br/>
 * <p>
 * 
 * 权限实体
 * 
 * <p>
 * 
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　周柏成　2016年5月25日 　<br/>
 * <p>
 * *******
 * <p>
 * 
 * @author zhou-baicheng
 * @email i@itboy.net
 * @version 1.0,2016年5月25日 <br/>
 * 
 */
@SuppressWarnings("serial")
@XmlRootElement
public class UPermission implements Serializable {


    private Long id;
    /** 操作的url */
    private String url;
    /** 操作的名称 */
    private String name;
    
    private Integer iconUrl;//菜单图标
    
    private Integer parentId;//父节点ID
    
    private Integer isButtonPer;//是否是按钮权限 0 不是 1 是
    
    private Integer porder; //排序
    
    private Integer checked;//是否选中,前端显示使用 1选中 0 未选中
    
    private Integer status;//0启用 1禁用
    
    private List<UPermission> child; 

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getIsButtonPer() {
		return isButtonPer;
	}

	public void setIsButtonPer(Integer isButtonPer) {
		this.isButtonPer = isButtonPer;
	}

	public String toString() {
	return ObjectUtil.toString(new UPermission());
    }

	public List<UPermission> getChild() {
		return child;
	}

	public void setChild(List<UPermission> child) {
		this.child = child;
	}

	public Integer getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(Integer iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	public Integer getPorder() {
		return porder;
	}

	public void setPorder(Integer porder) {
		this.porder = porder;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}