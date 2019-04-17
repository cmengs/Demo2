package cn.m.c;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import cn.m.u.Util;


@Controller
public class TestC3 {

	@RequestMapping("/getData")
	public String getData(String json) 
			throws NoSuchMethodException, SecurityException, 
				IllegalAccessException, IllegalArgumentException, InvocationTargetException, 
				InstantiationException, ClassNotFoundException, NoSuchFieldException{
		
		JSONObject jO = JSONObject.parseObject(json);
		Object o =  jO.get("method");
		if(null != o){

			String classPath = TestC1.class.toGenericString(); //获得类全路径
			classPath = classPath.substring(classPath.indexOf("class")+5+1, 
					classPath.length()); //indexOf获取到class c的位置，向后截取的话需要加上class本身字符长度和中间的1个空格
			
			Class<?> clazz = Class.forName(classPath);
			
			//获得类属性
			Field field = clazz.getDeclaredField("message");
			String message = (String) field.get(clazz);
			
			//调用类方法
			Method m = clazz.getDeclaredMethod("getData"+1, String.class); 
//			Method m = clazz.getMethod("getData"+1, String.class);
			return (String) m.invoke(clazz.newInstance(), "这是反射传参,message:"+message);
		}
		return JSONObject.toJSONString(Util.return_(200, "", null));
	}
	
	private String getData1(String param){
		Map map = new HashMap<String,String>();
		map.put("info", "执行Data1方法,param:"+param);
		return JSONObject.toJSONString(Util.return_(200, "", map));
	}
	
	private String getData2(String param){
		Map map = new HashMap<String,String>();
		map.put("info", "执行Data2方法,param:"+param);
		return JSONObject.toJSONString(Util.return_(200, "", map));
	}
}
