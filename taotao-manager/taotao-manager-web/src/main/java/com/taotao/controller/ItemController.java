package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDatagridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

/**
 * 商品的controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/id/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable(value="itemId")long id){
		TbItem item = itemService.getItemById(id);
		return item;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDatagridResult getItemList(@RequestParam(required=true,defaultValue="1")Integer page,@RequestParam(required=true,defaultValue="20")Integer rows){
		EUDatagridResult itemList = itemService.getItemList(page, rows);
		return itemList;
	}
	/**
	 * 
	 * @param item	商品的基本信息
	 * @param desc  商品的描述信息
	 * @param itemParams  商品的规格信息
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult saveItem(TbItem item,String desc,String itemParams){
		TaotaoResult result = itemService.insertItem(item,desc,itemParams);
		return result;
	}
}
