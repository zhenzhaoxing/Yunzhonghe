package com.star.test;

import com.star.commons.pojo.EasyUIDataGrid;
import com.star.service.impl.PageShowServiceImpl;

public class demo {
	
    private static  PageShowServiceImpl   pageShowServiceImpl = new PageShowServiceImpl();
    
    
    
     public static PageShowServiceImpl getPageShowServiceImpl() {
		return pageShowServiceImpl;
	}

	public static void setPageShowServiceImpl(PageShowServiceImpl pageShowServiceImpl) {
		demo.pageShowServiceImpl = pageShowServiceImpl;
	}

	public static void main(String[] args) {
		demo  d = new demo();
    	 EasyUIDataGrid show = d.show(2, 30);
    	System.out.println(show);
    	
    	 
	}
     
     public  EasyUIDataGrid show(int page,int rows) {
			return pageShowServiceImpl.show(page, rows);
     	 
      }
}
