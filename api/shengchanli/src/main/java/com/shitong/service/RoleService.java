package com.shitong.service;

import com.shitong.entity.Role;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年3月8日 上午11:36:05  
*/
public interface RoleService {
	public Page save(Role role);
	
	public Page update(Role role);
	
	public Page delete(Integer roleid);	
	
	public Page getRoleById(Integer roleid);
	
	public Page getRolePageList(Page page);

	public Page grantRole(Integer roleId, Integer[] menuids);
}
