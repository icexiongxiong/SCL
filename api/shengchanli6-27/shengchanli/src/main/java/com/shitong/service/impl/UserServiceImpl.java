package com.shitong.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shitong.dao.OrganizationDao;
import com.shitong.dao.UserDao;
import com.shitong.entity.Organization;
import com.shitong.entity.RoleCheck;
import com.shitong.entity.User;
import com.shitong.entity.Usercombo;
import com.shitong.entity.Userrole;
import com.shitong.service.UserService;
import com.shitong.util.HttpContext;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年3月5日 上午11:22:16  
*/
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private HttpContext httpContext;
	
	@Autowired
	private OrganizationDao organizationDao;


	@Override
	public Page getUser(int id) {
		Page page = new Page();
		page.setData(userDao.findUserById(id));
		page.setSuccess(true);
		return page;
	}

	@Override
	public Page getUserByUsernameAndPassword(User user) {
		Page page = new Page();
		user = userDao.findUserByUsernameAndPassword(user);
		System.out.println();
		if( user != null && user.getLeader() ){
			user.setIds(organizationDao.getOrganizationIds(user.getOrganizationid()));
			user.setPassword(null);
		}
		page.setData(user);
		return page;
	}

	@Override
	public Page save(User user) {
		Page page = new Page();
		Integer organid = user.getOrganizationid();
		Organization organ = organizationDao.getOrganizationById(organid);
		user.setOrganizationname(organ.getName());
		userDao.add(user);
		page.setSuccess(true);
		page.setMessage("用户添加成功");
		return page;
	}

	@Override
	public Page update(User user) {
		Page page = new Page();
		Integer organid = user.getOrganizationid();
		if(organid != null){
			Organization organ = organizationDao.getOrganizationById(organid);
			user.setOrganizationname(organ.getName());
		}
		userDao.update(user);
		page.setSuccess(true);
		page.setMessage("用户修改成功");
		return page;
	}

	@Override
	public Page delete(Integer userid) {
		Page page = new Page();
		userDao.delete(userid);
		page.setSuccess(true);
		page.setMessage("用户删除成功");
		return page;
	}

	@Override
	public Page getUserById(Integer userid) {
		Page page = new Page();
		User user = userDao.findUserById(userid);
		page.setData(user);
		page.setSuccess(true);
		return page;
	}

	@Override
	public Page getUserPageList(Page page) {
		page.setRoot(userDao.getUserPageList(page.getStart(), page.getLimit()));
		page.setTotal(userDao.getUserCount());
		page.setSuccess(true);
		return page;
	}

	@Override
	public Page grantUser(Integer userid, Integer[] roleids) {
		Page page = new Page();
		userDao.deleteUserroleByUserId(userid);
		List<Userrole> list = new ArrayList();
		for (int i = 0; i < roleids.length; i++) {
			Userrole ur = new Userrole();
			ur.setUserid(userid);
			ur.setRoleid(roleids[i]);
			list.add(ur);
		}

		userDao.grantUser(list);
		page.setSuccess(true);
		page.setMessage("角色授权成功");
		return page;
	}

	@Override
	public Page getRoleCheckByUserid(Integer userid) {
		Page page = new Page();
		List<RoleCheck> checklist = userDao.getRoleList();
		List<Userrole> userrolelist = userDao.getUserroleListByUserid(userid);
		List<RoleCheck> resultlist = new ArrayList(); 
		for (RoleCheck roleCheck : checklist) {
			for (Userrole userrole : userrolelist) {
				if(userrole.getRoleid() == roleCheck.getId()){
					roleCheck.setChecked(true);
				}
			}
			resultlist.add(roleCheck);
		}
		page.setRoot(resultlist);
		page.setSuccess(true);
		return page;
	}

	@Override
	public Page updatePassword(User user) {
		Page page = new Page();
		userDao.updatePassword(user);
		page.setSuccess(true);
		page.setMessage("密码修改成功");
		return page;
	}

	@Override
	public Page getUsercomboPageList(Page page) {
		User user = httpContext.getUser();
		List<Usercombo> list = userDao.getUsercomboList(user);
		page.setRoot(list);
		page.setSuccess(true);
		return page;
	}

}
