package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDatagridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemparamService;
/**
 * 商品规格模板的controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/item/param")
public class ItemparamController {
	@Autowired
	private ItemparamService itemparamService;
	
	@RequestMapping("/query/itemcatid/{itemcatid}")
	@ResponseBody
	public TaotaoResult getItemparamByItemcatId(@PathVariable(value="itemcatid")long itemcatId){
		TaotaoResult taotaoResult = itemparamService.getItemparamsByItemcatId(itemcatId);
		return taotaoResult;
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult insertItemparam(@PathVariable long cid,String paramData){
		TaotaoResult taotaoResult = itemparamService.insertItemparam(cid, paramData);
		return taotaoResult;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDatagridResult insertItemparam(@RequestParam(required=true,defaultValue="1")Integer page,@RequestParam(required=true,defaultValue="20")Integer rows){
		EUDatagridResult datagridResult = itemparamService.selectItemparamList(page, rows);
		return datagridResult;
	}
}
