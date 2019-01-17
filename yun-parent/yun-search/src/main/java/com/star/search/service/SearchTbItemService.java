package com.star.search.service;

import java.io.IOException;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;

public interface SearchTbItemService {
	/**
	 * 初始化
	 * @throws SolrServerException
	 * @throws IOException
	 */
	void init() throws SolrServerException, IOException;
	
	/**
	 * 查询
	 * @param query
	 * @param page
	 * @param rows
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	
	Map<String, Object>  selectbyq(String query,int page,int rows)throws SolrServerException, IOException;




  //  int addso(Map<String,Object> map,String desc) throws SolrServerException, IOException;



}
