package com.shitong.service.impl;

import javax.servlet.http.HttpSession;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shitong.dao.UseticketDao;
import com.shitong.entity.Useticket;
import com.shitong.entity.User;
import com.shitong.service.UseticketService;
import com.shitong.util.BeanHandler;
import com.shitong.util.HttpContext;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三 
*/
@Service
public class UseticketServiceImpl implements UseticketService {
	@Autowired
	private UseticketDao useticketDao;
	
	@Autowired
	private HttpContext httpContext;

	//根据id获取useticket表中的单条数据
	@Override
	public Page getUseticketById(int id) {
		Page page = new Page();
		Useticket useticket = useticketDao.findUseticketById(id);
		if(useticket == null){
			page.setSuccess(false);
			page.setMessage("数据库中无此数据");
		}else{
			page.setSuccess(true);
			page.setData(useticket);
		}
		return page;
	}

	//向useticket表中插入一条记录
	@Override
	public Page saveUseticket(Useticket useticket) {
		Page page = new Page();
		User user = httpContext.getUser();
		useticket.setUserId(user.getId());
		useticket.setUsername(user.getUsername());
		useticket.setOrganizationId(user.getOrganizationid());
		useticket.setOrganName(user.getOrganizationname());
		useticketDao.saveUseticket(useticket);
		page.setSuccess(true);
		page.setMessage("添加成功");
		return page;
	}
	
	//向useticket表中插入一条记录返回主键
	@Override
	public Page saveUseticketReturnPrimaryKey(Useticket useticket){
		Page page = new Page();
		User user = httpContext.getUser();
		useticket.setUserId(user.getId());
		useticket.setUsername(user.getUsername());
		useticket.setOrganizationId(user.getOrganizationid());
		useticket.setOrganName(user.getOrganizationname());
		Integer pkid = useticketDao.saveUseticketAndGetAutoIncreaseId(useticket);
	//	System.out.println(pkid);
		page.setSuccess(true);
		page.setMessage("添加成功");
		return page;
	}

	//根据主键id删除useticket表中的一条数据
	@Override
	public Page deleteUseticketById(int id) {
		Page page = new Page();
		useticketDao.deleteUseticketById(id);
		page.setSuccess(true);
		page.setMessage("删除成功");
		return page;
	}

	//根据主键id修改useticket表中的数据
	@Override
	public Page updateUseticketById(Useticket useticket) {
		Page page = new Page();
		useticketDao.updateUseticketById(useticket);
		page.setSuccess(true);
		page.setMessage("修改成功");
		return page;
	}

	//分页显示useticket表中的数据
	@Override
	public Page getUseticketPageList(Page page) {
		User user = httpContext.getUser();
		List<Useticket> list = useticketDao.getUseticketList(page.getStart(), page.getLimit(),user);
		page.setTotal(useticketDao.getUseticketCount(user));
		page.setRoot(list);
		page.setSuccess(true);
		return page;
	}

	//根据条件分页查询useticket表中的数据
	@Override
	public Page searchByEntity(Page page, Useticket useticket) {
		User user = httpContext.getUser();
		if(BeanHandler.beanIsNotNull(useticket)){
			List<Useticket> list = useticketDao.searchByEntityList(page.getStart(), page.getLimit(),useticket,user);
			page.setTotal(useticketDao.searchByEntityCount(useticket,user));
			page.setRoot(list);
			page.setSuccess(true);
		}else{
			page = this.getUseticketPageList(page);
		}
		return page;
	}
}
