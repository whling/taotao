package com.taotao.sso.service.impl;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.dao.JedisDao;
import com.taotao.sso.service.UserTokenService;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserTokenServiceImpl
  implements UserTokenService
{

  @Autowired
  private JedisDao jedisDao;

  @Value("${REDIS_USER_TOKEN_KEY}")
  private String USER_TOKEN_KEY;

  @Value("${REDIS_SESSION_EXPIRE_TIME}")
  private Integer SESSION_EXPIRE_TIME;

  public TaotaoResult getUserByToken(String token)
    throws Exception
  {
    String userJson = this.jedisDao.get(this.USER_TOKEN_KEY + ":" + token);

    if (StringUtils.isBlank(userJson)) {
      return TaotaoResult.build(Integer.valueOf(400), "该用户已过期");
    }

    TbUser user = (TbUser)JsonUtils.jsonToPojo(userJson, TbUser.class);

    this.jedisDao.expire(this.USER_TOKEN_KEY + ":" + token, this.SESSION_EXPIRE_TIME);

    return TaotaoResult.ok(user);
  }
}