/**
 * Project Name:poll File Name:Swagger2.java Package Name:com.briup.apps.poll.config
 * Date:2018年6月10日下午6:22:51 Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 */

package com.briup.queuesystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class Swagger2 {

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.briup.queuesystem.controller"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("智能排队系统API")
        .description("本次接口主要面向排队系统设计")
        .termsOfServiceUrl("http://121.196.174.196/resline")
        .version("1.0")
        .build();
  }
}

