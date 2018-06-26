package com.shitong.service;

import com.shitong.entity.Collectticket;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三   
*/
public interface CollectticketService {
	
	//根据id获取collectticket表中的单条数据
	public Page getCollectticketById(int id);

	//向collectticket表中插入一条记录
	public Page saveCollectticket(Collectticket collectticket);
	
	//向collectticket表中插入一条记录返回主键
	public Page saveCollectticketReturnPrimaryKey (Collectticket collectticket);

	//根据主键id删除collectticket表中的一条数据
	public Page deleteCollectticketById(int id);

	//根据主键id修改collectticket表中的数据
	public Page updateCollectticketById(Collectticket collectticket);

	//分页显示collectticket表中的数据
	public Page getCollectticketPageList(Page page);

	//根据条件分页查询collectticket表中的数据
	public Page searchByEntity(Page page, Collectticket collectticket);


}
