package com.taotao.portal.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.pojo.custom.ItemSearchResult;
import com.taotao.portal.pojo.ADResult;
import com.taotao.portal.service.ContentService;
import com.taotao.portal.service.SearchService;
import com.taotao.utils.JsonUtils;

@Controller
public class PageController {
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private SearchService searchService;
	/**
	 * 展示门户
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index")
	public String showIndex(Model model) {
		List<ADResult> list = contentService.getADs();
		String json = JsonUtils.objectToJson(list);
		model.addAttribute("ad1", json);
		return "index";
	}
	/**
	 * 门户搜索功能
	 * @param q
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/search")
	public String showSearch(String q,Integer page,Model model) {
		try {
			q = new String(q.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ItemSearchResult itemSearchResult = searchService.queryItemBySearch(q, page);
		model.addAttribute("itemList", itemSearchResult.getItemSearchCustoms());
		model.addAttribute("totalPages", itemSearchResult.getTotalPage());
		model.addAttribute("page", itemSearchResult.getPageNow());
		model.addAttribute("query", q);
		return "search";
	}
}
