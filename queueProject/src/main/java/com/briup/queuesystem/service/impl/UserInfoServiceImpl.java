package com.briup.queuesystem.service.impl;

import com.briup.queuesystem.bean.ReslineInfo;
import com.briup.queuesystem.mapper.ReslineInfoDao;
import com.briup.queuesystem.service.UserInfoService;
import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import com.briup.queuesystem.utils.RedisConfig;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisClusterCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
public class UserInfoServiceImpl implements UserInfoService {

  @Resource
  private ReslineInfoDao reslineInfoDao;

  @Override
  public Message InsertUserInfo(ReslineInfo userInfo) throws ParseException {
    RedisTemplate<String, String> userNumber = new RedisTemplate<>();
    RedisStandaloneConfiguration rsc = new RedisStandaloneConfiguration();
    rsc.setPort(6379);
    rsc.setPassword("123456");
    rsc.setHostName("121.196.174.196");
    JedisConnectionFactory fac = new JedisConnectionFactory(rsc);
    userNumber.setConnectionFactory(fac);
    userNumber.afterPropertiesSet();

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
    SimpleDateFormat pDay = new SimpleDateFormat("yyyyMMdd");
    Date parse = formatter.parse(formatter.format(date));
    String pDayFormat = pDay.format(date);
    userInfo.setPDay(pDayFormat);
    userInfo.setCreatedate(parse);
    //  开始取号码
    String number;
    if (userInfo.getCategoryId() == 1){
      number = userNumber.opsForValue().get(pDayFormat + "A");
      if (StringUtils.isEmpty(number)){
        userNumber.opsForValue().set(pDayFormat + "A","1");
        userInfo.setNumber("A01");
      }else {
        int numberAdd = Integer.valueOf(number);
        if (++numberAdd<10){
          userInfo.setNumber("A0" + numberAdd);
        }else {
          userInfo.setNumber("A" + numberAdd);
        }
        userNumber.opsForValue().set(pDayFormat + "A",String.valueOf(numberAdd));
      }
    }else {
      number = userNumber.opsForValue().get(pDayFormat + "B");
      if (StringUtils.isEmpty(number)){
        userNumber.opsForValue().set(pDayFormat + "B","1");
        userInfo.setNumber("B01");
      }else {
        int numberAdd = Integer.valueOf(number);
        if (++numberAdd<10){
          userInfo.setNumber("B0" + numberAdd);
        }else {
          userInfo.setNumber("B" + numberAdd);
        }
        userNumber.opsForValue().set(pDayFormat + "B",String.valueOf(numberAdd));
      }
    }
    if (reslineInfoDao.insert(userInfo)>0){
      return MessageUtil.success("取号成功",userInfo.toString());
    }else {
      return MessageUtil.error("取号失败，请联系管理员");
    }
  }
}
