package com.shitong.service;

import com.shitong.entity.Authen;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月15日 13时18分08秒  星期二   
*/
public interface AuthenService {
	
	//根据id获取authen表中的单条数据
	public Page getAuthenById(int id);

	//向authen表中插入一条记录
	public Page saveAuthen(Authen authen);
	
	//向authen表中插入一条记录返回主键
	public Page saveAuthenReturnPrimaryKey (Authen authen);

	//根据主键id删除authen表中的一条数据
	public Page deleteAuthenById(int id);

	//根据主键id修改authen表中的数据
	public Page updateAuthenById(Authen authen);

	//分页显示authen表中的数据
	public Page getAuthenPageList(Page page);

	//根据条件分页查询authen表中的数据
	public Page searchByEntity(Page page, Authen authen);


}
