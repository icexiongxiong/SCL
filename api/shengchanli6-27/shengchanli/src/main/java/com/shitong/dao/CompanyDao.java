package com.shitong.dao;

import java.util.List;

import com.shitong.entity.Company;
import com.shitong.entity.User;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三   
*/
public interface CompanyDao {

	//向company表插入数据，成功返回1
	Integer saveCompany(Company company);
	
	//向company表插入数据，成功返回新插入数据的主键
	Integer saveCompanyAndGetAutoIncreaseId(Company company) ;

	//根据company表的主键删除数据
	void deleteCompanyById(int id);

	//根据主键修改company表的内容
	void updateCompanyById(Company company);

	//批量向company表插入数据
	void saveCompanyList(List<Company> list);

	//分页显示company表中的内容，其中start为数据开始位置，limit每页条数
	List<Company> getCompanyList(Integer start, Integer limit ,User user);

	//分页显示company表中的内容，其中start为数据开始位置，limit每页条数
	List<Company> getCompanyNameList(Integer start, Integer limit ,User user);

	//获取company表中记录的条数
	Integer getCompanyCount(User user);

	//根据主键id从company表中查找一条数据
	Company findCompanyById(int id);

	//根据条件分页查询数据并以List的形式返回给业务层
	List<Company> searchByEntityList(Integer start, Integer limit, Company company ,User user);

	//根据条件分页查询company表中记录的条数
	Integer searchByEntityCount(Company company ,User user);

}
