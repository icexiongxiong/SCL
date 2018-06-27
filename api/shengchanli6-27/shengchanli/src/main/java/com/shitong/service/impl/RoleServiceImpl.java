package com.shitong.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shitong.dao.RoleDao;
import com.shitong.entity.Menurole;
import com.shitong.entity.Role;
import com.shitong.service.RoleService;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年3月8日 上午11:38:17  
*/
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Page save(Role role) {
		Page page= new Page();
		roleDao.save(role);
		page.setSuccess(true);
		page.setMessage("角色添加成功");
		return page;
	}

	@Override
	public Page update(Role role) {
		Page page= new Page();
		roleDao.update(role);
		page.setSuccess(true);
		page.setMessage("角色修改成功");
		return page;
	}

	//删除角色要同时删除角色表（Role），角色菜单表（Menurole）这两张表的信息
	@Override
	public Page delete(Integer roleid) {
		Page page= new Page();
		roleDao.delete(roleid);      //角色表中信息的删除
		roleDao.deleteRolemenuByRoleId(roleid);    //角色菜单表中信息的删除
		page.setSuccess(true);
		page.setMessage("角色删除成功");
		return page;
	}

	@Override
	public Page getRoleById(Integer roleid) {
		Page page= new Page();
		page.setData(roleDao.getRoleById(roleid));
		page.setSuccess(true);
		return page;
	}

	@Override
	public Page getRolePageList(Page page) {
		page.setRoot(roleDao.getRolePageList(page.getStart(), page.getLimit()));
		page.setTotal(roleDao.getRoleCount());
		page.setSuccess(true);
		return page;
	}

	@Override
	public Page grantRole(Integer roleid, Integer[] menuids) {
		Page page = new Page();
		roleDao.deleteRolemenuByRoleId(roleid);
		List<Menurole> list = new ArrayList();
		for (int i = 0; i < menuids.length; i++) {
			Menurole mr = new Menurole();
			mr.setRoleid(roleid);
			mr.setMenuid(menuids[i]);
			list.add(mr);
		}

		roleDao.grantRole(list);
		page.setSuccess(true);
		page.setMessage("角色授权成功");
		return page;
	}

	

}
