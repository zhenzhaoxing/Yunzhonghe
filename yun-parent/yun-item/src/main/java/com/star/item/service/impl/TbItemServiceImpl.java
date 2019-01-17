package com.star.item.service.impl;

import org.springframework.stereotype.Service;

import com.Star.pojo.TbItem;
import com.alibaba.dubbo.config.annotation.Reference;
import com.star.dubbo.service.TbItemDubboService;
import com.star.item.pojo.TbItemChilds;
import com.star.item.service.TbItemService;
@Service
public class TbItemServiceImpl implements TbItemService {

	@Reference
	private TbItemDubboService  tbItemDubboServiceImpl;
	
	
	@Override
	public TbItemChilds show(Long id) {
		TbItem item = tbItemDubboServiceImpl.selbyId(id);
		TbItemChilds child= new TbItemChilds();
		child.setId(item.getId());
		child.setTitle(item.getTitle());
		child.setPrice(item.getPrice());
		child.setSellPoint(item.getSellPoint());
		   child.setImages(item.getImage()!=null&&!item.equals("")?item.getImage().split(","):new String[1]);
		return child;
	}

}
