package com.shitong.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shitong.dao.MenuDao;
import com.shitong.entity.Menu;
import com.shitong.entity.User;
import com.shitong.service.MenuService;
import com.shitong.util.Page;

/**
 * @author 半天
 * @version 创建时间：2018年3月7日 上午11:03:49
 */
@RequestMapping("/menu")
@RestController
public class MenuController {

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/first")
	public Page firstlevel(HttpSession session) throws Exception {
		User user = (User) session.getAttribute("user");
		Page page = menuService.getMenuByUser(user);
		return page;
	}

	@RequestMapping(value = "/second/{menuid}")
	public Page secondlevel(HttpSession session, @PathVariable Integer menuid) throws Exception {
		User user = (User) session.getAttribute("user");
		Page page = menuService.getMenuByUserIdAndMenuId(user, menuid);
		return page;
	}

	@RequestMapping(value = "/add")
	public Page add(@ModelAttribute Menu menu) throws Exception {
		Page page = menuService.save(menu);
		return page;
	}

	@RequestMapping(value = "/update")
	public Page update(@ModelAttribute Menu menu) throws Exception {
		Page page = menuService.update(menu);
		return page;
	}

	@RequestMapping(value = "/delete/{menuid}")
	public Page delete(@PathVariable Integer menuid) throws Exception {
		Page page = menuService.delete(menuid);
		return page;
	}

	@RequestMapping(value = "/get/{menuid}")
	public Page get(@PathVariable Integer menuid) throws Exception {
		Page page = menuService.getMenuByMenuid(menuid);
		return page;
	}

	@RequestMapping(value = "/page")
	public Page getMenuPage(@ModelAttribute Page page) throws Exception {
		return menuService.getMenuList(page);
	}

	@RequestMapping(value = "/navi")
	public Page getNavigatorMenuPage(HttpSession session,@ModelAttribute Page page) throws Exception {
		User user = (User) session.getAttribute("user");
		return menuService.getFullMenuByUser(user);
	}
	
	@RequestMapping(value = "/combo")
	public Page getComboMenuPage(@ModelAttribute Page page) throws Exception {
		return menuService.getComboMenu();
	}

}
