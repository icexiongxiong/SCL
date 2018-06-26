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


import com.shitong.dao.CompanyTrainDao;
import com.shitong.entity.CompanyTrain;
import com.shitong.entity.User;
import com.shitong.util.BeanHandler;
import com.shitong.util.ConditionHandler;
import com.shitong.util.OrganizationHandler;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时44分35秒  星期三  
*/
@Repository
public class CompanyTrainDaoImpl implements CompanyTrainDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//向company_train表插入数据，成功返回1
	@Override
	public Integer saveCompanyTrain(CompanyTrain companyTrain) {
		ConditionHandler ch = BeanHandler.insert("company_train",companyTrain);
		return jdbcTemplate.update(ch.getSql(),ch.getParams());
	}
	
	//向company_train表插入数据，成功返回新插入数据的主键
	@Override
	public Integer saveCompanyTrainAndGetAutoIncreaseId(CompanyTrain companyTrain) {		
		final String sql = BeanHandler.insertReturnSql("company_train", companyTrain);
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

	//根据company_train表的主键删除数据
	@Override
	public void deleteCompanyTrainById(int id) {
		jdbcTemplate.update("delete from company_train where id=?",id);
	}

	//根据主键修改company_train表的内容
	@Override
	public void updateCompanyTrainById(CompanyTrain companyTrain) {
		ConditionHandler ch = BeanHandler.updateById("company_train","id",companyTrain);
		jdbcTemplate.update(ch.getSql(), ch.getParams());
		
	}

	//分页显示company_train表中的内容，其中start为数据开始位置，limit每页条数
	@Override
	public List<CompanyTrain> getCompanyTrainList(Integer start, Integer limit, User user) {
		final String sql ="select id,companyname,registertime,legalperson,fund,registertype,companyscale,companyfield,growtime,identifytime,identifybatch,taxoffice,region,person,telphone,user_id,organization_id,username,organ_name from company_train ";
		ConditionHandler ch = OrganizationHandler.returnCommonSql(user, sql);
        return (List<CompanyTrain>) jdbcTemplate.query(ch.getSql()+" limit ?,? ", new Object[] { start, limit },
                new BeanPropertyRowMapper<CompanyTrain>(CompanyTrain.class));
	
	}

	//获取company_train表中记录的条数
	@Override
	public Integer getCompanyTrainCount(User user) {
		final String sql ="select count(id) from company_train";
		ConditionHandler ch = OrganizationHandler.returnCommonSql(user, sql);
		return jdbcTemplate.queryForObject(ch.getSql(), Integer.class);
	}

	//根据主键id从company_train表中查找一条数据
	@Override
	public CompanyTrain findCompanyTrainById(int id) {
		List<CompanyTrain> list = jdbcTemplate.query("select id,companyname,registertime,legalperson,fund,registertype,companyscale,companyfield,growtime,identifytime,identifybatch,taxoffice,region,person,telphone,user_id,organization_id,username,organ_name from company_train where id = ?", new Object[] { id },
				new BeanPropertyRowMapper(CompanyTrain.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	//根据条件分页查询数据并以List的形式返回给业务层
	@Override
	public List<CompanyTrain> searchByEntityList(Integer start, Integer limit, CompanyTrain companyTrain,User user) {
		ConditionHandler chparam =OrganizationHandler.returnEntitySql(user,BeanHandler.selectAndByEntity(companyTrain));
		ConditionHandler ch = BeanHandler.selectAndByEntity(companyTrain);
		String sql = "select id,companyname,registertime,legalperson,fund,registertype,companyscale,companyfield,growtime,identifytime,identifybatch,taxoffice,region,person,telphone,user_id,organization_id,username,organ_name from company_train where "+chparam.getSql() +" limit "+start+","+limit+"";
		if(chparam.getSqlCount().trim().length()>0)
			return (List<CompanyTrain>)jdbcTemplate.query(sql , chparam.getParams() , new BeanPropertyRowMapper<CompanyTrain>(CompanyTrain.class));
		return (List<CompanyTrain>)jdbcTemplate.query(sql , new BeanPropertyRowMapper<CompanyTrain>(CompanyTrain.class));
	}

	//根据条件分页查询company_train表中记录的条数
	@Override
	public Integer searchByEntityCount(CompanyTrain companyTrain,User user) {
		ConditionHandler chparam =OrganizationHandler.returnEntitySql(user,BeanHandler.selectAndByEntity(companyTrain));
		String sql = "select count(id) from company_train where "+chparam.getSql();
		return jdbcTemplate.queryForObject(sql , chparam.getParams() , Integer.class);
	}
	
	
	//批量向company_train表插入数据
	public void saveCompanyTrainList(List<CompanyTrain> list){
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			CompanyTrain companyTrain = (CompanyTrain) iterator.next();
			this.saveCompanyTrain(companyTrain);
		}
	}
}
