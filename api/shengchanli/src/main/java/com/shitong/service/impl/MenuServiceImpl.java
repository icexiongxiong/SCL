package com.shitong.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shitong.dao.MenuDao;
import com.shitong.entity.Menu;
import com.shitong.entity.MenuCheckTree;
import com.shitong.entity.MenuTree;
import com.shitong.entity.User;
import com.shitong.service.MenuService;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年3月7日 下午12:48:08  
*/
@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;
	
	@Override
	public Page getMenuByUser(User user) throws Exception {
		// TODO Auto-generated method stub
		Page page = new Page();
		page.setRoot(menuDao.getMenuByUser(user));
		page.setSuccess(true);
		page.setMessage("一级菜单");
		return page;
	}

	@Override
	public Page getMenuByUserIdAndMenuId(User user, Integer menuId) throws Exception {
		Page page = new Page();
		page.setRoot(menuDao.getMenuByUserIdAndMenuId(user, menuId));
		page.setSuccess(true);
		page.setMessage("二级菜单");
		return page;
	}

	@Override
	public Page save(Menu menu) {
		Page page = new Page();
		menuDao.save(menu);
		page.setSuccess(true);
		page.setMessage("菜单添加");
		return page;
		
	}

	@Override
	public Page getMenuList(Page page) {
		List<Menu> list = menuDao.getMenuList(page.getStart(), page.getLimit());
		page.setTotal(menuDao.getMenuCount());
		page.setRoot(list);
		page.setSuccess(true);
		return page;
	}

	@Override
	public Page delete(Integer menuid) {
		Page page = new Page();
		List<Menu> list = menuDao.getMenuListByParentId(menuid);
		if(list.size()>0){
			page.setSuccess(false);
			page.setMessage("此菜单为父菜单删除失败");
		}else {
			menuDao.delete(menuid);
			page.setSuccess(true);
			page.setMessage("菜单删除成功");
		}
;
		return page;
	}

	@Override
	public Page update(Menu menu) {
		Page page = new Page();
		menuDao.update(menu);
		page.setSuccess(true);
		page.setMessage("菜单修改成功");
		return page;
	}

	@Override
	public Page getMenuByMenuid(Integer menuid) {
		Page page = new Page();
		Menu menu = menuDao.getMenuByMenuid(menuid);
		page.setData(menu);
		page.setSuccess(true);
		return page;
	}

	@Override
	public Page getCheckedTreeByRoleid(Integer roleId) {
		//从数据库menu表中获取MenuTree信息		
		List<MenuTree> list =menuDao.getMenuTreeAll();
		//根据roleid从menurole表中获取menuid，也就是获取roleid对应的权限
		List idList =menuDao.getMenuIdListByRoleId(roleId);
		List<MenuCheckTree> list1 = new ArrayList();
		//用两个循环获取权限用户授权树，用于返回给前台用户修改权限
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			MenuTree menuTree = (MenuTree) iterator.next();
			MenuCheckTree checkTree = new MenuCheckTree();
			checkTree.setChecked(false);
			int menuId = menuTree.getId();
			for (Object object : idList) {
				Map map =(Map) object;
				int mId =(Integer) map.get("id");
				if(menuId == mId){
					checkTree.setChecked(true);
					break;
				}
			}
			checkTree.setId(menuId);
			checkTree.setName(menuTree.getName());
			checkTree.setPid(menuTree.getParentId());
			list1.add(checkTree);
		}
		
		List<MenuCheckTree> treeList =  getChildsTreeManyGroup(list1, 0);
		String json = JSON.toJSONString(treeList);
		Page page = new Page();
		page.setRoot(treeList);
		page.setSuccess(true);
		return page;
	}
	
	//菜单权限修改
    private static List<MenuCheckTree> getChildsTreeManyGroup(List<MenuCheckTree> list,int id){  
        List<MenuCheckTree> retList = new ArrayList<MenuCheckTree>();  
        for(MenuCheckTree tree : list){  
            if(id == tree.getPid()){  
                tree.setChildren(getChildsTreeManyGroup(list, tree.getId()));    
                retList.add(tree);   
            }  
         }  
        return retList;  
    }

    //获取用户左侧菜单的权限
	@Override
	public Page getFullMenuByUser(User user) throws Exception {
		Page page = new Page();
		List<MenuTree> list = menuDao.getFullMenuByUser(user);
		list =  getMenuTreeManyGroup(list, 0);
		page.setRoot(list);
		return page;
	} 
	
	//菜单权限修改
    private static List<MenuTree> getMenuTreeManyGroup(List<MenuTree> list,int id){  
        List<MenuTree> retList = new ArrayList<MenuTree>();  
        for(MenuTree tree : list){  
            if(id == tree.getParentId()){  
                tree.setChildren(getMenuTreeManyGroup(list, tree.getId()));    
                retList.add(tree);   
            }  
         }  
        return retList;  
    }

	@Override
	public Page getComboMenu() {
		Page page = new Page();
		List<Menu> list = menuDao.getComboMenu();
		page.setRoot(list);
		page.setSuccess(true);
		return page;
	}


}
