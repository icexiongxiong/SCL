package com.shitong.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.shitong.dao.MenuDao;
import com.shitong.entity.Menu;
import com.shitong.entity.MenuTree;
import com.shitong.entity.User;

/** 
* @author  半天  
* @version 创建时间：2018年3月7日 上午11:09:18  
*/
@Repository
public class MenuDaoImpl implements MenuDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Menu> getMenuByUserIdAndMenuId(User user, Integer menuId) throws Exception {
		String sql = "select m.* from menu m, menurole mr,role r, userrole ur ,user u where m.id=mr.menuid and mr.roleid=r.id and r.id =ur.userid and ur.userid and ur.userid=u.id and u.id = ? and m.parentId = ?";
		return jdbcTemplate.query(sql, new Object[] { user.getId(), menuId}, new RowMapper<Menu>(){

				@Override
				public Menu mapRow(ResultSet rs, int arg1) throws SQLException {
					Menu menu =new Menu();
					menu.setId(rs.getInt("id"));
					menu.setName(rs.getString("name"));
					menu.setHtmlname(rs.getString("htmlname"));
					menu.setParentId(rs.getInt("parentId"));
					return menu;
				}
		});
	}


	@Override
	public List<Menu> getMenuByUser(User user) throws Exception {
		String sql = "select m.* from menu m, menurole mr,role r, userrole ur ,user u where m.id=mr.menuid and mr.roleid=r.id and r.id =ur.userid and ur.userid and ur.userid=u.id and u.id = ? and m.parentId=0";
		return jdbcTemplate.query(sql, new Object[] { user.getId() }, new RowMapper<Menu>(){

				@Override
				public Menu mapRow(ResultSet rs, int arg1) throws SQLException {
					Menu menu =new Menu();
					menu.setId(rs.getInt("id"));
					menu.setName(rs.getString("name"));
					menu.setHtmlname(rs.getString("htmlname"));
					menu.setParentId(rs.getInt("parentId"));
					return menu;
				}
		});
	}


	@Override
	public void save(Menu menu) {
		jdbcTemplate.update("insert into menu(name, htmlname,parentId) values(?,?,?)",
				menu.getName(),menu.getHtmlname(),menu.getParentId());
	}


	@Override
	public void update(Menu menu) {
	     jdbcTemplate.update("update menu SET name=? ,htmlname=?,parentId=? WHERE id=?",
	    		 menu.getName(),menu.getHtmlname(),menu.getParentId(),menu.getId());
	}


	@Override
	public void delete(Menu menu) {
		jdbcTemplate.update("delete from menu where id=?",menu.getId());
	}
	
	@Override
	public List<Menu> getMenuList(Integer from, Integer to) {
        /*final String sql ="select * from menu limit ?,? ";
        return (List<Menu>) jdbcTemplate.query(sql, new Object[] { from, to },
                new BeanPropertyRowMapper<Menu>(Menu.class));*/
		final String sql ="select * from menu limit ?,? ";
        List list = (List<Menu>) jdbcTemplate.query(sql, new Object[] { from, to },
                new BeanPropertyRowMapper<Menu>(Menu.class));
        System.out.println(JSON.toJSONString(list));
        return list;
	}


	@Override
	public Integer getMenuCount() {
		final String sql ="select count(id) from menu";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}


	@Override
	public void delete(Integer menuid) {
		jdbcTemplate.update("delete from menu where id=?",menuid);
	}


	@Override
	public Menu getMenuByMenuid(Integer menuid) {
		final String sql ="select * from menu where id = ?";
		return jdbcTemplate.queryForObject(sql,new Object[] { menuid },new BeanPropertyRowMapper<Menu>(Menu.class));
	}


	@Override
	public List<MenuTree> getMenuTreeAll() {
		final String sql ="select * from menu";
		return (List<MenuTree>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<MenuTree>(MenuTree.class));
	}


	@Override
	public List getMenuIdListByRoleId(int roleid) {
		String sql = "select DISTINCT(m.id) from Menu m, Menurole mr where m.id=mr.menuid and mr.roleid = ?";
		return jdbcTemplate.queryForList(sql, new Object[]{roleid});
	}

	@Override
	public List<MenuTree> getFullMenuByUser(User user) throws Exception {
		String sql = "select distinct(menu.id),menu.`name`,menu.htmlname,menu.parentId from menu join menurole on menu.id=menurole.menuid JOIN  role on menurole.roleid=role.id join userrole on userrole.roleid=role.id  join user on `user`.id=userrole.userid  where `user`.id = ?";
		return jdbcTemplate.query(sql, new Object[] { user.getId() }, new RowMapper<MenuTree>(){

				@Override
				public MenuTree mapRow(ResultSet rs, int arg1) throws SQLException {
					MenuTree menu =new MenuTree();
					menu.setId(rs.getInt("id"));
					menu.setName(rs.getString("name"));
					menu.setHtmlname(rs.getString("htmlname"));
					menu.setParentId(rs.getInt("parentId"));
					return menu;
				}
		});
	}


	@Override
	public List<Menu> getComboMenu() {
		String sql = "select id , name from menu where parentId = 0";
	    return (List<Menu>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Menu>(Menu.class));
	}


	@Override
	public List<Menu> getMenuListByParentId(Integer parentid) {
		String sql = "select id , name from menu where parentId = ?";
	    return (List<Menu>) jdbcTemplate.query(sql,new Object[]{parentid},new BeanPropertyRowMapper<Menu>(Menu.class));
	
	}




}
