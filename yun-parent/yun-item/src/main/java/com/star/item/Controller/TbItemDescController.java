package com.star.item.Controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.star.item.service.TbItemdescService;


@Controller
public class TbItemDescController {

	@Resource
	private TbItemdescService tbItemdescServiceImpl;
	@RequestMapping(value="item/desc/{id}.html",produces="text/html;charset=utf-8")
	@ResponseBody
	public String showDesc(@PathVariable long id) {
		return tbItemdescServiceImpl.showDesc(id);
	}
	
	
	
	
}
