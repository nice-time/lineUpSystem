package com.briup.queuesystem.bean;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReslineTableInfo {

  private String id;

  private String number;

  private String state;

  private String category_id;

  //YYYY-MM-DD HH:mm:SS
  private String createDate;

  //YYYY-MM-DD HH:mm:SS
  private String lastupdate;

  public JSONObject toJsonObject(ReslineTableInfo reslineTableInfo) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("id", reslineTableInfo.getId());
    jsonObject.put("number", reslineTableInfo.getNumber());
    jsonObject.put("state", reslineTableInfo.getState());
    jsonObject.put("categroy_id", reslineTableInfo.getCategory_id());
    jsonObject.put("createDate", reslineTableInfo.getCreateDate());
    jsonObject.put("lastupdate", reslineTableInfo.getLastupdate());

    return jsonObject;
  }


}
