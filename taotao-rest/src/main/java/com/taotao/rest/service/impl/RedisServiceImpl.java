package com.taotao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.dao.JedisDao;
import com.taotao.rest.service.RedisService;
import com.taotao.utils.ExceptionUtil;

@Service
public class RedisServiceImpl implements RedisService {
	@Autowired
	private JedisDao jedisDao;
	
	@Value("${redis.content_key}")
	private String REDIS_CONTENT_KEY;

	@Override
	public TaotaoResult synContent(long contentCid) {
		try {
			jedisDao.hdel(REDIS_CONTENT_KEY, contentCid + "");
		} catch (Exception e) {
			String msg = ExceptionUtil.getStackTrace(e);
			return TaotaoResult.build(500, msg);
		}
		return TaotaoResult.ok();
	}

}
