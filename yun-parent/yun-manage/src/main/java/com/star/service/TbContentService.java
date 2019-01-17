package com.star.service;

import com.Star.pojo.TbContent;
import com.star.commons.pojo.EasyUIDataGrid;
import com.star.commons.pojo.EgoResult;

public interface TbContentService {

	
	EasyUIDataGrid showContent(Long categoryId,int page,int rows);
	
	
	EgoResult insertCon(TbContent tbcontent);
	
	
	//删除功能
	int delete(String ids) throws Exception;
}
