package com.shitong.util;

/** 
* @author  半天  
* @version 创建时间：2018年03月28日 14时15分18秒  星期三 
*/

public class ConditionHandler {

	private String property;
	private String condition;
	private Object[] params;
	private String sql;
	private String sqlCount;
	public String getSqlCount() {
		return sqlCount;
	}
	public void setSqlCount(String sqlCount) {
		this.sqlCount = sqlCount;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
}
