package com.shitong.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shitong.dao.OrganizationDao;
import com.shitong.entity.Menu;
import com.shitong.entity.Organization;

/** 
* @author  半天  
* @version 创建时间：2018年3月7日 上午11:10:54  
*/
@Repository
public class OrganizationDaoImpl implements OrganizationDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	//添加组织机构
	@Override
	public void save(Organization organization) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("insert into organization(name, description,parentid) values(?,?,?)",
				organization.getName(),organization.getDescription(),organization.getParentid());
	}
	//修改组织机构
	@Override
	public void update(Organization organization) {
		// TODO Auto-generated method stub
	     jdbcTemplate.update("update organization SET name=? ,description=?,parentid=? WHERE id=?",
	    		 organization.getName(),organization.getDescription(),organization.getParentid(),organization.getId());
	}
	//删除组织机构
	@Override
	public void delete(Integer organid) {
		jdbcTemplate.update("delete from organization where id=?",organid);
	}
	//内连接分页查询组织机构
	@Override
	public List<Organization> getOrganizationPageList(Integer from, Integer to) {
		// TODO Auto-generated method stub
        //final String sql ="select o1.id,o1.`name`,o1.description,o2.id subordinateid,o2.`name` subordinatename from organization  o1 join organization o2 where o1.id = o2.parentid limit ?,? ";
		final String sql ="select * from organization limit ?,? ";
		return (List<Organization>) jdbcTemplate.query(sql, new Object[] { from, to },
                new BeanPropertyRowMapper<Organization>(Organization.class));
	}
	//获取总数量
	@Override
	public Integer getOrganizationCount() {
		// TODO Auto-generated method stub
		final String sql ="select count(id) from organization";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	//根据id查询组织机构
	@Override
	public Organization getOrganizationById(Integer organid) {
		final String sql ="select * from organization where id = ?";
		return jdbcTemplate.queryForObject(sql,new Object[] { organid },new BeanPropertyRowMapper<Organization>(Organization.class));
	}

	//获取组织机构中的id，name两个字段，为了combo填充数据
	@Override
	public List<Organization> getOrganizationComboList() {
		// TODO Auto-generated method stub
        final String sql ="select id,name from organization";
        return (List<Organization>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Organization>(Organization.class));
	}
	@Override
	public String getOrganizationIds(Integer id) {
		final String sql = "select getChildLst(?)" ;
		String ids = jdbcTemplate.queryForObject(sql, new Object[] { id } , String.class);
		return ids.substring(1, ids.length());
	}
	@Override
	public List<Organization> getOrganizationListByParentId(Integer organid) {
		String sql = "select * from organization where parentId = ?";
	    return (List<Organization>) jdbcTemplate.query(sql,new Object[]{organid},new BeanPropertyRowMapper<Organization>(Organization.class));
	}
	@Override
	public List getKeShiComboList() {
		String sql = "select id,name from organization where id not in(select parentid from organization)";
		return (List<Organization>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Organization>(Organization.class));
	}
}
