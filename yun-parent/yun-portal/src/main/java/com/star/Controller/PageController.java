package com.star.Controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.star.service.TbContentService;

@Controller
public class PageController {

	@RequestMapping("/")
	public String welcome(){
		
		return "forward:/showBigPic";
	}
	
}

