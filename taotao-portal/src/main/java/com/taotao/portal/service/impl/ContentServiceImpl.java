package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.ADResult;
import com.taotao.portal.service.ContentService;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;

@Service
public class ContentServiceImpl implements ContentService {
	@Value("${rest.baseUrl}")
	private String baseUrl;
	@Value("${rest.index_adsUrl}")
	private String index_adsUrl;
	@Value("${index.height}")
	private Integer height;
	@Value("${index.heightB}")
	private Integer heightB;
	@Value("${index.width}")
	private Integer width;
	@Value("${index.widthB}")
	private Integer widthB;

	@Override
	public List<ADResult> getADs() {
		// 远程调用服务，返回门户广告信息
		String string = HttpClientUtil.doGet(baseUrl + index_adsUrl);
		// 返回的数据为Taotaoresult的json格式数据.其中result的data属性为content的list集合
		TaotaoResult taotaoResult = TaotaoResult.formatToList(string, TbContent.class);
		List<TbContent> contents = (List<TbContent>) taotaoResult.getData();
		List<ADResult> adResults = new ArrayList<ADResult>();
		if (contents != null && contents.size() > 0) {
			for (TbContent content : contents) {
				ADResult adResult = new ADResult();
				adResult.setSrc(content.getPic());
				adResult.setSrcB(content.getPic2());
				adResult.setAlt(content.getSubTitle());
				adResult.setHref(content.getUrl());
				adResult.setHeight(height);
				adResult.setHeightB(heightB);
				adResult.setWidth(width);
				adResult.setWidthB(widthB);
				adResults.add(adResult);
			}
		}
		return adResults;
	}

}
