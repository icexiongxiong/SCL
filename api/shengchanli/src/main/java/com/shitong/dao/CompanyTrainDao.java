package com.shitong.dao;

import java.util.List;

import com.shitong.entity.CompanyTrain;
import com.shitong.entity.User;

/** 
* @author  半天  
* @version 创建时间：2018年05月09日 17时20分03秒  星期三   
*/
public interface CompanyTrainDao {

	//向company_train表插入数据，成功返回1
	Integer saveCompanyTrain(CompanyTrain companyTrain);
	
	//向company_train表插入数据，成功返回新插入数据的主键
	Integer saveCompanyTrainAndGetAutoIncreaseId(CompanyTrain companyTrain) ;

	//根据company_train表的主键删除数据
	void deleteCompanyTrainById(int id);

	//根据主键修改company_train表的内容
	void updateCompanyTrainById(CompanyTrain companyTrain);

	//批量向company_train表插入数据
	void saveCompanyTrainList(List<CompanyTrain> list);

	//分页显示company_train表中的内容，其中start为数据开始位置，limit每页条数
	List<CompanyTrain> getCompanyTrainList(Integer start, Integer limit ,User user);

	//获取company_train表中记录的条数
	Integer getCompanyTrainCount(User user);

	//根据主键id从company_train表中查找一条数据
	CompanyTrain findCompanyTrainById(int id);

	//根据条件分页查询数据并以List的形式返回给业务层
	List<CompanyTrain> searchByEntityList(Integer start, Integer limit, CompanyTrain companyTrain ,User user);

	//根据条件分页查询company_train表中记录的条数
	Integer searchByEntityCount(CompanyTrain companyTrain ,User user);

}
