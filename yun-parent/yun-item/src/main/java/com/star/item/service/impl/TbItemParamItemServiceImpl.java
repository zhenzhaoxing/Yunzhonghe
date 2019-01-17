package com.star.item.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Star.pojo.TbItemParamItem;
import com.alibaba.dubbo.config.annotation.Reference;
import com.star.commons.utils.JsonUtils;
import com.star.dubbo.service.TbItemParamItemDubboSerice;
import com.star.item.pojo.ParamItem;
import com.star.item.service.TbItemParamItemService;
@Service
public class TbItemParamItemServiceImpl implements TbItemParamItemService {
	@Reference
	private TbItemParamItemDubboSerice tbItemParamItemDubboSericeImpl;
	@Override
	public String showParam(long itemId) {
		TbItemParamItem item = tbItemParamItemDubboSericeImpl.selByItemid(itemId);
		
		List<ParamItem> list = JsonUtils.jsonToList(item.getParamData(), ParamItem.class);
		StringBuffer sf = new StringBuffer();
		for (ParamItem paramItem : list) {
			sf.append("<table width='500' style='color:gray;'>");
			for (int i = 0; i <paramItem.getParams().size(); i++) {
				if(i==0) {
					sf.append("<tr>");
					sf.append("<td align='right' width='30%'>"+paramItem.getGroupName()+"</td>");
					sf.append("<td align='right' width='30%'>"+paramItem.getParams().get(i).getK()+"</td>");
					sf.append("<td>"+paramItem.getParams().get(i).getV()+"</td>");
					sf.append("<tr/>");
				}else {
					sf.append("<tr>");
					sf.append("<td> </td>");
					sf.append("<td align='right' width='30%'>"+paramItem.getParams().get(i).getK()+"</td>");
					sf.append("<td>"+paramItem.getParams().get(i).getV()+"</td>");
					sf.append("<tr/>");
					
				}
				
			}sf.append("</table>");
			sf.append("<hr style='color:gray;'/>");
		}
		
		return sf.toString();
	}

}
