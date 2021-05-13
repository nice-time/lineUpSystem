package com.briup.queuesystem.bean;

import com.alibaba.fastjson.JSONObject;
import java.util.Date;
import lombok.Data;

@Data
public class ReslineSuggestInfo {


  private String suggestId;

  private String name;

  private String number;

  private String suggestion;

  private String time;


  public JSONObject toJsonObject(ReslineSuggestInfo reslineSuggestInfo) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("suggestId", reslineSuggestInfo.getSuggestId());
    jsonObject.put("name", reslineSuggestInfo.getName());
    jsonObject.put("number", reslineSuggestInfo.getNumber());
    jsonObject.put("suggestion", reslineSuggestInfo.getSuggestion());
    jsonObject.put("time", reslineSuggestInfo.getTime());

    return jsonObject;
  }


}
