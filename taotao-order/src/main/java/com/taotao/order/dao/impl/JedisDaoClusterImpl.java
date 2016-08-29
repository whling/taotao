package com.taotao.order.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.order.dao.JedisDao;

import redis.clients.jedis.JedisCluster;

public class JedisDaoClusterImpl implements JedisDao{
	@Autowired
	private JedisCluster jedisCluster;

	@Override
	public String set(String key, String value) {
		String string = jedisCluster.set(key, value);
		return string;
	}

	@Override
	public String get(String key) {
		String string = jedisCluster.get(key);
		return string;
	}

	@Override
	public long hset(String key, String field, String value) {
		Long hset = jedisCluster.hset(key, field, value);
		return hset;
	}

	@Override
	public String hget(String key, String field) {
		String string = jedisCluster.hget(key, field);
		return string;
	}

	@Override
	public long expire(String key, Integer seconds) {
		Long expire = jedisCluster.expire(key, seconds);
		return expire;
	}

	@Override
	public long ttl(String key) {
		Long ttl = jedisCluster.ttl(key);
		return ttl;
	}

	@Override
	public long hdel(String key, String field) {
		Long hdel = jedisCluster.hdel(key, field);
		return hdel;
	}

	@Override
	public long incr(String key) {
		Long incr = jedisCluster.incr(key);
		return incr;
	}

}
