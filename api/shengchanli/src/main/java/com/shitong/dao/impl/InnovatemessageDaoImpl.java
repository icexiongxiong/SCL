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


import com.shitong.dao.InnovatemessageDao;
import com.shitong.entity.Innovatemessage;
import com.shitong.util.BeanHandler;
import com.shitong.util.ConditionHandler;

/** 
* @author  半天  
* @version 创建时间：2018年05月07日 15时35分29秒  星期一  
*/
@Repository
public class InnovatemessageDaoImpl implements InnovatemessageDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//向innovatemessage表插入数据，成功返回1
	@Override
	public Integer saveInnovatemessage(Innovatemessage innovatemessage) {
		ConditionHandler ch = BeanHandler.insert("innovatemessage",innovatemessage);
		return jdbcTemplate.update(ch.getSql(),ch.getParams());
	}
	
	//向innovatemessage表插入数据，成功返回新插入数据的主键
	@Override
	public Integer saveInnovatemessageAndGetAutoIncreaseId(Innovatemessage innovatemessage) {		
		final String sql = BeanHandler.insertReturnSql("innovatemessage", innovatemessage);
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

	//根据innovatemessage表的主键删除数据
	@Override
	public void deleteInnovatemessageById(int id) {
		jdbcTemplate.update("delete from innovatemessage where id=?",id);
	}

	//根据主键修改innovatemessage表的内容
	@Override
	public void updateInnovatemessageById(Innovatemessage innovatemessage) {
		ConditionHandler ch = BeanHandler.updateById("innovatemessage","id",innovatemessage);
		jdbcTemplate.update(ch.getSql(), ch.getParams());
		
	}

	//分页显示innovatemessage表中的内容，其中start为数据开始位置，limit每页条数
	@Override
	public List<Innovatemessage> getInnovatemessageList(Integer start, Integer limit) {
		final String sql ="select id,companyname,companyaddress,companyperson,companyphone,companyemail,applicationdate,innovationmoney,innovationpurpose,projectname,contractvalue,usevalue,cash,actualcashmoney,cashdate,checkcondition,organizationname,organizationaddress,organizationperson,organizationphone,serverarea,ticketmoney,cashmoney,organizationemail from innovatemessage limit ?,? ";
        return (List<Innovatemessage>) jdbcTemplate.query(sql, new Object[] { start, limit },
                new BeanPropertyRowMapper<Innovatemessage>(Innovatemessage.class));
	
	}

	//获取innovatemessage表中记录的条数
	@Override
	public Integer getInnovatemessageCount() {
		final String sql ="select count(id) from innovatemessage";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	//根据主键id从innovatemessage表中查找一条数据
	@Override
	public Innovatemessage findInnovatemessageById(int id) {
		List<Innovatemessage> list = jdbcTemplate.query("select id,companyname,companyaddress,companyperson,companyphone,companyemail,applicationdate,innovationmoney,innovationpurpose,projectname,contractvalue,usevalue,cash,actualcashmoney,cashdate,checkcondition,organizationname,organizationaddress,organizationperson,organizationphone,serverarea,ticketmoney,cashmoney,organizationemail from innovatemessage where id = ?", new Object[] { id },
				new BeanPropertyRowMapper(Innovatemessage.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	//根据条件分页查询数据并以List的形式返回给业务层
	@Override
	public List<Innovatemessage> searchByEntityList(Integer start, Integer limit, Innovatemessage innovatemessage) {
		ConditionHandler ch = BeanHandler.selectAndByEntity(innovatemessage);
		String sql = "select id,companyname,companyaddress,companyperson,companyphone,companyemail,applicationdate,innovationmoney,innovationpurpose,projectname,contractvalue,usevalue,cash,actualcashmoney,cashdate,checkcondition,organizationname,organizationaddress,organizationperson,organizationphone,serverarea,ticketmoney,cashmoney,organizationemail from innovatemessage where "+ch.getProperty() +" limit "+start+","+limit+"";
		return (List<Innovatemessage>)jdbcTemplate.query(sql , ch.getParams() , new BeanPropertyRowMapper<Innovatemessage>(Innovatemessage.class));
	}

	//根据条件分页查询innovatemessage表中记录的条数
	@Override
	public Integer searchByEntityCount(Innovatemessage innovatemessage) {
		ConditionHandler ch = BeanHandler.selectAndByEntity(innovatemessage);
		String sql = "select count(id) from innovatemessage where "+ch.getProperty() ;
		return jdbcTemplate.queryForObject(sql , ch.getParams() , Integer.class);
	}
	
	
	//批量向innovatemessage表插入数据
	public void saveInnovatemessageList(List<Innovatemessage> list){
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Innovatemessage innovatemessage = (Innovatemessage) iterator.next();
			this.saveInnovatemessage(innovatemessage);
		}
	}
}
