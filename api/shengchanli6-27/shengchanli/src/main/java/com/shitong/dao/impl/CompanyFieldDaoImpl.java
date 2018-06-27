package com.shitong.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shitong.dao.CompanyFieldDao;
import com.shitong.entity.CompanyField;
import com.shitong.util.BeanHandler;
import com.shitong.util.ConditionHandler;

/** 
* @author  半天  
* @version 创建时间：2018年03月24日 22时39分03秒  星期六  
*/
@Repository
public class CompanyFieldDaoImpl implements CompanyFieldDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void saveCompanyField(CompanyField companyField) {
		ConditionHandler ch = BeanHandler.insert("company_field",companyField);
		jdbcTemplate.update(ch.getSql(),ch.getParams());
	}

	@Override
	public void deleteCompanyFieldById(int id) {
		jdbcTemplate.update("delete from company_field where field_id=?",id);
	}

	@Override
	public void updateCompanyFieldById(CompanyField companyField) {
		ConditionHandler ch = BeanHandler.updateById("company_field","filedId",companyField);
		jdbcTemplate.update(ch.getSql(), ch.getParams());
		
	}

	@Override
	public List<CompanyField> getCompanyFieldList(Integer start, Integer limit) {
		final String sql ="select * from company_field limit ?,? ";
        return (List<CompanyField>) jdbcTemplate.query(sql, new Object[] { start, limit },
                new BeanPropertyRowMapper<CompanyField>(CompanyField.class));
	
	}

	@Override
	public Integer getCompanyFieldCount() {
		final String sql ="select count(field_id) from company_field";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public CompanyField findCompanyFieldById(int id) {
		List<CompanyField> list = jdbcTemplate.query("select * from company_field where field_id = ?", new Object[] { id },
				new BeanPropertyRowMapper(CompanyField.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<CompanyField> searchByEntityList(Integer start, Integer limit, CompanyField companyField) {
		ConditionHandler ch = BeanHandler.selectAndByEntity(companyField);
		String sql = "select * from company_field where "+ch.getProperty() +" limit "+start+","+limit+"";
		return (List<CompanyField>)jdbcTemplate.query(sql , ch.getParams() , new BeanPropertyRowMapper<CompanyField>(CompanyField.class));
	}

	@Override
	public Integer searchByEntityCount(CompanyField companyField) {
		ConditionHandler ch = BeanHandler.selectAndByEntity(companyField);
		String sql = "select count(field_id) from company_field where "+ch.getProperty() ;
		return jdbcTemplate.queryForObject(sql , ch.getParams() , Integer.class);
	}
}
