package com.briup.queuesystem.rabbitMQ;


import java.util.Map;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "TestDirectQueue")//监听的队列名称 TestDirectQueue
public class DirectReceiverTest {

  @RabbitHandler
  public void process(Map testMessage) {
    System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());
  }
}
