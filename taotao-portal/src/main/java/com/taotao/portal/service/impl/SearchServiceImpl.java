package com.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.custom.ItemSearchResult;
import com.taotao.portal.service.SearchService;
import com.taotao.utils.HttpClientUtil;

@Service
public class SearchServiceImpl implements SearchService {
	@Value("${search.baseUrl}")
	private String url;

	@Override
	public ItemSearchResult queryItemBySearch(String keywords, Integer page) {
		Map<String, String> params=new HashMap<String, String>();
		params.put("q", keywords);
		if(page!=null){
			params.put("page", page+"");
		}
		//调用httpclient
		String result = HttpClientUtil.doGet(url, params);
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(result, ItemSearchResult.class);
		ItemSearchResult itemSearchResult = (ItemSearchResult) taotaoResult.getData();
		return itemSearchResult;
	}

}
