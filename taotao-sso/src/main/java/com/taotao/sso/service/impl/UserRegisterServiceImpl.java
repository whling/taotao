package com.taotao.sso.service.impl;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.service.UserRegisterService;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserRegisterServiceImpl
  implements UserRegisterService
{

  @Autowired
  private TbUserMapper userMapper;

  public TaotaoResult checkInfo(String value, String type)
    throws Exception
  {
    boolean result = false;

    if ("1".equals(type))
      result = checkUserName(value);
    else if ("2".equals(type))
      result = checkPhone(value);
    else if ("3".equals(type)) {
      result = checkEmail(value);
    }

    if (result) {
      return TaotaoResult.ok(Boolean.valueOf(result));
    }
    return TaotaoResult.build(Integer.valueOf(201), "此数值已经存在");
  }

  private boolean checkUserName(String userName)
    throws Exception
  {
    TbUserExample example = new TbUserExample();
    TbUserExample.Criteria criteria = example.createCriteria();
    criteria.andUsernameEqualTo(userName);
    List list = this.userMapper.selectByExample(example);

    return (list == null) || (list.isEmpty());
  }

  private boolean checkPhone(String phone)
    throws Exception
  {
    TbUserExample example = new TbUserExample();
    TbUserExample.Criteria criteria = example.createCriteria();
    criteria.andPhoneEqualTo(phone);
    List list = this.userMapper.selectByExample(example);

    return (list == null) || (list.isEmpty());
  }

  private boolean checkEmail(String email)
    throws Exception
  {
    TbUserExample example = new TbUserExample();
    TbUserExample.Criteria criteria = example.createCriteria();
    criteria.andEmailEqualTo(email);
    List list = this.userMapper.selectByExample(example);

    return (list == null) || (list.isEmpty());
  }

  public TaotaoResult register(TbUser user)
    throws Exception
  {
    if (StringUtils.isBlank(user.getUsername())) {
      return TaotaoResult.build(Integer.valueOf(400), "用户名不能为空");
    }
    if (StringUtils.isBlank(user.getPassword())) {
      return TaotaoResult.build(Integer.valueOf(400), "密码不能为空");
    }
    if (StringUtils.isBlank(user.getPhone())) {
      return TaotaoResult.build(Integer.valueOf(400), "手机不能为空");
    }

    user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

    user.setCreated(new Date());
    user.setUpdated(new Date());

    this.userMapper.insert(user);

    return TaotaoResult.ok();
  }
}