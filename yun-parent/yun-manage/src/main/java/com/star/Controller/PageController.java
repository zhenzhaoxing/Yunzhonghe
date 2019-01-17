package com.star.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.star.commons.pojo.EasyUIDataGrid;
import com.star.service.PageShowService;

@Controller
public class PageController {

	
	
	@RequestMapping("/")
	public String welcome() {
		return "index";	
	}
	
	@RequestMapping("{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
	
	
	
		
		
	}
	
	
	
	
	

