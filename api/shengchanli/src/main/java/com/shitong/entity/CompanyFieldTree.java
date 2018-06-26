package com.shitong.entity;

import java.util.List;

/** 
* @author  半天  
* @version 创建时间：2018年3月24日 下午12:32:45  
*/
public class CompanyFieldTree extends CompanyField {
	private List<CompanyFieldTree> children;

	public List<CompanyFieldTree> getChildren() {
		return children;
	}

	public void setChildren(List<CompanyFieldTree> children) {
		this.children = children;
	}
	
}
