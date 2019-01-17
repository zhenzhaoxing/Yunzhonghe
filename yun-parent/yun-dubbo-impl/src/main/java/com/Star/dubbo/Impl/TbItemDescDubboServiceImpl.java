package com.Star.dubbo.Impl;

import javax.annotation.Resource;

import com.Star.mapper.TbItemDescMapper;
import com.Star.mapper.TbItemMapper;
import com.Star.pojo.TbItem;
import com.Star.pojo.TbItemDesc;
import com.Star.pojo.TbItemExample;
import com.star.dubbo.service.TbItemDescDubboService;

public class TbItemDescDubboServiceImpl implements TbItemDescDubboService {
  
	@Resource
	 private TbItemDescMapper  tbItemDescMapper ;
	//查找出商品的详细
	@Override
	public TbItemDesc selbyid(Long id) {
	
	 TbItemDesc tbItem = tbItemDescMapper.selectByPrimaryKey(id);
	 
		return tbItem;
	}

}
