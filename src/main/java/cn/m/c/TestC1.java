package cn.m.c;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.m.mo.User;
import cn.m.u.Des3Util;
import cn.m.u.Util;
import cn.m.u.page.Page;

@Controller
@ResponseBody
public class TestC1 {
	
	@Autowired
	private HttpServletResponse response;
	
	@RequestMapping("/getData")
	public String getData(String json){
		JSONObject jO = JSONObject.parseObject(json);
		Object o =  jO.get("method");
		if(null != o){
			if("1".equals(o.toString())){
				return this.getData1();
			}
			if("2".equals(o.toString())){
				return this.getData2();
			}
		}
		return JSONObject.toJSONString(Util.return_(200, "", null));
	}
	
	private String getData1(){
		Map map = new HashMap<String,String>();
		map.put("info", "执行Data1方法");
		return JSONObject.toJSONString(Util.return_(200, "", map));
	}
	
	private String getData2(){
		Map map = new HashMap<String,String>();
		map.put("info", "执行Data2方法");
		return JSONObject.toJSONString(Util.return_(200, "", map));
	}
	
	
	
	
	
	

	@RequestMapping("/seleUser")
	public String seleUser(String json) throws Exception{
		//设置响应头
		response.setHeader("accept", "application/json");
		
		User user = null;
		String message = "";
		String id = (String) JSONObject.parseObject(json).get("id");
		
		if(!"default_".equals(id)){
			if("1".equals(id)) {
				user = (User) Util.setObjValue(new User("1", "张三", null, false));
			}
		}else{
			message = "没有完整的参数!";
		}
		if(null == user && message == "") message = "没有查询到指定信息!";
		String oJson = JSONObject.toJSONString(Util.return_(200, message, user));
		return "加密前："+oJson + "加密后："+Des3Util.encode(oJson);
	}
	
	@RequestMapping("/seleUsers")
	public String seleUsers(String json) throws Exception{
		//设置响应头
		response.setHeader("accept", "application/json");
		
		List list = Arrays.asList(
				Util.setObjValue(new User("1", "张三", null, true)),
				Util.setObjValue(new User("2", "李四", null, true)),
				Util.setObjValue(new User("3", "王五", null, true)));
		
		Map map = new HashMap<>();
		map.put("page", new Page(3, 1, 1, 1));
		map.put("list", list);
		
		String oJson = JSONObject.toJSONString(Util.return_(200, "", map));
		return "加密前："+oJson + "加密后："+Des3Util.encode(oJson) + "请求参数："+json;
	}
}
