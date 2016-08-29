package com.taotao.service;

import com.taotao.common.pojo.EUDatagridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	/**
	 * 通过商品ID获取商品信息
	 * @param id
	 * @return
	 */
	TbItem getItemById(long id);
	/**
	 * 获取商品列表
	 * @param page
	 * @param rows
	 * @return
	 */
	EUDatagridResult getItemList(Integer page,Integer rows);
	/**
	 * 插入商品
	 * @param item
	 * @param desc
	 * @param itemParams
	 * @return
	 */
	TaotaoResult insertItem(TbItem item,String desc,String itemParams);
}
