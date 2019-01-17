package com.star.dubbo.service;

import java.util.List;

import com.Star.pojo.TbContent;
import com.star.commons.pojo.EasyUIDataGrid;

public interface TbContentDubboService {
/**
 * 分页查询
 * @param categoryId
 * @param page
 * @param rows
 * @return
 */

EasyUIDataGrid selByCateID(Long categoryId,int page,int rows);
//增加新的内容
int  insertContent(TbContent content);
/**
 * 批量删除
 * @param ids
 * @return
 * @throws Exception 
 */
int deletebyid(String ids) throws Exception;


/**
 * 通过个数来查
 */

//TbContent selebycb(int count,boolean isSort);
List<TbContent> selByCount(int count,boolean isSort);


}
