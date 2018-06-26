package com.shitong.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.shitong.util.Page;

@Component
@Aspect
public class WebLogAspect {

	private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

	ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<Long>();

	@Pointcut("execution(public * com.shitong.controller..*.*(..))")
	public void controllerLog() {}

	@Around("controllerLog()")
	public Page around(ProceedingJoinPoint pjp) {
		String controller = pjp.getSignature().getDeclaringTypeName();
		String method = pjp.getSignature().getName();
		Page response = null;
		try {
			logger.info(controller + " - " + method + "  begin");
			response = (Page) pjp.proceed();
		} catch (Throwable e) {
			logger.info(controller + " - " + method + "  occurs exception:  " + e);
			e.printStackTrace();
		}
		logger.info(controller + " - " + method + "  end");
		return response;
	}
}
