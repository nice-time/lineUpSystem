package com.briup.queuesystem.controller.pc;

import com.alibaba.fastjson.JSONObject;
import com.briup.queuesystem.bean.ReslineAnnouncement;
import com.briup.queuesystem.service.AnnouncementService;
import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import java.util.Arrays;
import java.util.List;
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
  public Message MessageInsert(@RequestBody JSONObject jsonObject) {
    try {
      String messageContent = jsonObject.getString("MessageContent");
      String caption = jsonObject.getString("caption");
      if (StringUtils.isEmpty(caption)) {
        return MessageUtil.error("标题不能为空");
      }
      if (StringUtils.isEmpty(messageContent)) {
        return MessageUtil.error("公告信息不能为空");
      }
      ReslineAnnouncement reslineAnnouncement = new ReslineAnnouncement();
      reslineAnnouncement.setCaption(caption);
      reslineAnnouncement.setContents(messageContent);
      if (announcementService.insert(reslineAnnouncement) > 0) {
        return MessageUtil.success("公告发布成功");
      } else {
        return MessageUtil.error("公告发布失败，insert=0");
      }
    } catch (Exception e) {
      //  捕获异常，打印并曝出异常
      e.printStackTrace();
      return MessageUtil.error("出现异常:" + e.toString());
    }
  }

  @RequestMapping("/MessageSearchAll")
  public Message MessageSearchAll() {
    try {
      return MessageUtil.success("获取成功", announcementService.getAll());
    } catch (Exception e) {
      //  捕获异常，打印并曝出异常
      e.printStackTrace();
      return MessageUtil.error("出现异常:" + e.toString());
    }
  }

  @RequestMapping("/selectMessageById")
  public Message selectMessageById(@RequestBody JSONObject jsonObject) {
    try {
      String messageId = jsonObject.getString("messageId");
      if (StringUtils.isEmpty(messageId)) {
        return MessageUtil.error("公告Id不能为空");
      }
      ReslineAnnouncement reslineAnnouncement = announcementService.selectById(messageId);
      if (reslineAnnouncement == null) {
        return MessageUtil.error("未获取到相应公告。Id=" + messageId);
      }
      return MessageUtil.success("获取公告成功", reslineAnnouncement);
    } catch (Exception e) {
      //  捕获异常，打印并曝出异常
      e.printStackTrace();
      return MessageUtil.error("出现异常:" + e.toString());
    }
  }

  @RequestMapping("/updateMessageById")
  public Message updateMessageById(@RequestBody JSONObject jsonObject) {
    try {
      String messageId = jsonObject.getString("messageId");
      String caption = jsonObject.getString("caption");
      String contents = jsonObject.getString("contents");
      if (StringUtils.isEmpty(messageId)) {
        return MessageUtil.error("公告Id不能为空");
      }
      if (StringUtils.isEmpty(caption)) {
        return MessageUtil.error("标题不能为空");
      }
      if (StringUtils.isEmpty(contents)) {
        return MessageUtil.error("公告内容不能为空");
      }
      ReslineAnnouncement reslineAnnouncement = new ReslineAnnouncement();
      reslineAnnouncement.setId(messageId);
      reslineAnnouncement.setCaption(caption);
      reslineAnnouncement.setContents(contents);
      Integer update = announcementService.update(reslineAnnouncement);
      if (update > 0) {
        return MessageUtil.success("更新成功", update);
      }
      return MessageUtil.error("更新失败,messageId=" + messageId);
    } catch (Exception e) {
      //  捕获异常，打印并曝出异常
      e.printStackTrace();
      return MessageUtil.error("出现异常:" + e.toString());
    }
  }

  @RequestMapping("/delMessageById")
  public Message delMessageById(@RequestBody JSONObject jsonObject) {
    try {
      String messageId = jsonObject.getString("messageId");
      if (StringUtils.isEmpty(messageId)) {
        return MessageUtil.error("公告Id不能为空");
      }
      Integer integer = announcementService.delOne(messageId);
      if (integer > 0) {
        return MessageUtil.success("删除成功", integer);
      }
      return MessageUtil.error("删除失败,messageId=" + messageId);
    } catch (Exception e) {
      //  捕获异常，打印并曝出异常
      e.printStackTrace();
      return MessageUtil.error("出现异常:" + e.toString());
    }
  }

  @RequestMapping("/delMessageBySelect")
  public Message delMessageBySelect(@RequestBody JSONObject jsonObject) {
    try {
      String idStr = jsonObject.getString("idList");
      if (StringUtils.isEmpty(idStr)) {
        return MessageUtil.error("请选择要删除的公告，idStr=" + idStr);
      }
      List<String> idList = Arrays.asList(idStr.split(","));
      Integer del = announcementService.del(idList);
      if (del > 0) {
        return MessageUtil.success("批量删除成功，del=" + del);
      }
      return MessageUtil.error("批量删除失败，del=" + del);
    } catch (Exception e) {
      //  捕获异常，打印并曝出异常
      e.printStackTrace();
      return MessageUtil.error("出现异常:" + e.toString());
    }
  }

}
