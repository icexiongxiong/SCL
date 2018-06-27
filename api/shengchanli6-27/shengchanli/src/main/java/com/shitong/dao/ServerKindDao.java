package com.shitong.dao;

import java.util.List;

import com.shitong.entity.CompanyKind;
import com.shitong.entity.ServerKind;

/** 
* @author  半天  
* @version 创建时间：2018年03月28日 14时15分18秒  星期三   
*/
public interface ServerKindDao {

	Integer saveServerKind(ServerKind serverKind);

	void deleteServerKindById(int id);

	void updateServerKindById(ServerKind serverKind);

	void saveServerKindList(List<ServerKind> list,Integer detailId);

	List<ServerKind> getServerKindList(Integer start, Integer limit);

	Integer getServerKindCount();

	ServerKind findServerKindById(int id);

	List<ServerKind> searchByEntityList(Integer start, Integer limit, ServerKind serverKind);

	Integer searchByEntityCount(ServerKind serverKind);

	List<ServerKind> getServerKindListByDetailId(int id);


}
