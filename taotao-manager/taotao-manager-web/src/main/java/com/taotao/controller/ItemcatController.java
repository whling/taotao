package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeData;
import com.taotao.service.ItemcatService;

/**
 * 商品分类的controller
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/item/cat")
public class ItemcatController {
	@Autowired
	private ItemcatService itemcatService;

	/**
	 * 当展开一个封闭的节点，如果节点没有加载子节点，它将会把节点id的值作为http请求参数并命名为'id'，通过URL发送到服务器上面检索子节点
	 * 
	 * @param pid
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeData> getItemcatList(@RequestParam(value="id",defaultValue="0")long pid) {
		List<EUTreeData> itemcatList = itemcatService.getItemcatList(pid);
		return itemcatList;
	}

}
