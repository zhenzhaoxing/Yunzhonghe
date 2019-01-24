package com.star.order.Controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Star.pojo.TbOrder;
import com.alibaba.dubbo.config.annotation.Reference;
import com.star.commons.pojo.EgoResult;
import com.star.commons.pojo.TbOrderChild;
import com.star.dubbo.service.TbOrderDubboService;
import com.star.order.service.OrderService;
import com.star.order.service.WeixinPayService;


@Controller

public class PayController2 {
	@Resource
	private WeixinPayService weixinPayServiceImpl;
	@Resource
	private OrderService orderServiceImpl;
	
	/**
	 *生成2微码 进行支付
	 * @param model
	 * @param privre
	 * @param id
	 * @return
	 */

	
	
	/**
	 * 查询支付状态 在不超时的情况下不断查询 直到 返回数据
	 * @param out_trade_no
	 * @return
	 */
	
	@RequestMapping("/queryPayStatus")
	@ResponseBody
	public EgoResult queryPayStatus(String out_trade_no) {
	
		
		
		EgoResult re =null;
		int x=0;
		while(true) {
			Map map = weixinPayServiceImpl.queryPayStatus(out_trade_no);
		
		   
	             if(map==null) {
	            	 re=new EgoResult();
	            	 re.setStatus(0);
	            	 re.setMsg("支付失败");
	            	 break;
	             }
		
	             if(map.get("trade_state").equals("SUCCESS")){//如果成功				
	            	 re=new  EgoResult();
	            	 re.setStatus(200);
	            	 re.setMsg("支付成功");
	 				break;
	 			}		
	             
	             try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	             x++;
	             if(x>=100) {
	            	 re=new  EgoResult();
	            	 re.setStatus(100);
	            	 re.setMsg("二维码超时");
	 				break;
	             }

		}
		return re;
	
		
		
	}

	@RequestMapping("/order/paysuccess")
	public String succec(Model mo,int money,String out_trade_no) throws Exception {
        mo.addAttribute("money", money);
		//订单状态修改状态  
		//成功后修改订单状态
        TbOrder com = new TbOrder();
		com.setOrderId(out_trade_no);
		com.setStatus(2);
		int i = orderServiceImpl.update(com);
		return "paysuccess";
		
	}
	
	
	@RequestMapping("/payfail")
	public String fail(int money,Model mo) {
      mo.addAttribute("money", money);
		
		
		
		return "paysuccess";
		
	}
	
	@RequestMapping("order/my-orders")
	public String show(HttpServletRequest request,Model model) {
		List<TbOrderChild> showby = orderServiceImpl.showby(request);
		
		model.addAttribute("orderList", showby);
		
		return "my-orders";
	}
	
	
	
	
}
