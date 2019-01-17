package com.Star.dubbo.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.Star.mapper.TbItemDescMapper;
import com.Star.mapper.TbItemMapper;
import com.Star.mapper.TbItemParamItemMapper;
import com.Star.pojo.TbItem;
import com.Star.pojo.TbItemDesc;
import com.Star.pojo.TbItemExample;
import com.Star.pojo.TbItemParamItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.star.commons.pojo.EasyUIDataGrid;
import com.star.dubbo.service.TbItemDubboService;
/**
 * 这是提供的商品管理管理
 * @author xiang
 *
 */
// 交给dubbo 容器管理
public class TbItemDubboServiceImpl implements TbItemDubboService {
	@Resource
	private TbItemMapper tbItemMapper;
    @Resource
    private TbItemDescMapper tbItemDescMapper;
    @Resource
    private TbItemParamItemMapper tbItemParamItemMapper;
	@Override
	public EasyUIDataGrid show(int page, int rows) {

		PageHelper.startPage(page, rows);
		// 查询全部
		List<TbItem> list = tbItemMapper.selectByExample(new TbItemExample());

		// 配置分页
		// 设置分页条件
		PageInfo<TbItem> pi = new PageInfo<>(list);
		EasyUIDataGrid dataGrid = new EasyUIDataGrid();
		dataGrid.setRows(pi.getList());
		dataGrid.setTotal(pi.getTotal());
		return dataGrid;
	}

	/**
	 * 修改商品的状态
	 * 
	 */
	@Override
	public int updItemStatus(TbItem tbitem) {

		return tbItemMapper.updateByPrimaryKeySelective(tbitem);
	}

	/*
	 * (non-Javadoc)
	 * 完成 商品的新增和描述新增 包括事物
	 * @see
	 * com.star.dubbo.service.TbItemDubboService#insTbItemDesc(com.Star.pojo.TbItem,
	 * com.Star.pojo.TbItemDesc)
	 */
	@Override
	public int insTbItemDesc(TbItem tbItem, TbItemDesc tbItemDesc,TbItemParamItem paramItem) throws Exception {
		int index=0;
		
		try {
			index=tbItemMapper.insertSelective(tbItem);
			index+=tbItemDescMapper.insertSelective(tbItemDesc);
			index+=tbItemParamItemMapper.insertSelective(paramItem);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 if (index==3) {
		 return 1;
	}
	 else {
		 throw new Exception("新增失败,数据还原");
	 }
		 
		
		
	}
    /**
     * 根据状态来查
     */
	@Override
	public List<TbItem> selallbystatus(byte status) {
	TbItemExample example = new TbItemExample();
	example.createCriteria().andStatusEqualTo(status);
	List<TbItem> example2 = tbItemMapper.selectByExample(example);
		return example2;
	}

	@Override
	public TbItem selbyId(Long id) {
		// TODO Auto-generated method stub
		return tbItemMapper.selectByPrimaryKey(id);
	}

}
