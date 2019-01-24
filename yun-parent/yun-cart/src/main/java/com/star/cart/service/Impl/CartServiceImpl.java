package com.star.cart.service.Impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.Star.pojo.TbItem;
import com.alibaba.dubbo.config.annotation.Reference;
import com.star.cart.service.CartService;
import com.star.commons.pojo.EgoResult;
import com.star.commons.pojo.TbItemChild;
import com.star.commons.utils.CookieUtils;
import com.star.commons.utils.HttpClientUtil;
import com.star.commons.utils.JsonUtils;
import com.star.dubbo.service.TbItemDubboService;
import com.star.redis.dao.JedisDao;
import com.star.redis.dao.impl.JedisDaoImpl;

@Service
public class CartServiceImpl implements CartService {
	@Resource
	private JedisDao jedisDaoImpl;
	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;

	private String passportUrl = "http://localhost:8084/user/token/";

	private String cartKey = "cart:";

	@Override
	public void addCart(long id, int num, HttpServletRequest request) {
		/**
		 * 添加商品到购物车 购物车可能有多个商品 所以用集合存储 当有这个商品时 就对数量修改   没有这个商品时 就像这个key对应的value集合添加新的数据
		 */

		// 创建集合
		List<TbItemChild> list = new ArrayList<TbItemChild>();

		// 2 获取key 使用用户名作为目录
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		// 用户信息
		String jsonUser = HttpClientUtil.doPost(passportUrl + token);
		// 转为对象
		EgoResult er = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);
		String key = cartKey + ((LinkedHashMap) er.getData()).get("username");

		if (jedisDaoImpl.exists(key)) {
			String json = jedisDaoImpl.get(key);
			if (json != null && !json.equals("")) {
				list = JsonUtils.jsonToList(json, TbItemChild.class);
				for (TbItemChild tbItemChild : list) {
					if (tbItemChild.getId() == id) {//当 这个key对应的value里 没有这个 id时 就会跳出 不执行 if里面的方法
						tbItemChild.setNum(tbItemChild.getNum() + num);
						jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
						return;//若满足条件，直接方法
					}
				}
				
				
			}
		}
		
		TbItem item = tbItemDubboServiceImpl.selbyId(id);
		TbItemChild child = new TbItemChild();
		
		child.setId(item.getId());
		child.setTitle(item.getTitle());
		child.setImages(item.getImage()==null||item.getImage().equals("")?new String[1]:item.getImage().split(","));
		child.setNum(num);
		child.setPrice(item.getPrice());
		
		list.add(child);
		
		
		jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
		
		
	}

	@Override
	public List<TbItemChild> showcart(HttpServletRequest request) {
		       // 2 获取key 使用用户名作为目录
				String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
				// 用户信息
				String jsonUser = HttpClientUtil.doPost(passportUrl + token);
				// 转为对象
				EgoResult er = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);
		
				String key = cartKey + ((LinkedHashMap) er.getData()).get("username");
	
				String string = jedisDaoImpl.get(key);
				List<TbItemChild> toList = JsonUtils.jsonToList(string, TbItemChild.class);
				
				
				return toList;
	}

	@Override
	public EgoResult delete(long id, HttpServletRequest req) {
		  // 2 获取key 使用用户名作为目录
		String token = CookieUtils.getCookieValue(req, "TT_TOKEN");
		// 用户信息
		String jsonUser = HttpClientUtil.doPost(passportUrl + token);
		// 转为对象
		EgoResult er = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);

		String key = cartKey + ((LinkedHashMap) er.getData()).get("username");

		String string = jedisDaoImpl.get(key);
		List<TbItemChild> toList = JsonUtils.jsonToList(string, TbItemChild.class);
		TbItemChild delechild=null;
		for (TbItemChild tbItemChild : toList) {
			 if(tbItemChild.getId()==id) {
				 delechild=tbItemChild;
			 }
		}
		//移除出去
		toList.remove(delechild);
		String ok =jedisDaoImpl.set(key, JsonUtils.objectToJson(toList));
		EgoResult ego = new EgoResult();
		if(ok.equals("OK")){
			ego.setStatus(200);
			}
		return ego;
	}

	@Override
	public EgoResult updata(long id, int num, HttpServletRequest req) {
	
		  // 2 获取key 使用用户名作为目录
				String token = CookieUtils.getCookieValue(req, "TT_TOKEN");
				// 用户信息
				String jsonUser = HttpClientUtil.doPost(passportUrl + token);
				// 转为对象
				EgoResult er = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);

				String key = cartKey + ((LinkedHashMap) er.getData()).get("username");

				String string = jedisDaoImpl.get(key);
				List<TbItemChild> toList = JsonUtils.jsonToList(string, TbItemChild.class);
				
				for (TbItemChild tbItemChild : toList) {
					 if(tbItemChild.getId()==id) {
						tbItemChild.setNum(num);
						
						
						
						
						
	
					 }
				}
				//移除出去
				String ok =jedisDaoImpl.set(key, JsonUtils.objectToJson(toList));
				EgoResult ego = new EgoResult();
				if(ok.equals("OK")){
					ego.setStatus(200);
					}
				return ego;
	}

	
	
	
	
	
	
}
