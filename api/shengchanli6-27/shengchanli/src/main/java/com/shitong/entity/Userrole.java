package com.shitong.entity;

public class Userrole implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer roleid;
	private Integer userid;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}