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

import com.shitong.entity.CompanyField;
import com.shitong.service.CompanyFieldService;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年03月24日 22时39分03秒  星期六 
*/
@RequestMapping("/companyfield")
@RestController
public class CompanyFieldController {
	@Autowired
	private CompanyFieldService companyFieldService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
    	dateFormat.setLenient(false);  
    	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
    //根据id获取单条信息
    @RequestMapping(value = "/{id}")
    public Page getCompanyField(@PathVariable int id)    {
        return companyFieldService.getCompanyFieldById(id);
    }
	
    @RequestMapping(value = "/add")
    public Page addCompanyField(HttpSession session,@ModelAttribute CompanyField companyField)    {
    /*	User user = (User) session.getAttribute("user");
    	companyField.setUserId(user.getId());
    	companyField.setOrganizationId(user.getOrganizationid());*/
        return companyFieldService.saveCompanyField(companyField);
    }  
    
    //根据id主键删除信息
    @RequestMapping(value = "/delete/{id}")
    public Page deleteCompanyField(@PathVariable int id)    {
        return companyFieldService.deleteCompanyFieldById(id);
    }  
    
    //修改信息(id必须有)
    @RequestMapping(value = "/update")
    public Page updateCompanyField(@ModelAttribute CompanyField companyField)    {
        return companyFieldService.updateCompanyFieldById(companyField);
    }
    
	// 分页查询
	@RequestMapping(value = "/page")
	public Page page(@ModelAttribute Page page,HttpSession session) throws Exception {
		//page.setCond(session);
		return companyFieldService.getCompanyFieldPageList(page);
	}
	
    //根据条件查询@RequestParam HashMap map
    @RequestMapping(value = "/search")
    public Page searchByCompanyField(@ModelAttribute Page page,@ModelAttribute CompanyField companyField,HttpSession session) {
		//page.setCond(session);
    	return companyFieldService.searchByEntity(page,companyField);
    }
    
}
