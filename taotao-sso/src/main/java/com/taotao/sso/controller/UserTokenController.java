package com.taotao.sso.controller;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.sso.service.UserTokenService;
import com.taotao.utils.ExceptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/user"})
public class UserTokenController
{

  @Autowired
  private UserTokenService userTokenService;

  @RequestMapping({"/token/{token}"})
  @ResponseBody
  public Object getUserByToken(@PathVariable String token, String callback)
  {
    TaotaoResult result = null;
    try {
      result = this.userTokenService.getUserByToken(token);
    } catch (Exception e) {
      e.printStackTrace();
      result = TaotaoResult.build(Integer.valueOf(500), ExceptionUtil.getStackTrace(e));
    }

    if (!StringUtils.isBlank(callback)) {
      MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
      mappingJacksonValue.setJsonpFunction(callback);
      return mappingJacksonValue;
    }
    return result;
  }
}