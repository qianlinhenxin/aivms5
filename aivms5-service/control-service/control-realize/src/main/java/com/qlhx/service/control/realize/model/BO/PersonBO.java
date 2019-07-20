package com.qlhx.service.control.realize.model.BO;

public class PersonBO {
	
	private Integer id; //员工或访客的主键
	private Integer type;//1员工 2访客
	private String name;
	private String groupidentifier;
	private String groupname;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroupidentifier() {
		return groupidentifier;
	}
	public void setGroupidentifier(String groupidentifier) {
		this.groupidentifier = groupidentifier;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	
	

}
