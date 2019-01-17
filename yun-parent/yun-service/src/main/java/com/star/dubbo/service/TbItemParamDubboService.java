package com.star.dubbo.service;

import com.Star.pojo.TbItemParam;
import com.star.commons.pojo.EasyUIDataGrid;

public interface TbItemParamDubboService {
	/**
	 * 分页查询数据,
	 * @param page
	 * @param rows
	 * @return 包含:当前也显示数据和总条数
	 */
	EasyUIDataGrid showPage(int page,int rows);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 * @throws Exception 
	 */
	int del(String ids) throws Exception;
	/**
	 * 根据类目id查询参数模板
	 * @param catId
	 * @return
	 */
	TbItemParam selByCatid(long catId);
	
	/**
	 * 新增 模板信息 主键自动增加
	 * @param param
	 * @return
	 */
	int insParam(TbItemParam param);
	
	
	
	
	
}
