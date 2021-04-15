package com.briup.queuesystem.service;

import com.briup.queuesystem.bean.ReslineInfo;
import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import java.text.ParseException;

public interface UserInfoService {

  Message InsertUserInfo(ReslineInfo userInfo) throws ParseException;

  ReslineInfo searchWaitUserInfoByPhoneNumber(String phoneNumber);

}
