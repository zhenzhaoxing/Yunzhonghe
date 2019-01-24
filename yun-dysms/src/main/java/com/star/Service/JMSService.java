package com.star.Service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
/**
 * 接收信息   并调 sms
 * @author xiang
 *
 */
@Component
public class JMSService {
      
	@Resource
	SmsUtil smsUtil;
	
	@JmsListener(destination="sms")
	public void readMessage(Map<String,String> map) {
		
		
		System.out.println(map);
		/*try {
		
			SendSmsResponse response = smsUtil.sendSms(map.get("mobile"), map.get("template_codec"),
					map.get("sign_name"),
					map.get("param") );
			
			   System.out.println("Code=" + response.getCode());
		        System.out.println("Message=" + response.getMessage());
		        System.out.println("RequestId=" + response.getRequestId());
		        System.out.println("BizId=" + response.getBizId());		

			
			
			
			
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	*/

		
	}
	
	
	
}
