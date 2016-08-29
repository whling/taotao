package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeData;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCatService {
	/**
	 * 获取contentCat列表
	 * @param pid
	 * @return
	 */
	List<EUTreeData> getContentCatList(long pid);
	/**
	 * 新增内容分类
	 * @param pid
	 * @param name
	 * @return
	 */
	TaotaoResult createContentCat(Long pid,String name);
	/**
	 * 删除单个节点，修改父节点的属性（isParent）
	 * @param pid
	 * @param id
	 */
	void deleteContentCatById(Long pid ,Long id);
	/**
	 * 更新节点名称
	 * @param id
	 * @param name
	 */
	void updateContentCatById(long id,String name);
}
