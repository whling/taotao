package com.taotao.sso.service;
import com.taotao.common.pojo.TaotaoResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface UserLoginService
{
  public abstract TaotaoResult login(String paramString1, String paramString2, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws Exception;
}