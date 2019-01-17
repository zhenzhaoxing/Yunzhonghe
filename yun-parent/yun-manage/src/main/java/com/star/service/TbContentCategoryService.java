package com.star.service;

import java.util.List;

import com.Star.pojo.TbContentCategory;
import com.star.commons.pojo.EasyUITree;
import com.star.commons.pojo.EgoResult;

public interface TbContentCategoryService {
    
	
	/**
	 * 查出的类目转换为 easyui
	 */
	List<EasyUITree> selbyPid(Long id);
	/**
	 * 类目新增
	 * @return
	 */
	EgoResult create(TbContentCategory cate);
	
	/**
	 * 类目重命名
	 * @param cate
	 * @return
	 */
	EgoResult update(TbContentCategory cate);
	
	
	/**
	 * 类目删除
	 * @param cate
	 * @return
	 */
	EgoResult delete(TbContentCategory cate);
}
