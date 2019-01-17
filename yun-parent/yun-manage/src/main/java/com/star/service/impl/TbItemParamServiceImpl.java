package com.star.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Star.pojo.TbItemParam;
import com.alibaba.dubbo.config.annotation.Reference;

import com.star.commons.pojo.EasyUIDataGrid;
import com.star.commons.pojo.EgoResult;
import com.star.dubbo.service.TbItemParamDubboService;
import com.star.pojo.TbItemParamChild;
import com.star.service.TbItemParamService;
import com.star.dubbo.service.TbItemCatDubboService;

@Service
public class TbItemParamServiceImpl implements TbItemParamService {
	@Reference
	private TbItemParamDubboService tbItemParamDubboService;

	@Reference
	private TbItemCatDubboService tbItemCatDubboService;

	@Override
	public EasyUIDataGrid showPage(int page, int rows) {
		EasyUIDataGrid datagrid = tbItemParamDubboService.showPage(page, rows);
		List<TbItemParam> list = (List<TbItemParam>) datagrid.getRows();
		// 给数据 tbItemParamChile 添加 ItemCatName
		List<TbItemParamChild> listChild = new ArrayList<>();

		for (TbItemParam param : list) {
			TbItemParamChild t = new TbItemParamChild();

			t.setCreated(param.getCreated());
			t.setId(param.getId());
			t.setItemCatId(param.getItemCatId());
			t.setUpdated(param.getUpdated());
			t.setParamData(param.getParamData());
			t.setItemCatName(tbItemCatDubboService.selById(t.getItemCatId()).getName());

			listChild.add(t);

		}

		datagrid.setRows(listChild);

		return datagrid;
	}

	/**
	 * 批量删除
	 */
	@Override
	public int delete(String ids) throws Exception {
		return tbItemParamDubboService.del(ids);
	}

	/**
	 * 查是否有重复值
	 */
	@Override
	public TbItemParam selByCatid(long catId) {
		return tbItemParamDubboService.selByCatid(catId);
	}
  /**
   *  导入模板
   */
	@Override
	public EgoResult save(TbItemParam param) {
		EgoResult er = new EgoResult();
		int i = tbItemParamDubboService.insParam(param);
		if (i > 0) {
			er.setStatus(200);

		}
		return er;
	}

}
