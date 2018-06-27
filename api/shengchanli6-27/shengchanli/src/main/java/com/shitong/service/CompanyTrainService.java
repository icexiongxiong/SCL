package com.shitong.service;

import com.shitong.entity.CompanyTrain;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三   
*/
public interface CompanyTrainService {
	
	//根据id获取company_train表中的单条数据
	public Page getCompanyTrainById(int id);

	//向company_train表中插入一条记录
	public Page saveCompanyTrain(CompanyTrain companyTrain);
	
	//向company_train表中插入一条记录返回主键
	public Page saveCompanyTrainReturnPrimaryKey (CompanyTrain companyTrain);

	//根据主键id删除company_train表中的一条数据
	public Page deleteCompanyTrainById(int id);

	//根据主键id修改company_train表中的数据
	public Page updateCompanyTrainById(CompanyTrain companyTrain);

	//分页显示company_train表中的数据
	public Page getCompanyTrainPageList(Page page);

	//根据条件分页查询company_train表中的数据
	public Page searchByEntity(Page page, CompanyTrain companyTrain);


}
