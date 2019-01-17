package com.star.dubbo.service;

import com.Star.pojo.TbItemParamItem;

public interface TbItemParamItemDubboSerice {
	/**
	 * 根据商品id查询商品规格参数
	 * @param itemId
	 * @return
	 */
	TbItemParamItem selByItemid(long itemId);
}
