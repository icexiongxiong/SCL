package com.shitong.entity;

public class Menurole implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer menuid;
	private Integer roleid;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMenuid() {
		return this.menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

}