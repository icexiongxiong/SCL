package com.shitong.dao;

import java.util.List;

import com.shitong.entity.CompanyResearch;
import com.shitong.entity.User;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三   
*/
public interface CompanyResearchDao {

	//向company_research表插入数据，成功返回1
	Integer saveCompanyResearch(CompanyResearch companyResearch);
	
	//向company_research表插入数据，成功返回新插入数据的主键
	Integer saveCompanyResearchAndGetAutoIncreaseId(CompanyResearch companyResearch) ;

	//根据company_research表的主键删除数据
	void deleteCompanyResearchById(int id);

	//根据主键修改company_research表的内容
	void updateCompanyResearchById(CompanyResearch companyResearch);

	//批量向company_research表插入数据
	void saveCompanyResearchList(List<CompanyResearch> list);

	//分页显示company_research表中的内容，其中start为数据开始位置，limit每页条数
	List<CompanyResearch> getCompanyResearchList(Integer start, Integer limit ,User user);

	//获取company_research表中记录的条数
	Integer getCompanyResearchCount(User user);

	//根据主键id从company_research表中查找一条数据
	CompanyResearch findCompanyResearchById(int id);

	//根据条件分页查询数据并以List的形式返回给业务层
	List<CompanyResearch> searchByEntityList(Integer start, Integer limit, CompanyResearch companyResearch ,User user);

	//根据条件分页查询company_research表中记录的条数
	Integer searchByEntityCount(CompanyResearch companyResearch ,User user);

}
