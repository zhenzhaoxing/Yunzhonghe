package com.star.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.Star.pojo.TbItemCat;
import com.alibaba.dubbo.config.annotation.Reference;

import com.star.commons.utils.JsonUtils;
import com.star.dubbo.service.TbItemCatDubboService;
import com.star.item.pojo.PortalMenu;
import com.star.item.pojo.PortalMenuNode;
import com.star.item.service.TbItemCatService;
import com.star.redis.dao.JedisDao;


@Service
public class TbItemCatServiceImpl implements TbItemCatService {
//首先在上面价格缓存

	@Resource
	private JedisDao jedisDaoImpl;
	@Value("${redis.catege.key}")
	private String key;
	
	// 查询分类
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;
	

	// 通过递归查询出所有分类并按照格式返回去
	@Override
	public PortalMenu showCatMenu() {
		PortalMenu pm = new PortalMenu();
		// 先判断有没有key
		if (jedisDaoImpl.exists(key)) {
			String string = jedisDaoImpl.get(key);
			if (string != null && !string.equals("")) {
				
				pm.setData(JsonUtils.jsonToList(string, Object.class));
				return pm;
			}

		}
		List<TbItemCat> show = tbItemCatDubboServiceImpl.show(0);// 所有一级菜单的信息
		pm.setData(selAll(show));

		return pm;
	}

	// 按照格式进行来查询
	public List<Object> selAll(List<TbItemCat> show) {
		List<Object> listNode = new ArrayList<Object>();
		for (TbItemCat tbItemCat : show) {
			if (tbItemCat.getIsParent()) {
				PortalMenuNode pmd = new PortalMenuNode();
				pmd.setU("/products/" + tbItemCat.getId() + ".html");
				pmd.setN("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
				pmd.setI(selAll(tbItemCatDubboServiceImpl.show(tbItemCat.getId())));

				listNode.add(pmd);
			} else {
				listNode.add("/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName());
			}
		}

		jedisDaoImpl.set(key, JsonUtils.objectToJson(listNode));
		return listNode;
	}

}
