package com.shitong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shitong.entity.Role;
import com.shitong.service.MenuService;
import com.shitong.service.RoleService;
import com.shitong.util.Page;


/** 
* @author  半天  
* @version 创建时间：2018年3月7日 上午11:06:46  
*/
@RestController
@RequestMapping(value = "/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MenuService menuService;
	
	// 添加角色
	@RequestMapping(value = "/add")
	public Page add(@ModelAttribute Role role) throws Exception {
		return roleService.save(role);
	}

	// 根据角色ID删除其内容
	@RequestMapping(value = "/delete/{roleid}")
	public Page delete(@PathVariable Integer roleid) throws Exception {
		return roleService.delete(roleid);
	}

	// 修改修改角色内容
	@RequestMapping(value = "/update")
	public Page update(@ModelAttribute Role role) throws Exception {
		return roleService.update(role);
	}

	// 根据ID获取角色内容
	@RequestMapping(value = "/get/{roleid}")
	public Page get(@PathVariable Integer roleid) throws Exception {
		return roleService.getRoleById(roleid);
	}
	
	// 分页查询
	@RequestMapping(value = "/page")
	public Page page(@ModelAttribute Page page) throws Exception {
		return roleService.getRolePageList(page);
	}
	
	//根据角色roleid给指定角色授权  ，menuids为menuid的数组集合
    @RequestMapping("/grant/{roleid}/{menuids}")
    public Page grantRole(@PathVariable Integer roleid,@PathVariable Integer menuids[]) throws Exception{
    	return roleService.grantRole(roleid,menuids);
    }
    
    //根据角色roleid获取指定改角色所对应的权限树
    @RequestMapping("/menutree/{roleid}")
    public Page getMenuCheckedTreeByRoleid(@PathVariable Integer roleid) throws Exception{
    	return menuService.getCheckedTreeByRoleid(roleid);
    }
    
}
