package com.shitong.service;

import java.util.List;

import com.shitong.entity.Menu;
import com.shitong.entity.User;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年3月7日 下午12:47:28  
*/
public interface MenuService {
	
	public Page getMenuByMenuid(Integer menuid);
	
	public Page getMenuByUser(User user) throws Exception ;
	
	public Page getFullMenuByUser(User user) throws Exception ;

	public Page getMenuByUserIdAndMenuId(User user,Integer menuId) throws Exception ;

	public Page save(Menu menu);

	public Page update(Menu menu);
	
	public Page getMenuList(Page page);

	public Page delete(Integer menuid);
	
	public Page getCheckedTreeByRoleid(Integer roleid);

	public Page getComboMenu();

}
