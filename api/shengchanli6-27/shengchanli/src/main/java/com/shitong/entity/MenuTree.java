package com.shitong.entity;

import java.util.List;


/** 
* @author  半天  
* @version 创建时间：2018年3月8日 下午12:59:04  
*/
public class MenuTree extends Menu {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MenuTree> children;

	public List<MenuTree> getChildren() {
		return children;
	}

	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}
	

}
