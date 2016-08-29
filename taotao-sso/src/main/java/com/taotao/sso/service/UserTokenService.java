package com.taotao.sso.service;
import com.taotao.common.pojo.TaotaoResult;

public abstract interface UserTokenService
{
  public abstract TaotaoResult getUserByToken(String paramString)
    throws Exception;
}