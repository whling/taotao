package com.taotao.service;

import com.taotao.common.pojo.EUDatagridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	/**
	 * 查询内容
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	EUDatagridResult queryContentList(long categoryId,Integer page,Integer rows);
	/**
	 * 新增内容
	 * @param content
	 * @return
	 */
	TaotaoResult insertContent(TbContent content);
}
