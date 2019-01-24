package com.star.commons.pojo;

import java.util.List;

import com.Star.pojo.TbOrder;
import com.Star.pojo.TbOrderItem;

public class TbOrderChild extends TbOrder{
     //收货人姓名
	String receiverName;
	
	//这是 有关订单里面的商品的信息
	List<TbOrderItem> tborderItems;

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public List<TbOrderItem> getTborderItems() {
		return tborderItems;
	}

	public void setTborderItems(List<TbOrderItem> tborderItems) {
		this.tborderItems = tborderItems;
	}
	
	
	
}
