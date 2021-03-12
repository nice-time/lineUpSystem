package com.briup.queuesystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.briup.queuesystem.bean.TestBean;
import com.briup.queuesystem.service.TestService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijx
 */
@RestController
@RequestMapping("/testController")
public class TestController {

  @Resource
  private TestService testService;

  @PostMapping("/test")
  public String test(@RequestBody JSONObject jsonObject) {
    System.out.println(jsonObject);
    String id = jsonObject.getString("id");
    TestBean testBean = testService.selectTest(Integer.parseInt(id));
    return testBean.toString();
  }
}
