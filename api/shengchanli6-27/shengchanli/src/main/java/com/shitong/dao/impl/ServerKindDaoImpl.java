package com.shitong.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shitong.dao.ServerKindDao;
import com.shitong.entity.ServerKind;
import com.shitong.util.BeanHandler;
import com.shitong.util.ConditionHandler;

/** 
* @author  半天  
* @version 创建时间：2018年03月28日 14时15分18秒  星期三  
*/
@Repository
public class ServerKindDaoImpl implements ServerKindDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Integer saveServerKind(ServerKind serverKind) {
		ConditionHandler ch = BeanHandler.insert("server_kind",serverKind);
		return jdbcTemplate.update(ch.getSql(),ch.getParams());
	}

	@Override
	public void deleteServerKindById(int id) {
		jdbcTemplate.update("delete from server_kind where server_id=?",id);
	}

	@Override
	public void updateServerKindById(ServerKind serverKind) {
		ConditionHandler ch = BeanHandler.updateById("server_kind","serverId",serverKind);
		jdbcTemplate.update(ch.getSql(), ch.getParams());
		
	}

	@Override
	public List<ServerKind> getServerKindList(Integer start, Integer limit) {
		final String sql ="select * from server_kind limit ?,? ";
        return (List<ServerKind>) jdbcTemplate.query(sql, new Object[] { start, limit },
                new BeanPropertyRowMapper<ServerKind>(ServerKind.class));
	
	}

	@Override
	public Integer getServerKindCount() {
		final String sql ="select count(server_id) from server_kind";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public ServerKind findServerKindById(int id) {
		List<ServerKind> list = jdbcTemplate.query("select * from server_kind where server_id = ?", new Object[] { id },
				new BeanPropertyRowMapper(ServerKind.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	

	@Override
	public List<ServerKind> searchByEntityList(Integer start, Integer limit, ServerKind serverKind) {
		ConditionHandler ch = BeanHandler.selectAndByEntity(serverKind);
		String sql = "select * from server_kind where "+ch.getProperty() +" limit "+start+","+limit+"";
		return (List<ServerKind>)jdbcTemplate.query(sql , ch.getParams() , new BeanPropertyRowMapper<ServerKind>(ServerKind.class));
	}

	@Override
	public Integer searchByEntityCount(ServerKind serverKind) {
		ConditionHandler ch = BeanHandler.selectAndByEntity(serverKind);
		String sql = "select count(id) from server_kind where "+ch.getProperty() ;
		return jdbcTemplate.queryForObject(sql , ch.getParams() , Integer.class);
	}
	
	

	public void saveServerKindList(List<ServerKind> list,Integer detailId){
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			ServerKind serverKind = (ServerKind) iterator.next();
			serverKind.setDetailId(detailId);
			this.saveServerKind(serverKind);
		}
	}

	@Override
	public List<ServerKind> getServerKindListByDetailId(int id) {
		String sql = "select * from server_kind where detail_id=?";
		return (List<ServerKind>)jdbcTemplate.query(sql ,  new Object[]{ id }, new BeanPropertyRowMapper<ServerKind>(ServerKind.class));
	
	}
}
