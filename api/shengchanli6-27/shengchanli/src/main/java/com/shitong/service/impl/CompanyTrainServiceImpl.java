package com.shitong.service.impl;

import javax.servlet.http.HttpSession;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shitong.dao.CompanyTrainDao;
import com.shitong.entity.CompanyTrain;
import com.shitong.entity.User;
import com.shitong.service.CompanyTrainService;
import com.shitong.util.BeanHandler;
import com.shitong.util.HttpContext;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三 
*/
@Service
public class CompanyTrainServiceImpl implements CompanyTrainService {
	@Autowired
	private CompanyTrainDao companyTrainDao;
	
	@Autowired
	private HttpContext httpContext;

	//根据id获取company_train表中的单条数据
	@Override
	public Page getCompanyTrainById(int id) {
		Page page = new Page();
		CompanyTrain companyTrain = companyTrainDao.findCompanyTrainById(id);
		if(companyTrain == null){
			page.setSuccess(false);
			page.setMessage("数据库中无此数据");
		}else{
			page.setSuccess(true);
			page.setData(companyTrain);
		}
		return page;
	}

	//向company_train表中插入一条记录
	@Override
	public Page saveCompanyTrain(CompanyTrain companyTrain) {
		Page page = new Page();
		User user = httpContext.getUser();
		companyTrain.setUserId(user.getId());
		companyTrain.setUsername(user.getUsername());
		companyTrain.setOrganizationId(user.getOrganizationid());
		companyTrain.setOrganName(user.getOrganizationname());
		companyTrainDao.saveCompanyTrain(companyTrain);
		page.setSuccess(true);
		page.setMessage("添加成功");
		return page;
	}
	
	//向company_train表中插入一条记录返回主键
	@Override
	public Page saveCompanyTrainReturnPrimaryKey(CompanyTrain companyTrain){
		Page page = new Page();
		User user = httpContext.getUser();
		companyTrain.setUserId(user.getId());
		companyTrain.setUsername(user.getUsername());
		companyTrain.setOrganizationId(user.getOrganizationid());
		companyTrain.setOrganName(user.getOrganizationname());
		Integer pkid = companyTrainDao.saveCompanyTrainAndGetAutoIncreaseId(companyTrain);
	//	System.out.println(pkid);
		page.setSuccess(true);
		page.setMessage("添加成功");
		return page;
	}

	//根据主键id删除company_train表中的一条数据
	@Override
	public Page deleteCompanyTrainById(int id) {
		Page page = new Page();
		companyTrainDao.deleteCompanyTrainById(id);
		page.setSuccess(true);
		page.setMessage("删除成功");
		return page;
	}

	//根据主键id修改company_train表中的数据
	@Override
	public Page updateCompanyTrainById(CompanyTrain companyTrain) {
		Page page = new Page();
		companyTrainDao.updateCompanyTrainById(companyTrain);
		page.setSuccess(true);
		page.setMessage("修改成功");
		return page;
	}

	//分页显示company_train表中的数据
	@Override
	public Page getCompanyTrainPageList(Page page) {
		User user = httpContext.getUser();
		List<CompanyTrain> list = companyTrainDao.getCompanyTrainList(page.getStart(), page.getLimit(),user);
		page.setTotal(companyTrainDao.getCompanyTrainCount(user));
		page.setRoot(list);
		page.setSuccess(true);
		return page;
	}

	//根据条件分页查询company_train表中的数据
	@Override
	public Page searchByEntity(Page page, CompanyTrain companyTrain) {
		User user = httpContext.getUser();
		if(BeanHandler.beanIsNotNull(companyTrain)){
			List<CompanyTrain> list = companyTrainDao.searchByEntityList(page.getStart(), page.getLimit(),companyTrain,user);
			page.setTotal(companyTrainDao.searchByEntityCount(companyTrain,user));
			page.setRoot(list);
			page.setSuccess(true);
		}else{
			page = this.getCompanyTrainPageList(page);
		}
		return page;
	}
}
