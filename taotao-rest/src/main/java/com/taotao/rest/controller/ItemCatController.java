package com.taotao.rest.controller;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;
import com.taotao.utils.JsonUtils;

/**
 * 门户的商品分类展示
 * @author Administrator
 *
 */
@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	//返回json数据格式的字符串
	@RequestMapping(value="/itemcat/all",produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String showItemCatAll(String callback){
		ItemCatResult catResult = itemCatService.getItemCat();
		String stringJson = JsonUtils.objectToJson(catResult);
		String result=callback+"("+stringJson+")";
		return result;
	}
}
