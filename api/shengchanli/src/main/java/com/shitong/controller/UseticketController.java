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

import com.shitong.entity.Useticket;
import com.shitong.service.UseticketService;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三 
*/
@RequestMapping("/useticket")
@RestController
public class UseticketController {
	@Autowired
	private UseticketService useticketService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
    	dateFormat.setLenient(false);  
    	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
    //根据id获取useticket表单条信息
    @RequestMapping(value = "/{id}")
    public Page getUseticket(@PathVariable int id)    {
        return useticketService.getUseticketById(id);
    }
	
	//向useticket表添加一条信息
    @RequestMapping(value = "/add")
    public Page addUseticket(HttpSession session,@ModelAttribute Useticket useticket)    {
        return useticketService.saveUseticketReturnPrimaryKey(useticket);
    }  
    
    //根据useticket表的id主键删除信息
    @RequestMapping(value = "/delete/{id}")
    public Page deleteUseticket(@PathVariable int id)    {
        return useticketService.deleteUseticketById(id);
    }  
    
    //根据id主键修改useticket表信息(id必须有)
    @RequestMapping(value = "/update")
    public Page updateUseticket(@ModelAttribute Useticket useticket)    {
        return useticketService.updateUseticketById(useticket);
    }
    
	//分页显示useticket表中的信息
	@RequestMapping(value = "/page")
	public Page page(@ModelAttribute Page page,HttpSession session) throws Exception {
		//page.setCond(session);
		return useticketService.getUseticketPageList(page);
	}
	
    //根据条件查询显示useticket表中的信息@RequestParam HashMap map
    @RequestMapping(value = "/search")
    public Page searchByUseticket(@ModelAttribute Page page,@ModelAttribute Useticket useticket,HttpSession session) {
		//page.setCond(session);
    	return useticketService.searchByEntity(page,useticket);
    }
    
}
