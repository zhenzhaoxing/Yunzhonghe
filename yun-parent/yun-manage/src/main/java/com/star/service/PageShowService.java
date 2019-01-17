package com.star.service;

import com.Star.pojo.TbItem;
import com.star.commons.pojo.EasyUIDataGrid;

public interface PageShowService  {

	
	
	
	/**
	 *  显示商品
	 * @param page
	 * @param rows
	 * @return
	 */
	  EasyUIDataGrid show(int page,int rows);
	/**
	 *  修改状态
	 * @param ids
	 * @param status
	 * @return
	 */
	  int   updItemStatus(String ids,byte status);
	  
	  
	  
	  
	  
	  
	  
	  
	  /**
	   *   增加商品包括描述
	   * @param item
	   * @param desc
	   * @return
	 * @throws Exception 
	   */
	  int  save(TbItem item,String desc,String itemParams) throws Exception;
	  
	  
	  
}
