package com.star.passport.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import com.Star.pojo.TbUser;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.star.commons.utils.JsonUtils;
import com.star.dubbo.service.TbUserDubboService;
import com.star.passport.service.TbRegisterService;

@Service
public class TbRegisterServiceImpl implements TbRegisterService {

	@Reference
	private TbUserDubboService tbUserDubboServiceImpl;
	@Resource
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private Destination queueDestination;
	
	/*@Resource
	private JedisDao jedisDaoImpl;*/
	
	private String smsKey = "smscode:";
	

	
	private String template_code="云中鹤";
	
	
	private String sign_name="dayu";
	@Override
	public void createSmsCode(String phone) {
		// TODO Auto-generated method stub
		//随机生成6位验证码
		String code =  (long) (Math.random()*1000000)+"";
		System.out.println(code);
		//存入缓存中
	//	jedisDaoImpl.set(smsKey+phone, code);
		
		jmsTemplate.send(queueDestination,new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				message.setString("mobile", phone);
				message.setString("template_code", template_code);//模板
				message.setString("sign_name", sign_name);//签名
				
				Map map = new HashMap();
				map.put("number", code);
		        
				message.setString("param", JSON.toJSONString(map));
				
				return message;
			}
		});
	}
      /**
       * 根据手机号和验证码进行检验看是否一致
       */
	
	@Override
	public boolean checkSmsCode(String phone, String code) {
	
		
	/*	String string = jedisDaoImpl.get(smsKey+phone);
		System.out.println(string);
		if(string==null) {
			return false;
		}
		
		if(string.equals(code)) {
		return false;
	
		
		
		}*/
		
		return true;
		
		
		}
	@Override
	public int inseradd(TbUser user) {
	     Date date = new Date();
		user.setCreated(date);
		user.setUpdated(date);
		return tbUserDubboServiceImpl.insertUser(user);
		
		
		
		
	}
	
	
	
	
	
	
	
}
