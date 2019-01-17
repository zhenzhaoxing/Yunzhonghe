package com.Star.dubbo.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.Star.mapper.TbContentMapper;
import com.Star.pojo.TbContent;
import com.Star.pojo.TbContentExample;
import com.Star.pojo.TbItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.star.commons.pojo.EasyUIDataGrid;
import com.star.dubbo.service.TbContentDubboService;
/**
 * 这是提供的内容管理
 * @author xiang
 *
 */
public class TbContentDubboServiceImpl implements TbContentDubboService {

	
	@Resource
	private TbContentMapper  tbContentMapper;
	
	
	//查询结果集
	@Override
	public EasyUIDataGrid selByCateID(Long categoryId, int page, int rows) {
		PageHelper.startPage(page, rows);
		TbContentExample example = new TbContentExample();
		if(categoryId!=0){
			example.createCriteria().andCategoryIdEqualTo(categoryId);
		}
		List<TbContent> list = tbContentMapper.selectByExample(example);
	
		PageInfo<TbContent> pi = new PageInfo<>(list);
		
		EasyUIDataGrid datagrid = new EasyUIDataGrid();
		datagrid.setRows(pi.getList());
		datagrid.setTotal(pi.getTotal());
		return datagrid;
	}

  /**
   * 添加新的东西
   */
	@Override
	public int insertContent(TbContent content) {
		int i = tbContentMapper.insertSelective(content);
		return i;
	}


	//删除东西
	@Override
	public int deletebyid(String ids) throws Exception {
		String[] split = ids.split(",");
        int index=0;      
		for (String string : split) {
			index+=tbContentMapper.deleteByPrimaryKey(Long.parseLong(string));
		}
		if(index==split.length) {
			return 1;
		}else {
			throw new Exception("删除失败 ，数据可能不存在");
		}
	}

     //查询数据条数

	@Override
	public List<TbContent> selByCount(int count, boolean isSort) {
	
		TbContentExample example = new TbContentExample();
	
		if(isSort){
			example.setOrderByClause("updated desc");
		}
		
		if(count!=0){
		
			PageHelper.startPage(1, count);
			
			List<TbContent> list = tbContentMapper.selectByExample(example);
		
			PageInfo<TbContent> pi = new PageInfo<>(list);
			
			return pi.getList();
		}else{
			return tbContentMapper.selectByExampleWithBLOBs(example);
		}
	}
 
	
	
	/*@Test
	public void name() {
		//List<TbContent> count = selByCount( 1, true);
		//System.out.println(count);
		EasyUIDataGrid cateID = selByCateID(Long.valueOf(89), 1,3);
		System.out.println(cateID);
		long l = Long.parseLong("89".toString());
		Long valueOf = Long.valueOf(89);
		System.out.println(valueOf);
	}*/
	
}
