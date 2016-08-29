package com.taotao.portal.service;

import com.taotao.pojo.custom.ItemSearchResult;

public interface SearchService {
	/**
	 * 通过此接口调用搜索服务
	 * @param keywords
	 * @param page
	 * @return
	 */
	ItemSearchResult queryItemBySearch(String keywords,Integer page);
}
