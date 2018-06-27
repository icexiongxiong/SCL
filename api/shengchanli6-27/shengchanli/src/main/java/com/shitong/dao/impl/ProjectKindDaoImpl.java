package com.shitong.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shitong.dao.ProjectKindDao;
import com.shitong.entity.CompanyKind;
import com.shitong.entity.ProjectKind;
import com.shitong.util.BeanHandler;
import com.shitong.util.ConditionHandler;

/** 
* @author  半天  
* @version 创建时间：2018年03月28日 14时15分18秒  星期三  
*/
@Repository
public class ProjectKindDaoImpl implements ProjectKindDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Integer saveProjectKind(ProjectKind projectKind) {
		ConditionHandler ch = BeanHandler.insert("project_kind",projectKind);
		return jdbcTemplate.update(ch.getSql(),ch.getParams());
	}

	@Override
	public void deleteProjectKindById(int id) {
		jdbcTemplate.update("delete from project_kind where project_id=?",id);
	}

	@Override
	public void updateProjectKindById(ProjectKind projectKind) {
		ConditionHandler ch = BeanHandler.updateById("project_kind","projectId",projectKind);
		jdbcTemplate.update(ch.getSql(), ch.getParams());
		
	}

	@Override
	public List<ProjectKind> getProjectKindList(Integer start, Integer limit) {
		final String sql ="select * from project_kind limit ?,? ";
        return (List<ProjectKind>) jdbcTemplate.query(sql, new Object[] { start, limit },
                new BeanPropertyRowMapper<ProjectKind>(ProjectKind.class));
	
	}

	@Override
	public Integer getProjectKindCount() {
		final String sql ="select count(project_id) from project_kind";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public ProjectKind findProjectKindById(int id) {
		List<ProjectKind> list = jdbcTemplate.query("select * from project_kind where project_id = ?", new Object[] { id },
				new BeanPropertyRowMapper(ProjectKind.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	

	@Override
	public List<ProjectKind> searchByEntityList(Integer start, Integer limit, ProjectKind projectKind) {
		ConditionHandler ch = BeanHandler.selectAndByEntity(projectKind);
		String sql = "select * from project_kind where "+ch.getProperty() +" limit "+start+","+limit+"";
		return (List<ProjectKind>)jdbcTemplate.query(sql , ch.getParams() , new BeanPropertyRowMapper<ProjectKind>(ProjectKind.class));
	}

	@Override
	public Integer searchByEntityCount(ProjectKind projectKind) {
		ConditionHandler ch = BeanHandler.selectAndByEntity(projectKind);
		String sql = "select count(id) from project_kind where "+ch.getProperty() ;
		return jdbcTemplate.queryForObject(sql , ch.getParams() , Integer.class);
	}
	
	

	public void saveProjectKindList(List<ProjectKind> list,Integer detailId){
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			ProjectKind projectKind = (ProjectKind) iterator.next();
			projectKind.setDetailId(detailId);
			this.saveProjectKind(projectKind);
		}
	}

	@Override
	public List<ProjectKind> getProjectKindListByDetailId(int id) {
		String sql = "select * from project_kind where detail_id=?";
		return (List<ProjectKind>)jdbcTemplate.query(sql ,  new Object[]{ id }  , new BeanPropertyRowMapper<ProjectKind>(ProjectKind.class));
	}
}
