package com.shitong.service.impl;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.shitong.dao.CompanyDao;
import com.shitong.dao.CompanyDetailDao;
import com.shitong.dao.CompanyKindDao;
import com.shitong.dao.ProjectKindDao;
import com.shitong.dao.ServerKindDao;
import com.shitong.dao.SystemKindDao;
import com.shitong.entity.Company;
import com.shitong.entity.CompanyDetail;
import com.shitong.entity.CompanyDetailQuery;
import com.shitong.entity.CompanyKind;
import com.shitong.entity.ProjectKind;
import com.shitong.entity.ServerKind;
import com.shitong.entity.SystemKind;
import com.shitong.entity.User;
import com.shitong.service.CompanyDetailService;
import com.shitong.util.BeanHandler;
import com.shitong.util.HttpContext;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年03月28日 14时15分18秒  星期三 
*/
@Service
public class CompanyDetailServiceImpl implements CompanyDetailService {
	@Autowired
	private CompanyDetailDao companyDetailDao;
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private CompanyKindDao companyKindDao;
	
	@Autowired
	private ProjectKindDao projectKindDao;
	
	@Autowired
	private ServerKindDao serverKindDao;
	
	@Autowired
	private SystemKindDao systemKindDao;
	
	@Autowired
	private HttpContext httpContext;

	@Override
	public Page getCompanyDetailById(int id) {
		Page page = new Page();
		CompanyDetail companyDetail = companyDetailDao.findCompanyDetailById(id);
		List<CompanyKind> list1 = companyKindDao.getCompanyKindListByDetailId(id);
		if(list1.size() > 0){
			companyDetail.setCompanyKindList(list1);
		}
		List list2 = projectKindDao.getProjectKindListByDetailId(id);
		if(list2.size() > 0){
			companyDetail.setProjectKindList(list2);
		}
		List list3 = serverKindDao.getServerKindListByDetailId(id);
		if(list3.size() > 0){
			companyDetail.setServerKindList(list3);
		}
		List list4 = systemKindDao.getSystemKindListByDetailId(id);
		if(list4.size() > 0){
			companyDetail.setSystemKindList(list4);
		}
		if(companyDetail == null){
			page.setSuccess(false);
			page.setMessage("数据库中无此数据");
		}else{
			page.setSuccess(true);
			page.setData(companyDetail);
		}
		return page;
	}

	@Transactional
	@Override
	public Page saveCompanyDetail(CompanyDetail companyDetail) {
		Page page = new Page();
		User user = httpContext.getUser();
		companyDetail.setUserId(user.getId());
		companyDetail.setUsername(user.getUsername());
		companyDetail.setOrganizationId(user.getOrganizationid());
		companyDetail.setOrganName(user.getOrganizationname());
		Integer detailId = companyDetailDao.saveCompanyDetailAndGetAutoIncreaseId(companyDetail);
		System.out.println(JSON.toJSONString(companyDetail));
		List<CompanyKind> list1 = companyDetail.getCompanyKindList();
		if(list1.size() > 0){
			companyKindDao.saveCompanyKindList(list1, detailId);
		}
		List<ProjectKind> list2 = companyDetail.getProjectKindList();
		if(list2.size() > 0){
			projectKindDao.saveProjectKindList(list2, detailId);
		}
		List<ServerKind> list3 = companyDetail.getServerKindList();
		if(list3.size() > 0){
			serverKindDao.saveServerKindList(list3, detailId);
		}
		List<SystemKind> list4 = companyDetail.getSystemKindList();
		if(list4.size() > 0){
			systemKindDao.saveSystemKindList(list4, detailId);
		}
		page.setSuccess(true);
		page.setMessage("添加成功");
		return page;
	}

	@Transactional
	@Override
	public Page deleteCompanyDetailById(int id) {
		Page page = new Page();
		companyDetailDao.deleteCompanyDetailById(id);
		companyDetailDao.deleteChildByDetailId(id);
		page.setSuccess(true);
		page.setMessage("删除成功");
		return page;
	}

	@Transactional
	@Override
	public Page updateCompanyDetailById(CompanyDetail companyDetail) {
		Page page = new Page();
		System.out.println(JSON.toJSONString(companyDetail));
		companyDetailDao.updateCompanyDetailById(companyDetail);
		Integer detailId = companyDetail.getId();
		companyDetailDao.deleteChildByDetailId(detailId);
		List<CompanyKind> list1 = companyDetail.getCompanyKindList();
		if(list1.size() > 0){
			companyKindDao.saveCompanyKindList(list1, detailId);
		}
		List<ProjectKind> list2 = companyDetail.getProjectKindList();
		if(list2.size() > 0){
			projectKindDao.saveProjectKindList(list2, detailId);
		}
		List<ServerKind> list3 = companyDetail.getServerKindList();
		if(list3.size() > 0){
			serverKindDao.saveServerKindList(list3, detailId);
		}
		List<SystemKind> list4 = companyDetail.getSystemKindList();
		if(list4.size() > 0){
			systemKindDao.saveSystemKindList(list4, detailId);
		}
		page.setSuccess(true);
		page.setMessage("修改成功");
		return page;
	}

	@Override
	public Page getCompanyDetailPageList(Page page) {
		User user = httpContext.getUser();
		List<CompanyDetail> list = companyDetailDao.getCompanyDetailList(page.getStart(), page.getLimit(),user);
		page.setTotal(companyDetailDao.getCompanyDetailCount(user));
		page.setRoot(list);
		page.setSuccess(true);
		return page;
	}

	@Override
	public Page searchByEntity(Page page, CompanyDetail companyDetail) {
    	companyDetail.setServerKindList(null);
    	companyDetail.setProjectKindList(null);
    	companyDetail.setCompanyKindList(null);
    	companyDetail.setSystemKindList(null);
		User user = httpContext.getUser();
		if(BeanHandler.beanIsNotNull(companyDetail)){
			List<CompanyDetail> list = companyDetailDao.searchByEntityList(page.getStart(), page.getLimit(),companyDetail,user);
			page.setTotal(companyDetailDao.searchByEntityCount(companyDetail,user));
			page.setRoot(list);
			page.setSuccess(true);
		}else{
			page = this.getCompanyDetailPageList(page);
		}
		return page;
	}

	@Override
	public Page getCompanyDetailQueryById(int id) {
		Page page = new Page();
		CompanyDetailQuery companyDetailQuery = companyDetailDao.findCompanyDetailQueryById(id);

		if(companyDetailQuery == null){
			page.setSuccess(false);
			page.setMessage("数据库中无此数据");
		}else{
			List<CompanyKind> list1 = companyKindDao.getCompanyKindListByDetailId(id);
			if( list1.size() > 0 ){
				companyDetailQuery.setCompanyKindList(list1);
			}
			List<ProjectKind> list2 = projectKindDao.getProjectKindListByDetailId(id);
			if(list2.size() > 0){
				companyDetailQuery.setProjectKindList(list2);
			}
			List<ServerKind> list3 = serverKindDao.getServerKindListByDetailId(id);
			if(list3.size() > 0){
				companyDetailQuery.setServerKindList(list3);
			}
			List list4 = systemKindDao.getSystemKindListByDetailId(id);
			if(list4.size() > 0){
				companyDetailQuery.setSystemKindList(list4);
			}
			page.setSuccess(true);
			page.setData(companyDetailQuery);
		}
		return page;
	}
}
