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


import com.shitong.dao.AuthenDao;
import com.shitong.entity.Authen;
import com.shitong.entity.User;
import com.shitong.util.BeanHandler;
import com.shitong.util.ConditionHandler;
import com.shitong.util.OrganizationHandler;

/** 
* @author  半天  
* @version 创建时间：2018年05月15日 13时18分08秒  星期二  
*/
@Repository
public class AuthenDaoImpl implements AuthenDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//向authen表插入数据，成功返回1
	@Override
	public Integer saveAuthen(Authen authen) {
		ConditionHandler ch = BeanHandler.insert("authen",authen);
		return jdbcTemplate.update(ch.getSql(),ch.getParams());
	}
	
	//向authen表插入数据，成功返回新插入数据的主键
	@Override
	public Integer saveAuthenAndGetAutoIncreaseId(Authen authen) {		
		final String sql = BeanHandler.insertReturnSql("authen", authen);
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

	//根据authen表的主键删除数据
	@Override
	public void deleteAuthenById(int id) {
		jdbcTemplate.update("delete from authen where id=?",id);
	}

	//根据主键修改authen表的内容
	@Override
	public void updateAuthenById(Authen authen) {
		ConditionHandler ch = BeanHandler.updateById("authen","id",authen);
		jdbcTemplate.update(ch.getSql(), ch.getParams());
		
	}

	//分页显示authen表中的内容，其中start为数据开始位置，limit每页条数
	@Override
	public List<Authen> getAuthenList(Integer start, Integer limit, User user) {
		final String sql ="select id,companyname,address,certifyprogram,certifygist,certificatenumber,certifyscope,certifyorganname,issuedate,firstgettime,changetime,state,user_id,organization_id,username,organ_name from authen ";
		ConditionHandler ch = OrganizationHandler.returnCommonSql(user, sql);
        return (List<Authen>) jdbcTemplate.query(ch.getSql()+" limit ?,? ", new Object[] { start, limit },
                new BeanPropertyRowMapper<Authen>(Authen.class));
	
	}

	//获取authen表中记录的条数
	@Override
	public Integer getAuthenCount(User user) {
		final String sql ="select count(id) from authen";
		ConditionHandler ch = OrganizationHandler.returnCommonSql(user, sql);
		return jdbcTemplate.queryForObject(ch.getSql(), Integer.class);
	}

	//根据主键id从authen表中查找一条数据
	@Override
	public Authen findAuthenById(int id) {
		List<Authen> list = jdbcTemplate.query("select id,companyname,address,certifyprogram,certifygist,certificatenumber,certifyscope,certifyorganname,issuedate,firstgettime,changetime,state,user_id,organization_id,username,organ_name from authen where id = ?", new Object[] { id },
				new BeanPropertyRowMapper(Authen.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	//根据条件分页查询数据并以List的形式返回给业务层
	@Override
	public List<Authen> searchByEntityList(Integer start, Integer limit, Authen authen,User user) {
		ConditionHandler chparam =OrganizationHandler.returnEntitySql(user,BeanHandler.selectAndByEntity(authen));
		ConditionHandler ch = BeanHandler.selectAndByEntity(authen);
		String sql = "select id,companyname,address,certifyprogram,certifygist,certificatenumber,certifyscope,certifyorganname,issuedate,firstgettime,changetime,state,user_id,organization_id,username,organ_name from authen where "+chparam.getSql() +" limit "+start+","+limit+"";
		if(chparam.getSqlCount().trim().length()>0)
			return (List<Authen>)jdbcTemplate.query(sql , chparam.getParams() , new BeanPropertyRowMapper<Authen>(Authen.class));
		return (List<Authen>)jdbcTemplate.query(sql , new BeanPropertyRowMapper<Authen>(Authen.class));
	}

	//根据条件分页查询authen表中记录的条数
	@Override
	public Integer searchByEntityCount(Authen authen,User user) {
		ConditionHandler chparam =OrganizationHandler.returnEntitySql(user,BeanHandler.selectAndByEntity(authen));
		String sql = "select count(id) from authen where "+chparam.getSql();
		return jdbcTemplate.queryForObject(sql , chparam.getParams() , Integer.class);
	}
	
	
	//批量向authen表插入数据
	public void saveAuthenList(List<Authen> list){
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Authen authen = (Authen) iterator.next();
			this.saveAuthen(authen);
		}
	}
}
