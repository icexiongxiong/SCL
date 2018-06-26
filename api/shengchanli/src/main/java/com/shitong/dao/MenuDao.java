package com.shitong.dao;

import java.util.List;

import com.shitong.entity.Menu;
import com.shitong.entity.MenuTree;
import com.shitong.entity.User;

/** 
* @author  半天  
* @version 创建时间：2018年3月7日 上午11:08:55  
*/
public interface MenuDao {
	public Menu getMenuByMenuid(Integer menuid);
	
	public List<Menu> getMenuByUser(User user) throws Exception ;
	
	public List<MenuTree> getFullMenuByUser(User user) throws Exception ;

	public List<Menu> getMenuByUserIdAndMenuId(User user,Integer menuId) throws Exception ;
	
	public void save(Menu menu);
	
	public void update(Menu menu);
	
	public void delete(Integer menuid);
	
	public void delete(Menu menu);
	
	public List<Menu> getMenuList(Integer from, Integer to) ;
	
	public Integer getMenuCount() ;

	public List<MenuTree> getMenuTreeAll();	

	public List<Integer> getMenuIdListByRoleId(int roleid);

	public List<Menu> getComboMenu();

	public List<Menu> getMenuListByParentId(Integer id);
}
