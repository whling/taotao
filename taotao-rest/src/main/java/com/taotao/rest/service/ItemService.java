package com.taotao.rest.service;

import com.taotao.pojo.custom.ItemSearchCustom;

public interface ItemService {
	/**
	 * 通过商品id获取商品信息
	 * @param itemId
	 * @return
	 */
	ItemSearchCustom getItemByItemid(long itemId);
	/**
	 * 通过商品id获取商品描述信息
	 * @param itemId
	 * @return
	 */
	String getItemDescByItemid(long itemId);
	/**
	 * 通过商品id获取商品规格信息
	 * @param itemId
	 * @return
	 */
	String getItemParamByItemid(long itemId);
}
