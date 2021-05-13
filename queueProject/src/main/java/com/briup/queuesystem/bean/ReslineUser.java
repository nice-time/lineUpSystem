package com.briup.queuesystem.bean;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class ReslineUser {


  private String id;

  //用户名称
  private String username;

  //用户工号
  private String usernumber;

  private String pwd;

  private String level;

  private String phone;

  private String sex;

  //YYYY-MM-DD HH:mm:SS
  private String createDate;

  //YYYY-MM-DD HH:mm:SS
  private String lastupdate;

  public JSONObject toJsonObject(ReslineUser reslineUser) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("id", reslineUser.getId());
    jsonObject.put("username", reslineUser.getUsername());
    jsonObject.put("usernumber", reslineUser.getUsernumber());
    jsonObject.put("pwd", reslineUser.getPwd());
    jsonObject.put("level", reslineUser.getLevel());
    jsonObject.put("phone", reslineUser.getPhone());
    jsonObject.put("sex", reslineUser.getSex());
    jsonObject.put("createDate", reslineUser.getCreateDate());
    jsonObject.put("lastupdate", reslineUser.getLastupdate());

    return jsonObject;
  }
}
