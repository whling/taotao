package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeData;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemcatService;

@Service
public class ItemcatServiceImpl implements ItemcatService{
	@Autowired
	private TbItemCatMapper itemCatMapper;
	/**
	 * 通过父亲结点的ID，查询子商品分类下的目录
	 */
	@Override
	public List<EUTreeData> getItemcatList(long pid) {
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(pid);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<EUTreeData> treeDatas=new ArrayList<EUTreeData>();
		if(list!=null && list.size()>0){
			for (TbItemCat tbItemCat : list) {
				EUTreeData data=new EUTreeData();
				data.setId(tbItemCat.getId());
				data.setText(tbItemCat.getName());
				data.setState(tbItemCat.getIsParent()?"closed":"open");
				treeDatas.add(data);
			}
		}
		return treeDatas;
	}

}
