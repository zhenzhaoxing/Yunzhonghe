package com.star.dubbo.service;

import com.Star.pojo.TbUser;

public interface TbUserDubboService {
   
	//登录检测
	TbUser selbynp(TbUser user);
	
	
	
	/**
	 * 根据用户名进行检测
	 */
	boolean checkbyName(String username);
	
	/**
	 * 根据手机号进行检测
	 */
	boolean checkbyphone(String phone);
	
	
	/**
	 * 注册用户
	 */
	int insertUser(TbUser user);
}
