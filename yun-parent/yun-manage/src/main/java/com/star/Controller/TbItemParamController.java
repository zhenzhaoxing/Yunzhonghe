package com.star.Controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Star.pojo.TbItemParam;
import com.star.commons.pojo.EasyUIDataGrid;
import com.star.commons.pojo.EgoResult;
import com.star.service.impl.TbItemParamServiceImpl;

@Controller
public class TbItemParamController {
	@Resource
	private TbItemParamServiceImpl tbItemParamServiceImpl;

	/**
	 * 查询功能
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("item/param/list")
	@ResponseBody
	public EasyUIDataGrid showPage(int page, int rows) {
		return tbItemParamServiceImpl.showPage(page, rows);

	}

	/**
	 * 删除功能
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("item/param/delete")
	@ResponseBody
	public EgoResult delete(String ids) {
		EgoResult er = new EgoResult();
		int i;
		try {
			i = tbItemParamServiceImpl.delete(ids);
			if (i == 1) {
				er.setStatus(200);

			}
		} catch (Exception e) {

			e.printStackTrace();
			er.setData(e.getMessage());
		}

		return er;

	}

	/**
	 * 查询是否有 模板
	 * 
	 * @param catid
	 * @return
	 */
	@RequestMapping("item/param/query/itemcatid/{catid}")
	@ResponseBody
	public EgoResult selBy(@PathVariable long catid) {
		EgoResult er = new EgoResult();
		TbItemParam byCatid = tbItemParamServiceImpl.selByCatid(catid);
		if (byCatid != null) {
			er.setStatus(200);
			er.setData(byCatid);
		}
		return er;

	}

	/**
	 * 添加 类目模板
	 */

	@RequestMapping("item/param/save/{catid}")
	@ResponseBody
	public EgoResult insel(TbItemParam param, @PathVariable long catid) {
		param.setItemCatId(catid);
		return tbItemParamServiceImpl.save(param);

	}

}
