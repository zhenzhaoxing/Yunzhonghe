package com.star.item.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.star.dubbo.service.TbItemDescDubboService;

import com.star.item.service.TbItemdescService;
@Service
public class TbItemdescServiceImpl implements TbItemdescService {

	
	@Reference
	private TbItemDescDubboService tbItemDescDubboServiceImpl;
	@Override
	public String showDesc(long itemId) {
		return tbItemDescDubboServiceImpl.selbyid(itemId).getItemDesc();
	}

}
