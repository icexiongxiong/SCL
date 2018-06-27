package com.shitong.dao;

import java.util.List;

import com.shitong.entity.CompanyKind;

/** 
* @author  半天  
* @version 创建时间：2018年03月28日 14时15分18秒  星期三   
*/
public interface CompanyKindDao {

	Integer saveCompanyKind(CompanyKind companyKind);

	void deleteCompanyKindById(int id);

	void updateCompanyKindById(CompanyKind companyKind);

	void saveCompanyKindList(List<CompanyKind> list,Integer detailId);

	List<CompanyKind> getCompanyKindList(Integer start, Integer limit);

	Integer getCompanyKindCount();

	CompanyKind findCompanyKindById(int id);

	List<CompanyKind> searchByEntityList(Integer start, Integer limit, CompanyKind companyKind);

	Integer searchByEntityCount(CompanyKind companyKind);
	
	public List<CompanyKind> getCompanyKindListByDetailId(int id);

}
