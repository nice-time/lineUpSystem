package com.briup.queuesystem.mapper;

import com.briup.queuesystem.bean.ReslineInfo;
import java.util.List;

public interface ReslineInfoDao {

  int insert(ReslineInfo record);

  int insertSelective(ReslineInfo record);

  List<ReslineInfo> searchUserInfoByPhoneNumber(String phoneNumbber);

  ReslineInfo searchWaitUserInfoByPhoneNumber(String phoneNumber);
}