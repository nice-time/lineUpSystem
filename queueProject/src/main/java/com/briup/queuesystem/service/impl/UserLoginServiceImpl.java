package com.briup.queuesystem.service.impl;

import com.briup.queuesystem.bean.ReslineUser;
import com.briup.queuesystem.mapper.UserMapper;
import com.briup.queuesystem.service.UserLoginService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {

  @Resource
  UserMapper userMapper;

  @Override
  public ReslineUser userLogin(String userNumber, String passWord) {
    ReslineUser reslineUser = userMapper.userLogin(userNumber, passWord);
    if (reslineUser != null) {
      return reslineUser;
    } else {
      return null;
    }
  }
}
