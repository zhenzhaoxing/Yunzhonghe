package com.star.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.Star.pojo.TbContent;
import com.alibaba.dubbo.config.annotation.Reference;
import com.star.commons.utils.JsonUtils;
import com.star.dubbo.service.TbContentDubboService;
import com.star.redis.dao.JedisDao;
import com.star.service.TbContentService;
@Service
public class TbContentServiceImpl implements TbContentService {

   @Reference
  private TbContentDubboService tbContentDubboServiceImpl;
   
    @Resource
	private JedisDao JedisDaoImpl;
	@Value("${redis.bigpic.key}")
	private String key;
   //  //var data = [{"srcB":"},{},{}] 8个字段 list 里面有多个字段 map形式
   public String showBigPic() {
		//先判断在redis中是否存在
	   if(JedisDaoImpl.exists(key)) {
		   String value = JedisDaoImpl.get(key);
			if(value!=null&&!value.equals("")){
				return value;
	   }
	   }
		//如果不存在从mysql中取,取完后放入redis中
	   List<TbContent> list = tbContentDubboServiceImpl.selByCount(6, true);
		
		List<Map<String,Object>> listResult = new ArrayList<>();
		for (TbContent tc : list) {
			Map<String,Object> map = new HashMap<>();
			
			map.put("srcB", tc.getPic2());
			map.put("height", 240);
			map.put("alt", "对不起,加载图片失败");
			map.put("width", 670);
			map.put("src", tc.getPic());
			map.put("widthB", 550);
			map.put("href", tc.getUrl() );
			map.put("heightB", 240);
			
			listResult.add(map);
		}
		//把对象转成字符串
		String listJson = JsonUtils.objectToJson(listResult);
		//把数据放入到redis中
		JedisDaoImpl.set(key, listJson);
		return listJson;
	}

}
