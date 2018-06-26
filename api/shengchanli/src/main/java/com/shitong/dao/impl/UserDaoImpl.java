package com.shitong.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.shitong.dao.UserDao;
import com.shitong.entity.RoleCheck;
import com.shitong.entity.User;
import com.shitong.entity.Usercombo;
import com.shitong.entity.Userrole;
import com.shitong.util.ConditionHandler;
import com.shitong.util.OrganizationHandler;

/**
 * @author 半天
 * @version 创建时间：2018年3月5日 上午11:24:41
 */
@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int add(User user) {
		return jdbcTemplate.update("insert into user(username, password,organizationid,organizationname,leader) values(?, ?, ?, ?, ?)", user.getUsername(),
				user.getPassword(),user.getOrganizationid(),user.getOrganizationname(),user.getLeader());
	}

	@Override
	public int update(User user) {
		return jdbcTemplate.update("UPDATE  user SET username=? ,password=?, organizationid=?, organizationname=?,leader=? WHERE id=?", user.getUsername(),
				user.getPassword(),user.getOrganizationid(),user.getOrganizationname(),user.getLeader(), user.getId());
	}

	@Override
	public int delete(int id) {
		return jdbcTemplate.update("DELETE from user where id=?", id);
	}

	@Override
	public User findUserById(int id) {
		List<User> list = jdbcTemplate.query("select * from user where id = ?", new Object[] { id },
				new BeanPropertyRowMapper(User.class));
		if (list != null && list.size() > 0) {
			User user = list.get(0);
			return user;
		} else {
			return null;
		}
	}


	@Override
	public User findUserByUsernameAndPassword(User user) {
		List<User> list = jdbcTemplate.query("select * from user where username=? and password=?",
				new Object[] { user.getUsername(), user.getPassword() }, new BeanPropertyRowMapper(User.class));
		if( list.size() > 0 )
			return list.get(0);
		return null;
	}

	@Override
	public List<User> getUserPageList(Integer from, Integer to) {
		final String sql = "select id,username,organizationid,organizationname,leader from user limit ?,? ";
		return (List<User>) jdbcTemplate.query(sql, new Object[] { from, to },
				new BeanPropertyRowMapper<User>(User.class));
	}

	@Override
	public Integer getUserCount() {
		final String sql = "select count(id) from user";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public void deleteUserroleByUserId(Integer userid) {
		jdbcTemplate.update("DELETE from userrole where userid=?", userid);
	}

	@Override
	public void grantUser(final List<Userrole> list) {
		String sql = "insert into userrole(roleid,userid) values(?,?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Integer roleId = list.get(i).getRoleid();
				Integer userId = list.get(i).getUserid();
				ps.setInt(1, roleId);
				ps.setInt(2, userId);
			}

			public int getBatchSize() {
				return list.size();
			}
		});

	}

	@Override
	public List<Userrole> getUserroleListByUserid(Integer userid) {
		final String sql = "select * from userrole where userid = ?";
		return (List<Userrole>) jdbcTemplate.query(sql, new Object[] { userid },
				new BeanPropertyRowMapper<Userrole>(Userrole.class));

	}

	@Override
	public List<RoleCheck> getRoleList() {
		final String sql = "select id,name from role";
		return (List<RoleCheck>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<RoleCheck>(RoleCheck.class));
	}

	@Override
	public void updatePassword(User user) {
		jdbcTemplate.update("UPDATE  user SET password=? WHERE username=?", 
				user.getPassword(),user.getUsername());
	}

	@Override
	public List<Usercombo> getUsercomboList(User user) {
		final String sql ="select user_id,username from usercombo ";
		ConditionHandler ch = OrganizationHandler.returnCommonSql(user, sql);
        return (List<Usercombo>) jdbcTemplate.query(ch.getSql(),
                new BeanPropertyRowMapper<Usercombo>(Usercombo.class));
	}
}
