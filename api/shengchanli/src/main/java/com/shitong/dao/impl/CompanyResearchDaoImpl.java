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


import com.shitong.dao.CompanyResearchDao;
import com.shitong.entity.CompanyResearch;
import com.shitong.entity.User;
import com.shitong.util.BeanHandler;
import com.shitong.util.ConditionHandler;
import com.shitong.util.OrganizationHandler;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时44分35秒  星期三  
*/
@Repository
public class CompanyResearchDaoImpl implements CompanyResearchDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//向company_research表插入数据，成功返回1
	@Override
	public Integer saveCompanyResearch(CompanyResearch companyResearch) {
		ConditionHandler ch = BeanHandler.insert("company_research",companyResearch);
		return jdbcTemplate.update(ch.getSql(),ch.getParams());
	}
	
	//向company_research表插入数据，成功返回新插入数据的主键
	@Override
	public Integer saveCompanyResearchAndGetAutoIncreaseId(CompanyResearch companyResearch) {		
		final String sql = BeanHandler.insertReturnSql("company_research", companyResearch);
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

	//根据company_research表的主键删除数据
	@Override
	public void deleteCompanyResearchById(int id) {
		jdbcTemplate.update("delete from company_research where id=?",id);
	}

	//根据主键修改company_research表的内容
	@Override
	public void updateCompanyResearchById(CompanyResearch companyResearch) {
		ConditionHandler ch = BeanHandler.updateById("company_research","id",companyResearch);
		jdbcTemplate.update(ch.getSql(), ch.getParams());
		
	}

	//分页显示company_research表中的内容，其中start为数据开始位置，limit每页条数
	@Override
	public List<CompanyResearch> getCompanyResearchList(Integer start, Integer limit, User user) {
		final String sql ="select id,research_name,research_company,research_field,province,city,county,plat_kind,plat_class,research_totalincome,totalbenefit,project_gross,result_transfornum,patent_num,invent_patent,new_patent,new_inventpatent,researchgroup_person,thousandpplannum,thousandpplanname,academiciannum,academicianname,reasearch_sitearea,new_area,introduce_persontrain,device_num,device_value,key_technical,internal_standard,national_standard,industrial_standard,company_kindname,user_id,organization_id,username,organ_name from company_research ";
		ConditionHandler ch = OrganizationHandler.returnCommonSql(user, sql);
        return (List<CompanyResearch>) jdbcTemplate.query(ch.getSql()+" limit ?,? ", new Object[] { start, limit },
                new BeanPropertyRowMapper<CompanyResearch>(CompanyResearch.class));
	
	}

	//获取company_research表中记录的条数
	@Override
	public Integer getCompanyResearchCount(User user) {
		final String sql ="select count(id) from company_research";
		ConditionHandler ch = OrganizationHandler.returnCommonSql(user, sql);
		return jdbcTemplate.queryForObject(ch.getSql(), Integer.class);
	}

	//根据主键id从company_research表中查找一条数据
	@Override
	public CompanyResearch findCompanyResearchById(int id) {
		List<CompanyResearch> list = jdbcTemplate.query("select id,research_name,research_company,research_field,province,city,county,plat_kind,plat_class,research_totalincome,totalbenefit,project_gross,result_transfornum,patent_num,invent_patent,new_patent,new_inventpatent,researchgroup_person,thousandpplannum,thousandpplanname,academiciannum,academicianname,reasearch_sitearea,new_area,introduce_persontrain,device_num,device_value,key_technical,internal_standard,national_standard,industrial_standard,company_kindname,user_id,organization_id,username,organ_name from company_research where id = ?", new Object[] { id },
				new BeanPropertyRowMapper(CompanyResearch.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	//根据条件分页查询数据并以List的形式返回给业务层
	@Override
	public List<CompanyResearch> searchByEntityList(Integer start, Integer limit, CompanyResearch companyResearch,User user) {
		ConditionHandler chparam =OrganizationHandler.returnEntitySql(user,BeanHandler.selectAndByEntity(companyResearch));
		ConditionHandler ch = BeanHandler.selectAndByEntity(companyResearch);
		String sql = "select id,research_name,research_company,research_field,province,city,county,plat_kind,plat_class,research_totalincome,totalbenefit,project_gross,result_transfornum,patent_num,invent_patent,new_patent,new_inventpatent,researchgroup_person,thousandpplannum,thousandpplanname,academiciannum,academicianname,reasearch_sitearea,new_area,introduce_persontrain,device_num,device_value,key_technical,internal_standard,national_standard,industrial_standard,company_kindname,user_id,organization_id,username,organ_name from company_research where "+chparam.getSql() +" limit "+start+","+limit+"";
		if(chparam.getSqlCount().trim().length()>0)
			return (List<CompanyResearch>)jdbcTemplate.query(sql , chparam.getParams() , new BeanPropertyRowMapper<CompanyResearch>(CompanyResearch.class));
		return (List<CompanyResearch>)jdbcTemplate.query(sql , new BeanPropertyRowMapper<CompanyResearch>(CompanyResearch.class));
	}

	//根据条件分页查询company_research表中记录的条数
	@Override
	public Integer searchByEntityCount(CompanyResearch companyResearch,User user) {
		ConditionHandler chparam =OrganizationHandler.returnEntitySql(user,BeanHandler.selectAndByEntity(companyResearch));
		String sql = "select count(id) from company_research where "+chparam.getSql();
		return jdbcTemplate.queryForObject(sql , chparam.getParams() , Integer.class);
	}
	
	
	//批量向company_research表插入数据
	public void saveCompanyResearchList(List<CompanyResearch> list){
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			CompanyResearch companyResearch = (CompanyResearch) iterator.next();
			this.saveCompanyResearch(companyResearch);
		}
	}
}
