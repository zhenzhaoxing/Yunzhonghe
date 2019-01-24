package com.star.passport.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Star.pojo.TbUser;
import com.star.commons.pojo.EgoResult;

public interface TbUserService {
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	EgoResult login(TbUser user,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 根据token来获取登录信息
	 * @param token
	 * @return
	 */
	EgoResult getUserInfoByToken(String token);
	
	
	/**
	 * 根据token来退出
	 * @param token
	 * @return
	 */
	EgoResult logoutByToken(String token,HttpServletRequest request, HttpServletResponse response);







      
	/**
	 * 根据参数查看数据库里面有没有内容·1
	 * @param name
	 * @return
	 */
	
	EgoResult check(String name);
  
          /**
           * 根据 手机号看是否有内容
           * @param number
           * @return
           */
	EgoResult checkbyphone(String number);


}
 