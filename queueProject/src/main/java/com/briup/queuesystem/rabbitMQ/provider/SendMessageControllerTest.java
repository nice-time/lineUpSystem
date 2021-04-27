package com.briup.queuesystem.rabbitMQ.provider;

import com.alibaba.fastjson.JSONObject;
import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import java.nio.charset.StandardCharsets;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.thymeleaf.util.StringUtils;

@RestController
@RequestMapping("/RabbitMQ")
public class SendMessageControllerTest {

  // 使用RabbitTemplate,这提供了接收/发送等等方法
  @Autowired
  RabbitTemplate rabbitTemplate;

  @RequestMapping("/sendDirectMessage")
  public Message sendDirectMessage(@RequestBody JSONObject message) {
    try{
      String messageId = String.valueOf(UUID.randomUUID());
      String number = message.getString("number");
      if (StringUtils.isEmpty(number)) {
        return MessageUtil.error("叫号失败！号码不能为空。NUMBER="+number);
      }
      String createTime = LocalDateTime.now()
          .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      Map<String, Object> map = new HashMap<>();
      map.put("messageId", messageId);
      map.put("messageData", number);
      map.put("createTime", createTime);
      //  应该将叫号信息插入一张表
      //  本地测试时需要将云上的项目停掉 否则会自动消费掉消息
      // 将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
      rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
      return MessageUtil.success("叫号成功");
    }catch (Exception e){
      //  捕获异常，打印并曝出异常
      e.printStackTrace();
      return MessageUtil.error("出现异常:" + e.toString());
    }
  }


}
