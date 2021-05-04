package com.briup.queuesystem.service.impl;

import com.briup.queuesystem.bean.ReslineAnnouncement;
import com.briup.queuesystem.bean.ReslineSuggestInfo;
import com.briup.queuesystem.bean.ReslineUser;
import com.briup.queuesystem.mapper.UserMapper;
import com.briup.queuesystem.service.UserLoginService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

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

  @Override
  public List<ReslineUser> findAllUser() {
    return userMapper.findAllUser();
  }

  @Override
  public List<ReslineSuggestInfo> findAllComment() {
    return userMapper.findAllComment();
  }

  @Override
  public Integer addNewUser(ReslineUser reslineUser) {
    return userMapper.insert(reslineUser);
  }

  @Override
  public Integer updateUser(ReslineUser reslineUser) {
    return userMapper.update(reslineUser);
  }

  @Override
  public Integer delUser(List<String> idList) {
    return userMapper.del(idList);
  }

  @Override
  public Integer delComment(List<String> idList) {
    return userMapper.delComment(idList);
  }


}
