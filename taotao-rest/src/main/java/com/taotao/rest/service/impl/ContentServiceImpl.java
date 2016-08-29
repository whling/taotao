package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.dao.JedisDao;
import com.taotao.rest.service.ContentService;
import com.taotao.utils.JsonUtils;

/**
 * 内容管理
 * 
 * @author Administrator
 *
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;

	@Autowired
	private JedisDao jedisDao;

	@Value("${redis.content_key}")
	private String REDIS_CONTENT_KEY;

	@Override
	public List<TbContent> getContentListByCid(long contentCategoryId) {
		try {
			/**
			 * 从redis缓存中获取数据
			 */
			String result = jedisDao.hget(REDIS_CONTENT_KEY, contentCategoryId + "");
			if (!StringUtils.isBlank(result)) {
				List<TbContent> contents = JsonUtils.jsonToList(result, TbContent.class);
				return contents;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCategoryId);
		List<TbContent> list = contentMapper.selectByExample(example);
		try {
			/**
			 * 将从数据库中查到的数据放入缓存中
			 */
			String string = JsonUtils.objectToJson(list);
			jedisDao.hset(REDIS_CONTENT_KEY, contentCategoryId + "", string);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
