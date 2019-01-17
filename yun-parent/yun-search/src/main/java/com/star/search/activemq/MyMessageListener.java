package com.star.search.activemq;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import com.alibaba.dubbo.config.annotation.Reference;
import com.star.commons.utils.JsonUtils;
import com.star.dubbo.service.TbItemCatDubboService;

public class MyMessageListener implements MessageListener{
	@Resource
	private CloudSolrClient solrClient;
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;
	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			String text = textMessage.getText();
			//总体 包括 item 和desc
			 HashMap<String,Object> map = JsonUtils.jsonToPojo(text, HashMap.class);
			 //商品表
	         HashMap<String,Object> map3 = (HashMap<String, Object>) map.get("item");
			
	         Thread.sleep(1000);
			System.out.println(map3);
			SolrInputDocument doc = new SolrInputDocument();
			System.out.println(10);
			doc.setField("id", map3.get("id"));
			doc.setField("item_title", map3.get("title"));
			doc.setField("item_sell_point", map3.get("sellPoint"));
			doc.setField("item_price", map3.get("price"));
			doc.setField("item_image", map3.get("image"));
			//父类名称  tbItemCatDubboServiceImpl.selById((Integer)map.get("cid")).getName()                                                                     
			doc.addField("item_category_name",  tbItemCatDubboServiceImpl.selById((Integer)map.get("cid")).getName());
			//买点
			doc.addField("item_desc", map.get("desc"));
			  solrClient.add(doc);
		      solrClient.commit();
		
			
		
			
			
			
			
			
			
			
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
