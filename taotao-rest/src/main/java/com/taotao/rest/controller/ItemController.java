package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 商品信息获取的服务发布
 * @author Administrator
 *
 */
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.custom.ItemSearchCustom;
import com.taotao.rest.service.ItemService;
import com.taotao.utils.ExceptionUtil;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	/**
	 * 获取商品的基本信息
	 * @param itemid
	 * @return
	 */
	@RequestMapping("/{itemid}")
	@ResponseBody
	public TaotaoResult getItemByItemid(@PathVariable long itemid){
		try {
			ItemSearchCustom itemCustom = itemService.getItemByItemid(itemid);
			return TaotaoResult.ok(itemCustom);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
	}
	/**
	 * 获取商品介绍详情
	 * @param itemid
	 * @return
	 */
	@RequestMapping("/desc/{itemid}")
	@ResponseBody
	public TaotaoResult showItemDescByItemid(@PathVariable long itemid) {
		try {
			String itemDesc = itemService.getItemDescByItemid(itemid);
			return TaotaoResult.ok(itemDesc);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
	}
	/**
	 * 获取商品规格详情
	 * @param itemid
	 * @return
	 */
	@RequestMapping("/param/{itemid}")
	@ResponseBody
	public TaotaoResult showItemParamByItemid(@PathVariable long itemid) {
		try {
			String itemParamItem = itemService.getItemParamByItemid(itemid);
			return TaotaoResult.ok(itemParamItem);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
