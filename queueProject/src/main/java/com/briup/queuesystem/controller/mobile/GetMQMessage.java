package com.briup.queuesystem.controller.mobile;

import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import java.nio.charset.StandardCharsets;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

@CrossOrigin
@RestController
@RequestMapping("/getMQMessage")
public class GetMQMessage {
  private final static String QUEUE_NAME = "TestDirectQueue";

  @RequestMapping("/pullMessage")
  public Message pullMessage(){
    try{
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("121.196.174.196");
      factory.setPort(5672);
      factory.setUsername("root");
      factory.setPassword("root");
      //  连接服务器
      Connection connection = factory.newConnection();
      Channel channel = connection.createChannel();
      //  定义一个队列 不清楚为什么
      boolean duiable=true;//持久化
      boolean exclusive = false;//排他队列
      boolean autoDelete=false;//没有consumer时，队列是否自动删除
      channel.queueDeclare(QUEUE_NAME, duiable, exclusive, autoDelete, null);
      boolean autoAck = true;
      //  拿到消息
      GetResponse getMessage = channel.basicGet(QUEUE_NAME, autoAck);

      if (getMessage == null){
        return MessageUtil.error("未获取到RabbitMQ的消息");
      }else {
        //  转化消息
        String strMessage = new String(getMessage.getBody());

        return MessageUtil.success("获取成功",strMessage);
      }
    }catch (Exception e){
      //  捕获异常，打印并曝出异常
      e.printStackTrace();
      return MessageUtil.error("出现异常:" + e.toString());
    }
  }

}
