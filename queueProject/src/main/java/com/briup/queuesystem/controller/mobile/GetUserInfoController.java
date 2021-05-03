package com.briup.queuesystem.controller.mobile;

import com.alibaba.fastjson.JSONObject;
import com.briup.queuesystem.bean.ReslineInfo;
import com.briup.queuesystem.bean.ReslineSuggestInfo;
import com.briup.queuesystem.service.SuggestInfoService;
import com.briup.queuesystem.service.UserInfoService;
import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import com.briup.queuesystem.utils.legalMatch;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

/**
 * @author lijx
 */
@CrossOrigin
@RestController
@RequestMapping("/userInfo")
public class GetUserInfoController {

  @Resource
  private UserInfoService userInfoService;

  @Resource
  private SuggestInfoService suggestInfoService;

  /**
   * 用户取号接口
   *
   * @param jsonObject 入参：phoneNum 手机号码，peopleNum 就餐人数
   * @return 成功取号 或 异常失败 信息
   */
  @PostMapping("/getUserInfo")
  public Message getUserInfo(@RequestBody JSONObject jsonObject) {
    //  新建一个List为了返回值的格式
    List<ReslineInfo> reslineInfoList = new ArrayList<>();
    try {
      //  如果jsonObject的大小为0，说明入参异常，直接返回
      if (jsonObject.size() == 0) {
        return MessageUtil.error("非法入参，请联系管理员");
      }
      //  从JSONObject入参中，取出电话号码 phoneNum
      String phoneNum = jsonObject.getString("phoneNum");
      //  对电话号码进行正则校验，判断是否符合规则。符合：下一步 不符合：返回异常信息
      if (!legalMatch.isPhoneLegal(phoneNum)) {
        return MessageUtil.error("请输入正确的手机号码");
      }
      //  从JSONObject入参中，取出就餐人数 peopleNum
      String peopleNum = jsonObject.getString("peopleNum");
      //  正则判断 入参peopleNum字符串内容是否 是数字。是：继续 不是：返回异常信息
      if (!legalMatch.isNumber(peopleNum)) {
        return MessageUtil.error("请输入正确的人数");
      }
      String type = jsonObject.getString("type");
      //  调用方法查询 入参的手机号码是否已经取过号，若取过且状态为待就餐（排队中）。则不插入新的取号信息，返回旧的取号信息，以供展示
      ReslineInfo isWaituser = userInfoService.searchWaitUserInfoByPhoneNumber(phoneNum);
      reslineInfoList.add(isWaituser);
      //  ReslineInfo 不为空说明 该手机号已经取过号并且没有就餐，返回信息。
      if (isWaituser != null && ("0".equals(isWaituser.getState()) || "1".equals(isWaituser.getState()))) {
        return MessageUtil.success("您已取号，正在排队中或就餐中", reslineInfoList);
      }
      if (isWaituser != null && "3".equals(isWaituser.getState()) && "1".equals(type)) {
        return MessageUtil.success("您已取号，正在排队中", reslineInfoList);
      }
      //  将人数转为int类型
      int peopleNumInt = Integer.parseInt(peopleNum);
      //  新建一个 reslineinfo 对象
      ReslineInfo reslineInfo = new ReslineInfo();
      //  设置电话号码
      reslineInfo.setPhone(phoneNum);
      //  设置就餐人数
      reslineInfo.setPeople(String.valueOf(peopleNumInt));
      //  插入信息
      return userInfoService.InsertUserInfo(reslineInfo);
    } catch (Exception e) {
      //  捕获异常，打印并曝出异常
      e.printStackTrace();
      return MessageUtil.error("出现异常:" + e.toString());
    }
  }

  /**
   * 获取已取号（resline_info 有值）但未就餐（且 state为0）的用户
   *
   * @param jsonObject 入参：用户手机号码
   * @return
   */
  @RequestMapping("/getWaitUserInfo")
  public Message getWaitUserInfo(@RequestBody JSONObject jsonObject) {
    String phoneNum = jsonObject.getString("phoneNum");
    ReslineInfo reslineInfo = userInfoService.searchWaitUserInfoByPhoneNumber(phoneNum);
    List<ReslineInfo> reslineInfoList = new ArrayList<>();
    reslineInfoList.add(reslineInfo);
    return MessageUtil.success("查询成功", reslineInfoList);
  }

  @RequestMapping("/getWaitUserNum")
  public Message getwWaitUserNum(@RequestBody JSONObject jsonObject) {
    String phoneNum = jsonObject.getString("phoneNum");
    int i = userInfoService.selectBeforePeopleNum(phoneNum);
    return MessageUtil.success("查询成功", i);
  }

  @RequestMapping("/insertSuggestInfo")
  public Message insertSuggestInfo(@RequestBody JSONObject jsonObject) throws ParseException {
    try {
      String suggestion = jsonObject.getString("suggestion");
      if (StringUtils.isEmpty(suggestion)) {
        return MessageUtil.error("评价内容不能为空");
      }
      String name = jsonObject.getString("name") == null ? "" : jsonObject.getString("name");
      String number = jsonObject.getString("number") == null ? "" : jsonObject.getString("number");
      if (!StringUtils.isEmpty(number)) {
        if (!legalMatch.isPhoneLegal(number)) {
          return MessageUtil.error("请输入正确的手机号码");
        }
      }
      ReslineSuggestInfo reslineSuggestInfo = new ReslineSuggestInfo();
      reslineSuggestInfo.setSuggestion(suggestion);
      reslineSuggestInfo.setName(name);
      reslineSuggestInfo.setNumber(number);
      int insert = suggestInfoService.insert(reslineSuggestInfo);
      if (insert > 0) {
        return MessageUtil.success("评论成功");
      } else {
        return MessageUtil.error("评论失败，insert=0");
      }
    } catch (Exception e) {
      //  捕获异常，打印并曝出异常
      e.printStackTrace();
      return MessageUtil.error("出现异常:" + e.toString());
    }
  }
}
