package com.briup.queuesystem.service;

import com.briup.queuesystem.bean.ReslineAnnouncement;
import com.briup.queuesystem.bean.ReslineUser;
import io.swagger.models.auth.In;

import java.util.List;

public interface UserLoginService {

  ReslineUser userLogin(String userNumber, String passWord);

  List<ReslineUser> findAllUser();

  List<ReslineAnnouncement> findAllComment();

  Integer addNewUser(ReslineUser reslineUser);

  Integer updateUser(ReslineUser reslineUser);

  Integer delUser(List<String> idList);

  Integer delComment(List<String> idList);

}
