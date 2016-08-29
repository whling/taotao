package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.custom.ItemSearchCustom;
import com.taotao.portal.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;

	@RequestMapping("/{itemid}")
	public String showItemByItemid(@PathVariable long itemid, Model model) {
		ItemSearchCustom itemSearchCustom = itemService.getItemByItemid(itemid);
		itemSearchCustom.setId(itemid + "");
		model.addAttribute("item", itemSearchCustom);
		return "item";
	}

	@RequestMapping(value="/desc/{itemid}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String showItemDescByItemid(@PathVariable long itemid) {
		String result = itemService.getItemDescByItemid(itemid);
		return result;
	}

	@RequestMapping(value="/param/{itemid}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String showItemParamByItemid(@PathVariable long itemid) {
		String result =itemService.getItemParamItemByItemid(itemid);
		return result;
	}
}
