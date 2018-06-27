package com.shitong.dao;

import java.util.List;

import com.shitong.entity.CompanyField;

/** 
* @author  半天  
* @version 创建时间：2018年03月24日 22时39分03秒  星期六   
*/
public interface CompanyFieldDao {

	void saveCompanyField(CompanyField companyField);

	void deleteCompanyFieldById(int id);

	void updateCompanyFieldById(CompanyField companyField);

	List<CompanyField> getCompanyFieldList(Integer start, Integer limit);

	Integer getCompanyFieldCount();

	CompanyField findCompanyFieldById(int id);

	List<CompanyField> searchByEntityList(Integer start, Integer limit, CompanyField companyField);

	Integer searchByEntityCount(CompanyField companyField);

}
