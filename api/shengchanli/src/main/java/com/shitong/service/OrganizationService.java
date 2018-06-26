package com.shitong.service;

import java.util.List;

import com.shitong.entity.Organization;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年3月8日 上午9:19:17  
*/
public interface OrganizationService {
	public Page save(Organization organization);
	
	public Page update(Organization organization);
	
	public Page delete(Integer organid);	
	
	public Page getOrganizationById(Integer organid);
	
	public Page getOrganizationComboList();
	
	public Page getOrganizationPageList(Page page);

	public Page getKeShiComboList();
	
}
