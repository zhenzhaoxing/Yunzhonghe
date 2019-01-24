package com.star.cart.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;

import com.star.commons.pojo.EgoResult;
import com.star.commons.pojo.TbItemChild;

public interface CartService {
      
	/**
	 * 添加购物车
	 * @param id
	 * @param num
	 * @param request
	 */
	
	void addCart(long id,int num,HttpServletRequest request);
	
	
	/**
	 * 显示购物车
	 * @param request
	 * @return
	 */
	List<TbItemChild> showcart(HttpServletRequest request);
	/**
	 * 删除功能
	 * @param id
	 * @param req
	 * @return
	 */

	EgoResult delete(long id,HttpServletRequest req);
	
	
	/**
	 * 修改状态
	 * @param id
	 * @param num
	 * @param req
	 * @return
	 */
	EgoResult updata(long id,int num,HttpServletRequest req);
}
