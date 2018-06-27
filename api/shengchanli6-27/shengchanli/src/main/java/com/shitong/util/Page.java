package com.shitong.util;

/** 
* @author  半天  
* @version 创建时间：2018年03月28日 14时15分18秒  星期三 
*/

import java.util.List;

public class Page<T> {
    private Integer start;
    private Integer limit;
    private List<T> root; 
    private Integer total;
    private Object cond;
    private Boolean success;
    private T data;
    private String message;
    
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public List<T> getRoot() {
		return root;
	}
	public void setRoot(List<T> root) {
		this.root = root;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Object getCond() {
		return cond;
	}
	public void setCond(Object cond) {
		this.cond = cond;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
