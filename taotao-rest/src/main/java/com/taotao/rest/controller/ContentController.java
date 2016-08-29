package com.taotao.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ContentService;
import com.taotao.rest.service.RedisService;
import com.taotao.utils.ExceptionUtil;

/**
 * 内容服务的发布
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/list/{contentCategoryId}")
	@ResponseBody
	public TaotaoResult getContentListByCid(@PathVariable long contentCategoryId){
		try {
			List<TbContent> list = contentService.getContentListByCid(contentCategoryId);
			return TaotaoResult.ok(list);
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	/**
	 * redis缓存同步
	 * @param contentCategoryId
	 * @return
	 */
	@RequestMapping("/sync/{contentCategoryId}")
	@ResponseBody
	public TaotaoResult synContentListByCid(@PathVariable long contentCategoryId){
		TaotaoResult taotaoResult = redisService.synContent(contentCategoryId);
		return taotaoResult;
	}
	
}
