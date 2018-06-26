package com.shitong.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shitong.dao.InnovatemessageDao;
import com.shitong.entity.Innovatemessage;
import com.shitong.service.InnovatemessageService;
import com.shitong.util.BeanHandler;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月07日 15时35分29秒  星期一 
*/
@Service
public class InnovatemessageServiceImpl implements InnovatemessageService {
	@Autowired
	private InnovatemessageDao innovatemessageDao;

	//根据id获取innovatemessage表中的单条数据
	@Override
	public Page getInnovatemessageById(int id) {
		Page page = new Page();
		Innovatemessage innovatemessage = innovatemessageDao.findInnovatemessageById(id);
		if(innovatemessage == null){
			page.setSuccess(false);
			page.setMessage("数据库中无此数据");
		}else{
			page.setSuccess(true);
			page.setData(innovatemessage);
		}
		return page;
	}

	//向innovatemessage表中插入一条记录
	@Override
	public Page saveInnovatemessage(Innovatemessage innovatemessage) {
		Page page = new Page();
		innovatemessageDao.saveInnovatemessage(innovatemessage);
		page.setSuccess(true);
		page.setMessage("添加成功");
		return page;
	}
	
	//向innovatemessage表中插入一条记录返回主键
	@Override
	public Page saveInnovatemessageReturnPrimaryKey(Innovatemessage innovatemessage){
		Page page = new Page();
		Integer pkid = innovatemessageDao.saveInnovatemessageAndGetAutoIncreaseId(innovatemessage);
	//	System.out.println(pkid);
		page.setSuccess(true);
		page.setMessage("添加成功");
		return page;
	}

	//根据主键id删除innovatemessage表中的一条数据
	@Override
	public Page deleteInnovatemessageById(int id) {
		Page page = new Page();
		innovatemessageDao.deleteInnovatemessageById(id);
		page.setSuccess(true);
		page.setMessage("删除成功");
		return page;
	}

	//根据主键id修改innovatemessage表中的数据
	@Override
	public Page updateInnovatemessageById(Innovatemessage innovatemessage) {
		Page page = new Page();
		innovatemessageDao.updateInnovatemessageById(innovatemessage);
		page.setSuccess(true);
		page.setMessage("修改成功");
		return page;
	}

	//分页显示innovatemessage表中的数据
	@Override
	public Page getInnovatemessagePageList(Page page) {
		List<Innovatemessage> list = innovatemessageDao.getInnovatemessageList(page.getStart(), page.getLimit());
		page.setTotal(innovatemessageDao.getInnovatemessageCount());
		page.setRoot(list);
		page.setSuccess(true);
		return page;
	}

	//根据条件分页查询innovatemessage表中的数据
	@Override
	public Page searchByEntity(Page page, Innovatemessage innovatemessage) {
		if(BeanHandler.beanIsNotNull(innovatemessage)){
			List<Innovatemessage> list = innovatemessageDao.searchByEntityList(page.getStart(), page.getLimit(),innovatemessage);
			page.setTotal(innovatemessageDao.searchByEntityCount(innovatemessage));
			page.setRoot(list);
			page.setSuccess(true);
		}else{
			page = this.getInnovatemessagePageList(page);
		}
		return page;
	}
}
