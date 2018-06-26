package com.shitong.service;

import com.shitong.entity.CompanyResearch;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三   
*/
public interface CompanyResearchService {
	
	//根据id获取company_research表中的单条数据
	public Page getCompanyResearchById(int id);

	//向company_research表中插入一条记录
	public Page saveCompanyResearch(CompanyResearch companyResearch);
	
	//向company_research表中插入一条记录返回主键
	public Page saveCompanyResearchReturnPrimaryKey (CompanyResearch companyResearch);

	//根据主键id删除company_research表中的一条数据
	public Page deleteCompanyResearchById(int id);

	//根据主键id修改company_research表中的数据
	public Page updateCompanyResearchById(CompanyResearch companyResearch);

	//分页显示company_research表中的数据
	public Page getCompanyResearchPageList(Page page);

	//根据条件分页查询company_research表中的数据
	public Page searchByEntity(Page page, CompanyResearch companyResearch);


}
