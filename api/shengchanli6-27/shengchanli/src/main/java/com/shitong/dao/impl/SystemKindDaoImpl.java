package com.shitong.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shitong.dao.SystemKindDao;
import com.shitong.entity.SystemKind;
import com.shitong.util.BeanHandler;
import com.shitong.util.ConditionHandler;

/** 
* @author  半天  
* @version 创建时间：2018年03月28日 14时15分18秒  星期三  
*/
@Repository
public class SystemKindDaoImpl implements SystemKindDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Integer saveSystemKind(SystemKind systemKind) {
		ConditionHandler ch = BeanHandler.insert("system_kind",systemKind);
		return jdbcTemplate.update(ch.getSql(),ch.getParams());
	}

	@Override
	public void deleteSystemKindById(int id) {
		jdbcTemplate.update("delete from system_kind where system_id=?",id);
	}

	@Override
	public void updateSystemKindById(SystemKind systemKind) {
		ConditionHandler ch = BeanHandler.updateById("system_kind","systemId",systemKind);
		jdbcTemplate.update(ch.getSql(), ch.getParams());
		
	}

	@Override
	public List<SystemKind> getSystemKindList(Integer start, Integer limit) {
		final String sql ="select * from system_kind limit ?,? ";
        return (List<SystemKind>) jdbcTemplate.query(sql, new Object[] { start, limit },
                new BeanPropertyRowMapper<SystemKind>(SystemKind.class));
	
	}

	@Override
	public Integer getSystemKindCount() {
		final String sql ="select count(system_id) from system_kind";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public SystemKind findSystemKindById(int id) {
		List<SystemKind> list = jdbcTemplate.query("select * from system_kind where system_id = ?", new Object[] { id },
				new BeanPropertyRowMapper(SystemKind.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	

	@Override
	public List<SystemKind> searchByEntityList(Integer start, Integer limit, SystemKind systemKind) {
		ConditionHandler ch = BeanHandler.selectAndByEntity(systemKind);
		String sql = "select * from system_kind where "+ch.getProperty() +" limit "+start+","+limit+"";
		return (List<SystemKind>)jdbcTemplate.query(sql , ch.getParams() , new BeanPropertyRowMapper<SystemKind>(SystemKind.class));
	}

	@Override
	public Integer searchByEntityCount(SystemKind systemKind) {
		ConditionHandler ch = BeanHandler.selectAndByEntity(systemKind);
		String sql = "select count(id) from system_kind where "+ch.getProperty() ;
		return jdbcTemplate.queryForObject(sql , ch.getParams() , Integer.class);
	}
	
	

	public void saveSystemKindList(List<SystemKind> list,Integer detailId){
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			SystemKind systemKind = (SystemKind) iterator.next();
			systemKind.setDetailId(detailId);
			this.saveSystemKind(systemKind);
		}
	}

	@Override
	public List<SystemKind> getSystemKindListByDetailId(int id) {
		String sql = "select * from system_kind where detail_id=?";
		return (List<SystemKind>)jdbcTemplate.query(sql ,  new Object[]{ id } , new BeanPropertyRowMapper<SystemKind>(SystemKind.class));
	
	}
}
