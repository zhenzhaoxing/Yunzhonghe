package com.star.Controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.star.commons.pojo.EasyUITree;
import com.star.service.impl.TbItemCatServiceImpl;

@Controller
public class TbItemCatController {

	
	@Resource
	private   TbItemCatServiceImpl tbItemCatServiceImpl;
	
	
	
	
	@RequestMapping("item/cat/list")
	@ResponseBody
	public List<EasyUITree>  showList(@RequestParam(defaultValue="0")long id){
		
		
		return tbItemCatServiceImpl.show(id);
	}
	
	
	
}
