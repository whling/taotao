package com.taotao.sso.service;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public abstract interface UserRegisterService
{
  public abstract TaotaoResult checkInfo(String paramString1, String paramString2)
    throws Exception;

  public abstract TaotaoResult register(TbUser paramTbUser)
    throws Exception;
}