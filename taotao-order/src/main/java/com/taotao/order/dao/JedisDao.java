package com.taotao.order.dao;

public interface JedisDao {
	String set(String key,String value);
	String get(String key);
	long hset(String key,String field,String value);
	String hget(String key,String field);
	long hdel(String key,String field);
	long expire(String key,Integer seconds);
	long ttl(String key);
	long incr(String key);
}
