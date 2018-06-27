package com.shitong.service;

import com.shitong.entity.Useticket;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三   
*/
public interface UseticketService {
	
	//根据id获取useticket表中的单条数据
	public Page getUseticketById(int id);

	//向useticket表中插入一条记录
	public Page saveUseticket(Useticket useticket);
	
	//向useticket表中插入一条记录返回主键
	public Page saveUseticketReturnPrimaryKey (Useticket useticket);

	//根据主键id删除useticket表中的一条数据
	public Page deleteUseticketById(int id);

	//根据主键id修改useticket表中的数据
	public Page updateUseticketById(Useticket useticket);

	//分页显示useticket表中的数据
	public Page getUseticketPageList(Page page);

	//根据条件分页查询useticket表中的数据
	public Page searchByEntity(Page page, Useticket useticket);


}
