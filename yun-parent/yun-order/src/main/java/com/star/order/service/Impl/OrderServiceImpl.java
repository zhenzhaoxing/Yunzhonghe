package com.star.order.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.Star.pojo.TbItem;
import com.Star.pojo.TbOrder;
import com.Star.pojo.TbOrderItem;
import com.Star.pojo.TbOrderShipping;
import com.alibaba.dubbo.config.annotation.Reference;
import com.star.commons.pojo.EgoResult;
import com.star.commons.pojo.TbItemChild;
import com.star.commons.pojo.TbOrderChild;
import com.star.commons.utils.CookieUtils;
import com.star.commons.utils.HttpClientUtil;
import com.star.commons.utils.IDUtils;
import com.star.commons.utils.JsonUtils;
import com.star.dubbo.service.TbItemDubboService;
import com.star.dubbo.service.TbOrderDubboService;
import com.star.order.pojo.MyOrderinfo;
import com.star.order.service.OrderService;
import com.star.redis.dao.JedisDao;

@Service
public class OrderServiceImpl implements OrderService {

	@Resource
	private JedisDao jedisDaoImpl;
	
	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	
	@Reference
	private TbOrderDubboService tbOrderDubboServiceImpl;

	private String passportUrl = "http://localhost:8084/user/token/";
	
	private String cartKey = "cart:";
	
	public List<TbItemChild> showOrder(HttpServletRequest request, List<Long> ids) {
		String value = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String jsonData = HttpClientUtil.doPost(passportUrl + value);
		EgoResult er = JsonUtils.jsonToPojo(jsonData, EgoResult.class);
		String key = cartKey + ((LinkedHashMap) er.getData()).get("username");
		// 这个key就是 本用户的key
		String string = jedisDaoImpl.get(key);
		List<TbItemChild> jsonToList = JsonUtils.jsonToList(string, TbItemChild.class);
		// 创建一个新的集合
		List<TbItemChild> listNew = new ArrayList<>();
		// 判断看是否传来的ids 有没有和 redis里相同的数据 有就 放入新集合中
		for (TbItemChild tbItemChild : jsonToList) {
			for (Long id : ids) {
				if ((long) id == (long) tbItemChild.getId()) {
					TbItem tbItem = tbItemDubboServiceImpl.selbyId(id);
					if (tbItem.getNum() >= tbItemChild.getNum()) {
						tbItemChild.setEnough(true);

					} else {
						tbItemChild.setEnough(false);
					}
					listNew.add(tbItemChild);
				}
			}
		}

		return listNew;
	}
	@Override
	public EgoResult createOrder(MyOrderinfo orderinfo, HttpServletRequest request,Long id) {
		// 订单表数据
		TbOrder order = new TbOrder();
		order.setPayment(orderinfo.getPayment());
		order.setPaymentType(orderinfo.getPaymentType());
		
		order.setOrderId(id + "");
		Date date = new Date();
		order.setCreateTime(date);
		order.setUpdateTime(date);
		order.setStatus(1);
		
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String json = HttpClientUtil.doPost(passportUrl + token);
		EgoResult egoResult = JsonUtils.jsonToPojo(json, EgoResult.class);
		Map user = (LinkedHashMap) egoResult.getData();
		
		order.setUserId(Long.parseLong(user.get("id").toString()));
		order.setBuyerNick(user.get("username").toString());
		order.setBuyerRate(0);
		// 订单 商品表
		for (TbOrderItem item : orderinfo.getOrderItems()) {
			item.setId(IDUtils.genItemId() + "");
			item.setOrderId(id + "");
		}
		// 收货人信息
		TbOrderShipping shipping = orderinfo.getOrderShipping();
		shipping.setOrderId(id + "");
		shipping.setCreated(date);
		shipping.setUpdated(date);
		EgoResult erResult = new EgoResult();
		try {
			int i = tbOrderDubboServiceImpl.insertIOS(order, orderinfo.getOrderItems(), shipping);
			if (i > 0) {
				erResult.setStatus(200);
				// 删除在购物车里已经购买的物品
				String json2 = jedisDaoImpl.get(cartKey + user.get("username"));
				List<TbItemChild> jsonToList2 = JsonUtils.jsonToList(json2, TbItemChild.class);
				// 创建一个用来存被删除条目的集合
				List<TbItemChild> romoveList = new ArrayList<TbItemChild>();
				// 对redis数据进行遍历 对 订单中的数据进行遍历
				for (TbItemChild tbItemChild : jsonToList2) {
					for (TbOrderItem orderitems : orderinfo.getOrderItems()) {
						// 进行比较
						if (tbItemChild.getId().longValue() == Long.parseLong(orderitems.getItemId())) {
							romoveList.add(tbItemChild);
						}
					}
				}
				for (TbItemChild tbItemChild : romoveList) {
					jsonToList2.remove(tbItemChild);
				}
				jedisDaoImpl.set(cartKey + user.get("username"), JsonUtils.objectToJson(jsonToList2));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return erResult;
	}
	@Override
	public int update(TbOrder order) throws Exception {
	return	tbOrderDubboServiceImpl.update(order);
	
	}
	@Override
	public List<TbOrderChild> showby(HttpServletRequest request) {
		
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String json = HttpClientUtil.doPost(passportUrl + token);
		EgoResult egoResult = JsonUtils.jsonToPojo(json, EgoResult.class);
		Map user = (LinkedHashMap) egoResult.getData();
		
		long parseLong = Long.parseLong(user.get("id").toString());
		
		return tbOrderDubboServiceImpl.seletebyUserId(parseLong);
		
	}

}
