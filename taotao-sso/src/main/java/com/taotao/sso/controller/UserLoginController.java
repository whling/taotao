package com.taotao.sso.controller;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.sso.service.UserLoginService;
import com.taotao.utils.ExceptionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/user"})
public class UserLoginController
{

  @Autowired
  private UserLoginService userLoginService;

  @RequestMapping({"/showLogin"})
  public String showLogin(String redirect, Model model)
  {
    model.addAttribute("redirect", redirect);
    return "login";
  }
  @RequestMapping(value={"/login"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
    TaotaoResult result = null;
    try {
      result = this.userLoginService.login(username, password, request, response);
    } catch (Exception e) {
      e.printStackTrace();
      return TaotaoResult.build(Integer.valueOf(500), ExceptionUtil.getStackTrace(e));
    }

    return result;
  }
}