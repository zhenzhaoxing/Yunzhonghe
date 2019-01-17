package com.Star.dubbo.Impl;

import java.util.List;

import javax.annotation.Resource;

import com.Star.mapper.TbItemParamItemMapper;
import com.Star.pojo.TbItemParamItem;
import com.Star.pojo.TbItemParamItemExample;
import com.star.dubbo.service.TbItemParamItemDubboSerice;

public class TbItemParamItemDubboSericeImpl implements TbItemParamItemDubboSerice {

	
	
	@Resource
	private TbItemParamItemMapper tbItemParamItemMapper;
	
	@Override
	public TbItemParamItem selByItemid(long itemId) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		example.createCriteria().andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
		
	}

}
