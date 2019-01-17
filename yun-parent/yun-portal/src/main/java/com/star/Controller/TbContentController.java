package com.star.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.star.service.TbContentService;

@Controller
public class TbContentController {

	
	@Resource
	private TbContentService tbContentServiceImpl;
	
	@RequestMapping("showBigPic")
	public String showBigPic(Model model){
		model.addAttribute("ad1", tbContentServiceImpl.showBigPic());
		return "index";
	}
}

