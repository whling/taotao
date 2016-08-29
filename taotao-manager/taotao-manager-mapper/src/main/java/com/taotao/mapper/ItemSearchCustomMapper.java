package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.custom.ItemSearchCustom;
/**
 * 自定义mapper，用于从数据库向索引库中导入数据
 * @author Administrator
 *
 */
public interface ItemSearchCustomMapper {
	List<ItemSearchCustom> getSearchIndexFromDB();
}
