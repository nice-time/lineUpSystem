package com.briup.queuesystem.controller.mobile;

import com.alibaba.fastjson.JSONObject;
import com.briup.queuesystem.bean.ReslineInfo;
import com.briup.queuesystem.service.UserInfoService;
import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import com.briup.queuesystem.utils.legalMatch;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author lijx
 */
@CrossOrigin
@RestController
@RequestMapping("/userInfo")
public class GetUserInfoController {

  @Resource
  private UserInfoService userInfoService;

  @PostMapping("getUserInfo")
  public Message getUserInfo(@RequestBody JSONObject jsonObject){
    try{
      if (jsonObject.size() == 0){
        return MessageUtil.error("非法入参，请联系管理员");
      }
      String phoneNum = jsonObject.getString("phoneNum");
      if (!legalMatch.isPhoneLegal(phoneNum)){
        return MessageUtil.error("请输入正确的手机号码");
      }
      String peopleNum = jsonObject.getString("peopleNum");
      if (!legalMatch.isNumber(peopleNum)){
        return MessageUtil.error("请输入正确的人数");
      }
      int peopleNumInt = Integer.parseInt(peopleNum);
      ReslineInfo reslineInfo = new ReslineInfo();
      reslineInfo.setPhone(phoneNum);
      reslineInfo.setPeople(String.valueOf(peopleNumInt));
      return userInfoService.InsertUserInfo(reslineInfo);
    }catch (Exception e){
      e.printStackTrace();
      return MessageUtil.error("出现异常:" + e.toString());
    }
  }
}
