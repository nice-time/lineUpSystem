package com.briup.queuesystem.service.impl;

import com.briup.queuesystem.bean.ReslineInfo;
import com.briup.queuesystem.bean.ReslineUser;
import com.briup.queuesystem.mapper.ReslineInfoDao;
import com.briup.queuesystem.service.UserInfoService;
import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
public class UserInfoServiceImpl implements UserInfoService {

  @Resource
  private ReslineInfoDao reslineInfoDao;

  @Autowired
  private RedisTemplate redisTemplate;

  @Override
  public Message InsertUserInfo(ReslineInfo userInfo) throws ParseException {
    List<ReslineInfo> reslineInfoList = new ArrayList<>();
    if (Integer.parseInt(userInfo.getPeople()) > 10 || Integer.parseInt(userInfo.getPeople()) < 1) {
      return MessageUtil.error("请输入正确的人数");
    }
    if (Integer.parseInt(userInfo.getPeople()) > 5) {
      userInfo.setCategoryId(2);
    } else {
      userInfo.setCategoryId(1);
    }
    userInfo.setState("0");
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat pDay = new SimpleDateFormat("yyyyMMdd");
    Date parse = formatter.parse(formatter.format(date));
    String pDayFormat = pDay.format(date);
    userInfo.setPDay(pDayFormat);
    userInfo.setCreatedate(parse);
    //  开始取号码
    String number;
    if (userInfo.getCategoryId() == 1) {
      number = (String) redisTemplate.opsForValue().get(pDayFormat + "A");
      if (StringUtils.isEmpty(number)) {
        redisTemplate.opsForValue().set(pDayFormat + "A", "1");
        userInfo.setNumber("A01");
      } else {
        int numberAdd = Integer.parseInt(number);
        if (++numberAdd < 10) {
          userInfo.setNumber("A0" + numberAdd);
        } else {
          userInfo.setNumber("A" + numberAdd);
        }
        redisTemplate.opsForValue().set(pDayFormat + "A", String.valueOf(numberAdd));
      }
    } else {
      number = (String) redisTemplate.opsForValue().get(pDayFormat + "B");
      if (StringUtils.isEmpty(number)) {
        redisTemplate.opsForValue().set(pDayFormat + "B", "1");
        userInfo.setNumber("B01");
      } else {
        int numberAdd = Integer.parseInt(number);
        if (++numberAdd < 10) {
          userInfo.setNumber("B0" + numberAdd);
        } else {
          userInfo.setNumber("B" + numberAdd);
        }
        redisTemplate.opsForValue().set(pDayFormat + "B", String.valueOf(numberAdd));
      }
    }
    if (reslineInfoDao.insert(userInfo) > 0) {
      reslineInfoList.add(userInfo);
      return MessageUtil.success("取号成功", reslineInfoList);
    } else {
      return MessageUtil.error("取号失败，请联系管理员");
    }
  }

  @Override
  public ReslineInfo searchWaitUserInfoByPhoneNumber(String phoneNumber) {
    return reslineInfoDao.searchWaitUserInfoByPhoneNumber(phoneNumber);
  }

  @Override
  public int selectBeforePeopleNum(String phoneNumber) {
    return reslineInfoDao.selectBeforePeopleNum(phoneNumber);
  }

  @Override
  public List<ReslineInfo> getSmallWaitUserInfo() {
    return reslineInfoDao.getSmallWaitUserInfo();
  }

  @Override
  public List<ReslineInfo> getBigWaitUserInfo() {
    return reslineInfoDao.getBigWaitUserInfo();
  }

  @Override
  public void insert(ReslineUser reslineUser) {

  }

  @Override
  public Integer update(ReslineInfo reslineUse) {
    reslineUse.setState("3");
    return reslineInfoDao.updateUserInfo(reslineUse);
  }

  @Override
  public Integer billUpdate(ReslineInfo reslineInfo) {
    reslineInfo.setState("2");

    return reslineInfoDao.billUpdate(reslineInfo);
  }

  @Override
  public Integer confirmMeal(ReslineInfo reslineInfo) {
    reslineInfo.setState("2");
    return reslineInfoDao.confirmMeal(reslineInfo);
  }

  @Override
  public Integer cancelMeal(ReslineInfo reslineInfo) {
    reslineInfo.setState("3");
    return reslineInfoDao.cancelMeal(reslineInfo);
  }

  @Override
  public List<ReslineUser> getAll() {
    return null;
  }

  @Override
  public Integer del(List<String> id) {
    return null;
  }
}
