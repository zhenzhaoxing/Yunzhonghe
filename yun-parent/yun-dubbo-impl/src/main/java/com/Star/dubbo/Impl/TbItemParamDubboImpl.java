package com.Star.dubbo.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.Star.mapper.TbItemCatMapper;
import com.Star.mapper.TbItemParamItemMapper;
import com.Star.mapper.TbItemParamMapper;
import com.Star.pojo.TbItem;
import com.Star.pojo.TbItemCat;
import com.Star.pojo.TbItemParam;
import com.Star.pojo.TbItemParamExample;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.star.commons.pojo.EasyUIDataGrid;
import com.star.dubbo.service.TbItemParamDubboService;
/**
 * 这是提供的商品参数管理
 * @author xiang
 *
 */
public class TbItemParamDubboImpl implements TbItemParamDubboService {
	@Resource
	private TbItemParamMapper tbItemParamMapper;

	/**
	 * 查询全部
	 * 
	 */
	@Override
	public EasyUIDataGrid showPage(int page, int rows) {
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
		PageInfo<TbItemParam> pi = new PageInfo<>(list);

		EasyUIDataGrid data = new EasyUIDataGrid();
		data.setRows(pi.getList());
		data.setTotal(pi.getTotal());
		return data;
		// 通过 item_cat_id 去 找类目

	}

	/**
	 * 批量删除的功能
	 * 
	 */
	@Override
	public int del(String ids) throws Exception {
		int index = 0;
		String[] split = ids.split(",");
		for (String st : split) {
			index += tbItemParamMapper.deleteByPrimaryKey(Long.parseLong(st));
		}
		if (index == split.length) {
			return 1;
		} else {
			throw new Exception("删除失败 ，数据可能不存在");
		}

	}

	@Override
	public TbItemParam selByCatid(long catId) {
		TbItemParamExample example = new TbItemParamExample();
		example.createCriteria().andItemCatIdEqualTo(catId);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			// 要求每个类目只能有一个模板
			return list.get(0);
		}
		return null;
	}

	/**
	 * 添加 类目
	 */
	@Override
	public int insParam(TbItemParam param) {
		Date d = new Date();
		param.setCreated(d);
		param.setUpdated(d);

		int i = tbItemParamMapper.insertSelective(param);

		return i;
	}

}
