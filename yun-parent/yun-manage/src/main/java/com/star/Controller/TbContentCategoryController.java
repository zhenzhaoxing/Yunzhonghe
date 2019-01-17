package com.star.Controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Star.pojo.TbContentCategory;
import com.star.commons.pojo.EasyUITree;
import com.star.commons.pojo.EgoResult;
import com.star.service.TbContentCategoryService;

@Controller
public class TbContentCategoryController {

	@Resource
	private TbContentCategoryService tbContentCategoryServiceImpl;

	
	
	//控制器的代码
	@RequestMapping("content/category/list")
	@ResponseBody
	public List<EasyUITree> showLi(@RequestParam(defaultValue = "0") Long id) {
		return tbContentCategoryServiceImpl.selbyPid(id);
	}
	/**
	 * 新增内容类目
	 * @param cate
	 * @return
	 */
	@RequestMapping("content/category/create")
	@ResponseBody
	public EgoResult create(TbContentCategory cate){
		return tbContentCategoryServiceImpl.create(cate);
	}
	/**
	 * 重命名
	 * @param cate
	 * @return
	 */
	@RequestMapping("content/category/update")
	@ResponseBody
	public EgoResult update(TbContentCategory cate){
		return tbContentCategoryServiceImpl.update(cate);
	}
	
	
	/**
	 * 删除
	 * @param cate
	 * @return
	 */
	@RequestMapping("content/category/delete/")
	@ResponseBody
	public EgoResult delete(TbContentCategory cate) {
		
		return tbContentCategoryServiceImpl.delete(cate);
		
	}
	
}
