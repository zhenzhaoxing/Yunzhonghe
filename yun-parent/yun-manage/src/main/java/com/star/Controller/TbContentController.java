package com.star.Controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Star.pojo.TbContent;
import com.star.commons.pojo.EasyUIDataGrid;
import com.star.commons.pojo.EgoResult;
import com.star.service.impl.TbContentServiceImpl;

@Controller
public class TbContentController {

	@Resource
	private TbContentServiceImpl tbContentServiceImpl;
	
	//控制器的代码
		@RequestMapping("content/query/list")
		@ResponseBody
		public EasyUIDataGrid showContent(Long categoryId, int page, int rows) {
			return tbContentServiceImpl.showContent(categoryId, page, rows);
		}	
		
		
		//新增的代码实现
		
		@RequestMapping("content/save")
		@ResponseBody
		public EgoResult save(TbContent tbcontent) {
			
			EgoResult con = tbContentServiceImpl.insertCon(tbcontent);
		  return con;
		}
		
		
		
		//删除功能的实现
		
	 
		
		@RequestMapping("content/delete")
		@ResponseBody
		public EgoResult delete(String ids) throws Exception {
			EgoResult	e = new EgoResult();
			int i = tbContentServiceImpl.delete(ids);
			if (i == 1) {
				e.setStatus(200);
			}
			return e;

		}
		
		
		
}
