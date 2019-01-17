package com.star.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.Star.pojo.TbContent;
import com.alibaba.dubbo.config.annotation.Reference;

import com.star.commons.pojo.EasyUIDataGrid;
import com.star.commons.pojo.EgoResult;
import com.star.commons.utils.JsonUtils;
import com.star.dubbo.service.TbContentDubboService;
import com.star.redis.dao.JedisDao;
import com.star.service.TbContentService;
@Service
public class TbContentServiceImpl implements TbContentService {

	
	@Reference
	private TbContentDubboService  tbContentDubboServiceImpl;
	@Resource
	private JedisDao jedisDaoImpl;
	@Value("${redis.bigpic.key}")
	private String key;
	
	@Override
	public EasyUIDataGrid showContent(Long categoryId, int page, int rows) {
	
		return tbContentDubboServiceImpl.selByCateID(categoryId, page, rows);
	}


	@Override
	public EgoResult insertCon(TbContent tbcontent) {
		EgoResult er = new EgoResult();
		Date d = new Date();
		tbcontent.setCreated(d);
		tbcontent.setUpdated(d);
		int content = tbContentDubboServiceImpl.insertContent(tbcontent);
		if(jedisDaoImpl.exists(key)) {
		String string = jedisDaoImpl.get(key);
		if(string!=null&&!string.equals("")){
		List<HashMap> list = JsonUtils.jsonToList(string, HashMap.class);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("srcB", tbcontent.getPic2());
		map.put("height", 240);
		map.put("alt", "对不起,加载图片失败");
		map.put("width", 670);
		map.put("src", tbcontent.getPic());
		map.put("widthB", 550);
		map.put("href", tbcontent.getUrl() );
		map.put("heightB", 240);
		
		//保证六个
		if(list.size()==6){
			list.remove(5);
		}
		list.add(0, map);
		//再把hashmap总体数据封装 然后添加给 redis
		jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
		}
		}
		if(content>0) {
			er.setStatus(200);
		}
		return er;
	}
	/**
	 * 删除了数据后 怎么同步rerdis数据库    当把数据删除后 然后去mysql数据库查询所有的数据（删除后的） 打包为json 然后通过key删除redis商的数据
	 * 
	 * 下一步就是把新的数据赋值上
	 */
	@Override
	public int delete(String ids) throws Exception {
		int i = tbContentDubboServiceImpl.deletebyid(ids);
		//查询出的新数据
		List<TbContent> newdataList = tbContentDubboServiceImpl.selByCount(0, true);
		//判断是否存在
		List<Map<String,Object>> listnode= new ArrayList<>();
		for (TbContent tbcontent : newdataList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("srcB", tbcontent.getPic2());
			map.put("height", 240);
			map.put("alt", "对不起,加载图片失败");
			map.put("width", 670);
			map.put("src", tbcontent.getPic());
			map.put("widthB", 550);
			map.put("href", tbcontent.getUrl() );
			map.put("heightB", 240);
			listnode.add(map);
		}
		if(jedisDaoImpl.exists(key)) {
			
			jedisDaoImpl.set(key, JsonUtils.objectToJson(listnode));
		}
		//selByCount
		
		
		return i;
	}

}
