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

import com.shitong.entity.CompanyTrain;
import com.shitong.service.CompanyTrainService;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三 
*/
@RequestMapping("/companytrain")
@RestController
public class CompanyTrainController {
	@Autowired
	private CompanyTrainService companyTrainService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
    	dateFormat.setLenient(false);  
    	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
    //根据id获取company_train表单条信息
    @RequestMapping(value = "/{id}")
    public Page getCompanyTrain(@PathVariable int id)    {
        return companyTrainService.getCompanyTrainById(id);
    }
	
	//向company_train表添加一条信息
    @RequestMapping(value = "/add")
    public Page addCompanyTrain(HttpSession session,@ModelAttribute CompanyTrain companyTrain)    {
        return companyTrainService.saveCompanyTrainReturnPrimaryKey(companyTrain);
    }  
    
    //根据company_train表的id主键删除信息
    @RequestMapping(value = "/delete/{id}")
    public Page deleteCompanyTrain(@PathVariable int id)    {
        return companyTrainService.deleteCompanyTrainById(id);
    }  
    
    //根据id主键修改company_train表信息(id必须有)
    @RequestMapping(value = "/update")
    public Page updateCompanyTrain(@ModelAttribute CompanyTrain companyTrain)    {
        return companyTrainService.updateCompanyTrainById(companyTrain);
    }
    
	//分页显示company_train表中的信息
	@RequestMapping(value = "/page")
	public Page page(@ModelAttribute Page page,HttpSession session) throws Exception {
		//page.setCond(session);
		return companyTrainService.getCompanyTrainPageList(page);
	}
	
    //根据条件查询显示company_train表中的信息@RequestParam HashMap map
    @RequestMapping(value = "/search")
    public Page searchByCompanyTrain(@ModelAttribute Page page,@ModelAttribute CompanyTrain companyTrain,HttpSession session) {
		//page.setCond(session);
    	return companyTrainService.searchByEntity(page,companyTrain);
    }
    
}
