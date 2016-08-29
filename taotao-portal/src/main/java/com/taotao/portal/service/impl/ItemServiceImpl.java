package com.taotao.portal.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.custom.ItemSearchCustom;
import com.taotao.portal.service.ItemService;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;

/**
 * 通过httpclient远程调用服务获取商品信息
 * 
 * @author Administrator
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Value("${rest.baseUrl}")
	private String BASEURL;
	
	@Value("${rest.itemUrl}")
	private String ITEMURL;
	
	@Value("${rest.item.DescUrl}")
	private String REST_ITEM_DESCURL;
	
	@Value("${rest.item.ParamItemUrl}")
	private String REST_ITEM_PARAMITEMURL;

	@Override
	public ItemSearchCustom getItemByItemid(long itemId) {
		String result = HttpClientUtil.doGet(BASEURL + ITEMURL + "/" + itemId);
		if (!StringUtils.isBlank(result)) {
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(result, ItemSearchCustom.class);
			ItemSearchCustom itemCustom = (ItemSearchCustom) taotaoResult.getData();
			return itemCustom;
		}
		return null;

	}

	@Override
	public String getItemDescByItemid(long itemId) {
		String result = HttpClientUtil.doGet(BASEURL + REST_ITEM_DESCURL + "/" + itemId);
		if (!StringUtils.isBlank(result)) {
			TaotaoResult taotaoResult = TaotaoResult.format(result);
			String itemDesc = (String) taotaoResult.getData();
			return itemDesc;
		}
		return null;
	}

	@Override
	public String getItemParamItemByItemid(long itemId) {
		String result = HttpClientUtil.doGet(BASEURL + REST_ITEM_PARAMITEMURL + "/" + itemId);
		if (!StringUtils.isBlank(result)) {
			TaotaoResult taotaoResult = TaotaoResult.format(result);
			String itemParam = (String) taotaoResult.getData();
			return itemParam;
		}
		return null;
	}

}
