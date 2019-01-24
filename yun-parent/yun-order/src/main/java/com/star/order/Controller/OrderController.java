package com.star.order.Controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.star.commons.pojo.EgoResult;
import com.star.commons.utils.IDUtils;
import com.star.order.pojo.MyOrderinfo;
import com.star.order.service.OrderService;
import com.star.order.service.WeixinPayService;

@Controller
public class OrderController {

	@Resource
	private OrderService orderServiceImpl;

	@Resource
	private WeixinPayService weixinPayServiceImpl;
	
	/**
	 * 显示订单页面
	 * 
	 * @return
	 */
	@RequestMapping("order/order-cart.html")
	public String showCartOrder(Model c, HttpServletRequest request, @RequestParam("id") List<Long> ids) {
		c.addAttribute("cartList", orderServiceImpl.showOrder(request, ids));
		return "order-cart";

	}

	/**
	 * 创建新订单  返给支付页面 
	 */

	@RequestMapping("order/create.html")
	public String createOrder(MyOrderinfo orderinfo, HttpServletRequest request,Model model) {
		
		long id = IDUtils.genItemId();//订单id
		EgoResult egoResult = orderServiceImpl.createOrder(orderinfo, request,id);
		if (egoResult.getStatus() == 200) {
			
			Map createNative = weixinPayServiceImpl.createNative(Long.toString(id),orderinfo.getPayment());
			model.addAttribute("out_trade_no", createNative.get("out_trade_no"));
			model.addAttribute("money", createNative.get("total_fee"));
			model.addAttribute("twourl", "http://www.taobao.com");
			
			return "pay";
		} else {
			request.setAttribute("message", "订单创建失败");
			return "error/exception";
		}

	}

}
