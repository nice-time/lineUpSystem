package com.briup.queuesystem.controller.pc;

import com.alibaba.fastjson.JSONObject;
import com.briup.queuesystem.bean.ReslineInfo;
import com.briup.queuesystem.service.UserInfoService;
import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/NumberManagement")
public class NumberManagementController {

  @Resource
  UserInfoService userInfoService;

  /**
   * 获取顾客排队的信息
   * @return
   */
  @RequestMapping("/getLineUserInfo")
  public Message lineUserInfo(){
    try{
      //  获取全部状态为0（等待中）且是小桌的顾客
      List<ReslineInfo> smallWaitUserInfo = userInfoService.getSmallWaitUserInfo();
      //  获取全部状态为0（等待中）且是大桌的顾客
      List<ReslineInfo> bigWaitUserInfo = userInfoService.getBigWaitUserInfo();
      ArrayList<List<ReslineInfo>> resultList = new ArrayList<>();
      resultList.add(smallWaitUserInfo);
      resultList.add(bigWaitUserInfo);
      return MessageUtil.success("查询成功",resultList);
    }catch (Exception e){
      //  捕获异常，打印并曝出异常
      e.printStackTrace();
      return MessageUtil.error("出现异常:" + e.toString());
    }
  }


}
