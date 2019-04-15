package cn.m.u;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.m.mo.User;
import cn.m.u.entry.SystemParam;

public class Util {
	
	private static final Logger logger = LoggerFactory.getLogger(Util.class);

	/**
	 * 如果类中一个属性值为空返回false
	 * @param object
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws Exception
	 */
	public static boolean checkParamValue(Object object) throws NoSuchMethodException, SecurityException, Exception{
		
		Class<?> cla = object.getClass();
		Field[] f_s = cla.getDeclaredFields();
		Object obj_value = null;
		if(null != f_s && f_s.length != 0){
			for (Field field : f_s) {
				Method m = (Method) object.getClass().getMethod("get" + getMethodName(field.getName()));  
				obj_value = m.invoke(object);
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
	
	/**
	 * 如果类属性值为空返回对应的空值
	 * @param object
	 * @return
	 */
	public static Object setObjValue(Object object) {
		try {
			Class<?> cla = object.getClass();
			Field[] fields = cla.getDeclaredFields();
			if (null != fields && fields.length != 0) {

				String name;
				String type;
				for (Field field : fields) {

					name = field.getName();
					type = field.getGenericType().toString();

					// 需要把获得到的属性名首字母大写
					name = name.substring(0, 1).toUpperCase() + name.substring(1);
					Method method = cla.getMethod("get" + name);
					if (null == method.invoke(object)) { // 如果属性值为null 那么设置对应的空值

						if ("class java.lang.String".equals(type)) {
							method = cla.getMethod("set" + name, String.class);
							method.invoke(object, "");
						}
						if ("class java.lang.Integer".equals(type)) {
							method = cla.getMethod("set" + name, Integer.class);
							method.invoke(object, 0);
						}
					}
				}
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			logger.error("error", e);
		}
		return object;
	}
	
	private static Map return__(int code,String message){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("errcode", code);
		map.put("errmsg", message);
		return map;
	}
	
	public static Map return_(int code,String message,Object object){
		Map<String, Object> map = return__(code, message);
		map.put("data", object);
		return map;
	}
	

//	public static void main(String[] args) {
//		System.out.println(setObjValue(new User()));
//	}
}
