package com.taotao.sso.dao;
public abstract interface JedisDao
{
  public abstract String set(String paramString1, String paramString2);

  public abstract String get(String paramString);

  public abstract long hset(String paramString1, String paramString2, String paramString3);

  public abstract String hget(String paramString1, String paramString2);

  public abstract long hdel(String paramString1, String paramString2);

  public abstract long expire(String paramString, Integer paramInteger);

  public abstract long ttl(String paramString);
}