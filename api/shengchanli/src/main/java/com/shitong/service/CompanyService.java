package com.shitong.service;

import com.shitong.entity.Company;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三   
*/
public interface CompanyService {
	
	//根据id获取company表中的单条数据
	public Page getCompanyById(int id);

	//向company表中插入一条记录
	public Page saveCompany(Company company);
	
	//向company表中插入一条记录返回主键
	public Page saveCompanyReturnPrimaryKey (Company company);

	//根据主键id删除company表中的一条数据
	public Page deleteCompanyById(int id);

	//根据主键id修改company表中的数据
	public Page updateCompanyById(Company company);

	//分页显示company表中的数据
	public Page getCompanyPageList(Page page);
	
	public Page getCompanyNameList(Page page);

	//根据条件分页查询company表中的数据
	public Page searchByEntity(Page page, Company company);


}
