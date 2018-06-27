package com.shitong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shitong.entity.Menu;
import com.shitong.entity.Organization;
import com.shitong.service.OrganizationService;
import com.shitong.util.Page;

/**
 * @author 半天
 * @version 创建时间：2018年3月7日 上午11:07:58
 */
@RequestMapping("/organ")
@RestController
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;

	// 添加组织机构名称
	@RequestMapping(value = "/add")
	public Page add(@ModelAttribute Organization organization) throws Exception {
		return organizationService.save(organization);
	}

	// 根据组织机构ID删除其内容
	@RequestMapping(value = "/delete/{organid}")
	public Page delete(@PathVariable Integer organid) throws Exception {
		return organizationService.delete(organid);
	}

	// 修改组织机构内容
	@RequestMapping(value = "/update")
	public Page update(@ModelAttribute Organization organization) throws Exception {
		return organizationService.update(organization);
	}

	// 根据ID获取组织机构内容
	@RequestMapping(value = "/get/{organid}")
	public Page get(@PathVariable Integer organid) throws Exception {
		return organizationService.getOrganizationById(organid);
	}

	// 分页显示组织机构信息表
	@RequestMapping(value = "/page")
	public Page getOrganizationPage(@ModelAttribute Page page) throws Exception {
		return organizationService.getOrganizationPageList(page);
	}

	// combo组件显示组织机构信息
	@RequestMapping(value = "/combo")
	public Page getOrganizationComb() throws Exception {
		return organizationService.getOrganizationComboList();
	}
	
	// combo组件显示组织机构信息
	@RequestMapping(value = "/keshi")
	public Page getKeShiComb() throws Exception {
		return organizationService.getKeShiComboList();
	}
}
