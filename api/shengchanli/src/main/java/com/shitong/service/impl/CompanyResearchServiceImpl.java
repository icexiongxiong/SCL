package com.shitong.service.impl;

import javax.servlet.http.HttpSession;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shitong.dao.CompanyResearchDao;
import com.shitong.entity.CompanyResearch;
import com.shitong.entity.User;
import com.shitong.service.CompanyResearchService;
import com.shitong.util.BeanHandler;
import com.shitong.util.HttpContext;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三 
*/
@Service
public class CompanyResearchServiceImpl implements CompanyResearchService {
	@Autowired
	private CompanyResearchDao companyResearchDao;
	
	@Autowired
	private HttpContext httpContext;

	//根据id获取company_research表中的单条数据
	@Override
	public Page getCompanyResearchById(int id) {
		Page page = new Page();
		CompanyResearch companyResearch = companyResearchDao.findCompanyResearchById(id);
		if(companyResearch == null){
			page.setSuccess(false);
			page.setMessage("数据库中无此数据");
		}else{
			page.setSuccess(true);
			page.setData(companyResearch);
		}
		return page;
	}

	//向company_research表中插入一条记录
	@Override
	public Page saveCompanyResearch(CompanyResearch companyResearch) {
		Page page = new Page();
		User user = httpContext.getUser();
		companyResearch.setUserId(user.getId());
		companyResearch.setUsername(user.getUsername());
		companyResearch.setOrganizationId(user.getOrganizationid());
		companyResearch.setOrganName(user.getOrganizationname());
		companyResearchDao.saveCompanyResearch(companyResearch);
		page.setSuccess(true);
		page.setMessage("添加成功");
		return page;
	}
	
	//向company_research表中插入一条记录返回主键
	@Override
	public Page saveCompanyResearchReturnPrimaryKey(CompanyResearch companyResearch){
		Page page = new Page();
		User user = httpContext.getUser();
		companyResearch.setUserId(user.getId());
		companyResearch.setUsername(user.getUsername());
		companyResearch.setOrganizationId(user.getOrganizationid());
		companyResearch.setOrganName(user.getOrganizationname());
		Integer pkid = companyResearchDao.saveCompanyResearchAndGetAutoIncreaseId(companyResearch);
	//	System.out.println(pkid);
		page.setSuccess(true);
		page.setMessage("添加成功");
		return page;
	}

	//根据主键id删除company_research表中的一条数据
	@Override
	public Page deleteCompanyResearchById(int id) {
		Page page = new Page();
		companyResearchDao.deleteCompanyResearchById(id);
		page.setSuccess(true);
		page.setMessage("删除成功");
		return page;
	}

	//根据主键id修改company_research表中的数据
	@Override
	public Page updateCompanyResearchById(CompanyResearch companyResearch) {
		Page page = new Page();
		companyResearchDao.updateCompanyResearchById(companyResearch);
		page.setSuccess(true);
		page.setMessage("修改成功");
		return page;
	}

	//分页显示company_research表中的数据
	@Override
	public Page getCompanyResearchPageList(Page page) {
		User user = httpContext.getUser();
		List<CompanyResearch> list = companyResearchDao.getCompanyResearchList(page.getStart(), page.getLimit(),user);
		page.setTotal(companyResearchDao.getCompanyResearchCount(user));
		page.setRoot(list);
		page.setSuccess(true);
		return page;
	}

	//根据条件分页查询company_research表中的数据
	@Override
	public Page searchByEntity(Page page, CompanyResearch companyResearch) {
		User user = httpContext.getUser();
		if(BeanHandler.beanIsNotNull(companyResearch)){
			List<CompanyResearch> list = companyResearchDao.searchByEntityList(page.getStart(), page.getLimit(),companyResearch,user);
			page.setTotal(companyResearchDao.searchByEntityCount(companyResearch,user));
			page.setRoot(list);
			page.setSuccess(true);
		}else{
			page = this.getCompanyResearchPageList(page);
		}
		return page;
	}
}
