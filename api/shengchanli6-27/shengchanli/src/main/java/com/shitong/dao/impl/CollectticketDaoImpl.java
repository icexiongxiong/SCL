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


import com.shitong.dao.CollectticketDao;
import com.shitong.entity.Collectticket;
import com.shitong.entity.User;
import com.shitong.util.BeanHandler;
import com.shitong.util.ConditionHandler;
import com.shitong.util.OrganizationHandler;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时44分35秒  星期三  
*/
@Repository
public class CollectticketDaoImpl implements CollectticketDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//向collectticket表插入数据，成功返回1
	@Override
	public Integer saveCollectticket(Collectticket collectticket) {
		ConditionHandler ch = BeanHandler.insert("collectticket",collectticket);
		return jdbcTemplate.update(ch.getSql(),ch.getParams());
	}
	
	//向collectticket表插入数据，成功返回新插入数据的主键
	@Override
	public Integer saveCollectticketAndGetAutoIncreaseId(Collectticket collectticket) {		
		final String sql = BeanHandler.insertReturnSql("collectticket", collectticket);
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

	//根据collectticket表的主键删除数据
	@Override
	public void deleteCollectticketById(int id) {
		jdbcTemplate.update("delete from collectticket where id=?",id);
	}

	//根据主键修改collectticket表的内容
 	public void updateCollectticketById(Collectticket collectticket) {
		ConditionHandler ch = BeanHandler.updateById("collectticket","id",collectticket);
		jdbcTemplate.update(ch.getSql(), ch.getParams());
		
	}

	//分页显示collectticket表中的内容，其中start为数据开始位置，limit每页条数
	@Override
	public List<Collectticket> getCollectticketList(Integer start, Integer limit, User user) {
		final String sql ="select collectnum,collectname1,collectname2,collectname3,collectname4,collectname5,id,organizationname,address,person,phone,serverarea,ticketmoney,cashmoney,email,user_id,organization_id,username,organ_name from collectticket ";
		ConditionHandler ch = OrganizationHandler.returnCommonSql(user, sql);
        return (List<Collectticket>) jdbcTemplate.query(ch.getSql()+" limit ?,? ", new Object[] { start, limit },
                new BeanPropertyRowMapper<Collectticket>(Collectticket.class));
	
	}

	//获取collectticket表中记录的条数
	@Override
	public Integer getCollectticketCount(User user) {
		final String sql ="select count(id) from collectticket";
		ConditionHandler ch = OrganizationHandler.returnCommonSql(user, sql);
		return jdbcTemplate.queryForObject(ch.getSql(), Integer.class);
	}

	//根据主键id从collectticket表中查找一条数据
	@Override
	public Collectticket findCollectticketById(int id) {
		List<Collectticket> list = jdbcTemplate.query("select collectnum,collectname1,collectname2,collectname3,collectname4,collectname5,id,organizationname,address,person,phone,serverarea,ticketmoney,cashmoney,email,user_id,organization_id,username,organ_name from collectticket where id = ?", new Object[] { id },
				new BeanPropertyRowMapper(Collectticket.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	//根据条件分页查询数据并以List的形式返回给业务层
	@Override
	public List<Collectticket> searchByEntityList(Integer start, Integer limit, Collectticket collectticket,User user) {
		ConditionHandler chparam =OrganizationHandler.returnEntitySql(user,BeanHandler.selectAndByEntity(collectticket));
		ConditionHandler ch = BeanHandler.selectAndByEntity(collectticket);
		String sql = "select collectnum,collectname1,collectname2,collectname3,collectname4,collectname5,id,organizationname,address,person,phone,serverarea,ticketmoney,cashmoney,email,user_id,organization_id,username,organ_name from collectticket where "+chparam.getSql() +" limit "+start+","+limit+"";
		if(chparam.getSqlCount().trim().length()>0)
			return (List<Collectticket>)jdbcTemplate.query(sql , chparam.getParams() , new BeanPropertyRowMapper<Collectticket>(Collectticket.class));
		return (List<Collectticket>)jdbcTemplate.query(sql , new BeanPropertyRowMapper<Collectticket>(Collectticket.class));
	}

	//根据条件分页查询collectticket表中记录的条数
	@Override
	public Integer searchByEntityCount(Collectticket collectticket,User user) {
		ConditionHandler chparam =OrganizationHandler.returnEntitySql(user,BeanHandler.selectAndByEntity(collectticket));
		String sql = "select count(id) from collectticket where "+chparam.getSql();
		return jdbcTemplate.queryForObject(sql , chparam.getParams() , Integer.class);
	}
	
	
	//批量向collectticket表插入数据
	public void saveCollectticketList(List<Collectticket> list){
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Collectticket collectticket = (Collectticket) iterator.next();
			this.saveCollectticket(collectticket);
		}
	}
}
