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
	
	//TODO 这是一个bug
	@RequestMapping("/hello1")
	public String hello1(@RequestParam(name="message",required=false) String message){
		return message + "您输入的字符个数："+message.length();
	}
	
	
}
