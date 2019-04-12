package cn.m.aspect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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

	@Around("execution(* cn.m.c.*.*(..))")
	public Object checkRequestParam(ProceedingJoinPoint pjd) throws Throwable{
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		
		//获得请求参数
		Map<String, String[]> paramMap = request.getParameterMap();
		if(null != paramMap && paramMap.size() != 0){
			
			String paramValue = null;
			try{
				paramValue = paramMap.get("data")[0]; //获得data值
			}catch(NullPointerException e){return "非法请求!";}
			
			if(null != paramValue){
				paramValue = Des3Util.decode(paramValue); //通过解密获得json参数
				if(null != paramValue){
					SystemParam sysParam = JSONObject.parseObject(paramValue,SystemParam.class);
					if(!checkParamValue(sysParam)){
						return "请求参数缺失!";
					}else{
						if("/login".equals(request.getRequestURI())){
							return pjd.proceed(new Object[]{sysParam});
						}
					}
				}
			}
			return "请求参数处理异常!";
		}
		return pjd.proceed();
	}
	
	private boolean checkParamValue(SystemParam sysParam) throws NoSuchMethodException, SecurityException, Exception{
		
		Class<?> cla = sysParam.getClass();
		Field[] f_s = cla.getDeclaredFields();
		Object obj_value = null;
		if(null != f_s && f_s.length != 0){
			for (Field field : f_s) {
				Method m = (Method) sysParam.getClass().getMethod("get" + getMethodName(field.getName()));  
				obj_value = m.invoke(sysParam);
				if(null == obj_value) return false;
			}
		}else{
			return false;
		}
		return true;
	}
	
	// 把一个字符串的第一个字母大写、效率是最高的、  
	private static String getMethodName(String fildeName) throws Exception {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}
}
