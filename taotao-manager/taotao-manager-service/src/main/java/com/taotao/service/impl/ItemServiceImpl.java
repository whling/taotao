package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDatagridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
import com.taotao.utils.IDUtils;

/**
 * 商品管理service
 * 
 * @author Administrator
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper paramItemMapper;

	@Override
	public TbItem getItemById(long id) {
		return itemMapper.selectByPrimaryKey(id);
	}

	@Override
	public EUDatagridResult getItemList(Integer page, Integer rows) {
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		PageInfo pageInfo = new PageInfo(list);
		EUDatagridResult datagridResult = new EUDatagridResult();
		datagridResult.setTotal(pageInfo.getTotal());
		datagridResult.setRows(list);
		return datagridResult;
	}

	@Override
	public TaotaoResult insertItem(TbItem item,String desc ,String itemParams) {
		// 一些页面表单中没有的参数
		//添加商品的时间
		Date date=new Date();
		//商品主键
		long id = IDUtils.genItemId();
		item.setId(id);
		item.setStatus((byte) 1);
		item.setCreated(date);
		item.setUpdated(date);
		itemMapper.insert(item);
		//添加商品商品描述
		insertItemdesc(desc,id,date);
		//添加商品规格
		insertItemparamItem(itemParams,id,date);
		return TaotaoResult.ok();
	}

	//添加商品描述信息
	private void insertItemdesc(String desc, long id,Date date) {
		TbItemDesc itemDesc=new TbItemDesc();
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(id);
		itemDescMapper.insert(itemDesc);
	}
	//添加商品规格
	private void insertItemparamItem(String itemParams, long id, Date date) {
		TbItemParamItem itemParamItem=new TbItemParamItem();
		itemParamItem.setItemId(id);
		itemParamItem.setParamData(itemParams);
		itemParamItem.setCreated(date);
		itemParamItem.setUpdated(date);
		paramItemMapper.insertSelective(itemParamItem);
	}

}
