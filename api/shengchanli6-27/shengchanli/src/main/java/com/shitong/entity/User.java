package com.shitong.entity;

public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String password;
	private Integer organizationid;
	private String organizationname;
	private Boolean leader;
	private String ids;
	private String industrykind; //行业



	public String getIndustrykind() {
		return industrykind;
	}

	public void setIndustrykind(String industrykind) {
		this.industrykind = industrykind;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Boolean getLeader() {
		return leader;
	}

	public void setLeader(Boolean leader) {
		this.leader = leader;
	}

	public String getOrganizationname() {
		return organizationname;
	}

	public void setOrganizationname(String organizationname) {
		this.organizationname = organizationname;
	}

	public Integer getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(Integer organizationid) {
		this.organizationid = organizationid;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}