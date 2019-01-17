package com.star.dubbo.service;

import java.util.List;

import com.Star.pojo.TbContentCategory;

public interface TbContentCategoryDubboService {

	
	/**
	 * 根据id查出所有子类项目
	 */
	
	List<TbContentCategory> selbyPiD(Long id);
	
	/***
	 * 新增操作
	 * @param cate
	 * @return
	 */
	int insTbContentCategory(TbContentCategory cate);
	/**
	 * 修改isparent通过id
	 * @param id
	 * @param isParent
	 * @return
	 */
	int updIsParentById(TbContentCategory cate);
	
	
	/**
	 * 删除通过id
	 * @param id
	 * @param isParent
	 * @return
	 */
	int deleteParentById(TbContentCategory cate);
	
	/**
	 * 通过id查询内容类目详细信息
	 */
	TbContentCategory selById(long id);
}
