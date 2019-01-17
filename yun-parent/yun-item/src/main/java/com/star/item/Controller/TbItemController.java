package com.star.item.Controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.star.item.service.TbItemService;

@Controller
public class TbItemController {
   @Resource
	private  TbItemService  tbItemServiceImpl;
	
	
	/**
	 * 显示商品详情
	 */
	
	@RequestMapping("item/{id}.html")
	public String showItemDetails(@PathVariable long id,Model model){
		model.addAttribute("item", tbItemServiceImpl.show(id));
		return "item";
	}
	
}
