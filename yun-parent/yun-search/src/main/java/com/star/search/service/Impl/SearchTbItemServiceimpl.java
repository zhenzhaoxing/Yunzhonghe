package com.star.search.service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import com.Star.pojo.TbItem;
import com.Star.pojo.TbItemCat;
import com.Star.pojo.TbItemDesc;
import com.alibaba.dubbo.config.annotation.Reference;
import com.star.commons.pojo.TbItemChild;
import com.star.dubbo.service.TbItemCatDubboService;
import com.star.dubbo.service.TbItemDescDubboService;
import com.star.dubbo.service.TbItemDubboService;
import com.star.search.service.SearchTbItemService;
@Service
public class SearchTbItemServiceimpl implements SearchTbItemService {
	//商品表
	
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;
	@Reference
	private TbItemDescDubboService tbItemDescDubboServiceImpl;
	@Reference
	private  TbItemDubboService tbItemDubboServiceImpl;
	//云solr
	@Resource
	private CloudSolrClient solrClient;
/**
 *  要操作3张表
 */
	@Override
	public void init() throws SolrServerException, IOException {
		//solrClient.deleteByQuery("*:*");
		//solrClient.commit();
		List<TbItem> listitem = tbItemDubboServiceImpl.selallbystatus((byte)1);
		for (TbItem item : listitem) {
			
			//获取父类的名字
			TbItemCat cat = tbItemCatDubboServiceImpl.selById(item.getCid());
			
			//获取描述
			TbItemDesc desc = tbItemDescDubboServiceImpl.selbyid(item.getId());
			
			SolrInputDocument doc = new SolrInputDocument();
			
			doc.addField("id", item.getId());
			doc.addField("item_title", item.getTitle());
			doc.addField("item_sell_point", item.getSellPoint());
			doc.addField("item_price",item.getPrice() );
			doc.addField("item_image", item.getImage());
			//父类名称
			doc.addField("item_category_name", cat.getName());
			//买点
			doc.addField("item_desc", desc.getItemDesc());
			doc.addField("item_updated", item.getUpdated());
			 solrClient.add(doc);
		}
		solrClient.commit();
		
		
		
	}
	   /**
	    * 根据字段完成搜索功能
	    */
@Override
public Map<String, Object> selectbyq(String query, int page, int rows) throws SolrServerException, IOException {
	        
	
	
	
	         SolrQuery param = new SolrQuery();
	          param.setStart(rows*(page-1));
	          param.setRows(rows);
	          param.setQuery("item_keywords:"+query);
	          param.setHighlight(true);
	          param.addHighlightField("item_title");
	          param.setHighlightSimplePost("<span style='color:red'>");
	          param.setHighlightSimplePre("</span>");
	          param.setSort("item_updated", ORDER.desc);
	            //商品信息
	          List<TbItemChild> listChild = new ArrayList<>();
	           //响应的内容 包括头部
	          QueryResponse response = solrClient.query(param);
	          // 体部 没有高亮的内容
	          SolrDocumentList solrDocumentList = response.getResults();
	          //key string 是 id号  这是高亮的内容
	          Map<String, Map<String, List<String>>> maphight = response.getHighlighting();
	         
	            //遍历 
	          for (SolrDocument doc : solrDocumentList) {
	        	  TbItemChild  child = new TbItemChild();
	        	  child.setId(Long.parseLong(doc.getFieldValue("id").toString()));
	        	  //获取标题
	        	  List<String> list = maphight.get(doc.getFieldValue("id")).get("item_title");
	        	  //设置标题
	        	  if(list!=null&&list.size()>0) {child.setTitle(list.get(0));}
	        	  else {child.setTitle(doc.getFieldValue("item_title").toString());}
	        	  //设置价格
	        	  child.setPrice((Long)doc.getFieldValue("item_price"));
	        	  //卖点
	        	  child.setSellPoint(doc.getFieldValue("item_sell_point").toString());
	        	  //图片
	        	  Object image = doc.getFieldValue("item_image");
	  			  child.setImages(image==null||image.equals("")?new String[1]:image.toString().split(","));
	        	  listChild.add(child); 
			}
	          
	          
	          
	          Map<String, Object> resultMap = new HashMap<String, Object>();
	              resultMap.put("itemList", listChild);
	              //rows 每页显示多少
	              
	              resultMap.put("totalPages", solrDocumentList.getNumFound()%rows==0?solrDocumentList.getNumFound()/rows:(solrDocumentList.getNumFound()/rows)+1);
	          return resultMap;
}     /*
           *//**
            *   
            *//*
	@Override
	public int addso(Map<String, Object> map, String desc) throws SolrServerException, IOException {

		SolrInputDocument doc = new SolrInputDocument();
		
		doc.setField("id", map.get("id"));
		doc.setField("item_title", map.get("title"));
		doc.setField("item_sell_point", map.get("sellPoint"));
		doc.setField("item_price", map.get("price"));
		doc.setField("item_image", map.get("image"));
		//父类名称                                                                       
		doc.addField("item_category_name", tbItemCatDubboServiceImpl.selById((Integer)map.get("cid")).getName());
		//买点
		doc.addField("item_desc", desc);
		 UpdateResponse response = solrClient.add(doc);
	
	    solrClient.commit();
		
		
	    if(response.getStatus()==0){
			return 1;
		}
		return 0;
	}
*/
}
