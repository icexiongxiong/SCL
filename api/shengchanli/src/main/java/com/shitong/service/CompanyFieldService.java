package com.shitong.service;

import com.shitong.entity.CompanyField;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年03月24日 22时39分03秒  星期六   
*/
public interface CompanyFieldService {

	public Page getCompanyFieldById(int id);

	public Page saveCompanyField(CompanyField companyField);

	public Page deleteCompanyFieldById(int id);

	public Page updateCompanyFieldById(CompanyField companyField);

	public Page getCompanyFieldPageList(Page page);

	public Page searchByEntity(Page page, CompanyField companyField);


}
