package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.pojo.custom.ItemSearchCustom;
import com.taotao.rest.dao.JedisDao;
import com.taotao.rest.service.ItemService;
import com.taotao.utils.JsonUtils;

@Service
public class ItemServiceImpl implements ItemService {
	@Value("${redis.item_key}")
	private String REDIS_ITEM_KEY;

	@Value("${redis.item_desc_key}")
	private String REDIS_ITEM_DESC_KEY;

	@Value("${redis.item_param_item_key}")
	private String REDIS_ITEM_PARAM_ITEM_KEY;

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Autowired
	private JedisDao jedisDao;

	@Override
	public ItemSearchCustom getItemByItemid(long itemId) {
		ItemSearchCustom itemSearchCustom = new ItemSearchCustom();
		/**
		 * 从redis缓存中取商品数据,第二个参数为商品id
		 */
		try {
			String hget = jedisDao.hget(REDIS_ITEM_KEY, itemId + "");
			if (!StringUtils.isBlank(hget)) {
				TbItem item = JsonUtils.jsonToPojo(hget, TbItem.class);
				BeanUtils.copyProperties(item, itemSearchCustom);
				itemSearchCustom.setId(itemId+"");
				return itemSearchCustom;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbItem item = itemMapper.selectByPrimaryKey(itemId);

		BeanUtils.copyProperties(item, itemSearchCustom);
		itemSearchCustom.setId(itemId+"");
		/**
		 * 将商品数据放入redis缓存中
		 */
		try {
			String json = JsonUtils.objectToJson(item);
			jedisDao.hset(REDIS_ITEM_KEY, itemId + "", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemSearchCustom;
	}

	@Override
	public String getItemDescByItemid(long itemId) {
		/**
		 * 从redis缓存中取商品描述数据,第二个参数为商品id
		 */
		try {
			String hget = jedisDao.hget(REDIS_ITEM_DESC_KEY, itemId + "");
			if (!StringUtils.isBlank(hget)) {
				return hget;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		String desc = itemDesc.getItemDesc();
		/**
		 * 将商品描述放入redis缓存中
		 */
		try {
			jedisDao.hset(REDIS_ITEM_DESC_KEY, itemId + "", desc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return desc;
	}

	@Override
	public String getItemParamByItemid(long itemId) {
		String paramData ="";
		/**
		 * 从缓存中取出商品分类id
		 */
		try {
			String hget = jedisDao.hget(REDIS_ITEM_PARAM_ITEM_KEY, itemId+"");
			if(!StringUtils.isBlank(hget)){
				paramData=hget;
				return paramData;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * 通过商品id查询商品规格信息
		 */
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			paramData = list.get(0).getParamData();
			/**
			 * 将商品描述放入redis缓存中
			 */
			try {
				jedisDao.hset(REDIS_ITEM_PARAM_ITEM_KEY, itemId + "", paramData);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return paramData;
	}

}
