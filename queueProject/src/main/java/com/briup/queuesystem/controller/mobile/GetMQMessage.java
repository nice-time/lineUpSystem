package com.briup.queuesystem.controller.mobile;

import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.GetResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import org.apache.tomcat.util.buf.Utf8Decoder;
import org.apache.tomcat.util.buf.Utf8Encoder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;
import sun.misc.BASE64Encoder;

@CrossOrigin
@RestController
@RequestMapping("/getMQMessage")
public class GetMQMessage {
  private final static String QUEUE_NAME = "TestDirectQueue";
  private final static String EXCHANGE_NAME = "TestDirectExchange";
  @Autowired
  RabbitTemplate rabbitTemplate;


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
      channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
      channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"TestDirectRouting");
      // 创建消费者
      Consumer consumer = new DefaultConsumer(channel) {
        @Override
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
            byte[] body) throws IOException {
          String msg = new String(body, "UTF-8");
          System.out.println("Received message : '" + msg + "'");
          System.out.println("consumerTag : " + consumerTag );
          System.out.println("deliveryTag : " + envelope.getDeliveryTag() );
        }
      };
      channel.basicConsume(QUEUE_NAME,true,consumer);
//      //  拿到消息
//      GetResponse getMessage = channel.basicGet(QUEUE_NAME, true);
//
//      if (getMessage == null){
//        return MessageUtil.error("未获取到RabbitMQ的消息");
//      }else {
//        String encoding = rabbitTemplate.getEncoding();
//        System.out.println(encoding);
//        System.out.println(rabbitTemplate.isRunning());
//        //  转化消息
//        String strMessage = new String(getMessage.getBody());
//        return MessageUtil.success("获取成功",strMessage);
//      }
      return null;
    }catch (Exception e){
      //  捕获异常，打印并曝出异常
      e.printStackTrace();
      return MessageUtil.error("出现异常:" + e.toString());
    }
  }

}
