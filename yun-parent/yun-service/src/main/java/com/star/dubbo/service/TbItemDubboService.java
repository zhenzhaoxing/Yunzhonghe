package com.star.dubbo.service;

import java.util.List;

import com.Star.pojo.TbItem;
import com.Star.pojo.TbItemDesc;
import com.Star.pojo.TbItemParamItem;
import com.star.commons.pojo.EasyUIDataGrid;

public interface TbItemDubboService {
/**
 * 
 * 商品分页显示 
 * 
 * 
 */
	
	
	EasyUIDataGrid show(int page,int rows);


      /**
       * 根据id修改状态
       * 
       * 
       */

         int   updItemStatus(TbItem tbitem);
         
         /**
          * 同时显示 商品和描述的新增    使用事物管理
          * @param tbItem
          * @param tbItemDesc
          * @return
         * @throws Exception 
          */
         int insTbItemDesc(TbItem tbItem,TbItemDesc tbItemDesc,TbItemParamItem paramItem) throws Exception;
         /**
            * 根据状态查询
          * @param status
          * @return
          */
         
           List<TbItem> selallbystatus(byte status);
           
           /**
            * 根据id 来查
            * @param id
            * @return
            */
           TbItem selbyId(Long id);
           
           

}
