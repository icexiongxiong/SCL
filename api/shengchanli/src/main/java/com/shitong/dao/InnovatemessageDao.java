package com.shitong.dao;

import java.util.List;

import com.shitong.entity.Innovatemessage;

/** 
* @author  半天  
* @version 创建时间：2018年05月07日 15时35分29秒  星期一   
*/
public interface InnovatemessageDao {

	//向innovatemessage表插入数据，成功返回1
	Integer saveInnovatemessage(Innovatemessage innovatemessage);
	
	//向innovatemessage表插入数据，成功返回新插入数据的主键
	Integer saveInnovatemessageAndGetAutoIncreaseId(Innovatemessage innovatemessage) ;

	//根据innovatemessage表的主键删除数据
	void deleteInnovatemessageById(int id);

	//根据主键修改innovatemessage表的内容
	void updateInnovatemessageById(Innovatemessage innovatemessage);

	//批量向innovatemessage表插入数据
	void saveInnovatemessageList(List<Innovatemessage> list);

	//分页显示innovatemessage表中的内容，其中start为数据开始位置，limit每页条数
	List<Innovatemessage> getInnovatemessageList(Integer start, Integer limit);

	//获取innovatemessage表中记录的条数
	Integer getInnovatemessageCount();

	//根据主键id从innovatemessage表中查找一条数据
	Innovatemessage findInnovatemessageById(int id);

	//根据条件分页查询数据并以List的形式返回给业务层
	List<Innovatemessage> searchByEntityList(Integer start, Integer limit, Innovatemessage innovatemessage);

	//根据条件分页查询innovatemessage表中记录的条数
	Integer searchByEntityCount(Innovatemessage innovatemessage);

}
