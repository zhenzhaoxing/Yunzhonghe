package com.star.pay.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.wxpay.sdk.WXPayUtil;
import com.star.commons.utils.HttpClient;
import com.star.pay.service.WeixinPayService;
@Service
public class WeixinPayServiceImpl implements WeixinPayService {

	

	private String appid="wx8397f8696b538317";
	
	private String mch_id="1473426802";

	private String partnerkey="8A627A4578ACE384017C997F12D68B23";
   /**
    * 生产2微码
    */
	@Override
	public Map createNative(String out_trade_no,String total_fee){
		//1.创建参数
		Map<String,String> param=new HashMap();//创建参数
		param.put("appid", appid);//公众号
		param.put("mch_id", mch_id);//商户号
		param.put("nonce_str", WXPayUtil.generateNonceStr());//随机字符串		
		param.put("body", "品优购");//商品描述
		param.put("out_trade_no", out_trade_no);//商户订单号
		param.put("total_fee",total_fee);//总金额（分）
		param.put("spbill_create_ip", "127.0.0.1");//IP
		param.put("notify_url", "http://test.itcast.cn");//回调地址(随便写)
		param.put("trade_type", "NATIVE");//交易类型
		try {
			//2.生成要发送的xml 
			String xmlParam = WXPayUtil.generateSignedXml(param, "8A627A4578ACE384017C997F12D68B23");
			System.out.println(xmlParam);	
			HttpClient client=new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
			client.setHttps(true);
			client.setXmlParam(xmlParam);
			client.post();	
			//System.out.println(client.getContent());	
			
			/*
			//3.获得结果 
			String result = client.getContent();
			System.out.println(result);
			Map<String, String> resultMap = WXPayUtil.xmlToMap(result);	
			System.out.println(resultMap);*/
			Map<String, String> map=new HashMap<>();
			map.put("code_url", "http://www.taobao.com");//支付地址
			map.put("total_fee", total_fee);//总金额
			map.put("out_trade_no",out_trade_no);//订单号
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return new HashMap<>();
		}			
	}


@Override
public Map<String, String> queryPayStatus(String  out_no) {
	Map<String, String> param=new HashMap();
	
	param.put("appid", appid);//公众号
	param.put("mch_id", mch_id);//商户号


	param.put("out_trade_no", out_no);//商户订单号
	param.put("nonce_str", WXPayUtil.generateNonceStr());//随机字符串		
	String url="https://api.mch.weixin.qq.com/pay/orderquery";		
	
	try {
		/*String xmlParam = WXPayUtil.generateSignedXml(param, partnerkey);	
		HttpClient client=new HttpClient(url);
		client.setHttps(true);
		client.setXmlParam(xmlParam);
		client.post();
		String result = client.getContent();	
		System.out.println(result);*/
		
		//Map<String, String> map = WXPayUtil.xmlToMap(result);
		Map<String, String> map = new HashMap<String, String>();
	
		map.put("trade_state", "SUCCESS");
		
		//System.out.println(map);
		return map;				
}catch (Exception e) {
	e.printStackTrace();
	return null;
}	
}
}

