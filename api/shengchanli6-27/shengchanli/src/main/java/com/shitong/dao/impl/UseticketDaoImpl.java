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


import com.shitong.dao.UseticketDao;
import com.shitong.entity.Useticket;
import com.shitong.entity.User;
import com.shitong.util.BeanHandler;
import com.shitong.util.ConditionHandler;
import com.shitong.util.OrganizationHandler;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时44分35秒  星期三  
*/
@Repository
public class UseticketDaoImpl implements UseticketDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//向useticket表插入数据，成功返回1
	@Override
	public Integer saveUseticket(Useticket useticket) {
		ConditionHandler ch = BeanHandler.insert("useticket",useticket);
		return jdbcTemplate.update(ch.getSql(),ch.getParams());
	}
	
	//向useticket表插入数据，成功返回新插入数据的主键
	@Override
	public Integer saveUseticketAndGetAutoIncreaseId(Useticket useticket) {		
		final String sql = BeanHandler.insertReturnSql("useticket", useticket);
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

	//根据useticket表的主键删除数据
	@Override
	public void deleteUseticketById(int id) {
		jdbcTemplate.update("delete from useticket where id=?",id);
	}

	//根据主键修改useticket表的内容
	@Override
	public void updateUseticketById(Useticket useticket) {
		ConditionHandler ch = BeanHandler.updateById("useticket","id",useticket);
		jdbcTemplate.update(ch.getSql(), ch.getParams());
		
	}

	//分页显示useticket表中的内容，其中start为数据开始位置，limit每页条数
	@Override
	public List<Useticket> getUseticketList(Integer start, Integer limit, User user) {
		final String sql ="select collectnum,collectname1,collectname2,collectname3,collectname4,collectname5,id,companyname,companyaddress,companyperson,companyphone,companyemail,applicationdate,innovationmoney,innovationpurpose,projectname,contractvalue,usevalue,cash,actualcashmoney,cashdate,checkcondition,user_id,organization_id,username,organ_name from useticket ";
		ConditionHandler ch = OrganizationHandler.returnCommonSql(user, sql);
        return (List<Useticket>) jdbcTemplate.query(ch.getSql()+" limit ?,? ", new Object[] { start, limit },
                new BeanPropertyRowMapper<Useticket>(Useticket.class));
	
	}

	//获取useticket表中记录的条数
	@Override
	public Integer getUseticketCount(User user) {
		final String sql ="select count(id) from useticket";
		ConditionHandler ch = OrganizationHandler.returnCommonSql(user, sql);
		return jdbcTemplate.queryForObject(ch.getSql(), Integer.class);
	}

	//根据主键id从useticket表中查找一条数据
	@Override
	public Useticket findUseticketById(int id) {
		List<Useticket> list = jdbcTemplate.query("select collectnum,collectname1,collectname2,collectname3,collectname4,collectname5,id,companyname,companyaddress,companyperson,companyphone,companyemail,applicationdate,innovationmoney,innovationpurpose,projectname,contractvalue,usevalue,cash,actualcashmoney,cashdate,checkcondition,user_id,organization_id,username,organ_name from useticket where id = ?", new Object[] { id },
				new BeanPropertyRowMapper(Useticket.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	//根据条件分页查询数据并以List的形式返回给业务层
	@Override
	public List<Useticket> searchByEntityList(Integer start, Integer limit, Useticket useticket,User user) {
		ConditionHandler chparam =OrganizationHandler.returnEntitySql(user,BeanHandler.selectAndByEntity(useticket));
		ConditionHandler ch = BeanHandler.selectAndByEntity(useticket);
		String sql = "select collectnum,collectname1,collectname2,collectname3,collectname4,collectname5,id,companyname,companyaddress,companyperson,companyphone,companyemail,applicationdate,innovationmoney,innovationpurpose,projectname,contractvalue,usevalue,cash,actualcashmoney,cashdate,checkcondition,user_id,organization_id,username,organ_name from useticket where "+chparam.getSql() +" limit "+start+","+limit+"";
        System.out.print("==============================================="+sql);
		if(chparam.getSqlCount().trim().length()>0)
			return (List<Useticket>)jdbcTemplate.query(sql , chparam.getParams() , new BeanPropertyRowMapper<Useticket>(Useticket.class));
		return (List<Useticket>)jdbcTemplate.query(sql , new BeanPropertyRowMapper<Useticket>(Useticket.class));
	}

	//根据条件分页查询useticket表中记录的条数
	@Override
	public Integer searchByEntityCount(Useticket useticket,User user) {
		ConditionHandler chparam =OrganizationHandler.returnEntitySql(user,BeanHandler.selectAndByEntity(useticket));
		String sql = "select count(id) from useticket where "+chparam.getSql();
		return jdbcTemplate.queryForObject(sql , chparam.getParams() , Integer.class);
	}
	
	
	//批量向useticket表插入数据
	public void saveUseticketList(List<Useticket> list){
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Useticket useticket = (Useticket) iterator.next();
			this.saveUseticket(useticket);
		}
	}
}
