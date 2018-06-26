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

import com.shitong.entity.Collectticket;
import com.shitong.service.CollectticketService;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三 
*/
@RequestMapping("/collectticket")
@RestController
public class CollectticketController {
	@Autowired
	private CollectticketService collectticketService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
    	dateFormat.setLenient(false);  
    	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
    //根据id获取collectticket表单条信息
    @RequestMapping(value = "/{id}")
    public Page getCollectticket(@PathVariable int id)    {
        return collectticketService.getCollectticketById(id);
    }
	
	//向collectticket表添加一条信息
    @RequestMapping(value = "/add")
    public Page addCollectticket(HttpSession session,@ModelAttribute Collectticket collectticket)    {
        return collectticketService.saveCollectticketReturnPrimaryKey(collectticket);
    }  
    
    //根据collectticket表的id主键删除信息
    @RequestMapping(value = "/delete/{id}")
    public Page deleteCollectticket(@PathVariable int id)    {
        return collectticketService.deleteCollectticketById(id);
    }  
    
    //根据id主键修改collectticket表信息(id必须有)
    @RequestMapping(value = "/update")
    public Page updateCollectticket(@ModelAttribute Collectticket collectticket)    {
        return collectticketService.updateCollectticketById(collectticket);
    }
    
	//分页显示collectticket表中的信息
	@RequestMapping(value = "/page")
	public Page page(@ModelAttribute Page page,HttpSession session) throws Exception {
		//page.setCond(session);
		return collectticketService.getCollectticketPageList(page);
	}
	
    //根据条件查询显示collectticket表中的信息@RequestParam HashMap map
    @RequestMapping(value = "/search")
    public Page searchByCollectticket(@ModelAttribute Page page,@ModelAttribute Collectticket collectticket,HttpSession session) {
		//page.setCond(session);
    	return collectticketService.searchByEntity(page,collectticket);
    }
    
}
