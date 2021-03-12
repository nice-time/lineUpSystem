package com.briup.queuesystem.service.impl;

import com.briup.queuesystem.bean.TestBean;
import com.briup.queuesystem.mapper.TestMapper;
import com.briup.queuesystem.service.TestService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author lijx
 */
@Service
public class TestServiceImpl implements TestService {

  @Resource
  private TestMapper testMapper;

  @Override
  public TestBean selectTest(int id) {
    return testMapper.selectByPrimaryKey(id);
  }
}
