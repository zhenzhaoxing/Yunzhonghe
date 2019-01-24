package com.star.passport.service;

import com.Star.pojo.TbUser;

public interface TbRegisterService {

	
	
	
	
	
	/**
	 * 发送短信验证码
	 * @param phone
	 */
	public void createSmsCode(String phone);
	
	/**
	 * 校验验证码
	 * @param phone
	 * @param code
	 * @return
	 */
	public boolean checkSmsCode(String phone,String code);
	
	
	
	int inseradd(TbUser user);
	
	
	
}
