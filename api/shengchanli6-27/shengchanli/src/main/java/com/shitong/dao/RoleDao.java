package com.shitong.dao;

import java.util.List;

import com.shitong.entity.Menurole;
import com.shitong.entity.Role;
import com.shitong.entity.Userrole;

/** 
* @author  半天  
* @version 创建时间：2018年3月7日 上午11:09:38  
*/
public interface RoleDao {
	
	public void save(Role role);
	
	public void update(Role role);
	
	public void delete(Integer roleid);
	
	public Role getRoleById(Integer roleid);
	
	public List<Menurole> getUserRoleByRoleid(Integer roleid);
	
	public void grantRole(List<Menurole> list);
	
	public void deleteRolemenuByRoleId(Integer roleid);
	
	public List<Role> getRolePageList(Integer from, Integer to) ;
	
	public Integer getRoleCount() ;
	
}
