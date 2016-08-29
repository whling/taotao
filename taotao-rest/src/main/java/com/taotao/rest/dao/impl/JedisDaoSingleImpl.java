package com.taotao.rest.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.rest.dao.JedisDao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisDaoSingleImpl implements JedisDao {
	@Autowired
	private JedisPool jedisPool;

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.set(key, value);
		jedis.close();
		return string;
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get(key);
		jedis.close();
		return string;
	}

	@Override
	public long hset(String key, String field, String value) {
		Jedis jedis = jedisPool.getResource();
		Long hset = jedis.hset(key, field, value);
		jedis.close();
		return hset;
	}

	@Override
	public String hget(String key, String field) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.hget(key, field);
		jedis.close();
		return string;
	}

	@Override
	public long expire(String key, Integer seconds) {
		Jedis jedis = jedisPool.getResource();
		Long expire = jedis.expire(key, seconds);
		jedis.close();
		return expire;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long ttl = jedis.ttl(key);
		jedis.close();
		return ttl;
	}

	@Override
	public long hdel(String key, String field) {
		Jedis jedis = jedisPool.getResource();
		Long hdel = jedis.hdel(key, field);
		return hdel;
	}

}
