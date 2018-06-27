package com.shitong.interceptor;
/** 
* @author  半天  
* @version 创建时间：2018年3月5日 上午11:35:41  
*/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//@Component
@Configuration
public class ControllerInterceptor implements HandlerInterceptor {

	private static final Logger logger =  Logger.getLogger(ControllerInterceptor.class);


    //在请求处理之前进行调用（Controller方法调用之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String path = request.getServletPath();
        logger.info(path);
        if( path.equals("/login") ){
        	return true;
        } 
        Boolean flag =(Boolean) request.getSession().getAttribute("login");
        if( flag == null ){
        	return false ;    //如果false，停止流程，api被拦截
        }
        return flag;
    	//return true;
    			   
    }

    //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	//logger.info("postHandle被调用");
    }
    

    //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        //logger.info("afterCompletion被调用");
    }
}