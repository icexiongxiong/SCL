package com.shitong.service.impl;

import javax.servlet.http.HttpSession;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shitong.dao.CompanyDao;
import com.shitong.entity.Company;
import com.shitong.entity.User;
import com.shitong.service.CompanyService;
import com.shitong.util.BeanHandler;
import com.shitong.util.HttpContext;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三 
*/
@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private HttpContext httpContext;

	//根据id获取company表中的单条数据
	@Override
	public Page getCompanyById(int id) {
		Page page = new Page();
		Company company = companyDao.findCompanyById(id);
		if(company == null){
			page.setSuccess(false);
			page.setMessage("数据库中无此数据");
		}else{
			page.setSuccess(true);
			page.setData(company);
		}
		return page;
	}

	//向company表中插入一条记录
	@Override
	public Page saveCompany(Company company) {
		Page page = new Page();
		User user = httpContext.getUser();
		company.setUserId(user.getId());
		company.setUsername(user.getUsername());
		company.setOrganizationId(user.getOrganizationid());
		company.setOrganName(user.getOrganizationname());
		companyDao.saveCompany(company);
		page.setSuccess(true);
		page.setMessage("添加成功");
		return page;
	}
	
	//向company表中插入一条记录返回主键
	@Override
	public Page saveCompanyReturnPrimaryKey(Company company){
		Page page = new Page();
		User user = httpContext.getUser();
		company.setUserId(user.getId());
		company.setUsername(user.getUsername());
		company.setOrganizationId(user.getOrganizationid());
		company.setOrganName(user.getOrganizationname());
		Integer pkid = companyDao.saveCompanyAndGetAutoIncreaseId(company);
	//	System.out.println(pkid);
		page.setSuccess(true);
		page.setMessage("添加成功");
		return page;
	}

	//根据主键id删除company表中的一条数据
	@Override
	public Page deleteCompanyById(int id) {
		Page page = new Page();
		companyDao.deleteCompanyById(id);
		page.setSuccess(true);
		page.setMessage("删除成功");
		return page;
	}

	//根据主键id修改company表中的数据
	@Override
	public Page updateCompanyById(Company company) {
		Page page = new Page();
		companyDao.updateCompanyById(company);
		page.setSuccess(true);
		page.setMessage("修改成功");
		return page;
	}

	//分页显示company表中的数据
	@Override
	public Page getCompanyPageList(Page page) {
		User user = httpContext.getUser();
		List<Company> list = companyDao.getCompanyList(page.getStart(), page.getLimit(),user);
		page.setTotal(companyDao.getCompanyCount(user));
		page.setRoot(list);
		page.setSuccess(true);
		return page;
	}

	//根据条件分页查询company表中的数据
	@Override
	public Page searchByEntity(Page page, Company company) {
		User user = httpContext.getUser();
		if(BeanHandler.beanIsNotNull(company)){
			List<Company> list = companyDao.searchByEntityList(page.getStart(), page.getLimit(),company,user);
			page.setTotal(companyDao.searchByEntityCount(company,user));
			page.setRoot(list);
			page.setSuccess(true);
		}else{
			page = this.getCompanyPageList(page);
		}
		return page;
	}

	@Override
	public Page getCompanyNameList(Page page) {
		User user = httpContext.getUser();
		List<Company> list = companyDao.getCompanyNameList(0, 1000000, user);
		page.setRoot(list);
		page.setSuccess(true);
		return page;
	}
}
