package com.shitong.controller;

import java.io.IOException;
import java.util.Enumeration;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.shitong.entity.User;
import com.shitong.service.UserService;
import com.shitong.util.Page;

/**
 * @author 半天
 * @version 创建时间：2018年3月5日 下午1:25:21
 */
@RestController
public class LoginController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login")
	public Page login(@ModelAttribute User user, HttpSession session) throws JsonParseException, JsonMappingException, IOException {
		Page page = userService.getUserByUsernameAndPassword(user);
		user = (User) page.getData();
		Boolean flag = user != null;
		if (flag) {
			session.setAttribute("user", user);
			user.setPassword(null);
			session.setAttribute("login", flag);
			page.setMessage("登录成功");
		} else {
			page.setMessage("用户名或密码不正确，请重新登录");
		}
		page.setSuccess(flag);
		return page;
	}

	@RequestMapping(value = "/islogin")
	public Page isLogined(HttpSession session) {
		Boolean flag = (Boolean) session.getAttribute("login");
		Page page = new Page();
		page.setSuccess(flag);
		page.setMessage("合法用户");
		return page;
	}

	@RequestMapping(value = "/logout")
	public Page logout(HttpSession session) {
		Enumeration<String> em = session.getAttributeNames();
		while (em.hasMoreElements()) {
			session.removeAttribute(em.nextElement().toString());
		}
		Page page = new Page();
		page.setMessage("注销成功");
		page.setSuccess(true);
		return page;
	}
}