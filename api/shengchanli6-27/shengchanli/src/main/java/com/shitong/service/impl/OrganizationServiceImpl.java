package com.shitong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shitong.dao.OrganizationDao;
import com.shitong.entity.Organization;
import com.shitong.service.OrganizationService;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年3月8日 上午9:20:11  
*/
@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationDao organizationDao;

	@Override
	public Page save(Organization organization) {
		Page page = new Page();
		organizationDao.save(organization);
		page.setSuccess(true);
		page.setMessage("保存成功");
		return page;
	}

	@Override
	public Page update(Organization organization) {
		Page page = new Page();
		organizationDao.update(organization);
		page.setSuccess(true);
		page.setMessage("修改成功");
		return page;
	}

	@Override
	public Page delete(Integer organid) {
		Page page = new Page();
		List<Organization> list = organizationDao.getOrganizationListByParentId(organid);
		if(list.size()>0){
			page.setSuccess(false);
			page.setMessage("此记录下还有子记录，不能删除");
		}else {
			organizationDao.delete(organid);
			page.setSuccess(true);
			page.setMessage("删除成功");
		}
		return page;
	}

	@Override
	public Page getOrganizationById(Integer organid) {
		Page page = new Page();
		page.setData(organizationDao.getOrganizationById(organid));
		page.setSuccess(true);
		page.setMessage("单条记录查询成功");
		return page;
	}

	@Override
	public Page getOrganizationComboList() {
		Page page = new Page();
		page.setRoot(organizationDao.getOrganizationComboList());
		page.setSuccess(true);
		return page;
	}

	@Override
	public Page getOrganizationPageList(Page page) {
		// TODO Auto-generated method stub
		page.setRoot(organizationDao.getOrganizationPageList(page.getStart(), page.getLimit()));
		page.setTotal(organizationDao.getOrganizationCount());
		page.setSuccess(true);
		return page;
	}

	@Override
	public Page getKeShiComboList() {
		Page page = new Page();
		page.setRoot(organizationDao.getKeShiComboList());
		page.setSuccess(true);
		return page;
	}
}
