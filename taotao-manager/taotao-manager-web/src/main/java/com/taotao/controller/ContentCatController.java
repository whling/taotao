package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeData;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCatService;

/**
 * contentcategory的controller
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/content/category")
public class ContentCatController {
	@Autowired
	private ContentCatService contentCatService;

	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeData> getContentCatList(@RequestParam(defaultValue = "0", value = "id") long pid) {
		List<EUTreeData> catList = contentCatService.getContentCatList(pid);
		return catList;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String updateContentCatById(long id,String name) {
		contentCatService.updateContentCatById(id, name);
		return "ok";
	}

	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createContentCat(long parentId, String name) {
		TaotaoResult taotaoResult = contentCatService.createContentCat(parentId, name);
		return taotaoResult;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteContentCatById(Long parentId,Long id){
		contentCatService.deleteContentCatById(parentId, id);
		return "ok";
	}
}
