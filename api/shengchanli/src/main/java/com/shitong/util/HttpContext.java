package com.shitong.util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.shitong.entity.User;

@Component
public class HttpContext {
	public HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	public HttpSession getHttpSession() {
		return this.getHttpServletRequest().getSession();
	}
	
	public User getUser() {
		return (User) this.getHttpSession().getAttribute("user");
	}
}