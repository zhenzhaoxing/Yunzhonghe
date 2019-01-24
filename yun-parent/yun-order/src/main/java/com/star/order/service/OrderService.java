package com.star.order.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;

import com.Star.pojo.TbOrder;
import com.star.commons.pojo.EgoResult;
import com.star.commons.pojo.TbItemChild;
import com.star.commons.pojo.TbOrderChild;
import com.star.order.pojo.MyOrderinfo;

public interface OrderService {
   
	List<TbItemChild> showOrder(HttpServletRequest request,List<Long> ids);


   /*
    * 创建订单
    */
  EgoResult createOrder(MyOrderinfo orderinfo,HttpServletRequest request,Long id);

  
  
  // 更新状态
      int  update(TbOrder order) throws Exception;
      
      
      //查询订单
      List<TbOrderChild> showby(HttpServletRequest request);
      
      
}
