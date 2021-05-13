package com.briup.queuesystem.controller.mobile;

import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/getMQMessage")
public class GetMQMessage {
  private final static String QUEUE_NAME = "TestDirectQueue";
  private final static String EXCHANGE_NAME = "TestDirectExchange";
  @Autowired
  RabbitTemplate rabbitTemplate;


  @RequestMapping("/pullMessage")
  public Message pullMessage() {
    try {
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("121.196.174.196");
      factory.setPort(5672);
      factory.setUsername("root");
      factory.setPassword("root");
      //  连接服务器
      Connection connection = factory.newConnection();
      Channel channel = connection.createChannel();
      //  定义一个队列 不清楚为什么
      boolean duiable = true;//持久化
      boolean exclusive = false;//排他队列
      boolean autoDelete = false;//没有consumer时，队列是否自动删除
      channel.queueDeclare(QUEUE_NAME, duiable, exclusive, autoDelete, null);
      channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
      channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "TestDirectRouting");
      //  拿到消息
      GetResponse getMessage = channel.basicGet(QUEUE_NAME, true);
      if (getMessage == null) {
        return MessageUtil.error("未获取到叫号信息");
      }
      String s = new String(getMessage.getBody(), "UTF-8");
      return MessageUtil.success(s);
    } catch (UnsupportedEncodingException e) {
      log.error("编码错误");
      e.printStackTrace();
      return MessageUtil.error("编码错误");
    } catch (Exception e) {
      //  捕获异常，打印并曝出异常
      e.printStackTrace();
      return MessageUtil.error("出现异常:" + e.toString());
    }
  }
}
