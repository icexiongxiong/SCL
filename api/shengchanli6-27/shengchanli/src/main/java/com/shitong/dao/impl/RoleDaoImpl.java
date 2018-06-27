package com.shitong.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shitong.dao.RoleDao;
import com.shitong.entity.Menurole;
import com.shitong.entity.Role;

/** 
* @author  半天  
* @version 创建时间：2018年3月7日 上午11:10:00  
*/
@Repository
public class RoleDaoImpl implements RoleDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(Role role) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("insert into role(name, description) values(?,?)",
				role.getName(),role.getDescription());
	
	}

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		 jdbcTemplate.update("update role SET name=? ,description=? WHERE id=?",
				 role.getName(),role.getDescription(),role.getId());
	
	}

	@Override
	public void delete(Integer roleid) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("delete from role where id=?",roleid);
	}

	@Override
	public Role getRoleById(Integer roleid) {
		final String sql ="select * from role where id = ?";
		return jdbcTemplate.queryForObject(sql,new Object[] { roleid },new BeanPropertyRowMapper<Role>(Role.class));
	
	}

	@Override
	public List<Role> getRolePageList(Integer from, Integer to) {
		final String sql ="select * from role limit ?,? ";
		return (List<Role>) jdbcTemplate.query(sql, new Object[] { from, to },
                new BeanPropertyRowMapper<Role>(Role.class));
	}

	@Override
	public Integer getRoleCount() {
		// TODO Auto-generated method stub
		final String sql ="select count(id) from role";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public List<Menurole> getUserRoleByRoleid(Integer roleid) {
		final String sql ="select * from menurole where id = ?";
		return (List<Menurole>) jdbcTemplate.query(sql, new Object[] { roleid },
                new BeanPropertyRowMapper<Menurole>(Menurole.class));
	}

	@Override
	public void grantRole(final List<Menurole> list) {
		String sql = "insert into menurole(roleid,menuid) values(?,?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){
			  public void setValues(PreparedStatement ps,int i)throws SQLException
			   {
			    Integer roleId=list.get(i).getRoleid();
			    Integer menuId=list.get(i).getMenuid();
			    ps.setInt(1, roleId);
			    ps.setInt(2, menuId);
			   }
			   public int getBatchSize()
			   {
			    return list.size();
			   }
		});
	}
	

	@Override
	public void deleteRolemenuByRoleId(Integer roleid) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("delete from menurole where roleid=?",roleid);
	}
	
	


}
