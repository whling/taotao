package com.taotao.service;


import com.taotao.common.pojo.EUDatagridResult;
import com.taotao.common.pojo.TaotaoResult;

/**
 * 商品规格模板的service
 * @author Administrator
 *
 */
public interface ItemparamService {
	/*
	 * 通过商品类目ID获取商品规格模板
	 */
	TaotaoResult getItemparamsByItemcatId(Long itemcatId);
	/**
	 * 新增商品规格模板
	 * @param cid
	 * @param paramData
	 * @return
	 */
	TaotaoResult insertItemparam(long cid,String paramData);
	/**
	 *  获取商品规格列表
	 * @param page
	 * @param rows
	 * @return
	 */
	EUDatagridResult selectItemparamList(Integer page,Integer rows);
}
