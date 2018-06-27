package com.shitong.dao;

import java.util.List;

import com.shitong.entity.CompanyDetail;
import com.shitong.entity.CompanyDetailQuery;
import com.shitong.entity.User;

/** 
* @author  半天  
* @version 创建时间：2018年03月28日 14时15分18秒  星期三   
*/
public interface CompanyDetailDao {

	Integer saveCompanyDetail(CompanyDetail companyDetail);
	
	//向company_detail表插入数据，成功返回新插入数据的主键
	Integer saveCompanyDetailAndGetAutoIncreaseId(CompanyDetail companyDetail) ;
	
	void deleteCompanyDetailById(int id);

	void updateCompanyDetailById(CompanyDetail companyDetail);

	void saveCompanyDetailList(List<CompanyDetail> list);

	//分页显示company_detail表中的内容，其中start为数据开始位置，limit每页条数
	List<CompanyDetail> getCompanyDetailList(Integer start, Integer limit ,User user);

	//获取company_detail表中记录的条数
	Integer getCompanyDetailCount(User user);

	CompanyDetail findCompanyDetailById(int id);

	List<CompanyDetail> searchByEntityList(Integer start, Integer limit, CompanyDetail companyDetail);

	Integer searchByEntityCount(CompanyDetail companyDetail);

	void deleteChildByDetailId(int id);

	CompanyDetailQuery findCompanyDetailQueryById(int id);
	
	//根据条件分页查询数据并以List的形式返回给业务层
	List<CompanyDetail> searchByEntityList(Integer start, Integer limit, CompanyDetail companyDetail ,User user);

	//根据条件分页查询company_detail表中记录的条数
	Integer searchByEntityCount(CompanyDetail companyDetail ,User user);

}
