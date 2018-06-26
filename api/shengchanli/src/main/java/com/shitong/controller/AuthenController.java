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

import com.shitong.entity.Authen;
import com.shitong.service.AuthenService;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月15日 13时18分08秒  星期二 
*/
@RequestMapping("/authen")
@RestController
public class AuthenController {
	@Autowired
	private AuthenService authenService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
    	dateFormat.setLenient(false);  
    	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
    //根据id获取authen表单条信息
    @RequestMapping(value = "/{id}")
    public Page getAuthen(@PathVariable int id)    {
        return authenService.getAuthenById(id);
    }
	
	//向authen表添加一条信息
    @RequestMapping(value = "/add")
    public Page addAuthen(HttpSession session,@ModelAttribute Authen authen)    {
        return authenService.saveAuthenReturnPrimaryKey(authen);
    }  
    
    //根据authen表的id主键删除信息
    @RequestMapping(value = "/delete/{id}")
    public Page deleteAuthen(@PathVariable int id)    {
        return authenService.deleteAuthenById(id);
    }  
    
    //根据id主键修改authen表信息(id必须有)
    @RequestMapping(value = "/update")
    public Page updateAuthen(@ModelAttribute Authen authen)    {
        return authenService.updateAuthenById(authen);
    }
    
	//分页显示authen表中的信息
	@RequestMapping(value = "/page")
	public Page page(@ModelAttribute Page page,HttpSession session) throws Exception {
		//page.setCond(session);
		return authenService.getAuthenPageList(page);
	}
	
    //根据条件查询显示authen表中的信息@RequestParam HashMap map
    @RequestMapping(value = "/search")
    public Page searchByAuthen(@ModelAttribute Page page,@ModelAttribute Authen authen,HttpSession session) {
		//page.setCond(session);
    	return authenService.searchByEntity(page,authen);
    }
    
}
