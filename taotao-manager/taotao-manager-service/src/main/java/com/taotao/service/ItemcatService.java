package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeData;

public interface ItemcatService {
	/**
	 * 
	 * @param pid 父亲结点的ID
	 * @return
	 */
	List<EUTreeData> getItemcatList(long pid);
}
