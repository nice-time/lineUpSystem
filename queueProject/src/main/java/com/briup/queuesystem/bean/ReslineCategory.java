package com.briup.queuesystem.bean;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class ReslineCategory {

  private String id;

  private String type;

  private String desc;

  //YYYY-MM-DD HH:mm:SS
  private String createDate;

  //YYYY-MM-DD HH:mm:SS
  private String lastupdate;

  public JSONObject toJsonObject(ReslineCategory reslineCategory) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("id", reslineCategory.getId());
    jsonObject.put("type", reslineCategory.getType());
    jsonObject.put("desc", reslineCategory.getDesc());
    jsonObject.put("createDate", reslineCategory.getCreateDate());
    jsonObject.put("lastupdate", reslineCategory.getLastupdate());

    return jsonObject;
  }

}
