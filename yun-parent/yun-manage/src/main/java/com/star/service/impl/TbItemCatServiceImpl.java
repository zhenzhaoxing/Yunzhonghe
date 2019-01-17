package com.star.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Star.pojo.TbItemCat;
import com.alibaba.dubbo.config.annotation.Reference;

import com.star.commons.pojo.EasyUITree;
import com.star.dubbo.service.TbItemCatDubboService;
import com.star.service.TbItemCatService;
@Service
public class TbItemCatServiceImpl implements TbItemCatService{
   
	@Reference
	private  TbItemCatDubboService  tbItemCatDubboService;
	@Override
	public List<EasyUITree> show(long id) {

		List<TbItemCat> show = tbItemCatDubboService.show(id);
		//创建集合来接收 数据
		List<EasyUITree> listtree = new ArrayList<EasyUITree>();
		for (TbItemCat tbItemCat : show) {
			EasyUITree  euiTree =  new EasyUITree();
			euiTree.setId(tbItemCat.getId());
			euiTree.setText(tbItemCat.getName());
			euiTree.setState(tbItemCat.getIsParent()?"closed":"open");
		    listtree.add(euiTree);
		
		}
		return listtree;
	}

}
