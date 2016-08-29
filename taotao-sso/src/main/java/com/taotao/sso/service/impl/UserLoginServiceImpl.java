package com.taotao.sso.service.impl;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.dao.JedisDao;
import com.taotao.sso.service.UserLoginService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserLoginServiceImpl
  implements UserLoginService
{

  @Autowired
  private TbUserMapper userMapper;

  @Autowired
  private JedisDao jedisDao;

  @Value("${REDIS_USER_TOKEN_KEY}")
  private String USER_TOKEN_KEY;

  @Value("${REDIS_SESSION_EXPIRE_TIME}")
  private Integer SESSION_EXPIRE_TIME;

  public TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    TbUserExample example = new TbUserExample();
    TbUserExample.Criteria criteria = example.createCriteria();
    criteria.andUsernameEqualTo(username);
    List list = this.userMapper.selectByExample(example);
    if ((list == null) || (list.isEmpty())) {
      return TaotaoResult.build(Integer.valueOf(400), "用户不存在");
    }

    TbUser user = (TbUser)list.get(0);
    if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
      return TaotaoResult.build(Integer.valueOf(400), "密码错误");
    }

    String token = UUID.randomUUID().toString();
    this.jedisDao.set(this.USER_TOKEN_KEY + ":" + token, JsonUtils.objectToJson(user));

    this.jedisDao.expire(this.USER_TOKEN_KEY + ":" + token, this.SESSION_EXPIRE_TIME);

    CookieUtils.setCookie(request, response, "TT_TOKEN", token);
    return TaotaoResult.ok(token);
  }
}