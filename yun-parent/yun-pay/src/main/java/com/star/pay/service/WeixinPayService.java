package com.star.pay.service;

import java.util.Map;

/**
 * 微信支付的接口
 * @author xiang
 *
 */
public interface WeixinPayService {
	/**
	 * 生成微信支付二维码
	 * @param out_trade_no 订单号
	 * @param total_fee 金额(分)
	 * @return
	 */
	public Map createNative(String out_trade_no,String total_fee);
	
	
	
	
	/**
	 * 查询支付状态 订单号
	 * @param out_trade_no
	 */

	
	public Map<String, String> queryPayStatus(String out_no);
}
