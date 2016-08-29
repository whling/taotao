package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeData;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCatService;
import com.taotao.utils.IDUtils;
/**
 * contentCat相关的service
 * @author Administrator
 *
 */
@Service
public class ContentCatServiceImpl implements ContentCatService {
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<EUTreeData> getContentCatList(long pid) {
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(pid);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		
		List<EUTreeData> euTreeDatas=new ArrayList<EUTreeData>();
		
		if (list!=null && list.size()>0) {
			for (TbContentCategory tbContentCategory : list) {
				EUTreeData data=new EUTreeData();
				data.setId(tbContentCategory.getId());
				data.setParentId(tbContentCategory.getParentId());
				data.setText(tbContentCategory.getName());
				data.setState(tbContentCategory.getIsParent()?"closed":"open");
				euTreeDatas.add(data);
			}
		}
		return euTreeDatas;
	}

	@Override
	public TaotaoResult createContentCat(Long pid, String name) {
		TbContentCategory record = new TbContentCategory();
		try {
			TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(pid);
			if (!contentCategory.getIsParent()) {
				//修改变成父亲
				contentCategory.setIsParent(true);
				contentCategoryMapper.updateByPrimaryKey(contentCategory);
			}
			Date date = new Date();
			
			record.setIsParent(false);
			record.setName(name);
			record.setParentId(pid);
			record.setSortOrder(1);
			record.setCreated(date);
			record.setUpdated(date);
			contentCategoryMapper.insertSelective(record);
		} catch (Exception e) {
			//失败
			return TaotaoResult.build(500, null);
		}
		return TaotaoResult.ok(record);
	}

	@Override
	public void deleteContentCatById(Long pid, Long id) {
		//删除子节点
		TbContentCategoryExample example1=new TbContentCategoryExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andParentIdEqualTo(id);
		contentCategoryMapper.deleteByExample(example1);
		//删除自己
		contentCategoryMapper.deleteByPrimaryKey(id);
		//查询父节点为pid的子节点，如果不存在，则修改父节点isParent属性
		
		TbContentCategoryExample example2=new TbContentCategoryExample();
		Criteria criteria2 = example2.createCriteria();
		criteria2.andParentIdEqualTo(pid);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example2);
		if(list==null || list.size()==0){
			//表示父亲节点没有另外的子节点
			TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(pid);
			contentCategory.setIsParent(false);
			contentCategoryMapper.updateByPrimaryKey(contentCategory);
		}
	}

	@Override
	public void updateContentCatById(long id, String name) {
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		contentCategory.setName(name);
		contentCategoryMapper.updateByPrimaryKey(contentCategory);
		
	}

}
