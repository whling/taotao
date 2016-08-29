package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDatagridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.ItemparamCustomMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.pojo.custom.ItemparamCustom;
import com.taotao.service.ItemparamService;
@Service
public class ItemparamServiceImpl implements ItemparamService {
	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Autowired
	private ItemparamCustomMapper  itemparamCustomMapper;

	@Override
	public TaotaoResult getItemparamsByItemcatId(Long itemcatId) {
		TbItemParamExample example=new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(itemcatId);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if(list!=null && list.size()>0){
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insertItemparam(long cid, String paramData) {
		TbItemParam itemParam=new TbItemParam();
		Date date=new Date();
		itemParam.setCreated(date);
		itemParam.setUpdated(date);
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}

	@Override
	public EUDatagridResult selectItemparamList(Integer page,Integer rows) {
		PageHelper.startPage(page, rows);
		List<ItemparamCustom> itemparamList = itemparamCustomMapper.selectItemparamList();
		PageInfo pageInfo=new PageInfo<>(itemparamList);
		long total = pageInfo.getTotal();
		EUDatagridResult euDatagridResult=new EUDatagridResult();
		euDatagridResult.setTotal(total);
		euDatagridResult.setRows(itemparamList);
		return euDatagridResult;
	}

}
