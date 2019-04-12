package cn.m.c;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class TestC {

	@RequestMapping("/hello")
	public String hello(){
		return "hello";
	}
	
	//TODO 修复bug
	@RequestMapping("/hello1")
	public String hello1(@RequestParam(name="message",required=false) String message){
		int count = null != message ? message.length() : 0;
		return message + "您输入的字符个数："+ count;
		//return message + "您输入的字符个数："+ message.length();
	}
	
	
}
