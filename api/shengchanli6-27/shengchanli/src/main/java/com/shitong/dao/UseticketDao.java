package com.shitong.dao;

import java.util.List;

import com.shitong.entity.Useticket;
import com.shitong.entity.User;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三   
*/
public interface UseticketDao {

	//向useticket表插入数据，成功返回1
	Integer saveUseticket(Useticket useticket);
	
	//向useticket表插入数据，成功返回新插入数据的主键
	Integer saveUseticketAndGetAutoIncreaseId(Useticket useticket) ;

	//根据useticket表的主键删除数据
	void deleteUseticketById(int id);

	//根据主键修改useticket表的内容
	void updateUseticketById(Useticket useticket);

	//批量向useticket表插入数据
	void saveUseticketList(List<Useticket> list);

	//分页显示useticket表中的内容，其中start为数据开始位置，limit每页条数
	List<Useticket> getUseticketList(Integer start, Integer limit ,User user);

	//获取useticket表中记录的条数
	Integer getUseticketCount(User user);

	//根据主键id从useticket表中查找一条数据
	Useticket findUseticketById(int id);

	//根据条件分页查询数据并以List的形式返回给业务层
	List<Useticket> searchByEntityList(Integer start, Integer limit, Useticket useticket ,User user);

	//根据条件分页查询useticket表中记录的条数
	Integer searchByEntityCount(Useticket useticket ,User user);

}
