package com.shitong.dao.impl;

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

import com.shitong.dao.CompanyDetailDao;
import com.shitong.entity.CompanyDetail;
import com.shitong.entity.CompanyDetailQuery;
import com.shitong.entity.User;
import com.shitong.util.BeanHandler;
import com.shitong.util.ConditionHandler;
import com.shitong.util.OrganizationHandler;

/** 
* @author  半天  
* @version 创建时间：2018年03月28日 14时15分18秒  星期三  
*/
@Repository
public class CompanyDetailDaoImpl implements CompanyDetailDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Integer saveCompanyDetail(CompanyDetail companyDetail) {
		ConditionHandler ch = BeanHandler.insert("company_detail",companyDetail);
		jdbcTemplate.update(ch.getSql(),ch.getParams());
		final String sql ="select max(id) from company_detail";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	@Override
	public Integer saveCompanyDetailAndGetAutoIncreaseId(CompanyDetail companyDetail) {		
		final String sql = BeanHandler.insertReturnSql("company_detail", companyDetail);
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

	@Override
	public void deleteCompanyDetailById(int id) {
		jdbcTemplate.update("delete from company_detail where id=?",id);
	}
	
	@Override
	public void deleteChildByDetailId(int id) {
		jdbcTemplate.update("delete from company_kind where detail_id=?",id);
		jdbcTemplate.update("delete from project_kind where detail_id=?",id);
		jdbcTemplate.update("delete from server_kind where detail_id=?",id);
		jdbcTemplate.update("delete from system_kind where detail_id=?",id);
		
	}

	@Override
	public void updateCompanyDetailById(CompanyDetail companyDetail) {
		ConditionHandler ch = BeanHandler.updateById("company_detail","id",companyDetail);
		jdbcTemplate.update(ch.getSql(), ch.getParams());
		
	}

	//分页显示company_detail表中的内容，其中start为数据开始位置，limit每页条数
	@Override
	public List<CompanyDetail> getCompanyDetailList(Integer start, Integer limit, User user) {
		final String sql ="select * from company_detail ";
		ConditionHandler ch = OrganizationHandler.returnCommonSql(user, sql);
        return (List<CompanyDetail>) jdbcTemplate.query(ch.getSql()+" limit ?,? ", new Object[] { start, limit },
                new BeanPropertyRowMapper<CompanyDetail>(CompanyDetail.class));
	
	}

	//获取company_detail表中记录的条数
	@Override
	public Integer getCompanyDetailCount(User user) {
		final String sql ="select count(id) from company_detail";
		ConditionHandler ch = OrganizationHandler.returnCommonSql(user, sql);
		return jdbcTemplate.queryForObject(ch.getSql(), Integer.class);
	}

	@Override
	public CompanyDetail findCompanyDetailById(int id) {
		List<CompanyDetail> list = jdbcTemplate.query("select * from company_detail where id = ?", new Object[] { id },
				new BeanPropertyRowMapper(CompanyDetail.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	

	@Override
	public List<CompanyDetail> searchByEntityList(Integer start, Integer limit, CompanyDetail companyDetail) {
		ConditionHandler ch = BeanHandler.selectAndByEntity(companyDetail);
		String sql = "select * from company_detail where "+ch.getProperty() +" limit "+start+","+limit+"";
		return (List<CompanyDetail>)jdbcTemplate.query(sql , ch.getParams() , new BeanPropertyRowMapper<CompanyDetail>(CompanyDetail.class));
	}

	@Override
	public Integer searchByEntityCount(CompanyDetail companyDetail) {
		ConditionHandler ch = BeanHandler.selectAndByEntity(companyDetail);
		String sql = "select count(id) from company_detail where "+ch.getProperty() ;
		return jdbcTemplate.queryForObject(sql , ch.getParams() , Integer.class);
	}
	
	

	public void saveCompanyDetailList(List<CompanyDetail> list){
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			CompanyDetail companyDetail = (CompanyDetail) iterator.next();
			this.saveCompanyDetail(companyDetail);
		}
	}

	@Override
	public CompanyDetailQuery findCompanyDetailQueryById(int id) {
		final String sql = "select `company`.`companyname` AS `companyname`,`company`.`creditcode` AS `creditcode`,`company`.`legalperson` AS `legalperson`,`company`.`fund` AS `fund`,`company`.`buildtime` AS `buildtime`,`company`.`companytype` AS `companytype`,`company`.`province` AS `province`,`company`.`city` AS `city`,`company`.`coutry` AS `coutry`,`company`.`contactperson` AS `contactperson`,`company`.`telphone` AS `telphone`,`company`.`mainfield` AS `mainfield`,`company`.`legaltelphone` AS `legaltelphone`,`company`.`stockmessage` AS `stockmessage`,`company`.`companynum` AS `companynum`,`company`.`weixin` AS `weixin`,`company`.`email` AS `email`,`company_detail`.`id` AS `id`,`company_detail`.`companyid` AS `companyid`,`company_detail`.`common_brand` AS `common_brand`,`company_detail`.`famous_brand` AS `famous_brand`,`company_detail`.`resound_brand` AS `resound_brand`,`company_detail`.`software_copyright` AS `software_copyright`,`company_detail`.`invention` AS `invention`,`company_detail`.`utility_model` AS `utility_model`,`company_detail`.`appear_model` AS `appear_model`,`company_detail`.`other_model` AS `other_model`,`company_detail`.`company_nature` AS `company_nature`,`company_detail`.`research_kind` AS `research_kind`,`company_detail`.`research_num` AS `research_num`,`company_detail`.`record_year` AS `record_year`,`company_detail`.`research_money` AS `research_money`,`company_detail`.`contract_exploit` AS `contract_exploit`,`company_detail`.`contract_transfer` AS `contract_transfer`,`company_detail`.`contract_service` AS `contract_service`,`company_detail`.`contract_consult` AS `contract_consult`,`company_detail`.`industry_study_research` AS `industry_study_research`,`company_detail`.`operate_agency` AS `operate_agency`,`company_detail`.`finance` AS `finance`,`company_detail`.`instrument_share` AS `instrument_share`,`company_detail`.`instrument_name` AS `instrument_name`,`company_detail`.`instrument_type` AS `instrument_type`,`company_detail`.`instrument_money` AS `instrument_money`,`company_detail`.`authen_project` AS `authen_project`,`company_detail`.`authen_gist` AS `authen_gist`,`company_detail`.`certificate_num` AS `certificate_num`,`company_detail`.`authen_scope` AS `authen_scope`,`company_detail`.`authen_orgname` AS `authen_orgname`,`company_detail`.`award_time` AS `award_time`,`company_detail`.`award_firsttime` AS `award_firsttime`,`company_detail`.`new_time` AS `new_time`,`company_detail`.`certificate_state` AS `certificate_state`,`company_detail`.`user_id` AS `user_id`,`company_detail`.`organization_id` AS `organization_id`,`company_detail`.`username` AS `username`,`company_detail`.`organ_name` AS `organ_name`,`company_detail`.`applytime` AS `applytime`,`company_detail`.`applymoney` AS `applymoney`,`company_detail`.`cashmoney` AS `cashmoney`,`company_detail`.`innovatetype` AS `innovatetype`,`company_detail`.`startyear` AS `startyear`,`company_detail`.`endyear` AS `endyear` from (`company` join `company_detail` on((`company`.`id` = `company_detail`.`companyid`))) where `company_detail`.`id` = ?";
		List<CompanyDetailQuery> list = jdbcTemplate.query(sql, new Object[] { id },
				new BeanPropertyRowMapper(CompanyDetailQuery.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	//根据条件分页查询数据并以List的形式返回给业务层
	@Override
	public List<CompanyDetail> searchByEntityList(Integer start, Integer limit, CompanyDetail companyDetail,User user) {
		ConditionHandler chparam =OrganizationHandler.returnEntitySql(user,BeanHandler.selectAndByEntity(companyDetail));
		ConditionHandler ch = BeanHandler.selectAndByEntity(companyDetail);
		String sql = "select * from company_detail where "+chparam.getSql() +" limit "+start+","+limit+"";
		if(chparam.getSqlCount().trim().length()>0)
			return (List<CompanyDetail>)jdbcTemplate.query(sql , chparam.getParams() , new BeanPropertyRowMapper<CompanyDetail>(CompanyDetail.class));
		return (List<CompanyDetail>)jdbcTemplate.query(sql , new BeanPropertyRowMapper<CompanyDetail>(CompanyDetail.class));
	}

	//根据条件分页查询company_detail表中记录的条数
	@Override
	public Integer searchByEntityCount(CompanyDetail companyDetail,User user) {
		ConditionHandler chparam =OrganizationHandler.returnEntitySql(user,BeanHandler.selectAndByEntity(companyDetail));
		String sql = "select count(id) from company_detail where "+chparam.getSql();
		return jdbcTemplate.queryForObject(sql , chparam.getParams() , Integer.class);
	}
}
