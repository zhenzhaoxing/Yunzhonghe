package com.activemq;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.Star.pojo.TbItem;
import com.alibaba.dubbo.common.json.JSON;
import com.star.commons.utils.HttpClientUtil;
import com.star.commons.utils.IDUtils;
import com.star.commons.utils.JsonUtils;
import com.star.pojo.TbItemParamChild;

public class ActiveMqSpring {
	@Test
	public void sendMessage() throws Exception {
		/*TbItemParamChild t = new TbItemParamChild();
		t.setItemCatName("甄兆星");
        t.setParamData("vytghbjn ");
        t.setId((long)10);*/
		
		TbItem item = new TbItem();
		item.setTitle("小米手机就是好");
		item.setImage("http://localhost");
		         long id =IDUtils.genItemId();
					item.setId(id);
					item.setStatus((byte)1);
					Date date = new Date();
					item.setUpdated(date);
					item.setCreated(date);
					item.setNum(70);
					item.setSellPoint("sssssssssssssssssss方法的付付付付付付过或或或或");
					item.setPrice(id);
	     	String desc="eubbwefbefwewfiefwinfewni";
	    	Map<String, Object> map = new HashMap<String, Object>();
			map.put("item", item);
			map.put("desc", desc);
			//使用java代码调用其他项目的控制器
		//{"item":{"id":154770814421085,"title":null,"sellPoint":"sssssssssssssssssss","price":154770814421085,"num":70,"barcode":null,"image":"http://localhost","cid":null,"status":1,"created":1547708144211,"updated":1547708144211},"desc":"eubbwefbefwewfiefwinfewni"}

		String objectToJson = JsonUtils.objectToJson(map);
	       //	System.out.println(objectToJson);
         
		//初始化spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext-jms-producer.xml");
		//从容器中获得JmsTemplate对象。
		JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
		//从容器中获得一个Destination对象。
		Destination destination = (Destination) applicationContext.getBean("queueDestination");
		//发送消息
		jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(objectToJson);
			}
		});
	}

}