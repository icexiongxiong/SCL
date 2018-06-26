package com.shitong.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shitong.dao.CompanyKindDao;
import com.shitong.entity.CompanyKind;
import com.shitong.util.BeanHandler;
import com.shitong.util.ConditionHandler;

/** 
* @author  半天  
* @version 创建时间：2018年03月28日 14时15分18秒  星期三  
*/
@Repository
public class CompanyKindDaoImpl implements CompanyKindDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Integer saveCompanyKind(CompanyKind companyKind) {
		ConditionHandler ch = BeanHandler.insert("company_kind",companyKind);
		return jdbcTemplate.update(ch.getSql(),ch.getParams());
	}

	@Override
	public void deleteCompanyKindById(int id) {
		jdbcTemplate.update("delete from company_kind where kind_id=?",id);
	}

	@Override
	public void updateCompanyKindById(CompanyKind companyKind) {
		ConditionHandler ch = BeanHandler.updateById("company_kind","kindId",companyKind);
		jdbcTemplate.update(ch.getSql(), ch.getParams());
		
	}

	@Override
	public List<CompanyKind> getCompanyKindList(Integer start, Integer limit) {
		final String sql ="select * from company_kind limit ?,? ";
        return (List<CompanyKind>) jdbcTemplate.query(sql, new Object[] { start, limit },
                new BeanPropertyRowMapper<CompanyKind>(CompanyKind.class));
	
	}

	@Override
	public Integer getCompanyKindCount() {
		final String sql ="select count(kind_id) from company_kind";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public CompanyKind findCompanyKindById(int id) {
		List<CompanyKind> list = jdbcTemplate.query("select * from company_kind where kind_id = ?", new Object[] { id },
				new BeanPropertyRowMapper(CompanyKind.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	

	@Override
	public List<CompanyKind> searchByEntityList(Integer start, Integer limit, CompanyKind companyKind) {
		ConditionHandler ch = BeanHandler.selectAndByEntity(companyKind);
		String sql = "select * from company_kind where "+ch.getProperty() +" limit "+start+","+limit+"";
		return (List<CompanyKind>)jdbcTemplate.query(sql , ch.getParams() , new BeanPropertyRowMapper<CompanyKind>(CompanyKind.class));
	}

	@Override
	public Integer searchByEntityCount(CompanyKind companyKind) {
		ConditionHandler ch = BeanHandler.selectAndByEntity(companyKind);
		String sql = "select count(id) from company_kind where "+ch.getProperty() ;
		return jdbcTemplate.queryForObject(sql , ch.getParams() , Integer.class);
	}
	
	

	public void saveCompanyKindList(List<CompanyKind> list,Integer detailId){
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			CompanyKind companyKind = (CompanyKind) iterator.next();
			companyKind.setDetailId(detailId);
			this.saveCompanyKind(companyKind);
		}
	}

	@Override
	public List<CompanyKind> getCompanyKindListByDetailId(int id) {
		String sql = "select * from company_kind where detail_id=?";
		return (List<CompanyKind>)jdbcTemplate.query(sql , new Object[]{ id } , new BeanPropertyRowMapper<CompanyKind>(CompanyKind.class));
	}


}
