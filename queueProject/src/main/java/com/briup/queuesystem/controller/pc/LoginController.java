package com.briup.queuesystem.controller.pc;

import com.alibaba.fastjson.JSONObject;
import com.briup.queuesystem.bean.ReslineInfo;
import com.briup.queuesystem.bean.ReslineUser;
import com.briup.queuesystem.service.UserLoginService;
import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

@CrossOrigin
@RestController
@RequestMapping("/userLogin")
public class LoginController {

  @Resource
  UserLoginService userLoginService;

  @RequestMapping("/login")
  public Message userLogin(@RequestBody JSONObject jsonObject){
    try{
      String userNumber = jsonObject.getString("userNumber");
      String password = jsonObject.getString("password");
      if (StringUtils.isEmpty(userNumber)){
        return MessageUtil.error("用户名不能为空");
      }
      if (StringUtils.isEmpty(password)){
        return MessageUtil.error("密码不能为空");
      }
      ReslineUser reslineUser = userLoginService.userLogin(userNumber, password);
      if (reslineUser != null){
        List<ReslineUser> reslineUserList = new ArrayList<>();
        reslineUserList.add(reslineUser);
        return MessageUtil.success("登录成功",reslineUserList);
      }else {
        return MessageUtil.error("用户或密码错误，请重试");
      }
    }catch (Exception e){
      //  捕获异常，打印并曝出异常
      e.printStackTrace();
      return MessageUtil.error("出现异常:" + e.toString());
    }
  }

}
