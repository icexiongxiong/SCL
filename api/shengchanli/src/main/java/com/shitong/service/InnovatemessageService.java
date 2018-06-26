package com.shitong.service;

import com.shitong.entity.Innovatemessage;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月07日 15时35分29秒  星期一   
*/
public interface InnovatemessageService {
	
	//根据id获取innovatemessage表中的单条数据
	public Page getInnovatemessageById(int id);

	//向innovatemessage表中插入一条记录
	public Page saveInnovatemessage(Innovatemessage innovatemessage);
	
	//向innovatemessage表中插入一条记录返回主键
	public Page saveInnovatemessageReturnPrimaryKey (Innovatemessage innovatemessage);

	//根据主键id删除innovatemessage表中的一条数据
	public Page deleteInnovatemessageById(int id);

	//根据主键id修改innovatemessage表中的数据
	public Page updateInnovatemessageById(Innovatemessage innovatemessage);

	//分页显示innovatemessage表中的数据
	public Page getInnovatemessagePageList(Page page);

	//根据条件分页查询innovatemessage表中的数据
	public Page searchByEntity(Page page, Innovatemessage innovatemessage);


}
