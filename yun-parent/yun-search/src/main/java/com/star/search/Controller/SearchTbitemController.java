package com.star.search.Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.star.search.pojo.Gui;
import com.star.search.service.SearchTbItemService;

@Controller
public class SearchTbitemController {

	@Resource
	private SearchTbItemService SearchTbItemServiceimpl;

	/**
	 * 导入数据到索引库 //成功返回200 否则返回1
	 * 
	 * @param callback
	 * @return
	 */
	@RequestMapping("item/init")
	@ResponseBody
	public MappingJacksonValue demo(String callback) {
		Gui s = new Gui();
		try {
			SearchTbItemServiceimpl.init();
			s.status = 200;
		} catch (Exception e) {
			s.status = 1;

		}
		// 把构造函数方法转换为json字符串
		MappingJacksonValue mj = new MappingJacksonValue(s);
		// 最终返会结果的函数名
		mj.setJsonpFunction(callback);
		return mj;
	}
	/**
	 * 商品搜索功能
	 * 
	 * @param 
	 * @return
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	     @RequestMapping("search.html")
         public String  search(Model model,String q,@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="12")int rows) throws SolrServerException, IOException {
			//因为这是 get请求 可能会出现乱码
	    	 try {
				q= new String(q.getBytes("iso-8859-1"),"utf-8");
				
				Map<String, Object> map = SearchTbItemServiceimpl.selectbyq(q, page, rows);
				model.addAttribute("query", q);
				model.addAttribute("itemList", map.get("itemList"));
				model.addAttribute("totalPages", map.get("totalPages"));
				model.addAttribute("page", page);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	 return "search";
	     }
   

/*添加时因引用了 activemq消息中间件 所以这就没必要写了*/
	     /**
	 	 * 商品添加索引库功能
	 	 * 
	 	 * @param 
	 	 * @return
	 	 * @throws IOException 
	 	 * @throws SolrServerException 
	 	   @RequestBody 把json 转为对象数据
	 
	     @RequestMapping("solr/add")
	 	 @ResponseBody
	 	 public int add(@RequestBody Map<String,Object> map) {
	    	// System.out.println(map);
	    	 //System.out.println(map.get("item"));
	    	// {id=154748580063731, title=苹果mac, sellPoint=高清晰，16G运行内存，512G固态硬盘，配上m
	      try {
			return SearchTbItemServiceimpl.addso((LinkedHashMap)map.get("item"), map.get("desc").toString());
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		

	     }
	     	 */

}
