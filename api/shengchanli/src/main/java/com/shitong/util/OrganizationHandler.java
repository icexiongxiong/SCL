package com.shitong.util;

import com.shitong.entity.User;

/** 
* @author  半天  
* @version 创建时间：2018年3月22日 下午1:08:32  
*/
public class OrganizationHandler {
	public static ConditionHandler returnCommonSql(User user,String sql){
		ConditionHandler ch = new ConditionHandler();
		Boolean flag = user.getLeader();
		String param = "";
		if(!flag){
			param = " where user_id = " + user.getId();
		}else{
			param = " where organization_id in ( " + user.getIds() +" )";
		}
		ch.setSql(sql + param);
		//System.out.println(ch.getSql());
		return ch;
	}
	
	public static ConditionHandler returnEntitySql(User user,String param){
		ConditionHandler ch = new ConditionHandler();
		Boolean flag = user.getLeader();
		String sql = "";
		if(param.trim().length() > 0){
			sql = param + " and ";
		}
		if(!flag){
			sql += " user_id = " + user.getId();
		}else{
			sql += " organization_id in ( " + user.getIds() +" ) ";
		}
		ch.setSql(sql);
		//System.out.println(sql);
		return ch;
	}	
	
	public static ConditionHandler returnEntitySql(User user,ConditionHandler param){
		ConditionHandler ch = new ConditionHandler();
		Boolean flag = user.getLeader();
		//這一塊是條件拼接
		String sql = "";
		String sqlCount = "";
		if(param.getProperty().trim().length() > 0){
			sqlCount = param.getProperty();
			sql = sqlCount + " and ";
		}
		
		if(!flag){
			sql += " user_id = " + user.getId();
		}else{
			sql += " organization_id in ( " + user.getIds() +" ) ";
		}
		ch.setSql(sql);
		ch.setParams(param.getParams());
		ch.setSqlCount(sqlCount);
		//System.out.println(sql);
		return ch;
	}
}
