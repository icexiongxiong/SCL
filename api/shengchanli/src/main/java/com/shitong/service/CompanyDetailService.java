package com.shitong.service;

import com.shitong.entity.CompanyDetail;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年03月28日 14时15分18秒  星期三   
*/
public interface CompanyDetailService {

	public Page getCompanyDetailById(int id);

	public Page getCompanyDetailQueryById(int detailid);

	public Page saveCompanyDetail(CompanyDetail companyDetail);

	public Page deleteCompanyDetailById(int id);

	public Page updateCompanyDetailById(CompanyDetail companyDetail);

	public Page getCompanyDetailPageList(Page page);

	public Page searchByEntity(Page page, CompanyDetail companyDetail);


}
