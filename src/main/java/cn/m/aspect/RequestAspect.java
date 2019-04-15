package cn.m.aspect;

import java.lang.reflect.Field;
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
import cn.m.u.Util;
import cn.m.u.entry.SystemParam;

/**
 * 切面
 * @author Administrator
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
			}catch(NullPointerException e){return "非法请求!";} //data是加密后的参数,没有data参数属于非法请求
			
			if(null != paramValue){
				paramValue = Des3Util.decode(paramValue); //通过解密获得json参数
				if(null != paramValue){
					
					JSONObject jO = JSONObject.parseObject(paramValue); //解析全部参数
					SystemParam sysParam = JSONObject.parseObject(paramValue,SystemParam.class); //解析系统级参数
					
					if(!Util.checkParamValue(sysParam)){ //验证系统级参数是否正确传入
						return "请求参数缺失!";
					}else{
						//循环遍历系统级参数
						Field[] fields = sysParam.getClass().getDeclaredFields();
						if(null != fields && fields.length != 0){
							for (Field field : fields) {
								jO.remove(field.getName()); //把系统级参数从全部参数里删除，剩下的就是业务参数
							}
							return pjd.proceed(new Object[]{jO.toJSONString()}); //返回业务参数
						}
					}
				}
			}
			return "请求参数处理异常!";
		}
		return pjd.proceed();
	}
}
