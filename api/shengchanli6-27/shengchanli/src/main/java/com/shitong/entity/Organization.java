package com.shitong.entity;

public class Organization implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String description;
	private Integer parentid;
	private Integer subordinateid;
	private String subordinatename;

	
	public Integer getSubordinateid() {
		return subordinateid;
	}

	public void setSubordinateid(Integer subordinateid) {
		this.subordinateid = subordinateid;
	}

	public String getSubordinatename() {
		return subordinatename;
	}

	public void setSubordinatename(String subordinatename) {
		this.subordinatename = subordinatename;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getParentid() {
		return this.parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

}