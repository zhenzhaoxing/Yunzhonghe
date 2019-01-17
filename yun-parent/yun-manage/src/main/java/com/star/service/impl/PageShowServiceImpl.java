package com.star.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.Star.pojo.TbItem;
import com.Star.pojo.TbItemDesc;
import com.Star.pojo.TbItemParamItem;
import com.alibaba.dubbo.config.annotation.Reference;

import com.star.commons.pojo.EasyUIDataGrid;
import com.star.commons.utils.HttpClientUtil;
import com.star.commons.utils.IDUtils;
import com.star.commons.utils.JsonUtils;
import com.star.dubbo.service.TbItemDubboService;
import com.star.service.PageShowService;

@Service
public class PageShowServiceImpl implements PageShowService {
	
	@Resource
	JmsTemplate jmsTemplate ;
	//从容器中获得一个Destination对象。
    @Resource
	Destination destination ;
	@Reference
	private TbItemDubboService tbItemDubboService;
	@Value("${search.url}")
	private String url ;
	@Override
	public EasyUIDataGrid show(int page, int rows) {

		return tbItemDubboService.show(page, rows);
	}

	/**
	 * 批量修改
	 * 对商品的删除3 下架2 上架1
	 * 
	 */
	@Override
	public int updItemStatus(String ids, byte status) {
	int  	index =0;
				TbItem  tbitem = new TbItem();
         String[] split = ids.split(",");
           for (String id : split) {
        	   tbitem.setId(Long.parseLong(id));
        	   tbitem.setStatus(status);
        	  index+= tbItemDubboService.updItemStatus(tbitem);
		}
           if(index==split.length) {
        	   return 1;
           }
		return 0;
	}
/*
 * (non-Javadoc)
 *  新增商品
 * @see com.star.service.PageShowService#save(com.Star.pojo.TbItem, java.lang.String)
 */
	@Override
	public int save(TbItem item, String desc,String itemParams) throws Exception {

		
		   long id =IDUtils.genItemId();
			item.setId(id);
			item.setStatus((byte)1);
			Date date = new Date();
			item.setUpdated(date);
			item.setCreated(date);
			//商品描述对象
			TbItemDesc itemDesc = new TbItemDesc();
			itemDesc.setItemId(id);//与 商品id键一致
			itemDesc.setCreated(date);
			itemDesc.setUpdated(date);
			itemDesc.setItemDesc(desc);
			TbItemParamItem paramItem = new TbItemParamItem();
			paramItem.setUpdated(date);
			paramItem.setCreated(date);
			paramItem.setParamData(itemParams);
			paramItem.setItemId(id);
			int index = 0;
	        index=tbItemDubboService.insTbItemDesc(item, itemDesc,paramItem);
	    	Map<String, Object> map = new HashMap<String, Object>();
			map.put("item", item);
			map.put("desc", desc);
	        //使用消息中间件 添加使用点对点模式
	    	jmsTemplate.send(destination, new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(JsonUtils.objectToJson(map));
				}
			});
	        
	        
	      /*  
	        //没有使用消息中间件
		    new Thread(new Runnable() {
				
				@Override
				public void run() {

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("item", item);
				map.put("desc", desc);
				//使用java代码调用其他项目的控制器
				String url="http://localhost:8083/solr/add";
				HttpClientUtil.doPostJson(url, JsonUtils.objectToJson(map));
				}
			}).start();*/
		     
		    
		return index;
	}

}


















