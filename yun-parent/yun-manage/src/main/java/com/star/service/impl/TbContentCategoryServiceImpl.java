package com.star.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.Star.pojo.TbContentCategory;
import com.alibaba.dubbo.config.annotation.Reference;

import com.star.commons.pojo.EasyUITree;
import com.star.commons.pojo.EgoResult;
import com.star.commons.utils.IDUtils;
import com.star.dubbo.service.TbContentCategoryDubboService;
import com.star.service.TbContentCategoryService;
@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

   @Reference
   private TbContentCategoryDubboService tbContentCategoryDubboServiceImpl;
   
	
	//根据id查所有类目
	@Override
	public List<EasyUITree> selbyPid(Long id) {
		List<EasyUITree> listnode =  new ArrayList<EasyUITree>();
		List<TbContentCategory> list = tbContentCategoryDubboServiceImpl.selbyPiD(id);
		
		for (TbContentCategory tbContentCategory : list) {
			EasyUITree  e = new EasyUITree();
			e.setId(tbContentCategory.getId());
			e.setText(tbContentCategory.getName());
			e.setState(tbContentCategory.getIsParent()?"closed":"open");
			listnode.add(e);
		}
		return listnode;
	}


	@Override
	public EgoResult create(TbContentCategory cate) {
		EgoResult er = new EgoResult();
		//判断当前节点名称是否已经存在
		List<TbContentCategory> selbyPiD = tbContentCategoryDubboServiceImpl.selbyPiD(cate.getParentId());
	     for (TbContentCategory chinl : selbyPiD) {
			if(chinl.getName().equals(cate.getName())) {
				er.setData("该类名已存在");
				return er;
			}
		}
		
	     
	     
	     Date date = new Date();
	     
	        cate.setCreated(date);
			cate.setUpdated(date);
			cate.setStatus(1);
			cate.setSortOrder(1);
			cate.setIsParent(false);
			long id = IDUtils.genItemId();
			cate.setId(id);
			int index = tbContentCategoryDubboServiceImpl.insTbContentCategory(cate);
			//然后把这个所属分类 设置为父类目
			if(index>0){
				TbContentCategory parent = new TbContentCategory();
				parent.setId(cate.getParentId());
				parent.setIsParent(true);
				tbContentCategoryDubboServiceImpl.updIsParentById(parent);
			}
			er.setStatus(200);
			Map<String,Long> map = new HashMap<>();
			map.put("id", id);
			er.setData(map);
			return er;
	}
   /**
    * 修改功能
    */

	@Override
	public EgoResult update(TbContentCategory cate) {
		EgoResult er = new EgoResult();
		//查询当前节点信息
		TbContentCategory cateSelect =  tbContentCategoryDubboServiceImpl.selById(cate.getId());
		List<TbContentCategory> parent = tbContentCategoryDubboServiceImpl.selbyPiD(cateSelect.getParentId());
		for (TbContentCategory tbContentCategory : parent) {
			   if(tbContentCategory.getName().equals(cate.getName())) {
				   er.setData("该类名已存在");
					return er;
			   }
		}
		
		int index = tbContentCategoryDubboServiceImpl.updIsParentById(cate);
		if(index>0){
			er.setStatus(200);
		}
		
		return er;
	}


@Override
public EgoResult delete(TbContentCategory cate) {
	EgoResult er = new EgoResult();
	cate.setStatus(0);
	int i = tbContentCategoryDubboServiceImpl.updIsParentById(cate);
	if (i>0) {
		//根据传的id来查找信息
		TbContentCategory selbyPiD = tbContentCategoryDubboServiceImpl.selById(cate.getId());
		//查找到信息后然后 根据父节点判断是否有值
		List<TbContentCategory> piD = tbContentCategoryDubboServiceImpl.selbyPiD(selbyPiD.getParentId());
	
		if(piD==null||piD.size()==0) {
			TbContentCategory parent = new TbContentCategory();
			parent.setId(selbyPiD.getParentId());
			parent.setIsParent(false);
			int result=tbContentCategoryDubboServiceImpl.updIsParentById(parent);
			
			if(result>0) {
				er.setStatus(200);
			}
		}else {
			er.setStatus(200);
		}
	
	
	}
	
	
	
	return er;
}

}
