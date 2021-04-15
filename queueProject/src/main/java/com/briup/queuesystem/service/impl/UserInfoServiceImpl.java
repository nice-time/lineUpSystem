package com.briup.queuesystem.service.impl;

import com.briup.queuesystem.bean.ReslineInfo;
import com.briup.queuesystem.mapper.ReslineInfoDao;
import com.briup.queuesystem.service.UserInfoService;
import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

  @Resource
  private ReslineInfoDao reslineInfoDao;

  @Override
  public Message InsertUserInfo(ReslineInfo userInfo) throws ParseException {
    if (Integer.parseInt(userInfo.getPeople()) > 50 || Integer.parseInt(userInfo.getPeople()) < 1){
      return MessageUtil.error("请输入正确的人数");
    }
    if (Integer.parseInt(userInfo.getPeople()) > 5){
      userInfo.setCategoryId(2);
    }else {
      userInfo.setCategoryId(1);
    }
    userInfo.setState("0");
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    Date parse = formatter.parse(formatter.format(date));
    userInfo.setCreatedate(parse);
    if (reslineInfoDao.insert(userInfo)>0){
      return MessageUtil.success("取号成功",userInfo.toString());
    }else {
      return MessageUtil.error("取号失败，请联系管理员");
    }
  }
}
