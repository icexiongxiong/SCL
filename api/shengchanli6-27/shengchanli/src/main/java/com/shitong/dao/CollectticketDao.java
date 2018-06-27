package com.shitong.dao;

import java.util.List;

import com.shitong.entity.Collectticket;
import com.shitong.entity.User;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三   
*/
public interface CollectticketDao {

	//向collectticket表插入数据，成功返回1
	Integer saveCollectticket(Collectticket collectticket);
	
	//向collectticket表插入数据，成功返回新插入数据的主键
	Integer saveCollectticketAndGetAutoIncreaseId(Collectticket collectticket) ;

	//根据collectticket表的主键删除数据
	void deleteCollectticketById(int id);

	//根据主键修改collectticket表的内容
	void updateCollectticketById(Collectticket collectticket);

	//批量向collectticket表插入数据
	void saveCollectticketList(List<Collectticket> list);

	//分页显示collectticket表中的内容，其中start为数据开始位置，limit每页条数
	List<Collectticket> getCollectticketList(Integer start, Integer limit ,User user);

	//获取collectticket表中记录的条数
	Integer getCollectticketCount(User user);

	//根据主键id从collectticket表中查找一条数据
	Collectticket findCollectticketById(int id);

	//根据条件分页查询数据并以List的形式返回给业务层
	List<Collectticket> searchByEntityList(Integer start, Integer limit, Collectticket collectticket ,User user);

	//根据条件分页查询collectticket表中记录的条数
	Integer searchByEntityCount(Collectticket collectticket ,User user);

}
