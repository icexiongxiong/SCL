package com.shitong.dao;

import java.util.List;
import com.shitong.entity.Organization;

/** 
* @author  半天  
* @version 创建时间：2018年3月7日 上午11:10:31  
*/
public interface OrganizationDao {

	
	public void save(Organization organization);
	
	public void update(Organization organization);
	
	public void delete(Integer organid);	
	
	public Organization getOrganizationById(Integer organid);
	
	public List<Organization> getOrganizationComboList();	
	
	public List<Organization> getOrganizationPageList(Integer from, Integer to) ;
	
	public Integer getOrganizationCount() ;
	
	public String getOrganizationIds(Integer id) ;

	public List<Organization> getOrganizationListByParentId(Integer organid);

	public List getKeShiComboList();
}
