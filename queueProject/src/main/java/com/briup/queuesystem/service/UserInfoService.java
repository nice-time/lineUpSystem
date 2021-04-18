package com.briup.queuesystem.service;

import com.briup.queuesystem.bean.ReslineCategory;
import com.briup.queuesystem.bean.ReslineInfo;
import com.briup.queuesystem.bean.ReslineUser;
import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import java.text.ParseException;
import java.util.List;

public interface UserInfoService {

  Message InsertUserInfo(ReslineInfo userInfo) throws ParseException;

  ReslineInfo searchWaitUserInfoByPhoneNumber(String phoneNumber);

  void insert(ReslineUser reslineUser);

  Integer update(ReslineUser reslineUse);

  List<ReslineUser> getAll();

  Integer del(List<String> id);

}
