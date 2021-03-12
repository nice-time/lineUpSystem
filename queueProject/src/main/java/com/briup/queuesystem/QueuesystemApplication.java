package com.briup.queuesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lijx
 */
@SpringBootApplication
@MapperScan("com.briup.queuesystem.mapper")
public class QueuesystemApplication {

  public static void main(String[] args) {
    SpringApplication.run(QueuesystemApplication.class, args);
  }

}
