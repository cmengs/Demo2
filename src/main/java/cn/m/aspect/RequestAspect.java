package cn.m.aspect;

import java.lang.reflect.Field;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

import cn.m.u.Des3Util;
import cn.m.u.entry.SystemParam;

/**
 * 切面
 * @author Administrator
 *
 */
@Aspect
@Component
public class RequestAspect {

	private static final Logger logger = LoggerFactory.getLogger(RequestAspect.class);
	
	@Around("execution(* cn.m.c.*.*(..))")
	public Object checkRequestParam(ProceedingJoinPoint pjd) throws Throwable{
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		
		//获得请求参数
		Map<String, String[]> paramMap = request.getParameterMap();
		if(null != paramMap && paramMap.size() != 0){
			try{
				String value = paramMap.get("data")[0];
				if(null != value){
					value = Des3Util.decode(value); //通过解密获得json参数
					
					SystemParam sysParam = JSONObject.parseObject(value,SystemParam.class);
					Class<?> cla = sysParam.getClass();
					Field[] f_s = cla.getFields();
					for (Field field : f_s) {
						System.out.println(field.get(sysParam));
					}
				}
			}catch(NullPointerException e){
				
			}
		}
		
		return "缺失请求参数!";
	}
}
