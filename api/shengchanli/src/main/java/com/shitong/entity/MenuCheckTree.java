package com.shitong.entity;

import java.util.List;


/** 
* @author  半天  
* @version 创建时间：2018年3月8日 下午1:04:03  
*/
public class MenuCheckTree {
    private String name ;	
	private boolean checked ;	
	private Integer id;
	private Integer pid;
    private List<MenuCheckTree> children;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public List<MenuCheckTree> getChildren() {
		return children;
	}
	public void setChildren(List<MenuCheckTree> children) {
		this.children = children;
	}
    
}
