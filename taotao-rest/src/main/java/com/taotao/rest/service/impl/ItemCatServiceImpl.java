package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.ItemCatNode;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper itemCatMapper;

	/**
	 * 获取前台首页的商品分类展示
	 */
	@Override
	public ItemCatResult getItemCat() {
		ItemCatResult itemCatResult = new ItemCatResult();
		itemCatResult.setData(getItemCatList(0));
		return itemCatResult;
	}
	//此方法查询出了所有的节点信息（递归调用）
	private List<?> getItemCatList(long parentId) {
		PageHelper.startPage(1, 14);
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//此集合用于存储子目录节点的信息
		List catNodes = new ArrayList();
		if (list != null && list.size() > 0) {
			for (TbItemCat itemCat : list) {
				ItemCatNode catNode = new ItemCatNode();
				Long id = itemCat.getId();
				String name = itemCat.getName();
				Boolean isParent = itemCat.getIsParent();
				if (isParent) {
					//父节点，表示需要递归调用获取子目录
					List<?> catList = getItemCatList(id);
					catNode.setItem(catList);
					String url="/products/" + id + ".html";
					catNode.setUrl(url);
					catNode.setName("<a href='"+url+"'>"+name+"</a>");
					catNodes.add(catNode);
				} else {
					catNodes.add("/products/" + id + ".html"+"|"+name);
				}
			}
		}
		return catNodes;
	}

}
