package com.taotao.search.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.custom.ItemSearchResult;

public interface ItemSearchService {
	TaotaoResult importItemToIndex()throws Exception;
	ItemSearchResult queryItemByQuery(String keywords,Integer page,Integer rows)throws Exception;
}
