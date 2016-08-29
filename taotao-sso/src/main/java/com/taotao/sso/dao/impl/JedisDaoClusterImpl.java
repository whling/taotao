package com.taotao.sso.dao.impl;
import com.taotao.sso.dao.JedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

public class JedisDaoClusterImpl
  implements JedisDao
{

  @Autowired
  private JedisCluster jedisCluster;

  public String set(String key, String value)
  {
    String string = this.jedisCluster.set(key, value);
    return string;
  }

  public String get(String key)
  {
    String string = this.jedisCluster.get(key);
    return string;
  }

  public long hset(String key, String field, String value)
  {
    Long hset = this.jedisCluster.hset(key, field, value);
    return hset.longValue();
  }

  public String hget(String key, String field)
  {
    String string = this.jedisCluster.hget(key, field);
    return string;
  }

  public long expire(String key, Integer seconds)
  {
    Long expire = this.jedisCluster.expire(key, seconds.intValue());
    return expire.longValue();
  }

  public long ttl(String key)
  {
    Long ttl = this.jedisCluster.ttl(key);
    return ttl.longValue();
  }

  public long hdel(String key, String field)
  {
    Long hdel = this.jedisCluster.hdel(key, new String[] { field });
    return hdel.longValue();
  }
}