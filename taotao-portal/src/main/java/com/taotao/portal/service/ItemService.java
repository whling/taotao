package com.taotao.portal.service;

import com.taotao.pojo.custom.ItemSearchCustom;

public interface ItemService {
	/**
	 * 通过商品id获取商品信息
	 * @param itemId
	 * @return
	 */
	ItemSearchCustom getItemByItemid(long itemId);
	/**
	 * 获取商品描述信息
	 * @param itemId
	 * @return
	 */
	String getItemDescByItemid(long itemId);
	/**
	 * 获取商品规格信息
	 * @param itemId
	 * @return
	 */
	String getItemParamItemByItemid(long itemId);
}
