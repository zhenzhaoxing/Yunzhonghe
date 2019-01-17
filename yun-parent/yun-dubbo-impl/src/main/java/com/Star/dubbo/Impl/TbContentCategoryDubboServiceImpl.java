package com.Star.dubbo.Impl;

import java.util.List;

import javax.annotation.Resource;

import com.Star.mapper.TbContentCategoryMapper;
import com.Star.pojo.TbContentCategory;
import com.Star.pojo.TbContentCategoryExample;
import com.star.dubbo.service.TbContentCategoryDubboService;
/**
 * 这是提供的内容分类管理
 * @author xiang
 *
 */
public class TbContentCategoryDubboServiceImpl implements TbContentCategoryDubboService {

	@Resource
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List<TbContentCategory> selbyPiD(Long id) {//根据父菜单查找
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		example.createCriteria().andParentIdEqualTo(id).andStatusEqualTo(1);
		
		return tbContentCategoryMapper.selectByExample(example);
	}

	@Override
	public int insTbContentCategory(TbContentCategory cate) {
		// TODO Auto-generated method stub
		return tbContentCategoryMapper.insertSelective(cate);
	}

	@Override
	public int updIsParentById(TbContentCategory cate) {
		// TODO Auto-generated method stub
		return tbContentCategoryMapper.updateByPrimaryKeySelective(cate);
	}

	@Override
	public int deleteParentById(TbContentCategory cate) {
	  return	tbContentCategoryMapper.updateByPrimaryKey(cate);
	
	}

	//根据主id来查找信息
	public TbContentCategory selById(long id) {
		return tbContentCategoryMapper.selectByPrimaryKey(id);
	}

}
