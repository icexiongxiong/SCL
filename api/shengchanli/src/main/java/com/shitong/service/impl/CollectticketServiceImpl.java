package com.shitong.service.impl;

import javax.servlet.http.HttpSession;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shitong.dao.CollectticketDao;
import com.shitong.entity.Collectticket;
import com.shitong.entity.User;
import com.shitong.service.CollectticketService;
import com.shitong.util.BeanHandler;
import com.shitong.util.HttpContext;
import com.shitong.util.Page;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三 
*/
@Service
public class CollectticketServiceImpl implements CollectticketService {
	@Autowired
	private CollectticketDao collectticketDao;
	
	@Autowired
	private HttpContext httpContext;

	//根据id获取collectticket表中的单条数据
	@Override
	public Page getCollectticketById(int id) {
		Page page = new Page();
		Collectticket collectticket = collectticketDao.findCollectticketById(id);
		if(collectticket == null){
			page.setSuccess(false);
			page.setMessage("数据库中无此数据");
		}else{
			page.setSuccess(true);
			page.setData(collectticket);
		}
		return page;
	}

	//向collectticket表中插入一条记录
	@Override
	public Page saveCollectticket(Collectticket collectticket) {
		Page page = new Page();
		User user = httpContext.getUser();
		collectticket.setUserId(user.getId());
		collectticket.setUsername(user.getUsername());
		collectticket.setOrganizationId(user.getOrganizationid());
		collectticket.setOrganName(user.getOrganizationname());
		collectticketDao.saveCollectticket(collectticket);
		page.setSuccess(true);
		page.setMessage("添加成功");
		return page;
	}
	
	//向collectticket表中插入一条记录返回主键
	@Override
	public Page saveCollectticketReturnPrimaryKey(Collectticket collectticket){
		Page page = new Page();
		User user = httpContext.getUser();
		collectticket.setUserId(user.getId());
		collectticket.setUsername(user.getUsername());
		collectticket.setOrganizationId(user.getOrganizationid());
		collectticket.setOrganName(user.getOrganizationname());
		Integer pkid = collectticketDao.saveCollectticketAndGetAutoIncreaseId(collectticket);
	//	System.out.println(pkid);
		page.setSuccess(true);
		page.setMessage("添加成功");
		return page;
	}

	//根据主键id删除collectticket表中的一条数据
	@Override
	public Page deleteCollectticketById(int id) {
		Page page = new Page();
		collectticketDao.deleteCollectticketById(id);
		page.setSuccess(true);
		page.setMessage("删除成功");
		return page;
	}

	//根据主键id修改collectticket表中的数据
	@Override
	public Page updateCollectticketById(Collectticket collectticket) {
		Page page = new Page();
		collectticketDao.updateCollectticketById(collectticket);
		page.setSuccess(true);
		page.setMessage("修改成功");
		return page;
	}

	//分页显示collectticket表中的数据
	@Override
	public Page getCollectticketPageList(Page page) {
		User user = httpContext.getUser();
		List<Collectticket> list = collectticketDao.getCollectticketList(page.getStart(), page.getLimit(),user);
		page.setTotal(collectticketDao.getCollectticketCount(user));
		page.setRoot(list);
		page.setSuccess(true);
		return page;
	}

	//根据条件分页查询collectticket表中的数据
	@Override
	public Page searchByEntity(Page page, Collectticket collectticket) {
		User user = httpContext.getUser();
		if(BeanHandler.beanIsNotNull(collectticket)){
			List<Collectticket> list = collectticketDao.searchByEntityList(page.getStart(), page.getLimit(),collectticket,user);
			page.setTotal(collectticketDao.searchByEntityCount(collectticket,user));
			page.setRoot(list);
			page.setSuccess(true);
		}else{
			page = this.getCollectticketPageList(page);
		}
		return page;
	}
}
