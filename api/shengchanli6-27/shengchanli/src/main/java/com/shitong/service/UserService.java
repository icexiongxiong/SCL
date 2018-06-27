package com.shitong.service;

import com.shitong.entity.Role;
import com.shitong.entity.User;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年3月5日 上午11:20:11  
*/
public interface UserService {

	public Page getUser(int id);
	
	public Page getUserByUsernameAndPassword(User user);
	
	public Page save(User user);
	
	public Page update(User user);
	
	public Page delete(Integer userid);	
	
	public Page getUserById(Integer userid);
	
	public Page getUserPageList(Page page);

	public Page grantUser(Integer userid, Integer[] roleids);

	public Page getRoleCheckByUserid(Integer userid);

	public Page updatePassword(User user);
	
	//分页显示usercombo表中的数据
	public Page getUsercomboPageList(Page page);
}
