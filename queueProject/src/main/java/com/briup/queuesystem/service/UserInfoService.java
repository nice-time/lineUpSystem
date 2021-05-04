package com.briup.queuesystem.service;

import com.briup.queuesystem.bean.ReslineInfo;
import com.briup.queuesystem.bean.ReslineUser;
import com.briup.queuesystem.utils.Message;
import java.text.ParseException;
import java.util.List;

public interface UserInfoService {

  Message InsertUserInfo(ReslineInfo userInfo) throws ParseException;

  ReslineInfo searchWaitUserInfoByPhoneNumber(String phoneNumber);

  int selectBeforePeopleNum(String phoneNumber);

  List<ReslineInfo> getSmallWaitUserInfo();

  List<ReslineInfo> getBigWaitUserInfo();

  void insert(ReslineUser reslineUser);

  Integer update(ReslineInfo reslineUse);

  Integer billUpdate(ReslineInfo reslineInfo);

  List<ReslineUser> getAll();

  Integer del(List<String> id);

}
