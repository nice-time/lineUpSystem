package com.briup.queuesystem.controller.pc;

import com.alibaba.fastjson.JSONObject;
import com.briup.queuesystem.bean.ReslineAnnouncement;
import com.briup.queuesystem.service.AnnouncementService;
import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

@RestController
@RequestMapping("/MessageManagement")
public class MessageManagementConreoller {

  @Resource
  AnnouncementService announcementService;

  @RequestMapping("/MessageInsert")
  public Message MessageInsert(@RequestBody JSONObject jsonObject){
    try{
      String messageContent = jsonObject.getString("MessageContent");
      String caption = jsonObject.getString("caption");
      if (StringUtils.isEmpty(caption)){
        return MessageUtil.error("标题不能为空");
      }
      if (StringUtils.isEmpty(messageContent)){
        return MessageUtil.error("公告信息不能为空");
      }
      ReslineAnnouncement reslineAnnouncement = new ReslineAnnouncement();
      reslineAnnouncement.setCaption(caption);
      reslineAnnouncement.setContents(messageContent);
      if (announcementService.insert(reslineAnnouncement) > 0){
        return MessageUtil.success("公告发布成功");
      }else {
        return MessageUtil.error("公告发布失败，insert=0");
      }
    }catch (Exception e){
      //  捕获异常，打印并曝出异常
      e.printStackTrace();
      return MessageUtil.error("出现异常:" + e.toString());
    }
  }

}
