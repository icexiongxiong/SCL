package com.shitong.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.shitong.entity.CompanyDetail;
import com.shitong.entity.CompanyKind;
import com.shitong.entity.ProjectKind;
import com.shitong.entity.ServerKind;
import com.shitong.entity.SystemKind;
import com.shitong.entity.User;
import com.shitong.service.CompanyDetailService;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年03月28日 14时15分18秒  星期三 
*/
@RequestMapping("/companydetail")
@RestController
public class CompanyDetailController {
	@Autowired
	private CompanyDetailService companyDetailService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
    	dateFormat.setLenient(false);  
    	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
    //根据id获取单条信息
    @RequestMapping(value = "/{id}")
    public Page getCompanyDetail(@PathVariable int id)    {
        return companyDetailService.getCompanyDetailById(id);
    }
    
    //根据id获取单条信息
    @RequestMapping(value = "/query/{id}")
    public Page getCompanyDetailQuery(@PathVariable int id)    {
        return companyDetailService.getCompanyDetailQueryById(id);
    }
	
    
    @RequestMapping(value = "/add")
    public Page addCompanyDetail(@ModelAttribute CompanyDetail companyDetail,HttpSession session)    {
    	String string1 = companyDetail.getCompanyKinds();
    	if(string1 != null){
	    	List<CompanyKind> companyKindList = JSON.parseArray(string1, CompanyKind.class);
	    	companyDetail.setCompanyKindList(companyKindList);
	    	companyDetail.setCompanyKinds(null);
    	}
    	
    	String string2 = companyDetail.getProjectKinds();
    	if(string2 != null){
	    	List<ProjectKind> projectKindList = JSON.parseArray(string2, ProjectKind.class);
	    	companyDetail.setProjectKindList(projectKindList);
	    	companyDetail.setProjectKinds(null);
    	}
    	
    	String string3 = companyDetail.getServerKinds();
    	if(string3 != null){
	    	List<ServerKind> serverKindList = JSON.parseArray(string3, ServerKind.class);
	    	companyDetail.setServerKindList(serverKindList);
	    	companyDetail.setServerKinds(null);
    	}
    	String string4 = companyDetail.getSystemKinds();
    	if(string4 != null){
	    	List<SystemKind> systemKindList = JSON.parseArray(string4, SystemKind.class);
	    	companyDetail.setSystemKindList(systemKindList);
	    	companyDetail.setSystemKinds(null);
    	}
    	
        return companyDetailService.saveCompanyDetail(companyDetail);
    }  
    
    //根据id主键删除信息
    @RequestMapping(value = "/delete/{id}")
    public Page deleteCompanyDetail(@PathVariable int id)    {
        return companyDetailService.deleteCompanyDetailById(id);
    }  
    
    //修改信息(id必须有)
    @RequestMapping(value = "/update")
    public Page updateCompanyDetail(@ModelAttribute CompanyDetail companyDetail)    {
    	String string1 = companyDetail.getCompanyKinds();
    	if(string1 != null){
	    	List<CompanyKind> companyKindList = JSON.parseArray(string1, CompanyKind.class);
	    	companyDetail.setCompanyKindList(companyKindList);
    	}
    	companyDetail.setCompanyKinds(null);
    	
    	String string2 = companyDetail.getProjectKinds();
    	if(string2 != null){
	    	List<ProjectKind> projectKindList = JSON.parseArray(string2, ProjectKind.class);
	    	companyDetail.setProjectKindList(projectKindList);
    	}
    	companyDetail.setProjectKinds(null);
    	
    	String string3 = companyDetail.getServerKinds();
    	if(string3 != null){
	    	List<ServerKind> serverKindList = JSON.parseArray(string3, ServerKind.class);
	    	companyDetail.setServerKindList(serverKindList);
    	}
    	companyDetail.setServerKinds(null);
    	
    	String string4 = companyDetail.getSystemKinds();
    	if(string4 != null){
	    	List<SystemKind> systemKindList = JSON.parseArray(string4, SystemKind.class);
	    	companyDetail.setSystemKindList(systemKindList);
    	}
    	companyDetail.setSystemKinds(null);
        return companyDetailService.updateCompanyDetailById(companyDetail);
    }
    
	
    //根据条件查询显示company_detail表中的信息@RequestParam HashMap map
    @RequestMapping(value = "/search")
    public Page searchByCompanyDetail(@ModelAttribute Page page,@ModelAttribute CompanyDetail companyDetail) {
    	return companyDetailService.searchByEntity(page,companyDetail);
    }
    

    
}
