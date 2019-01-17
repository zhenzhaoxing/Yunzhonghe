package com.star.service;

import com.Star.pojo.TbItemParam;
import com.star.commons.pojo.EasyUIDataGrid;
import com.star.commons.pojo.EgoResult;

public interface TbItemParamService {
	/**
	 * 分页显示商品规格参数
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid showPage(int page,int rows);
	
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int delete(String ids) throws Exception;
	/**
	 * 根据 itemcatid 找是否有重复的模板
	 */
	
	TbItemParam selByCatid(long catId);
	
	EgoResult save(TbItemParam param);
	
	
}
