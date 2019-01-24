package com.star.cart.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.star.cart.service.CartService;
import com.star.cart.service.Impl.CartServiceImpl;
import com.star.commons.pojo.EgoResult;

@Controller
public class CartController {

	
	
	@Resource
	private CartService cartServiceImpl;
	/**
	 * 添加购物车
	 */
	
	@RequestMapping("cart/add/{id}.html")
	public String addCart(@PathVariable long id,int num,HttpServletRequest request) {
		cartServiceImpl.addCart(id, num, request);
		return "cartSuccess";
	}
	
	
	/**
	 * 显示购物车
	 * @param id
	 * @param num
	 * @param request
	 * @return
	 */
	@RequestMapping("cart/cart.html")
	public String showCart(Model model,HttpServletRequest request) {
		model.addAttribute("cartList", cartServiceImpl.showcart(request));
		return "cart";
	}
	
	/**
	 * 删除商品
	 * @param id
	 * @param req
	 * @return
	 */
	
	@RequestMapping("cart/delete/{id}.action")
	public EgoResult delete(@PathVariable long id,HttpServletRequest req) {
		
		
		return	cartServiceImpl.delete(id, req);
		
	
	}
	
	@RequestMapping("cart/update/num/{id}/{num}.action")
     public EgoResult updata(@PathVariable long id,@PathVariable int num,HttpServletRequest req) {
		
		
		return	cartServiceImpl.updata(id, num, req);
		
	
	}
	
	
	
	
	
	
	
	
}
