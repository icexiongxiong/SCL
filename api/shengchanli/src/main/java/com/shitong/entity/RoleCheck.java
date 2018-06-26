package com.shitong.entity;
/** 
* @author  半天  
* @version 创建时间：2018年3月13日 下午5:04:12  
*/
public class RoleCheck extends Role{	
	private Boolean checked ;

	public Boolean getChecked() {
		if(checked == null){
			return false;
		}
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}	
}
