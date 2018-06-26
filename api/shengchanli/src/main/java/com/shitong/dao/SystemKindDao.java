package com.shitong.dao;

import java.util.List;

import com.shitong.entity.CompanyKind;
import com.shitong.entity.SystemKind;

/** 
* @author  半天  
* @version 创建时间：2018年03月28日 14时15分18秒  星期三   
*/
public interface SystemKindDao {

	Integer saveSystemKind(SystemKind systemKind);

	void deleteSystemKindById(int id);

	void updateSystemKindById(SystemKind systemKind);

	void saveSystemKindList(List<SystemKind> list,Integer detailId);

	List<SystemKind> getSystemKindList(Integer start, Integer limit);

	Integer getSystemKindCount();

	SystemKind findSystemKindById(int id);

	List<SystemKind> searchByEntityList(Integer start, Integer limit, SystemKind systemKind);

	Integer searchByEntityCount(SystemKind systemKind);

	List<SystemKind> getSystemKindListByDetailId(int id);


}
