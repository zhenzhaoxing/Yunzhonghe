package com;

import java.util.ArrayList;
import java.util.List;

import com.star.commons.utils.JsonUtils;


public class Demo {
	
	
	
	
	
	
	
	
 public static void main(String[] args) {
	
	 List<Object> listNode = new ArrayList<Object>();
	 listNode.add("[{\"u\":\"/products/1.html\",\"n\":\"<a href='/products/1.html'>图书、音像、电子书刊</a>\",\"i\":[{\"u\":\"/products/2.html\",\"n\":\"电子书刊\",\"i\":[\"/products/3.html|电子书\",\"/products/4.html|网络原创\",\"/products/5.html|数字杂志\",\"/products/6.html|多媒体图书\"]},{\"u\":\"/products/7.html\",\"n\":\"音像\",\"i\":[\"/products/8.html|音乐\",\"/products/9.html|影视\",\"/products/10.html|教育音像\"]},{\"u\":\"/products/11.html\",\"n\":\"英文原版\",\"i\":[\"/products/12.html|少儿\",\"/products/13.html|商务投资\",\"/products/14.html|英语学习与考试\",\"/products/15.html|小说\",\"/products/16.html|传记\",\"/products/17.html|励志\"]}]");
	 String json = JsonUtils.objectToJson(listNode);
	 System.out.println(json);
	 PortalMenu1 jsonToPojo = JsonUtils.jsonToPojo(json, PortalMenu1.class);
	 System.out.println(jsonToPojo);
}
}

class PortalMenu1 {
	private List<Object> data;

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}
}

