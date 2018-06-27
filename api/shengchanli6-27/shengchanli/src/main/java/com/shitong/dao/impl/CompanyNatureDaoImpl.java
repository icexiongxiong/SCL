package com.shitong.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shitong.dao.CompanyNatureDao;
import com.shitong.entity.CompanyNature;


/** 
* @author  半天  
* @version 创建时间：2018年3月14日 下午3:49:47  
*/
@Repository
public class CompanyNatureDaoImpl implements CompanyNatureDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<CompanyNature> getCompanyNatureList() {
        final String sql ="select * from company_nature ";
        return (List<CompanyNature>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<CompanyNature>(CompanyNature.class));
	}


}
