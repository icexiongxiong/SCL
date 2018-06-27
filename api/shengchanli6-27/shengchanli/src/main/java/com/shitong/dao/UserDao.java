package com.shitong.dao;

import java.util.List;

import com.shitong.entity.Role;
import com.shitong.entity.RoleCheck;
import com.shitong.entity.User;
import com.shitong.entity.Usercombo;
import com.shitong.entity.Userrole;

/**
 * @author 半天
 * @version 创建时间：2018年3月5日 上午11:23:47
 */
public interface UserDao {
	int add(User user);

	int update(User user);

	int delete(int id);

	User findUserById(int id);

	User findUserByUsernameAndPassword(User user);
	
	public List<User> getUserPageList(Integer from, Integer to) ;
	
	public Integer getUserCount() ;

	void deleteUserroleByUserId(Integer userid);

	void grantUser(final List<Userrole> list);
	
	public List<Userrole> getUserroleListByUserid(Integer userid);
	
	public List<RoleCheck> getRoleList();

	void updatePassword(User user);
	
	List<Usercombo> getUsercomboList(User user);

}
