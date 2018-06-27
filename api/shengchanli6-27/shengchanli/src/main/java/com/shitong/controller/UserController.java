package com.shitong.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.shitong.entity.User;
import com.shitong.service.UserService;
import com.shitong.util.Page;


/** 
* @author  半天  
* @version 创建时间：2018年3月5日 上午11:19:01  
*/
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
    	dateFormat.setLenient(false);  
    	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
    //根据用户id获取单条用户信息
    @RequestMapping(value = "/{id}")
    public Page getUser(@PathVariable int id)    {
        return userService.getUser(id);
    }
    
    //添加用户
    //获取科室combo可以从OrganizationController中找getOrganizationComb()方法
    @RequestMapping(value = "/add")
    public Page addUser(@ModelAttribute User user)    {
        return userService.save(user);
    }  
    
    //根据id主键删除用户信息
    @RequestMapping(value = "/delete/{userid}")
    public Page deleteUser(@PathVariable int userid)    {
        return userService.delete(userid);
    }  
    
    //修改用户信息(id必须有)
    @RequestMapping(value = "/update")
    public Page updateUser(@ModelAttribute User user)    {
        return userService.update(user);
    }
    
    //修改用户信息(id必须有)
    @RequestMapping(value = "/pass")
    public Page updateUserPassword(@ModelAttribute User user)    {
        return userService.updatePassword(user);
    }
    
    
	// 分页查询
	@RequestMapping(value = "/page")
	public Page page(@ModelAttribute Page page) throws Exception {
		return userService.getUserPageList(page);
	}
	
	//根据角色userid给指定角色授权  ，roleids为roleid的数组集合
    @RequestMapping("/grant/{userid}/{roleids}")
    public Page grantUser(@PathVariable Integer userid,@PathVariable Integer roleids[]) throws Exception{
    	return userService.grantUser(userid, roleids);
    }
    
	//根据角色userid获取用户所拥有的角色。
    @RequestMapping("/getrole/{userid}")
    public Page getRoleByUserid(@PathVariable Integer userid) throws Exception{
    	return userService.getRoleCheckByUserid(userid);
    }
    
	//显示usercombo表中的信息
	@RequestMapping(value = "/combo")
	public Page combo(@ModelAttribute Page page) throws Exception {
		return userService.getUsercomboPageList(page);
	}
	
}
