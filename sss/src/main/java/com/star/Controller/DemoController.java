package com.star.Controller;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.star.pojo.Student;

@Controller
public class DemoController {

	
	
	@RequestMapping("demo2")
	@ResponseBody
	public MappingJacksonValue demo(String callback) {
		Student s = new Student();
		s.setAge(17);
		s.setName("蜘蛛侠");
		//把构造函数方法转换为json字符串
		MappingJacksonValue mj = new MappingJacksonValue(s);
		//最终返会结果的函数名
		mj.setJsonpFunction(callback);
	    System.out.println(mj);
		return mj;
		
	}
	
	@RequestMapping("demo3")
	@ResponseBody
	public String demo3(String name) {
		
		
		
		return "this"+name;
		
	}
	
	
}
