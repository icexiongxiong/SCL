package com.shitong.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shitong.entity.Company;
import com.shitong.service.CompanyService;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三 
*/
@RequestMapping("/company")
@RestController
public class CompanyController {
	@Autowired
	private CompanyService companyService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
    	dateFormat.setLenient(false);  
    	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
    //根据id获取company表单条信息
    @RequestMapping(value = "/{id}")
    public Page getCompany(@PathVariable int id)    {
        return companyService.getCompanyById(id);
    }
	
	//向company表添加一条信息
    @RequestMapping(value = "/add")
    public Page addCompany(HttpSession session,@ModelAttribute Company company)    {
        return companyService.saveCompanyReturnPrimaryKey(company);
    }  
    
    //根据company表的id主键删除信息
    @RequestMapping(value = "/delete/{id}")
    public Page deleteCompany(@PathVariable int id)    {
        return companyService.deleteCompanyById(id);
    }  
    
    //根据id主键修改company表信息(id必须有)
    @RequestMapping(value = "/update")
    public Page updateCompany(@ModelAttribute Company company)    {
        return companyService.updateCompanyById(company);
    }
    
	//分页显示company表中的信息
	@RequestMapping(value = "/page")
	public Page page(@ModelAttribute Page page,HttpSession session) throws Exception {
		//page.setCond(session);
		return companyService.getCompanyPageList(page);
	}
	
    //根据条件查询显示company表中的信息@RequestParam HashMap map
    @RequestMapping(value = "/search")
    public Page searchByCompany(@ModelAttribute Page page,@ModelAttribute Company company,HttpSession session) {
		//page.setCond(session);
    	return companyService.searchByEntity(page,company);
    }
    
    @RequestMapping(value = "/combo")
    public Page getCompanyNameList(@ModelAttribute Page page) {
		//page.setCond(session);
    	return companyService.getCompanyNameList(page);
    }
    
    
}
