package com.Star.dubbo.Impl;

import java.util.List;

import javax.annotation.Resource;

import com.Star.mapper.TbItemCatMapper;
import com.Star.pojo.TbItemCat;
import com.Star.pojo.TbItemCatExample;
import com.Star.pojo.TbItemCatExample.Criteria;
import com.star.dubbo.service.TbItemCatDubboService;
/**
 * 这是提供的商品分类管理
 * @author xiang
 *
 */
public class TbItemCatDubboServiceImpl implements TbItemCatDubboService {
@Resource
private TbItemCatMapper tbItemCatMapper;
    /**
 *   
 */
	@Override
	public List<TbItemCat> show(long id) {
		
		TbItemCatExample tbItemCatExample = new TbItemCatExample();
		//where 后面的
		Criteria createCriteria = tbItemCatExample.createCriteria();
		createCriteria.andParentIdEqualTo(id);
		return tbItemCatMapper.selectByExample(tbItemCatExample);
	}
	@Override
	/**
	 *  根据 id查找内容
	 */
	public TbItemCat selById(long id) {
		return tbItemCatMapper.selectByPrimaryKey(id);
	}

}
