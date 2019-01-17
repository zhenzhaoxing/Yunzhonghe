package com.star.item.Controller;

import javax.annotation.Resource;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.star.item.service.TbItemCatService;

@Controller
public class TbItemCatController {

	
	@Resource
	private TbItemCatService tbItemCatServiceImpl;
	/**
	 * 返回jsonp数据格式包含所有菜单信息
	 * @param callback
	 * @return
	 */
	@RequestMapping("rest/itemcat/all")
	@ResponseBody
	public MappingJacksonValue showMenu(String callback){
	
		MappingJacksonValue mj = new MappingJacksonValue(tbItemCatServiceImpl.showCatMenu());
		mj.setJsonpFunction(callback);
		return mj;
	}
	}
