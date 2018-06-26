package com.shitong.dao;

import java.util.List;

import com.shitong.entity.CompanyKind;
import com.shitong.entity.ProjectKind;

/** 
* @author  半天  
* @version 创建时间：2018年03月28日 14时15分18秒  星期三   
*/
public interface ProjectKindDao {

	Integer saveProjectKind(ProjectKind projectKind);

	void deleteProjectKindById(int id);

	void updateProjectKindById(ProjectKind projectKind);

	void saveProjectKindList(List<ProjectKind> list,Integer detailId);

	List<ProjectKind> getProjectKindList(Integer start, Integer limit);

	Integer getProjectKindCount();

	ProjectKind findProjectKindById(int id);

	List<ProjectKind> searchByEntityList(Integer start, Integer limit, ProjectKind projectKind);

	Integer searchByEntityCount(ProjectKind projectKind);

	List<ProjectKind> getProjectKindListByDetailId(int id);


}
