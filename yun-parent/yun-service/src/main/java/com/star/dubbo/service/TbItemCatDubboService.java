package com.star.dubbo.service;

import java.util.List;

import com.Star.pojo.TbItemCat;

public interface TbItemCatDubboService {

	
	
	/**
	 *   根据 父id=0 查询出所有分类列表
	 * @param i
	 * @return
	 */
	List<TbItemCat>   show(long i);
	
	/**
	 * 根据类目id查询
	 * @param id
	 * @return
	 */
	TbItemCat selById(long id);
	       
	
}
