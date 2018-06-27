package com.shitong.service.impl;

import javax.servlet.http.HttpSession;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shitong.dao.AuthenDao;
import com.shitong.entity.Authen;
import com.shitong.entity.User;
import com.shitong.service.AuthenService;
import com.shitong.util.BeanHandler;
import com.shitong.util.HttpContext;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月15日 13时18分08秒  星期二 
*/
@Service
public class AuthenServiceImpl implements AuthenService {
	@Autowired
	private AuthenDao authenDao;
	
	@Autowired
	private HttpContext httpContext;

	//根据id获取authen表中的单条数据
	@Override
	public Page getAuthenById(int id) {
		Page page = new Page();
		Authen authen = authenDao.findAuthenById(id);
		if(authen == null){
			page.setSuccess(false);
			page.setMessage("数据库中无此数据");
		}else{
			page.setSuccess(true);
			page.setData(authen);
		}
		return page;
	}

	//向authen表中插入一条记录
	@Override
	public Page saveAuthen(Authen authen) {
		Page page = new Page();
		User user = httpContext.getUser();
		authen.setUserId(user.getId());
		authen.setUsername(user.getUsername());
		authen.setOrganizationId(user.getOrganizationid());
		authen.setOrganName(user.getOrganizationname());
		authenDao.saveAuthen(authen);
		page.setSuccess(true);
		page.setMessage("添加成功");
		return page;
	}
	
	//向authen表中插入一条记录返回主键
	@Override
	public Page saveAuthenReturnPrimaryKey(Authen authen){
		Page page = new Page();
		User user = httpContext.getUser();
		authen.setUserId(user.getId());
		authen.setUsername(user.getUsername());
		authen.setOrganizationId(user.getOrganizationid());
		authen.setOrganName(user.getOrganizationname());
		Integer pkid = authenDao.saveAuthenAndGetAutoIncreaseId(authen);
	//	System.out.println(pkid);
		page.setSuccess(true);
		page.setMessage("添加成功");
		return page;
	}

	//根据主键id删除authen表中的一条数据
	@Override
	public Page deleteAuthenById(int id) {
		Page page = new Page();
		authenDao.deleteAuthenById(id);
		page.setSuccess(true);
		page.setMessage("删除成功");
		return page;
	}

	//根据主键id修改authen表中的数据
	@Override
	public Page updateAuthenById(Authen authen) {
		Page page = new Page();
		authenDao.updateAuthenById(authen);
		page.setSuccess(true);
		page.setMessage("修改成功");
		return page;
	}

	//分页显示authen表中的数据
	@Override
	public Page getAuthenPageList(Page page) {
		User user = httpContext.getUser();
		List<Authen> list = authenDao.getAuthenList(page.getStart(), page.getLimit(),user);
		page.setTotal(authenDao.getAuthenCount(user));
		page.setRoot(list);
		page.setSuccess(true);
		return page;
	}

	//根据条件分页查询authen表中的数据
	@Override
	public Page searchByEntity(Page page, Authen authen) {
		User user = httpContext.getUser();
		if(BeanHandler.beanIsNotNull(authen)){
			List<Authen> list = authenDao.searchByEntityList(page.getStart(), page.getLimit(),authen,user);
			page.setTotal(authenDao.searchByEntityCount(authen,user));
			page.setRoot(list);
			page.setSuccess(true);
		}else{
			page = this.getAuthenPageList(page);
		}
		return page;
	}
}
