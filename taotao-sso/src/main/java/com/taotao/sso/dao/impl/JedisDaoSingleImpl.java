package com.taotao.sso.dao.impl;
import com.taotao.sso.dao.JedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisDaoSingleImpl
  implements JedisDao
{

  @Autowired
  private JedisPool jedisPool;

  public String set(String key, String value)
  {
    Jedis jedis = this.jedisPool.getResource();
    String string = jedis.set(key, value);
    jedis.close();
    return string;
  }

  public String get(String key)
  {
    Jedis jedis = this.jedisPool.getResource();
    String string = jedis.get(key);
    jedis.close();
    return string;
  }

  public long hset(String key, String field, String value)
  {
    Jedis jedis = this.jedisPool.getResource();
    Long hset = jedis.hset(key, field, value);
    jedis.close();
    return hset.longValue();
  }

  public String hget(String key, String field)
  {
    Jedis jedis = this.jedisPool.getResource();
    String string = jedis.hget(key, field);
    jedis.close();
    return string;
  }

  public long expire(String key, Integer seconds)
  {
    Jedis jedis = this.jedisPool.getResource();
    Long expire = jedis.expire(key, seconds.intValue());
    jedis.close();
    return expire.longValue();
  }

  public long ttl(String key)
  {
    Jedis jedis = this.jedisPool.getResource();
    Long ttl = jedis.ttl(key);
    jedis.close();
    return ttl.longValue();
  }

  public long hdel(String key, String field)
  {
    Jedis jedis = this.jedisPool.getResource();
    Long hdel = jedis.hdel(key, new String[] { field });
    return hdel.longValue();
  }
}