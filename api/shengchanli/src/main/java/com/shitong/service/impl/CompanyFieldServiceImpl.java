package com.shitong.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shitong.dao.CompanyFieldDao;
import com.shitong.entity.CompanyField;
import com.shitong.service.CompanyFieldService;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年03月24日 22时39分03秒  星期六 
*/
@Service
public class CompanyFieldServiceImpl implements CompanyFieldService {
	@Autowired
	private CompanyFieldDao companyFieldDao;

	@Override
	public Page getCompanyFieldById(int id) {
		Page page = new Page();
		CompanyField companyField = companyFieldDao.findCompanyFieldById(id);
		if(companyField == null){
			page.setSuccess(false);
			page.setMessage("数据库中无此数据");
		}else{
			page.setSuccess(true);
			page.setData(companyField);
		}
		return page;
	}

	@Override
	public Page saveCompanyField(CompanyField companyField) {
		Page page = new Page();
		companyFieldDao.saveCompanyField(companyField);
		page.setSuccess(true);
		page.setMessage("添加成功");
		return page;
	}

	@Override
	public Page deleteCompanyFieldById(int id) {
		Page page = new Page();
		companyFieldDao.deleteCompanyFieldById(id);
		page.setSuccess(true);
		page.setMessage("删除成功");
		return page;
	}

	@Override
	public Page updateCompanyFieldById(CompanyField companyField) {
		Page page = new Page();
		companyFieldDao.updateCompanyFieldById(companyField);
		page.setSuccess(true);
		page.setMessage("修改成功");
		return page;
	}

	@Override
	public Page getCompanyFieldPageList(Page page) {
		List<CompanyField> list = companyFieldDao.getCompanyFieldList(page.getStart(), page.getLimit());
		page.setTotal(companyFieldDao.getCompanyFieldCount());
		page.setRoot(list);
		page.setSuccess(true);
		return page;
	}

	@Override
	public Page searchByEntity(Page page, CompanyField companyField) {
		List<CompanyField> list = companyFieldDao.searchByEntityList(page.getStart(), page.getLimit(),companyField);
		page.setTotal(companyFieldDao.searchByEntityCount(companyField));
		page.setRoot(list);
		page.setSuccess(true);
		return page;
	}
}
