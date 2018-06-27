package com.shitong.dao;

import java.util.List;

import com.shitong.entity.Authen;
import com.shitong.entity.User;

/** 
* @author  半天  
* @version 创建时间：2018年05月15日 13时18分08秒  星期二   
*/
public interface AuthenDao {

	//向authen表插入数据，成功返回1
	Integer saveAuthen(Authen authen);
	
	//向authen表插入数据，成功返回新插入数据的主键
	Integer saveAuthenAndGetAutoIncreaseId(Authen authen) ;

	//根据authen表的主键删除数据
	void deleteAuthenById(int id);

	//根据主键修改authen表的内容
	void updateAuthenById(Authen authen);

	//批量向authen表插入数据
	void saveAuthenList(List<Authen> list);

	//分页显示authen表中的内容，其中start为数据开始位置，limit每页条数
	List<Authen> getAuthenList(Integer start, Integer limit ,User user);

	//获取authen表中记录的条数
	Integer getAuthenCount(User user);

	//根据主键id从authen表中查找一条数据
	Authen findAuthenById(int id);

	//根据条件分页查询数据并以List的形式返回给业务层
	List<Authen> searchByEntityList(Integer start, Integer limit, Authen authen ,User user);

	//根据条件分页查询authen表中记录的条数
	Integer searchByEntityCount(Authen authen ,User user);

}
