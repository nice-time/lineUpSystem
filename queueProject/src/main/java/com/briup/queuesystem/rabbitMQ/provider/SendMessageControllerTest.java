package com.briup.queuesystem.rabbitMQ.provider;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.thymeleaf.util.StringUtils;

@RestController
@RequestMapping("/RabbitMQTest")
public class SendMessageControllerTest {

  // 使用RabbitTemplate,这提供了接收/发送等等方法
  @Autowired
  RabbitTemplate rabbitTemplate;

  @GetMapping("/sendDirectMessage")
  public String sendDirectMessage(String message) {
    String messageId = String.valueOf(UUID.randomUUID());
    if (StringUtils.isEmpty(message)) {
      return "发送消息不能为空！";
    }
    String createTime = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    Map<String, Object> map = new HashMap<>();
    map.put("messageId", messageId);
    map.put("messageData", message);
    map.put("createTime", createTime);
    // 将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
    rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
    return "ok";
  }
}
