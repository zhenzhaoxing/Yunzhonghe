package com.star.dubbo.service;

import java.util.List;
import java.util.Map;

import com.Star.pojo.TbOrder;
import com.Star.pojo.TbOrderItem;
import com.Star.pojo.TbOrderShipping;
import com.star.commons.pojo.TbOrderChild;

public interface TbOrderDubboService {

	
	/**
	 * 对3张表进行增加信息
	 * @return
	 */
	int  insertIOS(TbOrder order,List<TbOrderItem> orderItem,TbOrderShipping orderShipping)throws Exception;




   int  update(TbOrder order) throws Exception;

   
   
   //根据用户id进行查询操作
   List<TbOrderChild> seletebyUserId(Long userid);

}
