package com.star.service;

import java.util.List;

import com.star.commons.pojo.EasyUITree;

public interface TbItemCatService {
      
	/**
	 * 根据 id找分类
	 * @param id
	 * @return
	 */
	
	List<EasyUITree>  show(long id);
}
