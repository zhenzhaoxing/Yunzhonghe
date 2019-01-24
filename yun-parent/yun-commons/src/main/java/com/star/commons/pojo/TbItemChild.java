package com.star.commons.pojo;

import com.Star.pojo.TbItem;

public class TbItemChild extends TbItem{
	
	private String[]images;
     private Boolean Enough;
	public Boolean getEnough() {
		return Enough;
	}

	public void setEnough(Boolean enough) {
		Enough = enough;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}
	
}
