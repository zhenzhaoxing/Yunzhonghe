package com.star.passport.service.impl;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.Star.pojo.TbUser;
import com.alibaba.dubbo.config.annotation.Reference;
import com.star.commons.pojo.EgoResult;
import com.star.commons.utils.CookieUtils;
import com.star.commons.utils.JsonUtils;
import com.star.dubbo.service.TbUserDubboService;
import com.star.passport.service.TbUserService;

@Service
public class TbUserServiceImpl implements TbUserService {

	/*
	 * @Resource private JedisDao jedisDaoImpl;
	 */
	@Reference
	private TbUserDubboService tbUserDubboServiceImpl;

	@Override
	public EgoResult login(TbUser user, HttpServletRequest request, HttpServletResponse response) {
		EgoResult er = new EgoResult();
		TbUser tbUser = tbUserDubboServiceImpl.selbynp(user);
		// redis的值
		String key = UUID.randomUUID().toString();
		/*if (tbUser != null) {
			jedisDaoImpl.set(key, JsonUtils.objectToJson(tbUser));
			CookieUtils.setCookie(request, response, "TT_TOKEN", key, 60 * 60 * 24 * 7);
			jedisDaoImpl.expire(key, 60 * 60 * 24 * 7);

			er.setStatus(200);

		} else {
			er.setMsg("登录失败");
		}*/

		return er;
	}

	@Override
	public EgoResult getUserInfoByToken(String token) {
		EgoResult er = new EgoResult();
	/*	String string = jedisDaoImpl.get(token);
		if (string != null && !string.equals("")) {
			TbUser user = JsonUtils.jsonToPojo(string, TbUser.class);
			user.setPassword(null);
			er.setStatus(200);
			er.setMsg("OK");
			er.setData(user);

		} else {
			er.setMsg("获取失败");
		}*/
		return er;
	}

	@Override
	public EgoResult logoutByToken(String token, HttpServletRequest request, HttpServletResponse response) {
		//jedisDaoImpl.del(token);
		CookieUtils.deleteCookie(request, response, "TT_TOKEN");
		EgoResult er = new EgoResult();
		er.setStatus(200);
		er.setMsg("OK");
		return er;
	}

	/**
	 * 判断是否含有数据
	 */
	@Override
	public EgoResult check(String name) {
		EgoResult er = new EgoResult();
		boolean checkbyName = tbUserDubboServiceImpl.checkbyName(name);
		if (checkbyName) {
			er.setStatus(100);
		} else {
			er.setStatus(200);
		}
		return er;
	}

	@Override
	public EgoResult checkbyphone(String number) {

		EgoResult er = new EgoResult();
		boolean checkbyphone = tbUserDubboServiceImpl.checkbyphone(number);
		if (checkbyphone) {
			er.setStatus(100);
		} else {
			er.setStatus(200);
		}
		return er;
	}

}
