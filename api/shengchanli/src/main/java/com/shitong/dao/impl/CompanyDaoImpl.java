package com.shitong.dao.impl;

import java.util.Iterator;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import com.shitong.dao.CompanyDao;
import com.shitong.entity.Company;
import com.shitong.entity.User;
import com.shitong.util.BeanHandler;
import com.shitong.util.ConditionHandler;
import com.shitong.util.OrganizationHandler;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时44分35秒  星期三  
*/
@Repository
public class CompanyDaoImpl implements CompanyDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//向company表插入数据，成功返回1
	@Override
	public Integer saveCompany(Company company) {
		ConditionHandler ch = BeanHandler.insert("company",company);
		return jdbcTemplate.update(ch.getSql(),ch.getParams());
	}
	
	//向company表插入数据，成功返回新插入数据的主键
	@Override
	public Integer saveCompanyAndGetAutoIncreaseId(Company company) {		
		final String sql = BeanHandler.insertReturnSql("company", company);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public java.sql.PreparedStatement createPreparedStatement(java.sql.Connection conn) throws SQLException {
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql,
						PreparedStatement.RETURN_GENERATED_KEYS);
				return ps;
			}

		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	//根据company表的主键删除数据
	@Override
	public void deleteCompanyById(int id) {
		jdbcTemplate.update("delete from company where id=?",id);
	}

	//根据主键修改company表的内容
	@Override
	public void updateCompanyById(Company company) {
		ConditionHandler ch = BeanHandler.updateById("company","id",company);
		jdbcTemplate.update(ch.getSql(), ch.getParams());
		
	}

	//分页显示company表中的内容，其中start为数据开始位置，limit每页条数
	@Override
	public List<Company> getCompanyList(Integer start, Integer limit, User user) {
		final String sql ="select industrykind,whetherpublic,publiccode,publictime,publictype,stockcode,staffnum,sciencestaffnum,id,companyname,creditcode,legalperson,fund,buildtime,companytype,province,city,coutry,contactperson,telphone,mainfield,legaltelphone,stockmessage,companynum,weixin,email,user_id,organization_id,username,organ_name from company ";
		ConditionHandler ch = OrganizationHandler.returnCommonSql(user, sql);
        return (List<Company>) jdbcTemplate.query(ch.getSql()+" limit ?,? ", new Object[] { start, limit },
                new BeanPropertyRowMapper<Company>(Company.class));
	
	}

	//获取company表中记录的条数
	@Override
	public Integer getCompanyCount(User user) {
		final String sql ="select count(id) from company";
		ConditionHandler ch = OrganizationHandler.returnCommonSql(user, sql);
		return jdbcTemplate.queryForObject(ch.getSql(), Integer.class);
	}

	//根据主键id从company表中查找一条数据
	@Override
	public Company findCompanyById(int id) {
		List<Company> list = jdbcTemplate.query("select industrykind,whetherpublic,publiccode,publictime,publictype,stockcode,staffnum,sciencestaffnum,id,companyname,creditcode,legalperson,fund,buildtime,companytype,province,city,coutry,contactperson,telphone,mainfield,legaltelphone,stockmessage,companynum,weixin,email,user_id,organization_id,username,organ_name from company where id = ?", new Object[] { id },
				new BeanPropertyRowMapper(Company.class)); 
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	//根据条件分页查询数据并以List的形式返回给业务层
	@Override
	public List<Company> searchByEntityList(Integer start, Integer limit, Company company,User user) {
		System.out.println("123====="+company.getCompanyname()+company.getIndustrykind());
		ConditionHandler chparam =OrganizationHandler.returnEntitySql(user,BeanHandler.selectAndByEntity(company));
		ConditionHandler ch = BeanHandler.selectAndByEntity(company);
		String sql = "select industrykind,whetherpublic,publiccode,publictime,publictype,stockcode,staffnum,sciencestaffnum,id,companyname,creditcode,legalperson,fund,buildtime,companytype,province,city,coutry,contactperson,telphone,mainfield,legaltelphone,stockmessage,companynum,weixin,email,user_id,organization_id,username,organ_name from company where "+chparam.getSql() +" limit "+start+","+limit+"";
		if(chparam.getSqlCount().trim().length()>0)
			return (List<Company>)jdbcTemplate.query(sql ,chparam.getParams() , new BeanPropertyRowMapper<Company>(Company.class));
		return (List<Company>)jdbcTemplate.query(sql , new BeanPropertyRowMapper<Company>(Company.class));
	}

	//根据条件分页查询company表中记录的条数
	@Override
	public Integer searchByEntityCount(Company company,User user) {
		ConditionHandler chparam =OrganizationHandler.returnEntitySql(user,BeanHandler.selectAndByEntity(company));
		String sql = "select count(id) from company where "+chparam.getSql();
		return jdbcTemplate.queryForObject(sql , chparam.getParams() , Integer.class);
	}
	
	
	//批量向company表插入数据
	public void saveCompanyList(List<Company> list){
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Company company = (Company) iterator.next();
			this.saveCompany(company);
		}
	}

	@Override
	public List<Company> getCompanyNameList(Integer start, Integer limit, User user) {
		final String sql ="select id,companyname from company ";
		ConditionHandler ch = OrganizationHandler.returnCommonSql(user, sql);
        return (List<Company>) jdbcTemplate.query(ch.getSql()+" limit ?,? ", new Object[] { start, limit },
                new BeanPropertyRowMapper<Company>(Company.class));
	}
}
