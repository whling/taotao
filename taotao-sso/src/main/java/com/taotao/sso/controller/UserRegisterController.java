package com.taotao.sso.controller;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserRegisterService;
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
public class UserRegisterController
{

  @Autowired
  private UserRegisterService userRegisterService;

  @RequestMapping({"/showRegister"})
  public String showRegister()
  {
    return "register";
  }
  @RequestMapping({"/check/{param}/{type}"})
  @ResponseBody
  public Object checkInfo(@PathVariable String param, @PathVariable String type, String callback) { TaotaoResult result = null;
    try {
      result = this.userRegisterService.checkInfo(param, type);
    }
    catch (Exception e) {
      e.printStackTrace();
      result = TaotaoResult.build(Integer.valueOf(500), ExceptionUtil.getStackTrace(e));
    }

    if (!StringUtils.isBlank(callback)) {
      MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
      mappingJacksonValue.setJsonpFunction(callback);
      return mappingJacksonValue;
    }
    return result; } 
  @RequestMapping(value={"/register"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public TaotaoResult register(TbUser user) {
    TaotaoResult taotaoResult = null;
    try {
      taotaoResult = this.userRegisterService.register(user);
    }
    catch (Exception e) {
      e.printStackTrace();
      return TaotaoResult.build(Integer.valueOf(500), ExceptionUtil.getStackTrace(e));
    }
    return taotaoResult;
  }
}