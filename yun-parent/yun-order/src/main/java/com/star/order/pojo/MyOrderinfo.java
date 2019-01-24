package com.star.order.pojo;

import java.util.List;

import com.Star.pojo.TbOrderItem;
import com.Star.pojo.TbOrderShipping;

public class MyOrderinfo {
         private int paymentType;//支付类型
         private String payment;
	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;
	public int getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
}
