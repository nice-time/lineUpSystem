package com.briup.queuesystem.bean;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class ReslineAnnouncement {


  private String id;

  private String caption;

  private String contents;

  //YYYY-MM-DD HH:mm:SS
  private String createDate;

  //YYYY-MM-DD HH:mm:SS
  private String lastupdate;

  public JSONObject toJsonObject(ReslineAnnouncement reslineAnnouncement) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("id", reslineAnnouncement.getId());
    jsonObject.put("caption", reslineAnnouncement.getCaption());
    jsonObject.put("contents", reslineAnnouncement.getContents());
    jsonObject.put("createDate", reslineAnnouncement.getCreateDate());
    jsonObject.put("lastupdate", reslineAnnouncement.getLastupdate());

    return jsonObject;
  }


}
