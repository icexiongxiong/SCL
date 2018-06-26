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

import com.shitong.entity.CompanyResearch;
import com.shitong.service.CompanyResearchService;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三 
*/
@RequestMapping("/companyresearch")
@RestController
public class CompanyResearchController {
	@Autowired
	private CompanyResearchService companyResearchService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
    	dateFormat.setLenient(false);  
    	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
    //根据id获取company_research表单条信息
    @RequestMapping(value = "/{id}")
    public Page getCompanyResearch(@PathVariable int id)    {
        return companyResearchService.getCompanyResearchById(id);
    }
	
	//向company_research表添加一条信息
    @RequestMapping(value = "/add")
    public Page addCompanyResearch(HttpSession session,@ModelAttribute CompanyResearch companyResearch)    {
        return companyResearchService.saveCompanyResearchReturnPrimaryKey(companyResearch);
    }  
    
    //根据company_research表的id主键删除信息
    @RequestMapping(value = "/delete/{id}")
    public Page deleteCompanyResearch(@PathVariable int id)    {
        return companyResearchService.deleteCompanyResearchById(id);
    }  
    
    //根据id主键修改company_research表信息(id必须有)
    @RequestMapping(value = "/update")
    public Page updateCompanyResearch(@ModelAttribute CompanyResearch companyResearch)    {
        return companyResearchService.updateCompanyResearchById(companyResearch);
    }
    
	//分页显示company_research表中的信息
	@RequestMapping(value = "/page")
	public Page page(@ModelAttribute Page page,HttpSession session) throws Exception {
		//page.setCond(session);
		return companyResearchService.getCompanyResearchPageList(page);
	}
	
    //根据条件查询显示company_research表中的信息@RequestParam HashMap map
    @RequestMapping(value = "/search")
    public Page searchByCompanyResearch(@ModelAttribute Page page,@ModelAttribute CompanyResearch companyResearch,HttpSession session) {
		//page.setCond(session);
    	return companyResearchService.searchByEntity(page,companyResearch);
    }
    
}
