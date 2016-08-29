package com.taotao.portal.service;

import java.util.List;

import com.taotao.portal.pojo.ADResult;

public interface ContentService {
	/**
	 * 从远程获取广告位信息
	 * @return
	 */
	List<ADResult> getADs();
}
