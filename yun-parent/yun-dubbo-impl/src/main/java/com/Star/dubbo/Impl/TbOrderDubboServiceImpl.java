package com.Star.dubbo.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;



import com.Star.mapper.TbOrderItemMapper;
import com.Star.mapper.TbOrderMapper;
import com.Star.mapper.TbOrderShippingMapper;
import com.Star.pojo.TbOrder;
import com.Star.pojo.TbOrderExample;
import com.Star.pojo.TbOrderItem;
import com.Star.pojo.TbOrderShipping;
import com.star.commons.pojo.TbOrderChild;
import com.star.dubbo.service.TbOrderDubboService;

public class TbOrderDubboServiceImpl implements TbOrderDubboService {

	
	@Resource
	private TbOrderMapper tbOrderMapper;
	@Resource
	TbOrderItemMapper  tbOrderItemMapper;
	@Resource
	TbOrderShippingMapper  tbOrderShippingMapper;
	@Override 
	public int insertIOS(TbOrder order, List<TbOrderItem> orderItem, TbOrderShipping orderShipping) throws Exception {
		int index=tbOrderMapper.insertSelective(order);
		
		index+=tbOrderShippingMapper.insertSelective(orderShipping);
		for (TbOrderItem tbOrderItem : orderItem) {
			 index+=tbOrderItemMapper.insertSelective(tbOrderItem);
		}
		if(index==2+orderItem.size()){
			return 1;
				
			}else {
				throw new Exception("创建订单失败");
			}
		
		
		
		
	
	}
	
	
	@Override
	public int update(TbOrder order) throws Exception {
		 
		
		
			
		int i=tbOrderMapper.updateByPrimaryKeySelective(order);
		if(i>0) {
			return i;
		}else {
			throw new Exception("失败");
		}
		
		
	}


	@Override
	public List<TbOrderChild> seletebyUserId(Long userid) {

		
	  //	[ [] , [] , [] , []  ]
		//此集合用来添加
		List<TbOrderChild> th = new ArrayList<TbOrderChild>();
		
		
		TbOrderExample example = new TbOrderExample();
		example.createCriteria().andUserIdEqualTo(userid);
		List<TbOrder> userorder = tbOrderMapper.selectByExample(example);
		for (TbOrder tbOrder : userorder) {//用户下面可能有条订单  每一条订单下有 条目不限 和 收货人
		   TbOrderChild t = new TbOrderChild();
           t.setOrderId(tbOrder.getOrderId());
		   t.setStatus(tbOrder.getStatus());
		   t.setPayment(tbOrder.getPayment());
		   t.setPaymentTime(tbOrder.getPaymentTime());
		   t.setUpdateTime(tbOrder.getUpdateTime());
		   t.setReceiverName(tbOrderShippingMapper.selectByPrimaryKey(tbOrder.getOrderId()).getReceiverName());
		    
		   TbOrderItem selectByPrimaryKey = tbOrderItemMapper.selectByPrimaryKey(tbOrder.getOrderId());
		   List<TbOrderItem> sel = new ArrayList<TbOrderItem>();
		   sel.add(selectByPrimaryKey);
		   
		   
		   t.setTborderItems(sel);       
		  
		  th.add(t);
		}
		
		
		
		
		
		
		
		
		return th ;
	}

}
