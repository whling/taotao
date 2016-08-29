package com.taotao.search.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.parser.Keywords;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.custom.ItemSearchResult;
import com.taotao.search.service.ItemSearchService;
import com.taotao.utils.ExceptionUtil;

/**
 * 商品查询的服务发布
 * 
 * @author Administrator
 *
 */

@Controller
public class ItemSearchController {
	@Autowired
	private ItemSearchService itemSearchService;

	/**
	 * 数据库数据与索引库数据同步
	 * 
	 * @return
	 */
	@RequestMapping("/manager/importall")
	@ResponseBody
	public TaotaoResult importall() {
		TaotaoResult taotaoResult;
		try {
			taotaoResult = itemSearchService.importItemToIndex();
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return taotaoResult;
	}

	@RequestMapping(value="/query")
	@ResponseBody
	public TaotaoResult search(@RequestParam(value = "q") String keywords,
			@RequestParam(required = true, defaultValue = "1") Integer page,
			@RequestParam(required = true, defaultValue = "60") Integer rows) {
		// 查询条件不能为空
		if (StringUtils.isBlank(keywords)) {
			return TaotaoResult.build(400, "查询条件不能为空");
		}
		ItemSearchResult itemSearchResult;
		try {
			//GET方式请求的中文乱码问题处理
			keywords=new String(keywords.getBytes("ISO-8859-1"),"UTF-8");
			itemSearchResult = itemSearchService.queryItemByQuery(keywords, page, rows);
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok(itemSearchResult);
	}
}
