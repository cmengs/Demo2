package cn.m.aspect;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 切面
 * @author Administrator
 *
 */

public class RequestAspect_bat {

	private static final Logger logger = LoggerFactory.getLogger(RequestAspect_bat.class);
	
	@Pointcut("execution(* cn.m.c.*.*(..))")
	public void log(){}
	
	@Before("log()")
	public void doBefore(JoinPoint joinPoint){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		String method = request.getMethod();
		if(RequestMethod.GET.equals(method)){
			Enumeration<String> paranNames = request.getParameterNames();
			if(null != paranNames){
				while(paranNames.hasMoreElements()){
					System.out.println(paranNames.nextElement());
				}
			}
		}
	}
	
	@After("log()")
	public void doAfter(JoinPoint joinPoint){
		logger.debug("方法执行完毕！");
	}
	
	@AfterReturning(returning="object",pointcut="log()")
	public void doAfterReturning(Object object){
		logger.debug("response" + object);
	}
}
