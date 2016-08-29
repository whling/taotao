package com.taotao.rest.service;

import java.util.List;

import com.taotao.pojo.TbContent;

public interface ContentService {
	/**
	 * 通过内容分类ID获取内容列表
	 * @param contentCategoryId
	 * @return
	 */
	List<TbContent> getContentListByCid(long contentCategoryId);
}
