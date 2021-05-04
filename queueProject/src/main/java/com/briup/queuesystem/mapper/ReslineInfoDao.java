package com.briup.queuesystem.mapper;

import com.briup.queuesystem.bean.ReslineInfo;
import com.briup.queuesystem.bean.ReslineUser;

import java.util.List;

public interface ReslineInfoDao {

  int insert(ReslineInfo record);

  int insertSelective(ReslineInfo record);

  List<ReslineInfo> searchUserInfoByPhoneNumber(String phoneNumbber);

  ReslineInfo searchWaitUserInfoByPhoneNumber(String phoneNumber);

  int selectBeforePeopleNum(String phoneNumber);

  List<ReslineInfo> getSmallWaitUserInfo();

  List<ReslineInfo> getBigWaitUserInfo();

  int updateUserInfo(ReslineInfo reslineUse);

  int billUpdate(ReslineInfo reslineInfo);

  int confirmMeal(ReslineInfo reslineInfo);

  int cancelMeal(ReslineInfo reslineInfo);
}