package com.briup.queuesystem.bean;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class ReslineTableInfo {

  private String id;

  private String number;

  private String state;

  private String categroy_id;

  //YYYY-MM-DD HH:mm:SS
  private String createDate;

  //YYYY-MM-DD HH:mm:SS
  private String lastupdate;

  public JSONObject toJsonObject(ReslineTableInfo reslineTableInfo) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("id", reslineTableInfo.getId());
    jsonObject.put("number", reslineTableInfo.getNumber());
    jsonObject.put("state", reslineTableInfo.getState());
    jsonObject.put("categroy_id", reslineTableInfo.getCategroy_id());
    jsonObject.put("createDate", reslineTableInfo.getCreateDate());
    jsonObject.put("lastupdate", reslineTableInfo.getLastupdate());

    return jsonObject;
  }


}
