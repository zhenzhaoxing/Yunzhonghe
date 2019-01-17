package com.star.Controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Star.pojo.TbItem;
import com.star.commons.pojo.EasyUIDataGrid;
import com.star.commons.pojo.EgoResult;
import com.star.service.impl.PageShowServiceImpl;

@Controller
public class TbItemController {

	EgoResult e = null;
	@Resource
	private PageShowServiceImpl pageShowServiceImpl;

	/**
	 * 
	 * 分页显示商品
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("item/list")
	@ResponseBody
	public EasyUIDataGrid show(int page, int rows) {
		return pageShowServiceImpl.show(page, rows);

	}

	// 修改状态 返回状态为200 说明成功 删除
	@RequestMapping("rest/item/delete")
	@ResponseBody
	public EgoResult delete(String ids) {
		e = new EgoResult();
		int i = pageShowServiceImpl.updItemStatus(ids, (byte) 3);
		if (i == 1) {

			e.setStatus(200);
		}
		return e;

	}

	// 修改状态 返回状态为200 说明成功 上架
	@RequestMapping("rest/item/reshelf")
	@ResponseBody
	public EgoResult reshelf(String ids) {
		e = new EgoResult();
		int i = pageShowServiceImpl.updItemStatus(ids, (byte) 1);
		if (i == 1) {

			e.setStatus(200);
		}
		return e;

	}

	// 修改状态 返回状态为200 说明成功 下架
	@RequestMapping("rest/item/instock")
	@ResponseBody
	public EgoResult instock(String ids) {
		e = new EgoResult();
		int i = pageShowServiceImpl.updItemStatus(ids, (byte) 2);
		if (i == 1) {

			e.setStatus(200);
		}
		return e;

	}
    @RequestMapping("item/save")
    @ResponseBody
    public EgoResult insert(TbItem item,String desc,String itemParams) {
    	EgoResult er = new EgoResult();
    			int index;
    	try {
			index = pageShowServiceImpl.save(item, desc,itemParams);
			if(index==1) {
				er.setStatus(200);
			}
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		return er;
    }
}
